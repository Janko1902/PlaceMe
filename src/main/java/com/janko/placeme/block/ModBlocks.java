package com.janko.placeme.block;

import com.janko.placeme.PlaceMe;
import com.janko.placeme.block.custom.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlocks {
    public static final Block PUMPKIN_PIE = registerBlock("pumpkin_pie",
            new PumpkinPieBlock(AbstractBlock.Settings.copy(Blocks.CAKE).nonOpaque()));

    public static final Block BOWL = registerBlock("bowl",
            new BowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE).nonOpaque()));
    public static final Block BEETROOT_SOUP_BOWL = registerBlock("beetroot_soup_bowl",
            new SoupStewBowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE).nonOpaque()));
    public static final Block MUSHROOM_STEW_BOWL = registerBlock("mushroom_stew_bowl",
            new SoupStewBowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE).nonOpaque()));
    public static final Block RABBIT_STEW_BOWL = registerBlock("rabbit_stew_bowl",
            new RabbitStewBowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE).nonOpaque().nonOpaque()));
    //TODO: effects, !player.canConsume(true)
    public static final Block SUSPICIOUS_STEW_BOWL = registerBlock("suspicious_stew_bowl",
            new SoupStewBowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE).nonOpaque()));

    public static final Block RAW_CHICKEN = registerBlock("raw_chicken",
            new RawChickenBlock(AbstractBlock.Settings.create().nonOpaque()));
    public static final Block COOKED_CHICKEN = registerBlock("cooked_chicken",
            new CookedChickenBlock(AbstractBlock.Settings.create().nonOpaque()));
    //TODO: make it obtainable by throwing raw chicken into lava
    public static final Block LAVA_CHICKEN = registerBlockWithoutItem("lava_chicken",
            new LavaChickenBlock(AbstractBlock.Settings.create().luminance(state -> 4).nonOpaque()));


    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, PlaceMe.id(name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, PlaceMe.id(name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, PlaceMe.id(name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerBlocks() {
        PlaceMe.LOGGER.info("Registering Blocks for " + PlaceMe.MOD_ID);
    }
}
