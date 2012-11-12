package absorr.morecrafts.materials;
import absorr.morecrafts.base.CommonProxy;
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
		return CommonProxy.itemPic;
	}
}