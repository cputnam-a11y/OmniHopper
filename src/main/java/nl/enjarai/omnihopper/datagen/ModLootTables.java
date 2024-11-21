package nl.enjarai.omnihopper.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import nl.enjarai.omnihopper.blocks.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModLootTables extends FabricBlockLootTableProvider {
    protected ModLootTables(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        ModBlocks.ALL.forEach(block -> addDrop(block, this::nameableContainerDrops));
    }
}
