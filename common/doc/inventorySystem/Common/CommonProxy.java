package doc.inventorySystem.Common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import doc.inventorySystem.Client.GUI.LoadOutGuiContainer;
import doc.inventorySystem.Client.GUI.LoadoutContainer;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new LoadoutContainer(player, player.inventory, ExtendedEntityRender.get(player).inventory);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new LoadOutGuiContainer(player, player.inventory, ExtendedEntityRender.get(player).inventory);
	}

}
