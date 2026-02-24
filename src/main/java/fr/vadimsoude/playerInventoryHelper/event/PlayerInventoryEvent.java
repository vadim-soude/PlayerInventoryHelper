package fr.vadimsoude.playerInventoryHelper.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerInventoryEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    @Getter
    private final Player player;

    public PlayerInventoryEvent(Player player) {
        this.player = player;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public static class PlayerOpenInventoryEvent extends PlayerInventoryEvent {
        public PlayerOpenInventoryEvent(Player player) {
            super(player);
        }
    }

    public static class PlayerCloseInventoryEvent extends PlayerInventoryEvent {
        public PlayerCloseInventoryEvent(Player player) {
            super(player);
        }
    }
}