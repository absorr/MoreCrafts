package com.absorr.morecrafts.materials;
import com.absorr.morecrafts.base.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MoreItems extends Item
{
	public MoreItems(int i, int stack, CreativeTabs tab)
    {
         super(i);
         maxStackSize = stack;
         this.setCreativeTab(tab);
    }

	@Override
	public String getTextureFile() 
	{
		return CommonProxy.itemPic;
	}
}