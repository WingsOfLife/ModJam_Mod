package doc.inventorySystem.Common;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import doc.inventorySystem.Client.GUI.LoadOutGuiContainer;
import doc.inventorySystem.Client.GUI.LoadoutContainer;

public class CommonProxy implements IGuiHandler {

	public static final Map<String, NBTTagCompound> acrossDeathData = new HashMap<String, NBTTagCompound>();
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new LoadoutContainer(player, player.inventory, ExtendedEntityRender.get(player).inventory);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new LoadOutGuiContainer(player, player.inventory, ExtendedEntityRender.get(player).inventory);
	}
	
	public static void storeDeathData(String username, NBTTagCompound nbtTag) {
		acrossDeathData.put(username, nbtTag);
	}
	
	public static NBTTagCompound getDeathData(String username) {
		return acrossDeathData.remove(username);
	}

}
