package yoft.CoalConverter;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class CoalConverter extends JavaPlugin{
	public PermissionHandler Permissions;
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println("[" + pdfFile.getName() + "] has been disabled.");
	}
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
	    System.out.println("[" + pdfFile.getName() + "] version [" + pdfFile.getVersion() + "] is enabled!");
	    
	    CoalConverterCommandExecutor executor = new CoalConverterCommandExecutor(this);
			this.getCommand("coal").setExecutor(executor);
		
		setupPermissions();
	}
	
	private void setupPermissions() {
		Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
		
		if (this.Permissions == null) {
			if (test != null) {
				this.Permissions = ((Permissions)test).getHandler();
			} else {
				System.out.println("Permission system not detected, defaulting to OP");
			}
		}
	}
	
}
