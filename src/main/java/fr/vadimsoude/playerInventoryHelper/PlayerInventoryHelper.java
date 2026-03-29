package fr.vadimsoude.playerInventoryHelper;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import fr.vadimsoude.playerInventoryHelper.listener.BukkitListener;
import fr.vadimsoude.playerInventoryHelper.listener.PacketEventListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;

@Getter
public final class PlayerInventoryHelper extends JavaPlugin {

    private PacketUtils packetUtils;
    private boolean isFolia;
    private final ConcurrentSkipListSet<UUID> playersWithPacketSent = new ConcurrentSkipListSet<>();

    @Override
    public void onEnable() {

        packetUtils = new PacketUtils();

        isFolia = isFoliaEnv();

        PacketEvents.getAPI().getEventManager().registerListener(new PacketEventListener(this), PacketListenerPriority.NORMAL);
        this.getServer().getPluginManager().registerEvents(new BukkitListener(this), this);

        this.getLogger().info("PlayerInventoryHelper has been enabled!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("PlayerInventoryHelper has been enabled!");
    }

    private static boolean isFoliaEnv() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
