package irar.fireresistitems;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class FRConfig {

	public Server serverConfig;
	public ForgeConfigSpec serverConfigSpec;

	public FRConfig() {
		Pair<Server, ForgeConfigSpec> configPair = new ForgeConfigSpec.Builder().configure(Server::new);
		serverConfig = configPair.getLeft();
		serverConfigSpec = configPair.getRight();
	}
	
	public static class Server {
		public final BooleanValue applyWithFireEnch;
		public final BooleanValue allFireProof;
		public final BooleanValue floatInLava;
		public final BooleanValue mustHaveFireProof;
//		public final BooleanValue applyAtEnchantmentTable;

		public Server(ForgeConfigSpec.Builder builder) {
			builder.comment("Fire Resistant Items Configuration")
			       .push("fireresistitemsconfig");
			applyWithFireEnch = builder
				   .comment("This will allow the enchantment \"Fire Proof\" to be applied with other fire-type enchantments if set to true.")
				   .translation(FireRes.MODID + ".configgui.applyWithFireEnch")
				   .define("applyWithFireEnch", false);
			allFireProof = builder
					   .comment("This will allow all items to be fire proof regardless of whether or not they have fire-type enchantments.")
					   .translation(FireRes.MODID + ".configgui.allFireProof")
					   .define("allFireProof", false);
			floatInLava = builder
					   .comment("This will fire proof items to float in lava, instead of sinking to the bottom")
					   .translation(FireRes.MODID + ".configgui.floatInLava")
					   .define("floatInLava", true);
			mustHaveFireProof = builder
					   .comment("If this is true, items will only be fire proof if they have the enchantment \"Fire Proof\", and will not be fire proof just because they have other fire-type enchantments such as \"Fire Protection\"")
					   .translation(FireRes.MODID + ".configgui.mustHaveFireProof")
					   .define("mustHaveFireProof", false);
//			applyAtEnchantmentTable = builder
//				   .comment("This will allow items enchanted with the enchantment table to get the \"Fire Proof\" enchantment")
//				   .translation(FireRes.MODID + ".configgui.applyAtEnchantmentTable")
//				   .define("applyAtEnchantmentTable", false);
			
			builder.pop();
		}
	}
	
}
