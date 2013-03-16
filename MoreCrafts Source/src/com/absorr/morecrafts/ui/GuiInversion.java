package com.absorr.morecrafts.ui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.absorr.morecrafts.materials.TileEntityInversion;



public class GuiInversion extends GuiContainer
{
    private TileEntityInversion invFurnaceInventory;

    public GuiInversion(InventoryPlayer par1InventoryPlayer, TileEntityInversion par2TileEntityInversion)
    {
        super(new ContainerInversion(par1InventoryPlayer, par2TileEntityInversion));
        this.invFurnaceInventory = par2TileEntityInversion;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everythin in front of the items)
     */
    protected void drawGuiContainerForegroundLayer()
    {
        this.fontRenderer.drawString("Inversion Furnace", 60, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.func_98187_b("/gui/furnace.png");
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (this.invFurnaceInventory.isBurning())
        {
            i1 = this.invFurnaceInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = this.invFurnaceInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
}
