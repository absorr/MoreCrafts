package net.minecraft.src.absorr.morecrafts;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import java.io.File;
import net.minecraft.src.mod_moreCrafts;

public class Config 
{
	static Configuration configuration = new Configuration(new File(new File(".").getAbsolutePath(), "config/MoreCrafts.cfg"));
	public static int spawnerID = configurationProperties();
	public static int furnaceID;
	public static int chainID;
	public static int eggID;
	public static int woodMultiID;
	public static int stoneMultiID;
	public static int ironMultiID;
	public static int diamondMultiID;
	public static int goldMultiID;
	public static int advSpawnID;
	public static int eggMode;
    public static int configurationProperties()
    {
            configuration.load();
            spawnerID = Integer.parseInt(configuration.getBlock(Configuration.CATEGORY_BLOCK, "Empty_Monster_Spawner", 191).value);
            furnaceID = Integer.parseInt(configuration.getBlock(Configuration.CATEGORY_BLOCK, "Inversion_Furnace", 192).value);
            chainID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Chain", 5978).value);
            eggID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Blank_Spawn_Egg", 5979).value);
            woodMultiID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Wood_Multi_Tool", 5989).value);
            stoneMultiID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Stone_Multi_Tool", 5990).value);
            ironMultiID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Iron_Multi_Tool", 5991).value);
            diamondMultiID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Diamond_Multi_Tool", 5992).value);
            goldMultiID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Gold_Multi_Tool", 5993).value);
            advSpawnID = Integer.parseInt(configuration.getItem(Configuration.CATEGORY_ITEM, "Advanced_Spawn_Egg", 6001).value);
            eggMode = Integer.parseInt(configuration.get(Configuration.CATEGORY_GENERAL, "Blank_Spawn_Egg_Mode", 1).value);
            configuration.save();
            //mod_moreCrafts.propCheck();
            return spawnerID;
    }
}
