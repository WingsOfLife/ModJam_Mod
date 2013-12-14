package doc.inventorySystem.Helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
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
		
		boolean emptyLoadout = props.loadOuts.get(loadout) == null;
		
		if (!emptyLoadout) {
			ItemStack[] selectLoadout = props.loadOuts.get(loadout);
			boolean[] whatSwapped = { false, false, false, false, false, false, false, false, false };
			int[] selectedLoad = loadoutSlots(loadout);
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
