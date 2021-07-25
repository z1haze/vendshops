package com.z1haze.vendshops.setup;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {
    public static final RegistryObject<Item> VENDING_MACHINE = Registration.ITEMS.register("vending_machine", () ->
            new BlockItem(ModBlocks.VENDING_MACHINE.get(), new Item.Properties()
                    .tab(ItemGroup.TAB_DECORATIONS)
                    .rarity(Rarity.RARE)));

    static void register() {}
}
