package com.z1haze.vendshops.setup;

import com.z1haze.vendshops.block.vendingmachine.VendingMachineBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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

    @OnlyIn(Dist.CLIENT)
    public static void setRenderLayer() {
        RenderTypeLookup.setRenderLayer(VENDING_MACHINE.get(), RenderType.cutout());
    }

    static void register() {}
}
