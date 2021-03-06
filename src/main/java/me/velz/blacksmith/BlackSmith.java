package me.velz.blacksmith;

import lombok.Getter;
import me.velz.blacksmith.listener.InventoryClickListener;
import me.velz.blacksmith.listener.PlayerInteractEntityListener;
import me.velz.blacksmith.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BlackSmith extends JavaPlugin {
    
    @Getter
    private final FileManager fileManager = new FileManager();
    
    @Override
    public void onEnable() {
        this.getFileManager().setDefaults();
        this.getFileManager().load();
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEntityListener(this), this);
    }
    
    
}
