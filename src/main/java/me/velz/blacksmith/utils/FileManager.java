package me.velz.blacksmith.utils;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class FileManager {
    
    @Getter
    private final FileBuilder configBuilder = new FileBuilder("plugins/Blacksmith", "config.yml");
    
    @Getter @Setter
    private List<String> smithNames, whitelist;
    
    public void setDefaults() {
        this.getConfigBuilder().addDefault("blacksmith.names", new String[] {
            "§6Blacksmith"
        });
        this.getConfigBuilder().addDefault("blacksmith.whitelist", new String[] {
            "IRON_SWORD"
        });
        this.getConfigBuilder().save();
    }
    
    public void load() {
        this.setSmithNames(this.getConfigBuilder().getStringList("blacksmith.names"));
        this.setWhitelist(this.getConfigBuilder().getStringList("blacksmith.whitelist"));
    }
    
}
