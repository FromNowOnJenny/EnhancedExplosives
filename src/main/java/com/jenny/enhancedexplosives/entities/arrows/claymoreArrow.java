package com.jenny.enhancedexplosives.entities.arrows;

import com.jenny.enhancedexplosives.entities.entities;
import com.jenny.enhancedexplosives.items.items;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

public class claymoreArrow extends baseArrow{
    public claymoreArrow(EntityType<claymoreArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public claymoreArrow(@NotNull  Level pLevel, @NotNull LivingEntity pShooter) {
        super(pLevel, pShooter, entities.ARROW_CLAYMORE.get());
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity pTarget) {
        pTarget.hurt(damageSources().mobProjectile(this, (LivingEntity) getOwner()), 15);
    }

    @Override
    @NotNull
    protected ItemStack getPickupItem() {
        return new ItemStack(items.CONCUSSIVE_ARROW.get(), 0);
    }
}
