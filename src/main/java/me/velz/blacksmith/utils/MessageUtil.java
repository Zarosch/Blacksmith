package me.velz.blacksmith.utils;

import lombok.Getter;

public enum MessageUtil {

    PREFIX("§f[§eBlacksmith§f] "),
    
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
