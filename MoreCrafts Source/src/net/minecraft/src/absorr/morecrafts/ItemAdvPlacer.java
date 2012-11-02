package net.minecraft.src.absorr.morecrafts;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import java.util.*;

import net.minecraft.src.*;

public class ItemAdvPlacer extends Item
{
	private static Map advToEnt = new HashMap();
	
    public ItemAdvPlacer(int par1)
    {
        super(par1);
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabs.tabMisc);
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
        if (var1 == 23)
        	return "Spawn Baby Villager";
        else
        	return "Spawn Egg";
    }
    
    public static int getEntityID(int par1)
    {
    	return (Integer) advToEnt.get(par1);
    }
    
    public static int getAdvID(int par1)
    {
    	Map rev = new HashMap();
    	for (Object o : advToEnt.keySet()) 
        {
        	int x = (Integer) advToEnt.get(o);
        	rev.put(x, o);
        }
    	return (Integer) rev.get(par1);
    }
    
    public static void addAdvID(int par1, int par2)
    {
    	advToEnt.put(par1, par2);
    }
    
    public static void loadDefaultIDs()
    {
    	addAdvID(1, 91);
    	addAdvID(2, 91);
    	addAdvID(3, 91);
    	addAdvID(4, 91);
    	addAdvID(5, 91);
    	addAdvID(6, 91);
    	addAdvID(7, 91);
    	addAdvID(8, 91);
    	addAdvID(9, 91);
    	addAdvID(10, 91);
    	addAdvID(11, 91);
    	addAdvID(12, 91);
    	addAdvID(13, 91);
    	addAdvID(14, 91);
    	addAdvID(15, 91);
    	addAdvID(16, 91); //Baby Sheep
    	addAdvID(17, 90); //Baby Pig
    	addAdvID(18, 92); //Baby Cow
    	addAdvID(19, 96); //Baby Mooshroom
    	addAdvID(20, 93); //Baby Chicken
    	addAdvID(21, 95); //Baby Wolf
    	addAdvID(22, 98); //Baby Ocelot
    	addAdvID(23, 120); //Baby Villager
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2)
    {
        EntityEggInfo var3 = (EntityEggInfo)EntityList.entityEggs.get(getEntityID(par1ItemStack.getItemDamage()));
        return var3 != null ? (par2 == 0 ? var3.primaryColor : var3.secondaryColor) : 16777215;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
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

            if (spawnAdvCreature(par3World, getEntityID(par1ItemStack.getItemDamage()), (double)par4 + 0.5D, (double)par5 + var12, (double)par6 + 0.5D, par1ItemStack.getItemDamage()) != null && !par2EntityPlayer.capabilities.isCreativeMode)
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
    public static Entity spawnAdvCreature(World var0, int var1, double var2, double var4, double var6, int var7)
    {
        if (!EntityList.entityEggs.containsKey(Integer.valueOf(var1)))
        {
            return null;
        }
        else
        {
            Entity var8 = null;

            for (int var9 = 0; var9 < 1; ++var9)
            {
                var8 = EntityList.createEntityByID(var1, var0);

                if (var8 != null)
                {
                    var8.setLocationAndAngles(var2, var4, var6, var0.rand.nextFloat() * 360.0F, 0.0F);
                    ((EntityLiving)var8).func_82163_bD();
                    var0.spawnEntityInWorld(var8);
                    ((EntityLiving)var8).playLivingSound();
                    if (var7 < 15 && var8 instanceof EntitySheep)
                    {
                    	EntitySheep sheep = (EntitySheep) var8;
                    	sheep.setFleeceColor(var7);
                    }
                    else if (var8 instanceof EntityVillager)
                    {
                    	EntityVillager entity = (EntityVillager) var8;
                    	entity.setGrowingAge(-24000);
                    }
                    else
                    {
                    	EntityAnimal entity = (EntityAnimal) var8;
                    	entity.setGrowingAge(-24000);
                    }
                }
            }

            return var8;
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
    	par3List.add(new ItemStack(par1, 1, 1));
    	par3List.add(new ItemStack(par1, 1, 2));
    	par3List.add(new ItemStack(par1, 1, 3));
    	par3List.add(new ItemStack(par1, 1, 4));
    	par3List.add(new ItemStack(par1, 1, 5));
    	par3List.add(new ItemStack(par1, 1, 6));
    	par3List.add(new ItemStack(par1, 1, 7));
    	par3List.add(new ItemStack(par1, 1, 8));
    	par3List.add(new ItemStack(par1, 1, 9));
    	par3List.add(new ItemStack(par1, 1, 10));
    	par3List.add(new ItemStack(par1, 1, 11));
    	par3List.add(new ItemStack(par1, 1, 12));
    	par3List.add(new ItemStack(par1, 1, 13));
    	par3List.add(new ItemStack(par1, 1, 14));
    	par3List.add(new ItemStack(par1, 1, 15));
    	par3List.add(new ItemStack(par1, 1, 16));
    	par3List.add(new ItemStack(par1, 1, 17));
    	par3List.add(new ItemStack(par1, 1, 18));
    	par3List.add(new ItemStack(par1, 1, 19));
    	par3List.add(new ItemStack(par1, 1, 20));
    	par3List.add(new ItemStack(par1, 1, 21));
    	par3List.add(new ItemStack(par1, 1, 22));
    	par3List.add(new ItemStack(par1, 1, 23));
    }
}
