package net.minecraft.src.absorr.morecrafts;
import net.minecraft.src.*;

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
		return "/morecrafts/items.png";
	}
}