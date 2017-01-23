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
				sender.sendMessage("�cVoc� n�o � um mito do pvp!.");
				return true;
			}
			if(args[0].equalsIgnoreCase("off")){
				p.mito.notification(false);
				sender.sendMessage("�cSua notifica��o de login foi desligada.");
			}
			if(args[0].equalsIgnoreCase("on")){
				p.mito.notification(true);
				sender.sendMessage("�aSua notifica��o de login foi ligada.");
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
			sender.sendMessage("�6Mito atual n�o encontrado!.");
			return true;
		}else{
			sender.sendMessage("�5Mito atual: �6"+p.mito.getMito());
		}
		if(p.mito.getMito().equalsIgnoreCase(sender.getName())){
			if(contagem <= 0){
				sender.sendMessage("�4Voc� n�o pode ir mais a �rea mito at� o servidor reiniciar");
				return true;
			}
			if(contagem2 < 10){
				sender.sendMessage("�cVoc� precisa matar mais " + (10 - contagem2) + " jogador(es) para ir at� a �rea MITO.");
				return true;
			}
			contagem--;
			Player p = (Player)sender;
			p.sendMessage("�6Voc� podera voltar aqui mais �f" + contagem + "�6 Vez(ez)");
			p.sendMessage("�d Seja bem vindo a loja do Mito");
			Bukkit.dispatchCommand(p.getServer().getConsoleSender(), "ewarp mito "+ p.getName());
		}
		return true;
	}
}
