package me.velz.blacksmith.utils;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class FileManager {
    
    @Getter
    private final FileBuilder configBuilder = new FileBuilder("plugins/Blacksmith", "config.yml");
    
    @Getter @Setter
    private List<String> smithNames;
    
    public void setDefaults() {
        this.getConfigBuilder().addDefault("blacksmith.names", new String[] {
            "§6Blacksmith"
        });
        this.getConfigBuilder().save();
    }
    
    public void load() {
        this.setSmithNames(this.getConfigBuilder().getStringList("blacksmith.names"));
    }
    
}
