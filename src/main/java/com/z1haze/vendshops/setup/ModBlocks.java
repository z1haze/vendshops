package com.z1haze.vendshops.setup;

import com.z1haze.vendshops.block.vendingmachine.VendingMachineBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final RegistryObject<VendingMachineBlock> VENDING_MACHINE = registerNoItem("vending_machine", () ->
            new VendingMachineBlock(AbstractBlock.Properties
                    .of(Material.METAL)
                    .noOcclusion()
                    .isRedstoneConductor(VendingMachineBlock::isNormalCube)
                    .lightLevel(VendingMachineBlock::lightValue)
                    .strength(3, 10)
                    .sound(SoundType.METAL)));

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(ItemGroup.TAB_MISC)));

        return ret;
    }

    static void register() {}
}
