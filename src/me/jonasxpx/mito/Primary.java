package me.jonasxpx.mito;

import me.jonasxpx.mito.commands.SetMito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * 
 * @author Jonas de Farias Peretiako
 * @since 30.jan.2017
 * 
 */
public class Primary extends JavaPlugin{

	public Mito mito;
	protected static Primary instance;
	private Map<String, List<String>> msg = null;
	
	@Override
	public void onEnable() {
		load();
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
	
	
	private void load(){
		getConfig().options().copyDefaults(true);
		saveConfig();
		if(!getConfig().contains("messages.login")){
			getConfig().addDefault("messages.login", Arrays.asList("", "&5[MITO DO PVP] &c @player logou no EndCraft!", "").toArray());
			saveConfig();
		}
		if(!getConfig().contains("messages.catched")){
			getConfig().addDefault("messages.catched", Arrays.asList("", "&5[MITO] &c @player é o novo MITO do PVP!", "").toArray());
			saveConfig();
		}
		if(!getConfig().contains("messages.inactive")){
			getConfig().addDefault("messages.inactive", Arrays.asList("", "&5&l@player perdeu a MITO devido a inatividade.", "").toArray());
			saveConfig();
		}
		msg = new HashMap<>();
		msg.put("login", getConfig().getStringList("messages.login"));
		msg.put("catched", getConfig().getStringList("messages.catched"));
		msg.put("inactive", getConfig().getStringList("messages.inactive"));
	}
	
	public enum Messages{
		LOGIN(instance.msg.get("login")),
		CATCHED(instance.msg.get("catched")),
		INACTIVE(instance.msg.get("inactive"));
		
		private List<String> list;
		private Messages(List<String> msg) {
			this.list = msg;
		}
		
		public List<String> getArray(){
			return list;
		}
	}
	
}
