package absorr.morecrafts.base;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
	/*
	 * Preloads textures for use
	 * @see absorr.morecrafts.base.CommonProxy#registerRenderers()
	 */
	@Override
	public void registerRenderers() 
	{
		MinecraftForgeClient.preloadTexture(itemPic); 
    	MinecraftForgeClient.preloadTexture(blockPic);
	}
}
