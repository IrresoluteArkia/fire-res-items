package irar.fireresistitems;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class FireProofEnchantment extends Enchantment {

	protected FireProofEnchantment() {
		super(Rarity.UNCOMMON, EnchantmentType.create("any_item", (stack) -> {
			return true;
		}), new EquipmentSlotType[] {EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND});
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	protected boolean canApplyTogether(Enchantment ench) {
		return FireRes.config.serverConfig.applyWithFireEnch.get() || (!ench.equals(Enchantments.FIRE_ASPECT) && !ench.equals(Enchantments.FIRE_PROTECTION) && !ench.equals(Enchantments.FLAME));
	}

	@Override
	public ITextComponent getDisplayName(int level) {
		return super.getDisplayName(level);
	}

	@Override
	public boolean canApply(ItemStack stack) {
		return true;
	}

	@Override
	public boolean isTreasureEnchantment() {
		return true;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
//		return FireRes.config.serverConfig.applyAtEnchantmentTable.get();
		return false;
	}

	@Override
	public boolean isAllowedOnBooks() {
		return true;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return this.getMinEnchantability(enchantmentLevel) + 5000;
	}
	
	

}
