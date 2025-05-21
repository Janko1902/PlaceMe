package com.janko.placeme.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent LAVA_CHICKEN = new FoodComponent.Builder().nutrition(10).saturationModifier(12.0f).build();
}
