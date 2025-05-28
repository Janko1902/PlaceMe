package com.janko.placeme.block.entity;

import com.janko.placeme.PlaceMe;
import com.janko.placeme.block.ModBlocks;
import com.janko.placeme.block.entity.custom.SuspiciousStewBowlBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {

    public static final BlockEntityType<SuspiciousStewBowlBlockEntity> SUSPICIOUS_STEW_BOWL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, PlaceMe.id("be_suspicious_stew"),
                    BlockEntityType.Builder.create(SuspiciousStewBowlBlockEntity::new,
                            ModBlocks.SUSPICIOUS_STEW_BOWL
                    ).build());

    public static void registerModBlockEntities() {
        PlaceMe.LOGGER.info("Registering Block Entities for " + PlaceMe.MOD_ID);
    }
}