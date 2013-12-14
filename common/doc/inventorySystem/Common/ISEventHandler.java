package doc.inventorySystem.Common;

import doc.inventorySystem.Helper.ItemHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class ISEventHandler {

	/*@ForgeSubscribe
	public void onLivingUpdateEvent(LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
		}
	}*/

	@ForgeSubscribe
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && ExtendedEntityRender.get((EntityPlayer) event.entity) == null)
			ExtendedEntityRender.register((EntityPlayer) event.entity);
	}
	
	@ForgeSubscribe
	public void onEntityDeath(LivingDeathEvent event) {
		if (!(event.entity instanceof EntityPlayer))
			return;
		
		ExtendedEntityRender props = new ExtendedEntityRender((EntityPlayer) event.entity);
		for (int i = 0; i < props.inventory.getSizeInventory(); i++)
			if (props.inventory.getStackInSlot(i) != null)
				ItemHelper.spawnItem(props.inventory.getStackInSlot(i), event.entity.worldObj, event.entity.serverPosX, event.entity.serverPosY, event.entity.serverPosZ);
	}
}
