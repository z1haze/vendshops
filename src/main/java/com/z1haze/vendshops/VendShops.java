package com.z1haze.vendshops;

import com.z1haze.vendshops.setup.ModBlocks;
import com.z1haze.vendshops.setup.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VendShops.MOD_ID)
public class VendShops {
    public static final String MOD_ID = "vendshops";

    public VendShops() {
        Registration.register();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setupClient(final FMLClientSetupEvent event) {
        ModBlocks.setRenderLayer();
    }
}
