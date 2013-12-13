package doc.inventorySystem;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import doc.inventorySystem.Common.CommonProxy;
import doc.inventorySystem.Common.ISEventHandler;
import doc.inventorySystem.Packets.PacketHandler;

@Mod(modid="inventorySystems", name = "Inventory System", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "inventorySystem" }, packetHandler = PacketHandler.class)
public class InventorySystemCore {

	@Instance
	public static InventorySystemCore instance;
	
	@SidedProxy(clientSide = "doc.inventorySystems.Client.ClientProxy", serverSide = "doc.inventorySystems.Client.CommonProxy")
    public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ISEventHandler());
	}
	
}
