package net.minecraft.src.absorr.morecrafts;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.src.*;

public class ItemAdvPlacer extends Item
{
    public ItemAdvPlacer(int par1)
    {
        super(par1);
        this.setHasSubtypes(true);
        this.setTabToDisplayOn(CreativeTabs.tabMisc);
    }

    @SideOnly(Side.CLIENT)
    public String getItemDisplayName(ItemStack par1ItemStack)
    {
        int var1 = par1ItemStack.getItemDamage();
        if (var1 == 1)
        	return "Spawn Orange Sheep";
        if (var1 == 2)
        	return "Spawn Magenta Sheep";
        if (var1 == 3)
        	return "Spawn Light Blue Sheep";
        if (var1 == 4)
        	return "Spawn Yellow Sheep";
        if (var1 == 5)
        	return "Spawn Lime Sheep";
        if (var1 == 6)
        	return "Spawn Pink Sheep";
        if (var1 == 7)
        	return "Spawn Gray Sheep";
        if (var1 == 8)
        	return "Spawn Light Gray Sheep";
        if (var1 == 9)
        	return "Spawn Cyan Sheep";
        if (var1 == 10)
        	return "Spawn Purple Sheep";
        if (var1 == 11)
        	return "Spawn Blue Sheep";
        if (var1 == 12)
        	return "Spawn Brown Sheep";
        if (var1 == 13)
        	return "Spawn Green Sheep";
        if (var1 == 14)
        	return "Spawn Red Sheep";
        if (var1 == 15)
        	return "Spawn Black Sheep";
        if (var1 == 16)
        	return "Spawn Baby Sheep";
        if (var1 == 17)
        	return "Spawn Baby Pig";
        if (var1 == 18)
        	return "Spawn Baby Cow";
        if (var1 == 19)
        	return "Spawn Baby Mooshroom";
        if (var1 == 20)
        	return "Spawn Baby Chicken";
        if (var1 == 21)
        	return "Spawn Baby Wolf";
        if (var1 == 22)
        	return "Spawn Baby Ocelot";
        else
        	return "Spawn Egg";
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromDamage(int par1, int par2)
    {
        EntityEggInfo var3 = (EntityEggInfo)EntityList.entityEggs.get(Integer.valueOf(par1));
        return var3 != null ? (par2 == 0 ? var3.primaryColor : var3.secondaryColor) : 16777215;
    }

    public boolean tryPlaceIntoWorld(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par3World.isRemote)
        {
            return true;
        }
        else
        {
            int var11 = par3World.getBlockId(par4, par5, par6);
            par4 += Facing.offsetsXForSide[par7];
            par5 += Facing.offsetsYForSide[par7];
            par6 += Facing.offsetsZForSide[par7];
            double var12 = 0.0D;

            if (par7 == 1 && var11 == Block.fence.blockID || var11 == Block.netherFence.blockID)
            {
                var12 = 0.5D;
            }

            if (spawnCreature(par3World, par1ItemStack.getItemDamage(), (double)par4 + 0.5D, (double)par5 + var12, (double)par6 + 0.5D) && !par2EntityPlayer.capabilities.isCreativeMode)
            {
                --par1ItemStack.stackSize;
            }

            return true;
        }
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
        	Entity var8;
            if (par1 < 17)
            {
            	var8 = EntityList.createEntityByID(91, par0World);
            }
            if (par1 == 17)
            {
            	var8 = EntityList.createEntityByID(90, par0World);
            }
            if (par1 == 18)
            {
            	var8 = EntityList.createEntityByID(92, par0World);
            }
            if (par1 == 19)
            {
            	var8 = EntityList.createEntityByID(96, par0World);
            }
            if (par1 == 20)
            {
            	var8 = EntityList.createEntityByID(93, par0World);
            }
            if (par1 == 22)
            {
            	var8 = EntityList.createEntityByID(98, par0World);
            }
            else
            	var8 = EntityList.createEntityByID(95, par0World);

            if (var8 != null)
            {
                var8.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0F, 0.0F);
                if (var8 instanceof EntitySheep)
                {
                    EntitySheep var9 = (EntitySheep)var8;
                    var9.setFleeceColor(par1);
                    par0World.spawnEntityInWorld(var9);
                    return true;
                }
                else
                {
                	if (var8 instanceof EntityAnimal)
                    {
                        EntityAnimal var9 = (EntityAnimal)var8;
                        var9.spawnBabyAnimal(var9);
                        par0World.spawnEntityInWorld(var9);
                        return true;
                    }
                }

                par0World.spawnEntityInWorld(var8);
                ((EntityLiving)var8).playLivingSound();
            }

            return var8 != null;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
    public int getIconFromDamageForRenderPass(int par1, int par2)
    {
        return par2 > 0 ? super.getIconFromDamageForRenderPass(par1, par2) + 16 : super.getIconFromDamageForRenderPass(par1, par2);
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        Iterator var4 = EntityList.entityEggs.values().iterator();

        while (var4.hasNext())
        {
            EntityEggInfo var5 = (EntityEggInfo)var4.next();
            par3List.add(new ItemStack(par1, 1, var5.spawnedID));
        }
    }
}
