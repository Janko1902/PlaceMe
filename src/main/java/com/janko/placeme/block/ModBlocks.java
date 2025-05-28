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
    public static final PumpkinPieBlock PUMPKIN_PIE = registerBlock("pumpkin_pie",
            new PumpkinPieBlock(AbstractBlock.Settings.copy(Blocks.CAKE).nonOpaque()));

    public static final BowlBlock BOWL = registerBlock("bowl",
            new BowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE).nonOpaque()));
    public static final SoupStewBowlBlock BEETROOT_SOUP_BOWL = registerBlock("beetroot_soup_bowl",
            new SoupStewBowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE).nonOpaque().strength(0.5F)));
    public static final SoupStewBowlBlock MUSHROOM_STEW_BOWL = registerBlock("mushroom_stew_bowl",
            new SoupStewBowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE).nonOpaque().strength(0.5F)));
    public static final RabbitStewBowlBlock RABBIT_STEW_BOWL = registerBlock("rabbit_stew_bowl",
            new RabbitStewBowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE).nonOpaque().strength(0.5F)));
    public static final SuspiciousStewBowlBlock SUSPICIOUS_STEW_BOWL = registerBlock("suspicious_stew_bowl",
            new SuspiciousStewBowlBlock(AbstractBlock.Settings.copy(Blocks.CRAFTING_TABLE).nonOpaque().strength(0.5F)));

    public static final RawChickenBlock RAW_CHICKEN = registerBlock("raw_chicken",
            new RawChickenBlock(AbstractBlock.Settings.create().nonOpaque()));
    public static final CookedChickenBlock COOKED_CHICKEN = registerBlock("cooked_chicken",
            new CookedChickenBlock(AbstractBlock.Settings.create().nonOpaque()));
    //not obtainable, easter egg
    public static final LavaChickenBlock LAVA_CHICKEN = registerBlockWithoutItem("lava_chicken",
            new LavaChickenBlock(AbstractBlock.Settings.create().luminance(state -> 4).nonOpaque()));

    public static final AppleBlock APPLE = registerBlock("apple",
            new AppleBlock(AbstractBlock.Settings.create().nonOpaque().breakInstantly()));
    public static final GoldenAppleBlock GOLDEN_APPLE = registerBlock("golden_apple",
            new GoldenAppleBlock(AbstractBlock.Settings.create().nonOpaque().breakInstantly()));
    public static final EnchantedGoldenAppleBlock ENCHANTED_GOLDEN_APPLE = registerBlock("enchanted_golden_apple",
            new EnchantedGoldenAppleBlock(AbstractBlock.Settings.create().luminance(state -> 4).nonOpaque().breakInstantly()));


    private static <B extends Block> B registerBlockWithoutItem(String name, B block) {
        return Registry.register(Registries.BLOCK, PlaceMe.id(name), block);
    }

    private static <B extends Block> B registerBlock(String name, B block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, PlaceMe.id(name), block);
    }

    private static BlockItem registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, PlaceMe.id(name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        PlaceMe.LOGGER.info("Registering Blocks for " + PlaceMe.MOD_ID);
    }
}
