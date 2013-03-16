package com.absorr.morecrafts.materials;

import java.util.ArrayList;

import com.absorr.morecrafts.base.CommonProxy;
import com.absorr.morecrafts.base.MoreCrafts;
import com.absorr.morecrafts.ui.ContainerInversion;
import com.absorr.morecrafts.ui.GuiInversion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockInversionFurnace extends BlockContainer
{
	private Icon field_94459_cP;
	private Icon field_94458_cO;
	private boolean isActive;

	public BlockInversionFurnace(int i) 
    { 
        super(i, Material.iron);
        this.setCreativeTab(MoreCrafts.TabMoreCraft);
    }
	private static boolean keepFurnaceInventory = false;
	
	@SideOnly(Side.CLIENT)
	@Override
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return par1 == 1 ? this.field_94458_cO : (par1 == 0 ? this.field_94458_cO : (par1 != par2 ? this.field_94336_cN : this.field_94459_cP));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94336_cN = par1IconRegister.func_94245_a("morecrafts:furnaceside");
        this.field_94459_cP = par1IconRegister.func_94245_a(this.isActive ? "morecrafts:furnaceon" : "morecrafts:furnacefront");
        this.field_94458_cO = par1IconRegister.func_94245_a("morecrafts:furnaceside");
    }
	
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }
	private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int var5 = par1World.getBlockId(par2, par3, par4 - 1);
            int var6 = par1World.getBlockId(par2, par3, par4 + 1);
            int var7 = par1World.getBlockId(par2 - 1, par3, par4);
            int var8 = par1World.getBlockId(par2 + 1, par3, par4);
            byte var9 = 3;

            if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
            {
                var9 = 3;
            }

            if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
            {
                var9 = 2;
            }

            if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
            {
                var9 = 5;
            }

            if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
            {
                var9 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, var9, 1);
        }
    }
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var6 == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 1);
        }

        if (var6 == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 1);
        }

        if (var6 == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 1);
        }

        if (var6 == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 1);
        }
    }
    
    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
    	TileEntityInversion var6 = (TileEntityInversion)par1World.getBlockTileEntity(par2, par3, par4);
        if (var6 != null)
        {
        	par5EntityPlayer.openGui(MoreCrafts.instance, 0, par1World, par2, par3, par4);
        }

        return true;
    }
    public static void updateFurnaceBlockState(boolean par0, World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        TileEntity var6 = world.getBlockTileEntity(x, y, z);
        keepFurnaceInventory = true;

        if (par0)
        {
        	if (meta < 10)
        	{
        		world.setBlockAndMetadataWithNotify(x, y, z, MoreCrafts.inverseFurnace.blockID, meta + 10, 1);
        	}
        }
        else
        {
        	if (meta > 10)
        	{
        		world.setBlockAndMetadataWithNotify(x, y, z, MoreCrafts.inverseFurnace.blockID, meta - 10, 1);
        	}
        }

        keepFurnaceInventory = false;

        if (var6 != null)
        {
            var6.validate();
            world.setBlockTileEntity(x, y, z, var6);
        }
    }
	public void addCreativeItems(ArrayList itemList)
    {
            itemList.add(new ItemStack(this));
    }
	@Override
	public TileEntity createNewTileEntity(World var1) {
		// TODO Auto-generated method stub
		return new TileEntityInversion();
	}
}