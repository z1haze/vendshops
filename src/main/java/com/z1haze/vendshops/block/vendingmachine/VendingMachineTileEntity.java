package com.z1haze.vendshops.block.vendingmachine;

import com.z1haze.vendshops.setup.ModTileEntityTypes;
import net.minecraft.tileentity.TileEntity;

public class VendingMachineTileEntity extends TileEntity {
    public VendingMachineTileEntity() {
        super(ModTileEntityTypes.VENDING_MACHINE.get());
    }
}
