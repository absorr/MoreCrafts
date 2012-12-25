package absorr.morecrafts.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
	/*
	 * Preloads textures for use
	 * @see absorr.morecrafts.base.CommonProxy#registerRenderers()
	 */
	@Override
	public void registerRenderers() 
	{
		MinecraftForgeClient.preloadTexture(itemPic); 
    	MinecraftForgeClient.preloadTexture(blockPic);
	}
	@Override
	public void addMerchantRecipies()
	{
		//MerchantRecipeList npcTrade = new MerchantRecipeList();
    	//npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 4), new ItemStack (MoreCrafts.ironMulti, 1)));
    	//npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 4), new ItemStack (MoreCrafts.blankEgg, 3)));
    	//npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (MoreCrafts.blankEgg, 3), new ItemStack (Item.emerald, 1)));
    	//npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 3), new ItemStack (MoreCrafts.blankSpawner, 1)));
	}
}
