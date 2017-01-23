package me.jonasxpx.mito.commands;

import me.jonasxpx.mito.Primary;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetMito implements CommandExecutor{
	
	
	private Primary p;
	
	public SetMito(Primary p) {
		this.p = p;
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		if(args.length == 0 || args.length > 2){
			sender.sendMessage("§cUso correto: /setmito <nick> [-s]\n\n -s : Modo silencioso");
			return true;
		}
		if(!sender.isOp()){
			sender.sendMessage("§4Você não tem permição");
			return true;
		}
		if(!Bukkit.getOfflinePlayer(args[0]).isOnline()){
			sender.sendMessage("§cJogador offline!");
			p.mito.setMito(Bukkit.getOfflinePlayer(args[0]), args.length == 2 ? (args[1].equals("-s") ? true : false) : false);
			return true;
		}
		p.mito.setMito(Bukkit.getPlayer(args[0]), args.length == 2 ? (args[1].equals("-s") ? true : false) : false);
		return true;
	}
}
