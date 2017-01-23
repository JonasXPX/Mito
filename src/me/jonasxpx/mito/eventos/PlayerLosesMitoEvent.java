package me.jonasxpx.mito.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLosesMitoEvent extends Event implements Cancellable{

	private static final HandlerList handler = new HandlerList();
	private final Player player;
	private boolean canceled = false;
	
	public PlayerLosesMitoEvent(Player player) {
		this.player = player;
		this.canceled = false;
	}
	
	public Player getLoser(){
		return player;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handler;
	}

	public static HandlerList getHandlerList() {
	    return handler;
	}

	@Override
	public boolean isCancelled() {
		return canceled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		this.canceled = arg0;
	}
	
}
