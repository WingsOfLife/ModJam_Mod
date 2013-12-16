package doc.inventorySystem.Common;

import cpw.mods.fml.client.FMLClientHandler;
import doc.inventorySystem.Client.GUI.LoadoutContainer;
import doc.inventorySystem.Packets.PacketHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
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
		if (event.entity instanceof EntityPlayer) {
			NBTTagCompound saveAcrossDeath = new NBTTagCompound();
			ExtendedEntityRender props = ExtendedEntityRender.get((EntityPlayer) event.entity);

			for (int i = 0; i < props.inventory.getSizeInventory(); i++) {
				ItemStack stack = props.inventory.getStackInSlot(i);
				if (stack != null)
					event.entity.dropItem(stack.itemID, stack.stackSize);
			}
		}
	}

	@ForgeSubscribe
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			LoadoutContainer.saveLoadouts((EntityPlayer) event.entity); 
		}
	}
}
