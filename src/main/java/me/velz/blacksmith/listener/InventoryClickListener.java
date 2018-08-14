package me.velz.blacksmith.listener;

import me.velz.blacksmith.BlackSmith;
import me.velz.blacksmith.utils.MessageUtil;
import me.velz.facility.FacilityAPI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    private final BlackSmith plugin;

    public InventoryClickListener(BlackSmith plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory().getTitle().equalsIgnoreCase(MessageUtil.BLACKSMITH_INVENTORYNAME.getLocal())) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessageUtil.BLACKSMITH_REPAIRITEM.getLocal())) {
                if (player.getInventory().getItemInMainHand() != null) {
                    final ItemStack item = player.getInventory().getItemInMainHand();
                    if (plugin.getFileManager().getWhitelist().contains(item.getType().toString())) {
                        final Integer price = (int) item.getDurability();
                        if (!FacilityAPI.hasMoney(player, price.doubleValue())) {
                            player.sendMessage(MessageUtil.BLACKSMITH_NOTENOUGHMONEY.getLocal());
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 5L, 5L);
                            player.closeInventory();
                            return;
                        }
                        FacilityAPI.takeMoney(player, price.doubleValue());
                        item.setDurability((short) 0);
                        player.getInventory().setItemInMainHand(item);
                        player.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.BLACKSMITH_REPAIRED.getLocal().replaceAll("%price", String.valueOf(price)));
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 5L, 5L);
                    } else {
                        player.closeInventory();
                        player.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.BLACKSMITH_NOTWHITELISTED.getLocal());
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 5L, 5L);
                    }
                } else {
                    player.sendMessage(MessageUtil.PREFIX.getLocal() + MessageUtil.BLOCKSMITH_NOITEMINHAND.getLocal());
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 5L, 5L);
                }
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessageUtil.BLACKSMITH_CANCELITEM.getLocal())) {
                player.closeInventory();
            }
        }
    }

}
