package doc.inventorySystem.Helper;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import doc.inventorySystem.Client.GUI.LoadOutInventory;
import doc.inventorySystem.Common.ExtendedEntityRender;

public class LoadoutHelper {

	public static int[] loadoutOne = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	public static int[] loadoutTwo = { 9, 10, 11, 12, 13, 14, 15, 16 };
	public static int[] loadoutThr = { 17, 18, 19, 20, 21, 22, 23, 24 };
	public static int[] overflow   = { 25, 26, 27, 28, 29, 21, 31, 32 };

	public static void swapToLoadout(int loadout, EntityPlayer player) {
		ExtendedEntityRender props = ExtendedEntityRender.get(player);
		InventoryPlayer playerInventory = player.inventory;
		LoadOutInventory loadoutInventory = props.inventory;

		//props.loadOuts.add(new ItemStack[] { new ItemStack(Item.pickaxeDiamond), new ItemStack(Item.arrow, 32), null, new ItemStack(Block.cobblestone, 12) });
		boolean emptyLoadout = props.loadOuts.get(loadout - 1) == null;

		if (!emptyLoadout) {
			final ItemStack[] ghostLoadout = props.loadOuts.get(loadout - 1);
			boolean[] whatSwapped = { false, false, false, false, false, false, false, false, false };
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
		if (number >= 10 && number <= 18)
			return 2;
		else if (number >= 19 && number <= 27)
			return 3;
		else if (number >= 28 && number <= 36)
			return 4;
		return 1;
	}
}
