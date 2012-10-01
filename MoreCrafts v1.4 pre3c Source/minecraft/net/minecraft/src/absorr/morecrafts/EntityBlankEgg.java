package net.minecraft.src.absorr.morecrafts;

import net.minecraft.src.*;

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
        if (par1MovingObjectPosition.entityHit instanceof EntityLiving && par1MovingObjectPosition.entityHit instanceof EntityPlayer == false)
        {
            Entity entity = par1MovingObjectPosition.entityHit;
            int id = EntityList.getEntityID(entity);
            if (entity instanceof EntityBlankEgg)
            {
            	EntitySheep sheep = (EntitySheep)entity;
            	int color = sheep.getFleeceColor();
            	if (color == 0)
            		entity.entityDropItem(new ItemStack(Item.monsterPlacer, 1, id), 1);
            	else
            		entity.entityDropItem(new ItemStack(mod_moreCrafts.advSpawnEgg, 1, color), 1);
            }
            else
            	entity.entityDropItem(new ItemStack(Item.monsterPlacer, 1, id), 1);
            entity.setDead();
            this.worldObj.spawnParticle("largesmoke", par1MovingObjectPosition.hitVec.xCoord, par1MovingObjectPosition.hitVec.yCoord, par1MovingObjectPosition.hitVec.zCoord, 0.0D, 0.0D, 0.0D);
        }
    }
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