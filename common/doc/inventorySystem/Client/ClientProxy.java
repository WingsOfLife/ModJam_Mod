package doc.inventorySystem.Client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import doc.inventorySystem.Client.GUI.LoadOutGuiContainer;
import doc.inventorySystem.Common.CommonProxy;
import doc.inventorySystem.Common.ExtendedEntityRender;

public class ClientProxy extends CommonProxy {
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new LoadOutGuiContainer(player, player.inventory, ExtendedEntityRender.get(player).inventory);
	}
}
