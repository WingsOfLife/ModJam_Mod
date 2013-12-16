package doc.inventorySystem.Client.GUI;

import doc.inventorySystem.Common.ExtendedEntityRender;
import doc.inventorySystem.Helper.LoadoutHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class LoadoutContainer extends Container {
	
	public LoadoutContainer(EntityPlayer player, InventoryPlayer inventoryPlayer, LoadOutInventory inventoryCustom)	{
		/*this.addSlotToContainer(new Slot(inventoryCustom, 0, 80, 8));
		this.addSlotToContainer(new Slot(inventoryCustom, 1, 80, 26));*/
		
		for (int i = 0; i < 9; i++) // custom loadout 1 - 9s
			this.addSlotToContainer(new Slot(inventoryCustom, i, 8 + i * 18, 8));
		
		for (int i = 0; i < 3; i++) // custom loadout inventory
			for (int j = 0; j < 9; j++) {
				if (j + i * 9 + 9 < 27)
					this.addSlotToContainer(new Slot(inventoryCustom, j + i * 9 + 9, 8 + j * 18, 26 + i * 18));
				else
					this.addSlotToContainer(new overflowSlot(inventoryCustom, j + i * 9 + 9, 8 + j * 18, 26 + i * 18));
			}
		
		for (int i = 0; i < 3; i++) // vanilla inventory
			for (int j = 0; j < 9; j++)
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

		for (int i = 0; i < 9; i++) // vanilla actionSlots
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	/*
	 * No shift clicking in attempt to prevent abuse.
	 */
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        return null;
    }
	
	@Override
	public void onContainerClosed(EntityPlayer ePlayer) {
		super.onContainerClosed(ePlayer);
		
		//saveLoadouts(ePlayer);
	}
	
	public static void saveLoadouts(EntityPlayer ePlayer) {
		ExtendedEntityRender props = ExtendedEntityRender.get(ePlayer);
		LoadOutInventory loadoutInventory = props.inventory;
		props.loadOuts.clear();
		
		ItemStack[] loadout = new ItemStack[9];
		for (int i = 0; i < LoadoutHelper.loadoutOne.length; i++) {
			loadout[i] = loadoutInventory.getStackInSlot(LoadoutHelper.loadoutOne[i]);
		}
		props.setLoadout(loadout, 0);
		
		loadout = new ItemStack[9];
		for (int i = 0; i < LoadoutHelper.loadoutTwo.length; i++) {
			loadout[i] = loadoutInventory.getStackInSlot(LoadoutHelper.loadoutTwo[i]);
		}
		props.setLoadout(loadout, 1);
		
		loadout = new ItemStack[9];
		for (int i = 0; i < LoadoutHelper.loadoutThr.length; i++) {
			loadout[i] = loadoutInventory.getStackInSlot(LoadoutHelper.loadoutThr[i]);
		}
		props.setLoadout(loadout, 2);
	}
}
