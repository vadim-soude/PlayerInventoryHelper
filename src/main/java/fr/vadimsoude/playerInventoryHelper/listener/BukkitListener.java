package fr.vadimsoude.playerInventoryHelper.listener;

import com.github.retrooper.packetevents.PacketEvents;
import fr.vadimsoude.playerInventoryHelper.PlayerInventoryHelper;
import fr.vadimsoude.playerInventoryHelper.event.PlayerInventoryEvent;
import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;

public record BukkitListener(PlayerInventoryHelper plugin) implements Listener {

    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        PacketEvents.getAPI().getPlayerManager().sendPacket(event.getPlayer(), plugin.packetUtils().REMOVE_FAKE_RECIPE_PACKET);
        sendFakeRecipe(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent event) {
        plugin.playersWithPacketSent().remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerCloseInventory(org.bukkit.event.inventory.InventoryCloseEvent event) {
        if (event.getInventory().getType() != InventoryType.CRAFTING)
            return;

        new PlayerInventoryEvent.PlayerCloseInventoryEvent((Player) event.getPlayer()).callEvent();

        if(plugin.isFolia()) {
            AsyncScheduler asyncScheduler = Bukkit.getServer().getAsyncScheduler();
            asyncScheduler.runNow(plugin, (task) -> sendFakeRecipe((Player) event.getPlayer()));
            return;
        }
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> sendFakeRecipe((Player) event.getPlayer()));
    }

    private void sendFakeRecipe(Player player) {
        if(plugin.playersWithPacketSent().contains(player.getUniqueId()))
            return;
        PacketEvents.getAPI().getPlayerManager().sendPacket(player, plugin.packetUtils().OPEN_RECIPE_BOOK_PACKET);
        PacketEvents.getAPI().getPlayerManager().sendPacket(player, plugin.packetUtils().ADD_FAKE_RECIPE_PACKET);
        plugin.playersWithPacketSent().add(player.getUniqueId());
    }

}
