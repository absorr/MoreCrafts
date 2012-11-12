package absorr.morecrafts.base;

import net.minecraft.src.*;

public class CommonProxy
{
	public static String itemPic = "/absorr/morecrafts/items.png";
	public static String blockPic = "/absorr/morecrafts/blocks.png";
	
	/*
	 * Creates more villager trading recipes
	 */
	public static void addMerchantRecipies()
	{
		MerchantRecipeList npcTrade = new MerchantRecipeList();
    	npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 4), new ItemStack (mod_moreCrafts.ironMulti, 1)));
    	npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 4), new ItemStack (mod_moreCrafts.blankEgg, 3)));
    	npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (mod_moreCrafts.blankEgg, 3), new ItemStack (Item.emerald, 1)));
    	npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 3), new ItemStack (mod_moreCrafts.blankSpawner, 1)));
	}
	
	/*
	 * Only used client side
	 */
	public void registerRenderers() {}
}
