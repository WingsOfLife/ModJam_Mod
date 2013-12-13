package doc.inventorySystem.Common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayerRender implements IExtendedEntityProperties {

	public static final String EXT_NAME = "customInventoryRender";
	
	public final EntityPlayer player;
	
	public ExtendedPlayerRender(EntityPlayer player) {
		this.player = player;
	}
	
	public static finalVoid
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub
		
	}

}
