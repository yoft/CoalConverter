package yoft.CoalConverter;

import org.bukkit.ChatColor;
import org.bukkit.CoalType;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Coal;

public class CoalConverterCommandExecutor implements CommandExecutor {
	
	private CoalConverter plugin;
	
	public CoalConverterCommandExecutor(CoalConverter plugin) {
		this.plugin = plugin;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (!commandLabel.equalsIgnoreCase("coal"))
			return false;
		
		if (!(sender instanceof Player)){
			System.out.println("You need to be on a client to do that.");
			return true;
		}
		
		if (!plugin.Permissions.has((Player) sender, "coal.convert")) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to use that command.");
			return true;
		}

		Player player = ((Player) sender);
		
		if (player.getItemInHand().getType() != Material.COAL){
			sender.sendMessage(ChatColor.RED + "You need to have  Coal or Charcoal selected to use that command.");
			return true;
		}
		
		ItemStack coalStack = player.getItemInHand();
		
		if (((Coal) coalStack.getData()).getType() == CoalType.COAL){
			coalStack = new ItemStack(Material.COAL, coalStack.getAmount(),(short) 0, CoalType.CHARCOAL.getData());
			
			player.setItemInHand(coalStack);
			sender.sendMessage(ChatColor.GOLD + "Your Coal is now Charcoal.");
			
		}else if (((Coal) coalStack.getData()).getType() == CoalType.CHARCOAL){
			coalStack = new ItemStack(Material.COAL, coalStack.getAmount(),(short) 0, CoalType.COAL.getData());
			player.setItemInHand(coalStack);
			
			sender.sendMessage(ChatColor.GOLD + "Your Charcoal is now Coal.");
			
		}else{
			plugin.getServer().broadcastMessage(ChatColor.RED + ((Player) sender).getDisplayName() + " may be hacking their coal stacks...");
			System.out.println(((Player) sender).getDisplayName() + " may be hacking their coal stacks...");
			return true;
		}
		
		return true;
	}

}