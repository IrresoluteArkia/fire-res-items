package irar.fireresistitems;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FireResistEventHandler {
	
	public List<ItemEntity> fireProofEntities = new ArrayList<>();
	
	@SubscribeEvent
	public void onItemSpawn(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		if(entity instanceof ItemEntity) {
			ItemEntity itemEntity = (ItemEntity) entity;
			if(isItemProtected(itemEntity)) {
				itemEntity.setInvulnerable(true);
				fireProofEntities.add(itemEntity);
			}
		}
	}
	
	@SubscribeEvent
	public void tick(WorldTickEvent event) {
		try {
			List<ItemEntity> toRemove = new ArrayList<>();
			for(ItemEntity item : fireProofEntities) {
				if(item.isAlive()) {
					if(entityInLava(item) && FireRes.config.serverConfig.floatInLava.get()) {
						item.setVelocity(0, 0, 0);
						item.addVelocity(0, 0.3, 0);
					}
				}else {
					toRemove.add(item);
				}
			}
			fireProofEntities.removeAll(toRemove);
		}catch(ConcurrentModificationException e) {
			FireRes.LOGGER.error("ConcurrentModificationException caught from checking fire proof items, this should never happen...");
		}
	}
	
	private boolean isItemProtected(ItemEntity item) {
		ItemStack stack = item.getItem();
		if(stack.isEnchanted()) {
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
			if((!FireRes.config.serverConfig.mustHaveFireProof.get() && (enchantments.containsKey(Enchantments.FLAME) || enchantments.containsKey(Enchantments.FIRE_PROTECTION) || enchantments.containsKey(Enchantments.FIRE_ASPECT))) || enchantments.containsKey(FireRes.FIREPROOF)) {
				return true;
			}
		}
		return FireRes.config.serverConfig.allFireProof.get();
	}

	public boolean entityInLava(Entity entity) {
		return entity.isInLava();
	}

}
