package me.velz.blacksmith.listener;

import java.util.ArrayList;
import java.util.List;
import me.velz.blacksmith.BlackSmith;
import me.velz.blacksmith.utils.ItemBuilder;
import me.velz.blacksmith.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractEntityListener implements Listener {

    private final BlackSmith plugin;

    public PlayerInteractEntityListener(BlackSmith plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        for (String name : plugin.getFileManager().getSmithNames()) {
            if (event.getRightClicked().getName().equalsIgnoreCase(name)) {
                if (event.getPlayer().getInventory().getItemInMainHand() != null) {
                    final ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
                    final Material type = item.getType();
                    if (plugin.getFileManager().getWhitelist().contains(type.toString())) {
                        final Integer price = type.getMaxDurability() - item.getDurability();
                        final Inventory inventory = Bukkit.createInventory(null, 9, MessageUtil.BLACKSMITH_INVENTORYNAME.getLocal());
                        final List<String> repairLore = new ArrayList<>();
                        repairLore.add(MessageUtil.BLACKSMITH_REPAIRITEMLORE_PRICE.getLocal().replaceAll("%price", String.valueOf(price)));
                        inventory.setItem(1, new ItemBuilder().setMaterial(Material.CONCRETE).setLore(repairLore).setDisplayName(MessageUtil.BLACKSMITH_REPAIRITEM.getLocal()).setDurability((short)5).build());
                        inventory.setItem(7, new ItemBuilder().setMaterial(Material.CONCRETE).setDisplayName("§cAbbrechen").setDurability((short)14).build());
                        event.getPlayer().openInventory(inventory);
                    } else {
                        event.getPlayer().sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.BLACKSMITH_NOTWHITELISTED.getLocal());
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_BREAK, 5L, 5L);
                    }
                } else {
                    event.getPlayer().sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.BLOCKSMITH_NOITEMINHAND.getLocal());
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ANVIL_BREAK, 5L, 5L);
                }
            }
        }
    }

}
