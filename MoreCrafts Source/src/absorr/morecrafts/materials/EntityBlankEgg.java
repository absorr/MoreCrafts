package absorr.morecrafts.materials;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import absorr.morecrafts.base.MoreCrafts;

public class EntityBlankEgg extends EntityThrowable
{
    public EntityBlankEgg(World par1World)
    {
        super(par1World);
    }

    public EntityBlankEgg(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
    }

    public EntityBlankEgg(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (par1MovingObjectPosition.entityHit instanceof EntityLiving && par1MovingObjectPosition.entityHit instanceof EntityPlayer == false && par1MovingObjectPosition.entityHit.isEntityAlive())
        {
            Entity entity = par1MovingObjectPosition.entityHit;
            EntityLiving living = (EntityLiving) entity;
            int id = EntityList.getEntityID(entity);
            if (entity instanceof EntitySheep)
            {
            	EntitySheep sheep = (EntitySheep)entity;
            	int color = sheep.getFleeceColor();
            	System.out.println("Sheep color " + color);
            	if (color == 0)
            		entity.entityDropItem(new ItemStack(Item.monsterPlacer, 1, id), 1);
            	else
            		entity.entityDropItem(new ItemStack(MoreCrafts.advSpawnEgg, 1, color), 1);
            }
            else if (living.isChild())
            {
            	int advID = ItemAdvPlacer.getAdvID(id);
            	if ((Integer)advID != null)
            		entity.entityDropItem(new ItemStack(MoreCrafts.advSpawnEgg, 1, advID), 1);
            	else
            		entity.entityDropItem(new ItemStack(Item.monsterPlacer, 1, id), 1);
            }
            else if (entity instanceof EntityVillager && !living.isChild())
            {
            	EntityVillager npc = (EntityVillager) living;
            	entity.entityDropItem(new ItemStack(MoreCrafts.advSpawnEgg, 1, npc.getProfession() + 24), 1);
            }
            else if (entity instanceof EntityTameable && !living.isChild())
            {
            	if (id == 95)
            		entity.entityDropItem(new ItemStack(MoreCrafts.advSpawnEgg, 1, 30), 1);
            	if (id == 98)
            		entity.entityDropItem(new ItemStack(MoreCrafts.advSpawnEgg, 1, 31), 1);
            }
            else
            	entity.entityDropItem(new ItemStack(Item.monsterPlacer, 1, id), 1);
            entity.setDead();
            this.worldObj.spawnParticle("largesmoke", par1MovingObjectPosition.hitVec.xCoord, par1MovingObjectPosition.hitVec.yCoord, par1MovingObjectPosition.hitVec.zCoord, 0.0D, 0.0D, 0.0D);
            this.setDead();
        }
    }
}