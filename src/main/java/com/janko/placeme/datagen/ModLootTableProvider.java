package com.janko.placeme.datagen;

import com.janko.placeme.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(Blocks.CAKE);

        addDrop(ModBlocks.PUMPKIN_PIE, Items.PUMPKIN_PIE);

        addDrop(ModBlocks.BOWL, Items.BOWL);
        addDrop(ModBlocks.BEETROOT_SOUP_BOWL, Items.BEETROOT_SOUP);
        addDrop(ModBlocks.MUSHROOM_STEW_BOWL, Items.MUSHROOM_STEW);
        addDrop(ModBlocks.RABBIT_STEW_BOWL, Items.RABBIT_STEW);
        addDrop(ModBlocks.SUSPICIOUS_STEW_BOWL, Items.SUSPICIOUS_STEW);

        addDrop(ModBlocks.RAW_CHICKEN, Items.CHICKEN);
        addDrop(ModBlocks.COOKED_CHICKEN, Items.COOKED_CHICKEN);
        addDrop(ModBlocks.LAVA_CHICKEN);
    }
}
