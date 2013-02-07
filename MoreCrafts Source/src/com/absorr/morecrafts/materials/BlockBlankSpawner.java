package com.absorr.morecrafts.materials;
import java.util.ArrayList;

import com.absorr.morecrafts.base.MoreCrafts;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
public class BlockBlankSpawner extends Block
{
	public BlockBlankSpawner(int i, int j) 
    { 
        super(i, j, Material.iron);
        this.setCreativeTab(MoreCrafts.TabMoreCraft);
    }
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z,EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	int metad = world.getBlockMetadata(x, y, z);
    	if (metad == 0)
    	{
    		ItemStack heldItem = player.inventory.getCurrentItem();
        	if (heldItem.getItem().equals(Item.monsterPlacer))
        	{
        		int meta = heldItem.getItemDamage();
        		String mob = EntityList.getStringFromID(meta);
        		world.setBlockAndMetadataWithNotify(x, y, z, Block.mobSpawner.blockID, meta);
        		((TileEntityMobSpawner)world.getBlockTileEntity(x, y, z)).setMobID(mob);
        		player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(MoreCrafts.blankEgg));
        		return true;
        	}else
        		return false;
    	}else
    		return false;
    }
    public String getItemNameIS(ItemStack itemstack) {
        String name = "";
        switch(itemstack.getItemDamage()) {
        case 0: {
                name = "blankSpawn";
                break;
        }
        case 1: {
                name = "rsBlock"; 
                break;
        }
        case 2: {
            name = "rsBlockActive"; 
            break;
        }
        default: name = "blankSpawn";
        }
        return name;
    }
    public boolean canConnectRedstone(IBlockAccess iba, int i, int j, int k, int dir)
    {
    	int meta = iba.getBlockMetadata(i, j, k);
    	if (meta == 1) return true;
    	else
    	{
    		if (meta == 2) return true;
    		else return false;
    	}
    }
    public boolean canProvidePower()
    {
        return true;
    }
    public int getBlockTextureFromSideAndMetadata(int i, int j) {
        switch (j) {
        case 0:
                return 65;
        case 1:
                return 22;
        case 2:
            return 22;
        default:
                return 65;
        }
    }
    public int getRenderColor(int par1)
    {
    	if (par1 == 1) return 8388608;
    	else return 16777215;
    }
    public int colorMultiplier(IBlockAccess iba, int i, int j, int k)
    {
    	int meta = iba.getBlockMetadata(i, j, k);
    	if (meta == 1) return 8388608;
    	else return 16777215;
    }
    /**
     * Is this block powering the block on the specified side
     */
    public boolean isPoweringTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return par1IBlockAccess.getBlockMetadata(par2, par3, par4) > 1;
    }

    /**
     * Is this block indirectly powering the block on the specified side
     */
    public boolean isIndirectlyPoweringTo(World par1World, int par2, int par3, int par4, int par5)
    {
        return par1World.getBlockMetadata(par2, par3, par4) > 1;
    }
    public void addCreativeItems(ArrayList itemList)
    {
            itemList.add(new ItemStack(this));
            itemList.add(new ItemStack(MoreCrafts.blankSpawner, 1, 1));
    }
}