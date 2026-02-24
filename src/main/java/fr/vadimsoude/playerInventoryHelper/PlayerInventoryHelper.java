package fr.vadimsoude.playerInventoryHelper;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import fr.vadimsoude.playerInventoryHelper.listener.BukkitListener;
import fr.vadimsoude.playerInventoryHelper.listener.PacketEventListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerInventoryHelper extends JavaPlugin {

    @Getter
    private PacketUtils packetUtils;

    @Override
    public void onEnable() {

        packetUtils = new PacketUtils();

        PacketEvents.getAPI().getEventManager().registerListener(new PacketEventListener(this), PacketListenerPriority.NORMAL);
        this.getServer().getPluginManager().registerEvents(new BukkitListener(this), this);

        this.getLogger().info("PlayerInventoryHelper has been enabled!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("PlayerInventoryHelper has been enabled!");
    }
}
