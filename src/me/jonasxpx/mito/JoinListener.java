package me.jonasxpx.mito;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import me.jonasxpx.mito.Primary.Messages;
import me.jonasxpx.mito.eventos.PlayerLosesMitoEvent;

public class JoinListener implements Listener{

	private Primary p;
	private PlayerLosesMitoEvent event = null;
	private BukkitTask antiburle = null;
	public JoinListener(Primary p){
		this.p = p;
	}
	
	@EventHandler
	public void playerChat(ChatMessageEvent e){
		if(p.mito.getMito() == null){
			return;
		}
		if(e.getSender().getName().equalsIgnoreCase(p.mito.getMito())){
			e.setTagValue("mito", "§5[MITO DO PVP]");
		}
	}
	
	@EventHandler
	public void playerLoginEvent(PlayerJoinEvent e){
		if(p.mito.getMito() != null){
			if(p.mito.getSecondsFromTheLastLogin() > 43200){
				for(String line : Messages.INACTIVE.getArray()){
					Bukkit.broadcastMessage(line.replaceAll("@player", p.mito.getMito()));
				}
				p.mito.setMito(null, true);
			}
			if(e.getPlayer().getName().equalsIgnoreCase(p.mito.getMito())){
				if(p.mito.hasNotification()){
					for(String line : Messages.LOGIN.getArray()){
						Bukkit.broadcastMessage(line.replaceAll("@player", e.getPlayer().getName()));
					}
				}
				antiburle =	new BukkitRunnable() {
					@Override
					public void run() {
						p.mito.updateLastLogin();						
					}
				}.runTaskLater(this.p, 120 * 20);
			}
		}else
			System.out.println("[Mito Plugin]is null");
	}
	@EventHandler(priority = EventPriority.LOW)
	public void playerDeathEvent(PlayerDeathEvent e){
		if(p.mito.getMito() != null){
			if(e.getEntity().getName().equalsIgnoreCase(p.mito.getMito())){
				if(e.getEntity().getKiller() != null){
					event = new PlayerLosesMitoEvent(e.getEntity());
					p.getServer().getPluginManager().callEvent(event);
					if(!event.isCancelled()){
						p.mito.setMito(e.getEntity().getKiller(), false);
						me.jonasxpx.mito.commands.Mito.contagem2 = 0;
					}
				}
			}
			if(e.getEntity().getKiller() != null && e.getEntity().getKiller().getName().equalsIgnoreCase(p.mito.getMito())){
				me.jonasxpx.mito.commands.Mito.contagem2++;
			}
		}
	}
	@EventHandler
	public void playerHit(EntityDamageByEntityEvent e){
		if(p.mito.getMito() == null){
			return;
		}
		if(!(e.getDamager() instanceof Player)){
			return;
		}
		if(!(e.getEntity() instanceof Player)){
			return;
		}
		if(((Player)e.getDamager()).getName().equalsIgnoreCase(p.mito.getMito())){
			Mito.addEffects(((Player)e.getDamager()), PotionEffectType.SPEED, 10, 1);
			Mito.addEffects(((Player)e.getDamager()), PotionEffectType.INCREASE_DAMAGE, 10, 1);
		}
	}
	@EventHandler
	public void playerLeave(PlayerQuitEvent e){
		if(p.mito.getMito() == null){
			return;
		}
		if(p.mito.getMito().equalsIgnoreCase(e.getPlayer().getName())){
			if(antiburle == null){
				return;
			}
			System.out.println("[MITO] Anti-Burle apitou.");
			antiburle.cancel();
			antiburle = null;
		}
	}
	@EventHandler
	public void playerLeave(PlayerKickEvent e){
		if(p.mito.getMito() == null){
			return;
		}
		if(p.mito.getMito().equalsIgnoreCase(e.getPlayer().getName())){
			if(antiburle == null){
				return;
			}
			System.out.println("[MITO] Anti-Burle apitou.");
			antiburle.cancel();
			antiburle = null;
		}
	}
	
	
}
