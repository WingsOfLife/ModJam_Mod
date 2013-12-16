package doc.inventorySystem.Common;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import doc.inventorySystem.Client.GUI.LoadOutInventory;

public class ExtendedEntityRender implements IExtendedEntityProperties {

	public static final String EXT_NAME = "customInventoryRender";

	public final EntityPlayer player;
	public final LoadOutInventory inventory = new LoadOutInventory();
	public ArrayList<ItemStack[]> loadOuts = new ArrayList<ItemStack[]>();

	public ItemStack[] hotbarSave = new ItemStack[9];

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
	public void saveNBTData(NBTTagCompound compound) {
		inventory.writeToNBT(compound);

		/*NBTTagList neighborsList = new NBTTagList();
		for (int i = 0; i < loadOuts.size(); i++) {
			NBTTagCompound nbt = new NBTTagCompound();
			for (int j = 0; j < 9; j++) {
				loadOuts.get(i)[j].writeToNBT(nbt);
				neighborsList.appendTag(nbt);
			}
			
			compound.setTag("Loadout", neighborsList);
		}*/
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		inventory.readFromNBT(compound);
		
		/*NBTTagList neighborTag = compound.getTagList("Loadout");
		loadOuts.clear();

        for (int iter = 0; iter < neighborTag.tagCount(); iter++) {
            NBTTagCompound nbt = (NBTTagCompound) neighborTag.tagAt(iter);
            ItemStack[] totalStack = new ItemStack[9];
            for (int i = 0; i < 9; i++)
            	totalStack[i] = ItemStack.loadItemStackFromNBT(nbt);
            loadOuts.add(totalStack);
        }*/
	}

	@Override
	public void init(Entity entity, World world) {}

	public void setLoadout(ItemStack[] loadout, int pos) {
		loadOuts.add(pos, loadout);
	}

	public void setHotbarSave(ItemStack[] stack) {
		hotbarSave = stack;
	}

}
