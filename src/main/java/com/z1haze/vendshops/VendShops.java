package com.z1haze.vendshops;

import com.z1haze.vendshops.setup.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(VendShops.MOD_ID)
public class VendShops {
    public static final String MOD_ID = "vendshops";

    public VendShops() {
        Registration.register();

        MinecraftForge.EVENT_BUS.register(this);
    }
}
