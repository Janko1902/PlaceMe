package com.janko.placeme.block;

import com.janko.placeme.PlaceMe;
import com.janko.placeme.block.custom.SoupStewBowlBlock;
import com.janko.placeme.block.custom.BowlBlock;
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

    public static final Block BOWL = registerBlock("bowl",
            new BowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE)));
    public static final Block BEETROOT_SOUP_BOWL = registerBlock("beetroot_soup_bowl",
            new SoupStewBowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE)));
    public static final Block MUSHROOM_STEW_BOWL = registerBlock("mushroom_stew_bowl",
            new SoupStewBowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE)));
    public static final Block RABBIT_STEW_BOWL = registerBlock("rabbit_stew_bowl",
            new SoupStewBowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE).nonOpaque()));
    //TODO: effects, !player.canConsume(true)
    public static final Block SUSPICIOUS_STEW_BOWL = registerBlock("suspicious_stew_bowl",
            new SoupStewBowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE)));


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
