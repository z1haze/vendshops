package com.z1haze.vendshops.setup;

import com.z1haze.vendshops.block.vendingmachine.VendingMachineContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;

public class ModContainerTypes {

    public static final RegistryObject<ContainerType<VendingMachineContainer>> VENDING_MACHINE = register("vending_machine", VendingMachineContainer::new);

    private static <T extends Container> RegistryObject<ContainerType<T>> register(String name, IContainerFactory<T> factory) {
        return Registration.CONTAINERS.register(name, () ->
                IForgeContainerType.create(factory));
    }

    static void register() {}
}
