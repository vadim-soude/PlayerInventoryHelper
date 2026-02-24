package fr.vadimsoude.playerInventoryHelper.listener;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import fr.vadimsoude.playerInventoryHelper.PlayerInventoryHelper;
import fr.vadimsoude.playerInventoryHelper.event.PlayerInventoryEvent;
import org.bukkit.entity.Player;

public record PacketEventListener(PlayerInventoryHelper plugin) implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {

        if (event.getPacketType() != PacketType.Play.Client.SET_DISPLAYED_RECIPE)
            return;

        event.getUser().sendPacket(plugin.packetUtils().REMOVE_FAKE_RECIPE_PACKET);

        Player player = event.getPlayer();
        new PlayerInventoryEvent.PlayerOpenInventoryEvent(player).callEvent();
    }
}
