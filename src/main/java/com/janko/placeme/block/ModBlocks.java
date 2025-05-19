package com.janko.placeme.block;

import com.janko.placeme.PlaceMe;
import com.janko.placeme.block.custom.PumpkinPieBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block PUMPKIN_PIE = registerBlock("pumpkin_pie",
            new PumpkinPieBlock(AbstractBlock.Settings.copy(Blocks.CAKE)));


    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(PlaceMe.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(PlaceMe.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(PlaceMe.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerBlocks() {
        PlaceMe.LOGGER.info("Registering Blocks for " + PlaceMe.MOD_ID);
    }
}
