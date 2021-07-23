package com.z1haze.vendshops.data.client;

import com.z1haze.vendshops.VendShops;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, VendShops.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("vending_machine", modLoc("block/vending_machine"));
    }
}
