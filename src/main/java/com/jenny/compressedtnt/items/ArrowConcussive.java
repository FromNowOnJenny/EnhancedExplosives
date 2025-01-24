package com.jenny.compressedtnt.items;

import com.jenny.compressedtnt.entities.arrows.concussiveArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ArrowConcussive extends ArrowItem {
    public ArrowConcussive(Item.Properties properties){
        super(properties);
    }

    @Override
    @NotNull
    public AbstractArrow createArrow(@NotNull Level pLevel, @NotNull ItemStack pStack, @NotNull LivingEntity pShooter) {
        return new concussiveArrow(pLevel, pShooter);
    }
}
