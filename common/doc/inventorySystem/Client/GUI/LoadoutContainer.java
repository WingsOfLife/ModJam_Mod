package doc.inventorySystem.Client.GUI;

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
		
		for (int i = 0; i < 3; i++) // custom loadout inventory
			for (int j = 0; j < 9; j++)
				this.addSlotToContainer(new Slot(inventoryCustom, j + i * 9 + 9, 8 + j * 18, 8 + i * 18));
		
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
	public void onContainerClosed(EntityPlayer entityPlayer) {
		super.onContainerClosed(entityPlayer);
	}
}
