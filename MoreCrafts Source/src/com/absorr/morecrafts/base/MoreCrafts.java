package com.absorr.morecrafts.base;

//import ic2.api.Ic2Recipes;

import java.util.Random;

import com.absorr.morecrafts.materials.BlockInversionFurnace;
import com.absorr.morecrafts.materials.ItemAdvPlacer;
import com.absorr.morecrafts.materials.ItemBlankEgg;
import com.absorr.morecrafts.materials.ItemMultiTool;
import com.absorr.morecrafts.materials.MoreItems;
import com.absorr.morecrafts.materials.TileEntityInversion;
import com.absorr.morecrafts.ui.GuiHandler;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenRiver;
import net.minecraftforge.common.DungeonHooks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid="MoreCrafts", name="MoreCrafts", version="Build 021")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class MoreCrafts
{
	@SidedProxy(clientSide = "com.absorr.morecrafts.base.ClientProxy", serverSide = "com.absorr.morecrafts.base.CommonProxy", bukkitSide = "com.absorr.morecrafts.base.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance
	public static MoreCrafts instance;
	
    static boolean vanRecipes = true;
    static boolean rechargeRs = true;
    static boolean otherStuff = true;
	
	//Creates the items and blocks
    public static Item chain;
    public static Item blankEgg;
    public static Item woodMulti;
    public static Item stoneMulti;
    public static Item ironMulti;
    public static Item goldMulti;
    public static Item diamondMulti;
    public static Item advSpawnEgg;
    public static Block blankSpawner; 
    public static Block inverseFurnace;
    
    //Creates a name for the creative tab
    public static CreativeTabs TabMoreCraft;
    @Init
    public void load(FMLInitializationEvent event)
    {
    	//Loads the proxies
    	proxy.addMerchantRecipies();
    	proxy.registerRenderers();
    	
    	//Loads the Creative Tab
    	TabMoreCraft = new TabMoreCraft("MoreCrafts");
    	
    	//Loads the blocks and items
    	chain = new MoreItems(Config.chainID, 64, "chain").setUnlocalizedName("chain").setCreativeTab(TabMoreCraft);
    	blankEgg = new ItemBlankEgg(Config.eggID).setUnlocalizedName("blankEgg").setCreativeTab(TabMoreCraft);
    	woodMulti = new ItemMultiTool(Config.woodMultiID, 1, EnumToolMaterial.WOOD).setUnlocalizedName("woodenMultitool").setCreativeTab(TabMoreCraft);
    	woodMulti = new ItemMultiTool(Config.woodMultiID, 1, EnumToolMaterial.WOOD).setUnlocalizedName("woodenMultitool").setCreativeTab(TabMoreCraft);
        stoneMulti = new ItemMultiTool(Config.stoneMultiID, 1, EnumToolMaterial.STONE).setUnlocalizedName("stoneMultitool").setCreativeTab(TabMoreCraft);
        ironMulti = new ItemMultiTool(Config.ironMultiID, 1, EnumToolMaterial.IRON).setUnlocalizedName("ironMultitool").setCreativeTab(TabMoreCraft);
        goldMulti = new ItemMultiTool(Config.goldMultiID, 1, EnumToolMaterial.GOLD).setUnlocalizedName("goldMultitool").setCreativeTab(TabMoreCraft);
        diamondMulti = new ItemMultiTool(Config.diamondMultiID, 1, EnumToolMaterial.EMERALD).setUnlocalizedName("diamondMultitool").setCreativeTab(TabMoreCraft);
        advSpawnEgg = new ItemAdvPlacer(Config.advSpawnID).setUnlocalizedName("advancedEgg").setCreativeTab(TabMoreCraft); 
        inverseFurnace = new BlockInversionFurnace(Config.furnaceID).setHardness(1.0F).setResistance(6000.0F).setLightValue(0.0F).setUnlocalizedName("Inversion Furnace").setCreativeTab(TabMoreCraft);
    	
        loadMaterials();
        
        //Other junk
        NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
    	ItemAdvPlacer.loadDefaultIDs();
  		ModLoader.registerTileEntity(TileEntityInversion.class, "Inversion Furnace");
    	String maploaded = InversionRecipes.loader();
    	EntityList.entityEggs.put(Integer.valueOf(63), new EntityEggInfo(63, 0, 9118312));
    }
    
    private void loadMaterials()
    {
    	//Vanilla Recipes
    	if (vanRecipes){
    		//Spawn Eggs
    		if (Config.eggMode == 2 || Config.eggMode == 3)
    		{
    			//Ocelot
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 98), new Object[] {blankEgg, Item.fishRaw,});
                //Pig
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 90), new Object[] {blankEgg, Item.porkRaw,});
                //Sheep
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 91), new Object[] {blankEgg, Block.cloth,});
                //Cow
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 92), new Object[] {blankEgg, Item.beefRaw,});
                //Chicken
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 93), new Object[] {blankEgg, Item.chickenRaw,});
                //Squid
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 94), new Object[] {blankEgg, Item.dyePowder,});
                //Wolf
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 95), new Object[] {blankEgg, Item.bone,});
                //Mooshroom Cow
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 96), new Object[] {blankEgg, Block.mushroomRed,});
                //Villager
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 120), new Object[] {blankEgg, Block.wood,});
                //Creeper
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 50), new Object[] {blankEgg, Item.gunpowder,});
                //Skeleton
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 51), new Object[] {blankEgg, Item.arrow,});
                //Spider
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 52), new Object[] {blankEgg, Item.silk,});
                //Giant
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 53), new Object[] {blankEgg, Item.rottenFlesh, Item.diamond,});
                //Zombie
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 54), new Object[] {blankEgg, Item.rottenFlesh,});
                //Slime
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 55), new Object[] {blankEgg, Item.slimeBall,});
                //Ghast
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 56), new Object[] {blankEgg, Item.ghastTear,});
                //Zombie Pigman
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 57), new Object[] {blankEgg, Item.goldNugget,});
                //Enderman
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 58), new Object[] {blankEgg, Item.enderPearl,});
                //Cave Spider
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 59), new Object[] {blankEgg, Item.spiderEye,});
                //Silverfish
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 60), new Object[] {blankEgg, Block.stoneBrick,});
                //Blaze
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 61), new Object[] {blankEgg, Item.blazeRod,});
                //Magma Cube
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 62), new Object[] {blankEgg, Item.magmaCream,});
                //Enderdragon
                ModLoader.addShapelessRecipe(new ItemStack(Item.monsterPlacer, 1, 63), new Object[] {blankEgg, Block.whiteStone, Item.diamond});
    		}
            //Bottle o' Enchanting
            ModLoader.addShapelessRecipe(new ItemStack(Item.expBottle, 1), new Object[] {Item.glassBottle, Item.diamond,});
            //Saddle
            ModLoader.addRecipe(new ItemStack(Item.saddle, 1), new Object[]{
                "LLL", "LLL", "0I0", 
                'L', Item.leather,
                'I', Item.ingotIron});
            //Chain
            ModLoader.addName(chain, "Chain");
            ModLoader.addShapelessRecipe(new ItemStack(chain, 2), new Object[] {Block.fenceIron});
            //Chain Armor
            ModLoader.addRecipe(new ItemStack(Item.helmetChain, 1), new Object[]{
                "000", "LLL", "L0L", 
                'L', chain});
            ModLoader.addRecipe(new ItemStack(Item.plateChain, 1), new Object[]{
                "L0L", "LLL", "LLL", 
                'L', chain});
            ModLoader.addRecipe(new ItemStack(Item.legsChain, 1), new Object[]{
                "LLL", "L0L", "L0L", 
                'L', chain});
            ModLoader.addRecipe(new ItemStack(Item.bootsChain, 1), new Object[]{
                "000", "L0L", "L0L", 
                'L', chain});
            //Broken Stone Bricks
            ModLoader.addSmelting(Block.stoneBrick.blockID, new ItemStack(Block.stoneBrick, 1, 2));
            //Dragon Egg
            ModLoader.addRecipe(new ItemStack(Block.dragonEgg, 1), new Object[]{
                "0O0", "OEO", "OSO", 
                'S', Block.whiteStone, 'E', new ItemStack(Item.monsterPlacer, 1, 63), 'O', Block.obsidian});
            //Mossy Cobblestone
            InversionRecipes.addSmelting(Block.cobblestoneMossy.blockID, new ItemStack(Block.cobblestone));
            //Mossy Stone Brick
            //TODO: Add Inversion Furnace recipe for this
            //Ice
            ModLoader.addShapelessRecipe(new ItemStack(Block.ice, 1), new Object[] {Item.bucketWater, Block.blockSnow});
            //Blank Spawn Egg
            ModLoader.addName(blankEgg, "Blank Spawn Egg");
            ModLoader.addShapelessRecipe(new ItemStack(blankEgg, 1), new Object[] {Item.ingotIron, Item.egg});
            //End Portal Frame
            ModLoader.addRecipe(new ItemStack(Block.endPortalFrame, 1), new Object[] {"LEL", "EOE", "EEE", 'E', Block.whiteStone, 'O', Block.obsidian, 'L', Block.blockLapis});
            //End Stone
            ModLoader.addRecipe(new ItemStack(Block.whiteStone, 1), new Object[] {"SCS", "CIC", "SCS", 'S', Block.sponge, 'C', Block.cobblestone, 'I', Block.blockSteel});
            //Ic2Recipes.addCompressorRecipe(new ItemStack(Block.sponge, 4), new ItemStack(Block.whiteStone));
    	}
    	//Wooden Multi-Tool
        ModLoader.addName(woodMulti, "Wooden Multi-Tool");
        ModLoader.addRecipe(new ItemStack(woodMulti, 1), new Object[] {"AHP", "OSO", "OSO", 'S', Item.stick, 'A', Item.axeWood, 'H', Item.shovelWood, 'P', Item.pickaxeWood});
        //Stone Multi-Tool
        ModLoader.addName(stoneMulti, "Stone Multi-Tool");
        ModLoader.addRecipe(new ItemStack(stoneMulti, 1), new Object[] {"AHP", "OSO", "OSO", 'S', Item.stick, 'A', Item.axeStone, 'H', Item.shovelStone, 'P', Item.pickaxeStone});
        //Iron Multi-Tool
        ModLoader.addName(ironMulti, "Iron Multi-Tool");
        ModLoader.addRecipe(new ItemStack(ironMulti, 1), new Object[] {"AHP", "OSO", "OSO", 'S', Item.stick, 'A', Item.axeSteel, 'H', Item.shovelSteel, 'P', Item.pickaxeSteel});
        //Gold Multi-Tool
        ModLoader.addName(goldMulti, "Gold Multi-Tool");
        ModLoader.addRecipe(new ItemStack(goldMulti, 1), new Object[] {"AHP", "OSO", "OSO", 'S', Item.stick, 'A', Item.axeGold, 'H', Item.shovelGold, 'P', Item.pickaxeGold});
        //Diamond Multi-Tool
        ModLoader.addName(diamondMulti, "Diamond Multi-Tool");
        ModLoader.addRecipe(new ItemStack(diamondMulti, 1), new Object[] {"AHP", "OSO", "OSO", 'S', Item.stick, 'A', Item.axeDiamond, 'H', Item.shovelDiamond, 'P', Item.pickaxeDiamond});
        
        //Other stuff
        if (otherStuff){
            //Inversion Furnace
            ModLoader.registerBlock(inverseFurnace); 
            ModLoader.addName(inverseFurnace, "Inversion Furnace");
            ModLoader.addRecipe(new ItemStack(inverseFurnace, 1), new Object[] {"DED", "EFE", "DED", 'E', Block.whiteStone, 'D', Item.diamond, 'F', Block.furnaceIdle});
        }
    }
    
    public static void propCheck()
    {
    	if (Config.eggMode > 3 || Config.eggMode < 1)
        {
        	throw new Error("Configuration Property 'eggMode' must equil 1, 2, or 3");
        }
    }
    
    public static boolean eggUsable()
    {
    	if (Config.eggMode == 1 || Config.eggMode == 3) return true;
    	else return false;
    }
    
    public boolean classExists (String className)
    {
    	try {
    		Class.forName (className);
    		return true;
    	}
    	catch (ClassNotFoundException exception) {
    		return false;
    	}
    }
    
    public MoreCrafts()
    {
    	
    }
    
    public void generateSurface(World world, Random random, int chunkX, int chunkZ)
    {
    	//test generation using seed -839019905251343089
    	BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(chunkX, chunkZ);
    	if(biomegenbase instanceof BiomeGenOcean || biomegenbase instanceof BiomeGenRiver)
    	{
    		for(int k = 0; k < 10; k++)
            {
                   int RandPosX = chunkX + random.nextInt(5);
                   int RandPosY = random.nextInt(80);
                   int RandPosZ = chunkZ + random.nextInt(5);
                   (new WorldGenSponge(6)).generate(world, random, RandPosX, RandPosY, RandPosZ);
            }
    	}
    }
    public String getVersion()
    {
        return "1.4.0.021";
    }
}