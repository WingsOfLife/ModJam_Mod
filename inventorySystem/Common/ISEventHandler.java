package doc.inventorySystem.Common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

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
}
