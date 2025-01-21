package com.jenny.compressedtnt.entities;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class StrongerPrimedTNT extends PrimedTnt {
    final float pRadius;

    public StrongerPrimedTNT (Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float pRadius, int fuseTime) {
        super(pLevel, pX, pY, pZ, pOwner);
        this.pRadius = pRadius;
        this.setFuse(fuseTime);
    }

    @Override
    protected void explode() {
        float $$0 = pRadius;
        this.level().explode(this, this.getX(), this.getY((double)0.0625F), this.getZ(), pRadius, Level.ExplosionInteraction.TNT);
    }
}
