package net.minecraft.src;

import net.minecraft.src.absorr.morecrafts.*;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.*;
import java.awt.List;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.asm.*;


public class mod_moreCrafts extends BaseMod
{
	//Creates the configuration integers from the config class
	static int spawnerID = Config.spawnerID;
	static int furnaceID = Config.furnaceID;
    static int chainID = Config.chainID;
    static int eggID = Config.eggID;
    static int woodMultiID = Config.woodMultiID;
    static int stoneMultiID = Config.stoneMultiID;
    static int ironMultiID = Config.ironMultiID;
    static int diamondMultiID = Config.diamondMultiID;
    static int goldMultiID = Config.goldMultiID;
    static int advSpawnID = Config.advSpawnID;
    static boolean vanRecipes = true;
    static boolean rechargeRs = true;
    static boolean otherStuff = true;
    static int eggMode = Config.eggMode;
	
	//Creates the items and blocks
    public static final Item chain = new MoreItems(chainID, 64, CreativeTabs.tabMisc).setItemName("chain").setIconIndex(0);
    public static final Item blankEgg = new ItemBlankEgg(eggID).setItemName("blankEgg").setIconIndex(Item.monsterPlacer.iconIndex);
    public static final Item woodMulti = new ItemMultiTool(woodMultiID, 1, EnumToolMaterial.WOOD).setItemName("woodenMultitool").setIconIndex(11);
    public static final Item stoneMulti = new ItemMultiTool(stoneMultiID, 1, EnumToolMaterial.STONE).setItemName("stoneMultitool").setIconIndex(10);
    public static final Item ironMulti = new ItemMultiTool(ironMultiID, 1, EnumToolMaterial.IRON).setItemName("ironMultitool").setIconIndex(9);
    public static final Item goldMulti = new ItemMultiTool(goldMultiID, 1, EnumToolMaterial.GOLD).setItemName("goldMultitool").setIconIndex(12);
    public static final Item diamondMulti = new ItemMultiTool(diamondMultiID, 1, EnumToolMaterial.EMERALD).setItemName("diamondMultitool").setIconIndex(13);
    public static final Item advSpawnEgg = new ItemAdvPlacer(advSpawnID).setItemName("advancedEgg").setIconIndex(Item.monsterPlacer.iconIndex);
    public static Block blankSpawner = new BlockBlankSpawner(spawnerID, 65).setHardness(1.0F).setResistance(6000.0F).setLightValue(0.0F).setBlockName("Empty Monster Spawner"); 
    public static Block inverseFurnace = new BlockInversionFurnace(furnaceID, 0).setHardness(1.0F).setResistance(6000.0F).setLightValue(0.0F).setBlockName("Inversion Furnace");
    
