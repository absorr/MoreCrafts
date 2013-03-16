package com.absorr.morecrafts.materials;

import com.absorr.morecrafts.base.InversionRecipes;
import com.absorr.morecrafts.materials.*;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.*;

public class TileEntityInversion extends TileEntity implements IInventory, ISidedInventory
{
    /**
     * The ItemStacks that hold the items currently being used in the furnace
     */
    private ItemStack[] invFurnaceItemStacks = new ItemStack[3];

    /** The number of ticks that the furnace will keep burning */
    public int invFurnaceBurnTime = 0;

    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
     */
    public int currentItemBurnTime = 0;

    /** The number of ticks that the current item has been cooking for */
    public int invFurnaceCookTime = 0;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.invFurnaceItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.invFurnaceItemStacks[par1];
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.invFurnaceItemStacks[par1] != null)
        {
            ItemStack var3;

            if (this.invFurnaceItemStacks[par1].stackSize <= par2)
            {
                var3 = this.invFurnaceItemStacks[par1];
                this.invFurnaceItemStacks[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.invFurnaceItemStacks[par1].splitStack(par2);

                if (this.invFurnaceItemStacks[par1].stackSize == 0)
                {
                    this.invFurnaceItemStacks[par1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.invFurnaceItemStacks[par1] != null)
        {
            ItemStack var2 = this.invFurnaceItemStacks[par1];
            this.invFurnaceItemStacks[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.invFurnaceItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Inversion Furnace";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.invFurnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.invFurnaceItemStacks.length)
            {
                this.invFurnaceItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.invFurnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.invFurnaceCookTime = par1NBTTagCompound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.invFurnaceItemStacks[1]);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.invFurnaceBurnTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.invFurnaceCookTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.invFurnaceItemStacks.length; ++var3)
        {
            if (this.invFurnaceItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.invFurnaceItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getCookProgressScaled(int par1)
    {
        return this.invFurnaceCookTime * par1 / 200;
    }

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.invFurnaceBurnTime * par1 / this.currentItemBurnTime;
    }

    /**
     * Returns true if the furnace is currently burning
     */
    public boolean isBurning()
    {
        return this.invFurnaceBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean var1 = this.invFurnaceBurnTime > 0;
        boolean var2 = false;

        if (this.invFurnaceBurnTime > 0)
        {
            --this.invFurnaceBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.invFurnaceBurnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.invFurnaceBurnTime = getItemBurnTime(this.invFurnaceItemStacks[1]);

                if (this.invFurnaceBurnTime > 0)
                {
                    var2 = true;

                    if (this.invFurnaceItemStacks[1] != null)
                    {
                        --this.invFurnaceItemStacks[1].stackSize;

                        if (this.invFurnaceItemStacks[1].stackSize == 0)
                        {
                            this.invFurnaceItemStacks[1] = this.invFurnaceItemStacks[1].getItem().getContainerItemStack(invFurnaceItemStacks[1]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.invFurnaceCookTime;

                if (this.invFurnaceCookTime == 200)
                {
                    this.invFurnaceCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }
            else
            {
                this.invFurnaceCookTime = 0;
            }

            if (var1 != this.invFurnaceBurnTime > 0)
            {
                var2 = true;
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.invFurnaceItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack var1 = InversionRecipes.smelting().getInversionResult(this.invFurnaceItemStacks[0]);
            if (var1 == null) return false;
            if (this.invFurnaceItemStacks[2] == null) return true;
            if (!this.invFurnaceItemStacks[2].isItemEqual(var1)) return false;
            int result = invFurnaceItemStacks[2].stackSize + var1.stackSize;
            return (result <= getInventoryStackLimit() && result <= var1.getMaxStackSize());
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack var1 = InversionRecipes.smelting().getInversionResult(this.invFurnaceItemStacks[0]);

            if (this.invFurnaceItemStacks[2] == null)
            {
                this.invFurnaceItemStacks[2] = var1.copy();
            }
            else if (this.invFurnaceItemStacks[2].isItemEqual(var1))
            {
                invFurnaceItemStacks[2].stackSize += var1.stackSize;
            }

            --this.invFurnaceItemStacks[0].stackSize;

            if (this.invFurnaceItemStacks[0].stackSize <= 0)
            {
                this.invFurnaceItemStacks[0] = null;
            }
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack par0ItemStack)
    {
        return TileEntityFurnace.getItemBurnTime(par0ItemStack);
    }

    /**
     * Return true if item is a fuel source (getItemBurnTime() > 0).
     */
    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}
    
    @Override
    public int getStartInventorySide(ForgeDirection side)
    {
        if (side == ForgeDirection.DOWN) return 1;
        if (side == ForgeDirection.UP) return 0; 
        return 2;
    }

    @Override
    public int getSizeInventorySide(ForgeDirection side)
    {
        return 1;
    }

	@Override
	public boolean func_94042_c() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean func_94041_b(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}
}
