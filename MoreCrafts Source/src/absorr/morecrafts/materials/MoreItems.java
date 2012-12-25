package absorr.morecrafts.materials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import absorr.morecrafts.base.CommonProxy;

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