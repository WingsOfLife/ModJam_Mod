package doc.inventorySystem.Client.GUI;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class LoadoutContainer extends Container {
	
	public LoadoutContainer(EntityPlayer player, InventoryPlayer inventoryPlayer, LoadOutInventory inventoryCustom)	{
		this.addSlotToContainer(new Slot(inventoryCustom, 0, 80, 8));
		this.addSlotToContainer(new Slot(inventoryCustom, 1, 80, 26));

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
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        if (slotObject != null && slotObject.getHasStack())
        {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            if (slot < 0)
                if (!this.mergeItemStack(stackInSlot, 0, 35, true))
                {
                    return null;
                }
                else if (!this.mergeItemStack(stackInSlot, 0, 9, false))
                {
                    return null;
                }

            if (stackInSlot.stackSize == 0)
            {
                slotObject.putStack(null);
            }
            else
            {
                slotObject.onSlotChanged();
            }

            if (stackInSlot.stackSize == stack.stackSize)
            {
                return null;
            }

            slotObject.onPickupFromSlot(player, stackInSlot);
        }

        return stack;
    }
}
