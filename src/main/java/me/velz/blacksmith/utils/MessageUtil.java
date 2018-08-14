package me.velz.blacksmith.utils;

import lombok.Getter;

public enum MessageUtil {

    PREFIX("§f[§eBlacksmith§f] "),
    
    BLACKSMITH_REPAIRITEM("§aReparieren"),
    BLACKSMITH_REPAIRITEMLORE_PRICE("§fPreis: §e%price Münzen"),
    BLACKSMITH_CANCELITEM("§cAbbrechen"),
    BLACKSMITH_INVENTORYNAME("§8§lSchmied"),
    BLACKSMITH_NOTENOUGHMONEY("§cDu hast nicht genügend Münzen."),
    BLACKSMITH_REPAIRED("§aDein Item wurde für §e%price Münzen§a repariert."),
    BLACKSMITH_NOTWHITELISTED("§cDieses Item kann ich leider nicht reparieren."),
    BLOCKSMITH_NOITEMINHAND("§cBitte halte das Item was du reparieren willst in der Hand."),
    
    ERROR_NOPERMISSIONS("§cDazu hast du keine Berechtigung.");

    @Getter
    private String local;

    private MessageUtil(String local) {
        this.local = local;
    }

    public static void load() {
        FileBuilder message = new FileBuilder("plugins/Blacksmith", "messages.yml");
        for (MessageUtil m : MessageUtil.values()) {
            message.addDefault("message." + m.toString().replaceAll("_", ".").toLowerCase(), m.local.replaceAll("§", "&"));
        }
        message.save();
        for (MessageUtil m : MessageUtil.values()) {
            m.local = message.getConfiguration().getString("message." + m.toString().replaceAll("_", ".").toLowerCase()).replaceAll("&", "§");
        }
    }

}
