package com.absorr.morecrafts.base;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TabMoreCraft extends CreativeTabs {
	public TabMoreCraft(String par2Str)
	{
		super(par2Str);
	}
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return MoreCrafts.inverseFurnace.blockID;
	}

	public String getTranslatedTabLabel()
	{
		return "MoreCrafts";
	}
}