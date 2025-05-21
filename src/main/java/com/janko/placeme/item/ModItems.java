package com.janko.placeme.item;

import com.janko.placeme.PlaceMe;
import com.janko.placeme.block.ModBlocks;
import com.janko.placeme.item.custom.LavaChickenBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static Item LAVA_CHICKEN = registerItem("lava_chicken",
            new LavaChickenBlockItem(ModBlocks.LAVA_CHICKEN, new Item.Settings().food(ModFoodComponents.LAVA_CHICKEN)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(PlaceMe.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PlaceMe.LOGGER.info("Registering Mod Items for " + PlaceMe.MOD_ID);
    }
}