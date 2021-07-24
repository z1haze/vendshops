package com.z1haze.vendshops.data;

import com.z1haze.vendshops.setup.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder shapedRecipeBuilder = new ShapedRecipeBuilder(ModBlocks.VENDING_MACHINE.get(), 1) {
            @Override
            public void ensureValid(ResourceLocation p_200463_1_) {}
        };

        shapedRecipeBuilder
                .define('i', Items.IRON_BLOCK)
                .define('g', Items.GLASS_PANE)
                .define('p', Items.PISTON)
                .define('r', Items.REDSTONE)
                .pattern("iii")
                .pattern("rgr")
                .pattern("ipi")
                .save(consumer);
    }
}