    public void load()
    {
    	MinecraftForgeClient.preloadTexture("/morecrafts/items.png"); 
    	MinecraftForgeClient.preloadTexture("/morecrafts/blocks.png");
    	ItemAdvPlacer.loadDefaultIDs();
  		ModLoader.registerTileEntity(TileEntityInversion.class, "Inversion Furnace");
    	String maploaded = InversionRecipes.loader();
    	EntityList.entityEggs.put(Integer.valueOf(63), new EntityEggInfo(63, 0, 9118312));
    	
    	//Villager Trading "Recipes"
    	MerchantRecipeList npcTrade = new MerchantRecipeList();
    	npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 4), new ItemStack (ironMulti, 1)));
    	npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 4), new ItemStack (blankEgg, 3)));
    	npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (blankEgg, 3), new ItemStack (Item.emerald, 1)));
    	npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 3), new ItemStack (blankSpawner, 1)));
    }
    
    public static void propCheck()
    {
    	if (eggMode > 3 || eggMode < 1)
        {
        	throw new Error("Configuration Property 'eggMode' must equil 1, 2, or 3");
        }
    }
    public static boolean eggUsable()
    {
    	if (eggMode == 1 || eggMode == 3) return true;
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
    public mod_moreCrafts()
    {
    	//ModLoader.getMinecraftInstance().thePlayer.addChatMessage("MoreCrafts Development Build Loaded Succesfully");
    	
    	//Vanilla Recipes
    	if (vanRecipes){
    		//Spawn Eggs
    		if (eggMode == 2 || eggMode == 3)
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
            //New Stone Brick Thing
            ModLoader.addRecipe(new ItemStack(Block.stoneBrick, 1, 3), new Object[]{
                "BBB", "B0B", "BBB", 
                'B', Block.stoneBrick});
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
            //Ore Recipes replaced by Inversion Furnace
            /** //Iron Ore
            ModLoader.addRecipe(new ItemStack(Block.oreIron, 1), new Object[]{
                "SSS", "SIS", "SSS", 
                'S', Block.stone, 'I', Item.ingotIron});
            //Coal Ore
            ModLoader.addRecipe(new ItemStack(Block.oreCoal, 1), new Object[]{
                "SSS", "SIS", "SSS", 
                'S', Block.stone, 'I', Item.coal});
            //Gold Ore
            ModLoader.addRecipe(new ItemStack(Block.oreGold, 1), new Object[]{
                "SSS", "SIS", "SSS", 
                'S', Block.stone, 'I', Item.ingotGold});
            //Diamond Ore
            ModLoader.addRecipe(new ItemStack(Block.oreDiamond, 1), new Object[]{
                "SSS", "SIS", "SSS", 
                'S', Block.stone, 'I', Item.diamond});
            //Redstone Ore
            ModLoader.addRecipe(new ItemStack(Block.oreRedstone, 1), new Object[]{
                "SIS", "ISI", "SIS", 
                'S', Block.stone, 'I', Item.redstone});
            //Lapis Lazuli Ore
            ModLoader.addRecipe(new ItemStack(Block.oreLapis, 1), new Object[]{
                "SIS", "ISI", "SIS", 
                'S', Block.stone, 'I', new ItemStack(Item.dyePowder, 1, 4)}); **/
            //Broken Stone Bricks
            ModLoader.addSmelting(Block.stoneBrick.blockID, new ItemStack(Block.stoneBrick, 1, 2));
            //Dragon Egg
            ModLoader.addRecipe(new ItemStack(Block.dragonEgg, 1), new Object[]{
                "0O0", "OEO", "OSO", 
                'S', Block.whiteStone, 'E', new ItemStack(Item.monsterPlacer, 1, 63), 'O', Block.obsidian});
            //Mossy Cobblestone
            ModLoader.addRecipe(new ItemStack(Block.cobblestoneMossy, 1), new Object[]{
                "CVC", "VCV", "CVC", 
                'V', Block.vine, 'C', Block.cobblestone});
            //Mossy Stone Brick
            ModLoader.addRecipe(new ItemStack(Block.cobblestoneMossy, 1), new Object[]{
                "CVC", "VCV", "CVC", 
                'V', Block.vine, 'C', Block.stoneBrick});
            //Ice
            ModLoader.addShapelessRecipe(new ItemStack(Block.ice, 1), new Object[] {Item.bucketWater, Block.blockSnow});
            //Blank Spawn Egg
            ModLoader.addName(blankEgg, "Blank Spawn Egg");
            ModLoader.addShapelessRecipe(new ItemStack(blankEgg, 1), new Object[] {Item.ingotIron, Item.egg});
            //End Portal Frame
            ModLoader.addRecipe(new ItemStack(Block.endPortalFrame, 1), new Object[] {"LEL", "EOE", "EEE", 'E', Block.whiteStone, 'O', Block.obsidian, 'L', Block.blockLapis});
            //End Stone
            ModLoader.addRecipe(new ItemStack(Block.whiteStone, 1), new Object[] {"SCS", "CIC", "SCS", 'S', Block.sponge, 'C', Block.cobblestone, 'I', Block.blockSteel});
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
        	//Empty Monster Spawner
            ModLoader.registerBlock(blankSpawner); 
            ModLoader.addName(blankSpawner, "Empty Monster Spawner");
            ModLoader.addRecipe(new ItemStack(blankSpawner, 1), new Object[] { 
            "III", "IEI", "III", Character.valueOf('I'), Block.fenceIron, Character.valueOf('E'), blankEgg});
            //Inversion Furnace
            ModLoader.registerBlock(inverseFurnace); 
            ModLoader.addName(inverseFurnace, "Inversion Furnace");
            ModLoader.addRecipe(new ItemStack(inverseFurnace, 1), new Object[] {"DED", "EFE", "DED", 'E', Block.whiteStone, 'D', Item.diamond, 'F', Block.stoneOvenIdle});
        }
        //Insta-Smelt
    	/**ModLoader.addName(instaSmelt, "Insta-Smelt");
    	if(classExists("mod_IC2"))
    	{
    		ModLoader.addShapelessRecipe(new ItemStack(instaSmelt, 16), new Object[] {Block.stoneOvenIdle, Items.getItem("electronicCircuit")});
    		ModLoader.addShapelessRecipe(new ItemStack(instaSmelt, 36), new Object[] {Block.stoneOvenIdle, Items.getItem("advancedCircuit")});
    	}
    	else ModLoader.addRecipe(new ItemStack(instaSmelt, 26), new Object[] {"RGR", "IFI", "RGR", 'F', Block.stoneOvenIdle, 'I', Item.ingotIron, 'R', Item.redstone, 'G', Item.ingotGold});
        instaRecipes(); **/
        
        //Adds Items to Dungeon Loot
        DungeonHooks.addDungeonLoot(new ItemStack(chain), 1, 1, 8);
        DungeonHooks.addDungeonLoot(new ItemStack(woodMulti), (int) 0.65, 1, 8);
        DungeonHooks.addDungeonLoot(new ItemStack(blankEgg), (int) 0.1, 1, 8);
        DungeonHooks.addDungeonLoot(new ItemStack(Block.sponge), (int) 3.6, 1, 8);
    }
    /**private void instaRecipes()
    {
    	//Smelting
    	Map smeltMap = FurnaceRecipes.smelting().getSmeltingList();
    	for (Object o : smeltMap.keySet()) 
        {
        	ItemStack x = (ItemStack) smeltMap.get(o);
        	ItemStack y = new ItemStack((Integer) o, 1, 0);
        	ModLoader.addShapelessRecipe(x, new Object[] {instaSmelt, y.getItem()});
        }
    	if(classExists("mod_IC2"))
    	{
        	//Compressing
        	java.util.List<Entry<ItemStack, ItemStack>> compMap = Ic2Recipes.getCompressorRecipes();
        	int p = compMap.size();
        	int[] e = new int[p];
        	System.out.println(e);
    	}
    }**/
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
        return "1.4 pre4";
    }
    public static String getModVersion()
    {
        return "1.4 pre4";
    }
}