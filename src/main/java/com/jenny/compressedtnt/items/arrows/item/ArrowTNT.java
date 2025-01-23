package com.jenny.compressedtnt.items.arrows.item;

import com.jenny.compressedtnt.items.arrows.entity.EntityArrowTNT;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ArrowTNT extends ArrowItem {
    public ArrowTNT(Item.Properties properties){
        super(properties);
    }

    @Override
    @NotNull
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        return new EntityArrowTNT(pLevel, pShooter);
    }
}
