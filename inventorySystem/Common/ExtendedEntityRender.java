package doc.inventorySystem.Common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedEntityRender implements IExtendedEntityProperties {

	public static final String EXT_NAME = "customInventoryRender";
	
	public final EntityPlayer player;
	
	public ExtendedEntityRender(EntityPlayer player) {
		this.player = player;
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(EXT_NAME, new ExtendedEntityRender(player));
	}
	
	public static final ExtendedEntityRender get(EntityPlayer player) {
		return (ExtendedEntityRender) player.getExtendedProperties(EXT_NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {}

	@Override
	public void loadNBTData(NBTTagCompound compound) {}

	@Override
	public void init(Entity entity, World world) {}

}
