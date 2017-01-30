package me.jonasxpx.mito;

import java.io.BufferedReader;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.jonasxpx.mito.Primary.Messages;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.DespawnReason;
import net.citizensnpcs.api.npc.NPC;


public class Mito {
	
	private JavaPlugin jp;
	private String nickMito = null;
	private Long lastlogin = null;
	
	public Mito(JavaPlugin jp){
		this.jp = jp;
	}
	
	public String getMito(){
		if(nickMito == null){
			if(jp.getConfig().contains("Mito")){
				setMitoString(jp.getConfig().getString("Mito"));
				return this.nickMito;
			}else
				return null;
		} else
			return nickMito;
	}
	
	public void setMito(Player player, boolean quiet){
			jp.getConfig().set("Mito", player == null ? null : player.getName());
			jp.saveConfig();
			setMitoString(player == null ? null : player.getName());
			updateLastLogin();
			if(!quiet){
				for(String line : Messages.CATCHED.getArray()){
					Bukkit.broadcastMessage(line.replaceAll("@player", player.getName()));
				}
				Location loc = player.getLocation();
				loc.getWorld().strikeLightningEffect(player.getLocation().add(2, 0, 0));
				loc.getWorld().strikeLightningEffect(player.getLocation().add(-2, 0, 0));
				loc.getWorld().strikeLightningEffect(player.getLocation().add(0, 0, 2));
				loc.getWorld().strikeLightningEffect(player.getLocation().add(0, 0, -2));
				loc.getWorld().strikeLightningEffect(player.getLocation().add(-2, 0, 2));
				loc.getWorld().strikeLightningEffect(player.getLocation().add(2, 0, -2));
				for(Player players : jp.getServer().getOnlinePlayers()){
					players.playSound(players.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
				}
			}
			if(player != null)updateSkin(player.getName());
	}
	
	public void setMito(OfflinePlayer player, boolean quiet){
		jp.getConfig().set("Mito", player == null ? null : player.getName());
		jp.saveConfig();
		setMitoString(player == null ? null : player.getName());
		updateLastLogin();
		if(!quiet){
			for(String line : Messages.CATCHED.getArray()){
				Bukkit.broadcastMessage(line.replaceAll("@player", player.getName()));
			}
			for(Player players : jp.getServer().getOnlinePlayers()){
				players.playSound(players.getLocation(), Sound.ENDERDRAGON_GROWL, 1, 1);
			}
		}
		if(player != null)updateSkin(player.getName());
}
	
	public void updateLastLogin(){
		this.lastlogin = System.currentTimeMillis();
		jp.getConfig().set("LastLogin", lastlogin);
		jp.saveConfig();
	}
	
	public Long getLastLogin(){
		return this.lastlogin == null ? jp.getConfig().getLong("LastLogin") : this.lastlogin;
	}
	
	public void notification(boolean b){
		jp.getConfig().set("notificacao", b);
		jp.saveConfig();
	}
	
	public boolean hasNotification(){
		return jp.getConfig().getBoolean("notificacao");
	}
	
	public Long getSecondsFromTheLastLogin(){
		return (System.currentTimeMillis() / 1000) - (getLastLogin() / 1000);
	}
	
	public void setMitoString(String mito){
		this.nickMito = mito;
	}
	
	public static void updateSkin(String mito){
		NPC mitoNpc = CitizensAPI.getNPCRegistry().getById(0);
		mitoNpc.setName(("§5" + mito).length() > 16 ? ("§5" + mito).substring(0, 16) : ("§5" + mito));
		Location tempLoc = new Location(Bukkit.getWorld("endcraft"), 1214.5, 9.5, 251.6);
		mitoNpc.despawn(DespawnReason.PENDING_RESPAWN);
		mitoNpc.spawn(tempLoc);
	}
	
	/**
	 *  Adiciona um efeito de potion no jogador.
	 * @param player Jogador.
	 * @param ef Efeito.
	 * @param dura Duração em segundos.
	 * @param ampl 0 = 1.
	 */
	public static void addEffects(Player player, PotionEffectType ef, int dura, int ampl){
		if(player.hasPotionEffect(ef)){
			return;
		}
		player.addPotionEffect(new PotionEffect(ef, dura * 20, ampl));
	}
	
	public static void main(String[] args) {
		int levelPo = 1500;
		int xp = 5;
		int nivel = 0;
		int multiplicador = 1;
		System.out.println(nivel + " / " + levelPo);
		
	}
	
	
}
