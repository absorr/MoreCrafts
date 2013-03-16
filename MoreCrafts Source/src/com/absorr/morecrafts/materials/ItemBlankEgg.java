package com.absorr.morecrafts.materials;

import com.absorr.morecrafts.base.MoreCrafts;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlankEgg extends ItemMonsterPlacer
{
    public ItemBlankEgg(int par1)
    {
        super(par1);
        this.setHasSubtypes(true);
        this.setCreativeTab(MoreCrafts.TabMoreCraft);
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

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS !
     */
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) 
    {
        if (entity instanceof EntityLiving && MoreCrafts.eggUsable() && entity instanceof EntityPlayer == false)
        {
        	int id = EntityList.getEntityID(entity);
        	if(!player.capabilities.isCreativeMode)
        	{
	        	if (player.inventory.getCurrentItem().stackSize == 1)
	            	player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
	        	else
	        		--player.inventory.getCurrentItem().stackSize;
        	}
        	entity.entityDropItem(new ItemStack(Item.monsterPlacer, 1, id), 1);
        	entity.setDead();
        	return true;
        }
        else return false;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if (MoreCrafts.eggUsable())
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
}