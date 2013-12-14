package doc.inventorySystem.Common;

import java.util.ArrayList;

import doc.inventorySystem.Client.GUI.LoadOutInventory;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedEntityRender implements IExtendedEntityProperties {

	public static final String EXT_NAME = "customInventoryRender";
	
	public final EntityPlayer player;
	public final LoadOutInventory inventory = new LoadOutInventory();
	public ArrayList<ItemStack[]> loadOuts = new ArrayList<ItemStack[]>();
	
	public ExtendedEntityRender(EntityPlayer player) {
		this.player = player;
		
		loadOuts.add(new ItemStack[] { new ItemStack(Item.pickaxeDiamond), new ItemStack(Item.arrow, 32), null, new ItemStack(Block.cobblestone, 12) });
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(EXT_NAME, new ExtendedEntityRender(player));
	}
	
	public static final ExtendedEntityRender get(EntityPlayer player) {
		return (ExtendedEntityRender) player.getExtendedProperties(EXT_NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		inventory.writeToNBT(compound);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		inventory.readFromNBT(compound);
	}

	@Override
	public void init(Entity entity, World world) {}

}
