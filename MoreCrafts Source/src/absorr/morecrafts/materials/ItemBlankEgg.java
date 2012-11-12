package absorr.morecrafts.materials;

import absorr.morecrafts.materials.EntityBlankEgg;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.*;

public class ItemBlankEgg extends Item
{
    public ItemBlankEgg(int par1)
    {
        super(par1);
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromDamage(int par1, int par2)
    {
        EntityEggInfo var3 = (EntityEggInfo)EntityList.entityEggs.get(Integer.valueOf(par1));
        return var3 != null ? (par2 == 0 ? var3.primaryColor : var3.secondaryColor) : 16777215;
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
    public int getIconFromDamageForRenderPass(int par1, int par2)
    {
        return par2 > 0 ? super.getIconFromDamageForRenderPass(par1, par2) + 16 : super.getIconFromDamageForRenderPass(par1, par2);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS !
     */
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) 
    {
        if (entity instanceof EntityLiving && mod_moreCrafts.eggUsable() && entity instanceof EntityPlayer == false)
        {
        	int id = EntityList.getEntityID(entity);
        	player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Item.monsterPlacer, 1, id));
        	entity.setDead();
        	return true;
        }
        else return false;
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if (mod_moreCrafts.eggUsable())
    	{
    		if (!par3EntityPlayer.capabilities.isCreativeMode)
            {
                --par1ItemStack.stackSize;
            }

            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!par2World.isRemote )
            {
                par2World.spawnEntityInWorld(new EntityBlankEgg(par2World, par3EntityPlayer));
            }
    	}
    	return par1ItemStack;
    }

    /**
     * Spawns the creature specified by the egg's type in the location specified by the last three parameters.
     * Parameters: world, entityID, x, y, z.
     */
    public static boolean spawnCreature(World par0World, int par1, double par2, double par4, double par6)
    {
        if (!EntityList.entityEggs.containsKey(Integer.valueOf(par1)))
        {
            return false;
        }
        else
        {
            Entity var8 = EntityList.createEntityByID(par1, par0World);

            if (var8 != null)
            {
                var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
                par0World.spawnEntityInWorld(var8);
                ((EntityLiving)var8).playLivingSound();
            }

            return var8 != null;
        }
    }
}