package com.z1haze.vendshops.block.vendingmachine;

import com.z1haze.vendshops.setup.ModContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;

public class VendingMachineContainer extends Container {

    public final VendingMachineTileEntity te;
    private final IWorldPosCallable canInteractWithCallable;

    public VendingMachineContainer(final int windowId, final PlayerInventory playerInventory, final VendingMachineTileEntity te) {
        super(ModContainerTypes.VENDING_MACHINE.get(), windowId);

        this.te = te;
        this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());

        // tile entity inventory
        this.addSlot(new Slot(te, 0, 80, 35));

        // player inventory (3 rows of 9)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // player hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    public VendingMachineContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static VendingMachineTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "Player Inventory cannot be null.");
        Objects.requireNonNull(data, "Packet Buffer cannot be null.");

        final TileEntity te = playerInventory.player.level.getBlockEntity(data.readBlockPos());

        if (te instanceof VendingMachineTileEntity) {
            return (VendingMachineTileEntity) te;
        }

        throw new IllegalStateException("Tile Entity is not a valid VendingMachineTileEntity");
    }

    @Override
    public boolean stillValid(PlayerEntity playerEntity) {
        return this.te.stillValid(playerEntity);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerEntity, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();

            if (index < VendingMachineTileEntity.slots && !this.moveItemStackTo(stack1, VendingMachineTileEntity.slots, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            } else if (!this.moveItemStackTo(stack1, 0, VendingMachineTileEntity.slots, false)) {
                return ItemStack.EMPTY;
            } else if (stack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return stack;
    }
}
