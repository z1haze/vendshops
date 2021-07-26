package com.z1haze.vendshops.setup;

import com.z1haze.vendshops.block.vendingmachine.VendingMachineTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModTileEntityTypes {
    public static final RegistryObject<TileEntityType<VendingMachineTileEntity>> VENDING_MACHINE = register(
            "vending_machine",
            VendingMachineTileEntity::new,
            ModBlocks.VENDING_MACHINE
            );

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<T> factory, RegistryObject<? extends Block> block) {
        return Registration.TILE_ENTITIES.register(name, () -> TileEntityType.Builder.of(factory, block.get()).build(null));
    }

    static void register() {}
}
