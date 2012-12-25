package absorr.morecrafts.base;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TabMoreCraft extends CreativeTabs {
	public TabMoreCraft(int par1, String par2Str)
	{
		super(par1, par2Str);
	}
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return MoreCrafts.inverseFurnace.blockID;
	}

	public String getTranslatedTabLabel()
	{
		return "MoreCraft";
	}
}