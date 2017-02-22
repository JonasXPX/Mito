package me.jonasxpx.mito.commands;

import me.jonasxpx.mito.MitoAPI;
import me.jonasxpx.mito.Primary;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mito implements CommandExecutor{
	
	
	private Primary p;
	private int contagem = 1;
	public static int contagem2= 0;
	
	public Mito(Primary p) {
		this.p = p;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmds, String label,
			String[] args) {
		if(args.length == 1 && (args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("on"))){
			if(!sender.getName().equalsIgnoreCase(p.mito.getMito())){
				sender.sendMessage("§cVocê não é um mito do pvp!.");
				return true;
			}
			if(args[0].equalsIgnoreCase("off")){
				p.mito.notification(false);
				sender.sendMessage("§cSua notificação de login foi desligada.");
			}
			if(args[0].equalsIgnoreCase("on")){
				p.mito.notification(true);
				sender.sendMessage("§aSua notificação de login foi ligada.");
			}
			return true;
		}
		if(args.length == 1 && sender.isOp()){
			if(args[0].equalsIgnoreCase("update")){
				me.jonasxpx.mito.Mito.updateSkin(MitoAPI.getMito().getMito());
				return true;
			}
		}
		if(p.mito.getMito() == null){
			sender.sendMessage("§6Mito atual não encontrado!.");
			return true;
		}else{
			sender.sendMessage("§5Mito atual: §6"+p.mito.getMito());
		}
		if(p.mito.getMito().equalsIgnoreCase(sender.getName())){
			if(contagem <= 0){
				sender.sendMessage("§4Você não pode ir mais a área mito até o servidor reiniciar");
				return true;
			}
			if(contagem2 < 20){
				sender.sendMessage("§cVocê precisa matar mais " + (20 - contagem2) + " jogador(es) para ir até a área MITO.");
				return true;
			}
			contagem--;
			Player p = (Player)sender;
			p.sendMessage("§6Você podera voltar aqui mais §f" + contagem + "§6 Vez(ez)");
			p.sendMessage("§d Seja bem vindo a loja do Mito");
			Bukkit.dispatchCommand(p.getServer().getConsoleSender(), "ewarp mito "+ p.getName());
		}
		return true;
	}
}
