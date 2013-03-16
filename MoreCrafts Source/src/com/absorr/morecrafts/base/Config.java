package com.absorr.morecrafts.base;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import java.io.File;

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
            spawnerID = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Empty_Monster_Spawner", 191).getInt();
            furnaceID = configuration.getBlock(Configuration.CATEGORY_BLOCK, "Inversion_Furnace", 192).getInt();
            chainID = configuration.getItem(Configuration.CATEGORY_ITEM, "Chain", 5978).getInt();
            eggID = configuration.getItem(Configuration.CATEGORY_ITEM, "Blank_Spawn_Egg", 5979).getInt();
            woodMultiID = configuration.getItem(Configuration.CATEGORY_ITEM, "Wood_Multi_Tool", 5989).getInt();
            stoneMultiID = configuration.getItem(Configuration.CATEGORY_ITEM, "Stone_Multi_Tool", 5990).getInt();
            ironMultiID = configuration.getItem(Configuration.CATEGORY_ITEM, "Iron_Multi_Tool", 5991).getInt();
            diamondMultiID = configuration.getItem(Configuration.CATEGORY_ITEM, "Diamond_Multi_Tool", 5992).getInt();
            goldMultiID = configuration.getItem(Configuration.CATEGORY_ITEM, "Gold_Multi_Tool", 5993).getInt();
            advSpawnID = configuration.getItem(Configuration.CATEGORY_ITEM, "Advanced_Spawn_Egg", 6001).getInt();
            eggMode = configuration.get(Configuration.CATEGORY_GENERAL, "Blank_Spawn_Egg_Mode", 1).getInt();
            configuration.save();
            //mod_moreCrafts.propCheck();
            return spawnerID;
    }
}
