package doc.inventorySystem.Helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import doc.inventorySystem.Client.GUI.LoadOutInventory;
import doc.inventorySystem.Common.ExtendedEntityRender;

public class LoadoutHelper {

	public static int[] loadoutOne = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	public static int[] loadoutTwo = { 9, 10, 11, 12, 13, 14, 15, 16, 17 };
	public static int[] loadoutThr = { 18, 19, 20, 21, 22, 23, 24, 25, 26 };
	public static int[] overflow   = { 27, 28, 29, 30, 31, 32, 33, 34, 35 };

	public static void swapToLoadout(int loadout, EntityPlayer player) {
		ExtendedEntityRender props = ExtendedEntityRender.get(player);
		InventoryPlayer playerInventory = player.inventory;
		LoadOutInventory loadoutInventory = props.inventory;

		boolean hasLoadout = props.loadOuts.size() >= loadout;

		if (loadout == 0) {
			repopulateLoadouts(player);
			return;
		}			
		
		//props.setHotbarSave(saveHotBar(player)); //save hotbar before swap
		repopulateLoadouts(player); //try to restore loadouts from current hotbar
		
		if (hasLoadout) {
			final ItemStack[] ghostLoadout = props.loadOuts.get(loadout - 1);
			boolean[] whatSwapped = { false, false, false, false, false, false, false, false, false, false };
			int[] searchSlots = loadoutSlots(loadout);

			for (ItemStack searchFor : ghostLoadout) {
				if (searchFor != null) {
					for (int i = 0; i < searchSlots.length; i++) { //search loadout row first
						if (loadoutInventory.getStackInSlot(searchSlots[i]) != null && searchFor.itemID == loadoutInventory.getStackInSlot(searchSlots[i]).itemID && !whatSwapped[i]) {
							if (playerInventory.getStackInSlot(i) != null)
								placeItemStackAway(playerInventory.getStackInSlot(i), player);
							playerInventory.setInventorySlotContents(i, searchFor);
							loadoutInventory.setInventorySlotContents(searchSlots[i], null);
							whatSwapped[i] = true;
						}
					}	

					for (int j = 9; j < playerInventory.getSizeInventory(); j++) { //search vanilla
						int slotNumber = j >= (rowNumber(j) * 8) ? (j - (rowNumber(j) * 8)) + (((rowNumber(j) * 8) - 1) % 8) - rowNumber(j) + 1: (j % 8) - rowNumber(j); 
						if (playerInventory.getStackInSlot(j) != null && searchFor.itemID == playerInventory.getStackInSlot(j).itemID && !whatSwapped[slotNumber]) {
							if (playerInventory.getStackInSlot(searchSlots[slotNumber]) != null)
								placeItemStackAway(playerInventory.getStackInSlot(searchSlots[slotNumber]), player);
							playerInventory.setInventorySlotContents(searchSlots[slotNumber], searchFor);
							ItemStack stack = playerInventory.getStackInSlot(j);
							stack.splitStack(searchFor.stackSize);
							playerInventory.setInventorySlotContents(j, stack.stackSize == 0 ? null : stack);
							whatSwapped[slotNumber] = true;
						}
					}
				}
			}


		}

	}

	public static boolean placeItemStackAway(ItemStack stack, EntityPlayer ePlayer) {
		ExtendedEntityRender props = ExtendedEntityRender.get(ePlayer);
		InventoryPlayer playerInventory = ePlayer.inventory;
		LoadOutInventory loadoutInventory = props.inventory;

		for (int j = 9; j < playerInventory.mainInventory.length; j++) { // vanilla
			if (playerInventory.getStackInSlot(j) == null) {
				playerInventory.setInventorySlotContents(j, stack);
				return true;
			}				
		}

		for (int i = 27; i < loadoutInventory.getSizeInventory(); i++) {
			if (loadoutInventory.getStackInSlot(i) == null) {
				loadoutInventory.setInventorySlotContents(i, stack);
				return true;
			}
		}
		return false;
	}


	public static ItemStack[] saveHotBar(EntityPlayer ePlayer) {

		InventoryPlayer inventory = ePlayer.inventory;
		ItemStack[] hotbarSave = new ItemStack[9];
		for (int i = 0; i < 9; i++)
			hotbarSave[i] = inventory.getStackInSlot(i);
		return hotbarSave;
	}

	public static void repopulateLoadouts(EntityPlayer ePlayer) {
		ExtendedEntityRender props = ExtendedEntityRender.get(ePlayer);
		InventoryPlayer playerInventory = ePlayer.inventory;
		LoadOutInventory loadoutInventory = props.inventory;

		
		/*
		 * Loadout One
		 */
		ItemStack[] ghostLoadout = props.loadOuts.get(0);
		for (int j = 0; j < 9; j++) {
			if (playerInventory.getStackInSlot(j) != null && ghostLoadout[j] != null && playerInventory.getStackInSlot(j).itemID == ghostLoadout[j].itemID) {
				ItemStack split = playerInventory.getStackInSlot(j).copy();
				split.splitStack(ghostLoadout[j].stackSize);
				
				playerInventory.setInventorySlotContents(j, split.stackSize == 0 ? null : split);
				loadoutInventory.setInventorySlotContents(loadoutOne[j], ghostLoadout[j]);
			}
		}
		
		/*
		 * Loadout Two
		 */
		ghostLoadout = props.loadOuts.get(1);
		for (int j = 0; j < 9; j++) {
			if (playerInventory.getStackInSlot(j) != null && ghostLoadout[j] != null && playerInventory.getStackInSlot(j).itemID == ghostLoadout[j].itemID) {
				ItemStack split = playerInventory.getStackInSlot(j).copy();
				split.splitStack(ghostLoadout[j].stackSize);
				
				playerInventory.setInventorySlotContents(j, split.stackSize == 0 ? null : split);
				loadoutInventory.setInventorySlotContents(loadoutTwo[j], ghostLoadout[j]);
			}
		}
		
		/*
		 * Loadout Thr
		 */
		ghostLoadout = props.loadOuts.get(2);
		for (int j = 0; j < 9; j++) {
			if (playerInventory.getStackInSlot(j) != null && ghostLoadout[j] != null && playerInventory.getStackInSlot(j).itemID == ghostLoadout[j].itemID) {
				ItemStack split = playerInventory.getStackInSlot(j).copy();
				split.splitStack(ghostLoadout[j].stackSize);
				
				playerInventory.setInventorySlotContents(j, split.stackSize == 0 ? null : split);
				loadoutInventory.setInventorySlotContents(loadoutThr[j], ghostLoadout[j]);
			}
		}
	}

	public static boolean containsOneItem(int loadout, ItemStack[] stack) {
		int[] slots = loadoutSlots(loadout);

		for (int i = 0; i < slots.length; i++) {
			if (stack[i] != null)
				return true;
		}

		return false;
	}

	public static int[] loadoutSlots(int loadout) {
		switch (loadout) {
		case 1: return loadoutOne;
		case 2: return loadoutTwo;
		case 3: return loadoutThr;
		}
		return new int[] {};
	}

	public static int rowNumber(int number) {
		if (number >= 9 && number <= 17)
			return 2;
		else if (number >= 18 && number <= 26)
			return 3;
		else if (number >= 27 && number <= 35)
			return 4;
		return 1;
	}
}
