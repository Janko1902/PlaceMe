package com.janko.placeme.datagen;

import com.janko.placeme.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(Blocks.CAKE);

        addDrop(ModBlocks.PUMPKIN_PIE);

        addDrop(ModBlocks.BOWL);
        addDrop(ModBlocks.BEETROOT_SOUP_BOWL);
        addDrop(ModBlocks.MUSHROOM_STEW_BOWL);
        addDrop(ModBlocks.RABBIT_STEW_BOWL);
        addDrop(ModBlocks.SUSPICIOUS_STEW_BOWL);

        addDrop(ModBlocks.RAW_CHICKEN);
        addDrop(ModBlocks.COOKED_CHICKEN);
        addDrop(ModBlocks.LAVA_CHICKEN);
    }
}
