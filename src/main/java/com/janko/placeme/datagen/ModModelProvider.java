package com.janko.placeme.datagen;

import com.janko.placeme.block.ModBlocks;
import com.janko.placeme.block.custom.AppleBlock;
import com.janko.placeme.block.custom.EnchantedGoldenAppleBlock;
import com.janko.placeme.block.custom.GoldenAppleBlock;
import com.janko.placeme.block.custom.PumpkinPieBlock;
import com.janko.placeme.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.BlockState;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(ModBlocks.PUMPKIN_PIE)
                        .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, PumpkinPieBlock.BITES)
                                .register((direction, bites) ->
                                        createVariant("pumpkin_pie", bites, direction)
                                )
                        )
        );

        blockStateModelGenerator.registerSimpleState(ModBlocks.BOWL);
        blockStateModelGenerator.registerSimpleState(ModBlocks.BEETROOT_SOUP_BOWL);
        blockStateModelGenerator.registerSimpleState(ModBlocks.MUSHROOM_STEW_BOWL);
        blockStateModelGenerator.registerSimpleState(ModBlocks.RABBIT_STEW_BOWL);
        blockStateModelGenerator.registerSimpleState(ModBlocks.SUSPICIOUS_STEW_BOWL);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.RAW_CHICKEN);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.COOKED_CHICKEN);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.LAVA_CHICKEN);

        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(ModBlocks.APPLE)
                        .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, AppleBlock.APPLES)
                                .register((direction, apples) ->
                                        createVariant("apple", apples - 1, direction)
                                )
                        )
        );
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(ModBlocks.GOLDEN_APPLE)
                        .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, GoldenAppleBlock.APPLES)
                                .register((direction, apples) ->
                                        createVariant("golden_apple", apples - 1, direction)
                                )
                        )
        );
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(ModBlocks.ENCHANTED_GOLDEN_APPLE)
                        .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, EnchantedGoldenAppleBlock.APPLES)
                                .register((direction, apples) ->
                                        createVariant("enchanted_golden_apple", apples - 1, direction)
                                )
                        )
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.LAVA_CHICKEN, Models.GENERATED);
    }

    private BlockStateVariant createVariant(String modelBaseName, int variant, Direction direction) {
        VariantSettings.Rotation rotation = switch (direction) {
            case EAST -> VariantSettings.Rotation.R90;
            case SOUTH -> VariantSettings.Rotation.R180;
            case WEST -> VariantSettings.Rotation.R270;
            default -> VariantSettings.Rotation.R0;
        };
        return BlockStateVariant.create()
                .put(VariantSettings.MODEL, Identifier.of("placeme", "block/" + modelBaseName + "_" + variant))
                .put(VariantSettings.Y, rotation);
    }
}
