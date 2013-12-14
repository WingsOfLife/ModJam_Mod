package doc.inventorySystem.Helper;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import doc.inventorySystem.Client.GUI.LoadOutInventory;
import doc.inventorySystem.Common.ExtendedEntityRender;

public class LoadoutHelper {

	public static int[] loadoutOne = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	public static int[] loadoutTwo = { 10, 11, 12, 13, 14, 15, 16, 17, 18 };
	public static int[] loadoutThr = { 19, 20, 21, 22, 23, 24, 25, 26, 27 };
	public static int[] overflow   = { 28, 29, 30, 31, 32, 33, 34, 35, 36 };

	public static void swapToLoadout(int loadout, EntityPlayer player) {
		ExtendedEntityRender props = ExtendedEntityRender.get(player);
		InventoryPlayer playerInventory = player.inventory;
		LoadOutInventory loadoutInventory = props.inventory;

		props.loadOuts.add(new ItemStack[] { new ItemStack(Item.pickaxeDiamond), new ItemStack(Item.arrow, 32), null, new ItemStack(Block.cobblestone, 12) });
		boolean emptyLoadout = props.loadOuts.get(loadout - 1) == null;

		if (!emptyLoadout) {
			final ItemStack[] ghostLoadout = props.loadOuts.get(loadout - 1);
			boolean[] whatSwapped = { false, false, false, false, false, false, false, false, false };
			int[] searchSlots = loadoutSlots(loadout);

			for (ItemStack searchFor : ghostLoadout) {
				if (searchFor != null) {
					for (int i = 0; i < searchSlots.length; i++) { //search loadout row first
						if (loadoutInventory.getStackInSlot(searchSlots[i]) != null && searchFor.equals(loadoutInventory.getStackInSlot(searchSlots[i])) && !whatSwapped[i]) {
							playerInventory.setInventorySlotContents(i, searchFor);
							whatSwapped[i] = true;
						}
					}	
					
					/*for (int j = 9; j < playerInventory.getSizeInventory(); j++) { //search vanilla
						if (searchFor.equals(loadoutInventory.getStackInSlot(searchSlots[j])) && !whatSwapped[j]) {
							playerInventory.setInventorySlotContents(i, searchFor);
							whatSwapped[j] = true;
						}
					}*/
				}
			}


		}

	}

	public static int[] loadoutSlots(int loadout) {
		switch (loadout) {
		case 1: return loadoutOne;
		case 2: return loadoutTwo;
		case 3: return loadoutThr;
		}
		return new int[] {};
	}
}
