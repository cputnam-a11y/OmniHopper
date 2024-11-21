package nl.enjarai.omnihopper.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import nl.enjarai.omnihopper.blocks.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipes extends FabricRecipeProvider {
    public ModRecipes(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new Generator(wrapperLookup, recipeExporter);
    }

    @Override
    public String getName() {
        return "Recipes";
    }

    static class Generator extends RecipeGenerator {
        protected Generator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
            super(registries, exporter);
        }

        @Override
        public void generate() {
            createShaped(ModBlocks.FLUID_HOPPER_BLOCK)
                    .pattern("c c")
                    .pattern("cBc")
                    .pattern(" c ")
                    .input('c', Items.COPPER_INGOT)
                    .input('B', Items.BUCKET)
                    .criterion(
                            hasItem(Items.COPPER_INGOT),
                            conditionsFromItem(Items.COPPER_INGOT)
                    )
                    .criterion(
                            hasItem(Items.BUCKET),
                            conditionsFromItem(Items.BUCKET)
                    )
                    .showNotification(false)
                    .offerTo(exporter);


            createShapeless(ModBlocks.FLUID_OMNIHOPPER_BLOCK)
                    .input(Items.COPPER_INGOT)
                    .input(ModBlocks.FLUID_HOPPER_BLOCK)
                    .criterion(
                            hasItem(Items.COPPER_INGOT),
                            conditionsFromItem(Items.COPPER_INGOT)
                    )
                    .criterion(
                            hasItem(ModBlocks.FLUID_HOPPER_BLOCK),
                            conditionsFromItem(ModBlocks.FLUID_HOPPER_BLOCK)
                    )
                    .offerTo(exporter);


            createShapeless(ModBlocks.OMNIHOPPER_BLOCK)
                    .input(Items.COPPER_INGOT)
                    .input(Blocks.HOPPER)
                    .criterion(
                            hasItem(Items.COPPER_INGOT),
                            conditionsFromItem(Items.COPPER_INGOT)
                    )
                    .criterion(
                            hasItem(Blocks.HOPPER),
                            conditionsFromItem(Blocks.HOPPER)
                    )
                    .offerTo(exporter);
            createShaped(ModBlocks.OPEN_BOX_BLOCK)
                    .pattern("w w")
                    .pattern("w w")
                    .pattern("w w")
                    .input('w', ItemTags.PLANKS)
                    .criterion(
                            "has_any_planks",
                            conditionsFromTag(ItemTags.PLANKS)
                    )
                    .offerTo(exporter);
            createShaped(ModBlocks.WOODEN_HOPPER_BLOCK)
                    .pattern("w w")
                    .pattern("wCw")
                    .pattern(" w ")
                    .input('w', ItemTags.PLANKS)
                    .input('C', Blocks.CHEST)
                    .criterion(
                            "has_any_planks",
                            conditionsFromTag(ItemTags.PLANKS)
                    )
                    .criterion(
                            hasItem(Blocks.CHEST),
                            conditionsFromItem(Blocks.CHEST)
                    )
                    .showNotification(false)
                    .offerTo(exporter);
            createShapeless(ModBlocks.WOODEN_OMNIHOPPER_BLOCK)
                    .input(ModBlocks.WOODEN_HOPPER_BLOCK)
                    .input(Items.COPPER_INGOT)
                    .criterion(
                            hasItem(ModBlocks.WOODEN_HOPPER_BLOCK),
                            conditionsFromItem(ModBlocks.WOODEN_HOPPER_BLOCK)
                    )
                    .criterion(
                            hasItem(Items.COPPER_INGOT),
                            conditionsFromItem(Items.COPPER_INGOT)
                    )
                    .offerTo(exporter);
        }

        private ShapedRecipeJsonBuilder createShaped(ItemConvertible outputItem) {
            return ShapedRecipeJsonBuilder.create(registries.getOrThrow(RegistryKeys.ITEM), RecipeCategory.REDSTONE, outputItem);
        }

        private ShapelessRecipeJsonBuilder createShapeless(ItemConvertible outputItem) {
            return ShapelessRecipeJsonBuilder.create(registries.getOrThrow(RegistryKeys.ITEM), RecipeCategory.REDSTONE, outputItem);
        }
    }
}
