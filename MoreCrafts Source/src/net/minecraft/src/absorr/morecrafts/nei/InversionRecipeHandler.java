package net.minecraft.src.absorr.morecrafts.nei;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.src.*;
import net.minecraft.src.absorr.morecrafts.*;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.recipe.*;
import codechicken.nei.recipe.FurnaceRecipeHandler.SmeltingPair;

public class InversionRecipeHandler extends FurnaceRecipeHandler
{
	@Override
	public Class<? extends GuiContainer> getGuiClass()
	{
		return GuiInversion.class;
	}
	
	@Override
	public String getRecipeName()
	{
		return "Inverting";
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void loadCraftingRecipes(String outputId, Object... results)
	{
		Map list = InversionRecipes.smelting().getSmeltingList();
		for(Object key : list.keySet())
		{
			int item = (Integer) list.get(key);
			arecipes.add(new SmeltingPair((ItemStack) key, new ItemStack(item, 1, 0)));
		}
	}
}