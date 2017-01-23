package me.jonasxpx.mito;

import me.jonasxpx.mito.commands.SetMito;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class Primary extends JavaPlugin{

	public Mito mito;
	protected static Primary instance;
	
	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		mito = new Mito(this);
		getCommand("mito").setExecutor(new me.jonasxpx.mito.commands.Mito(this));
		getCommand("setmito").setExecutor(new SetMito(this));
		getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		instance = this;
	}
	
	@Override
	public void onDisable() {
		HandlerList.unregisterAll(this);
	}
}
