package com.jenny.enhancedexplosives.items;

import com.jenny.enhancedexplosives.entities.arrows.tunnelArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ArrowTunnel extends ArrowAbstract {
    public ArrowTunnel(Properties properties){
        super(properties);
    }

    @Override
    @NotNull
    public AbstractArrow createArrow(@NotNull Level pLevel, @NotNull ItemStack pStack, @NotNull LivingEntity pShooter) {
        return new tunnelArrow(pLevel, pShooter);
    }
}
