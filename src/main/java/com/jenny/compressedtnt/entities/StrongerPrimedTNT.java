package com.jenny.compressedtnt.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class StrongerPrimedTNT extends basePrimedTNT {
    public StrongerPrimedTNT (Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float power, int fuse) {
        super(entities.TNT_STRONGER.get(), pLevel, pOwner);
        this.setPos(pX, pY, pZ);
        this.setFuse(fuse);
        this.setPower(power);
    }

    public StrongerPrimedTNT(EntityType<StrongerPrimedTNT> entityType, Level level) {
        super(entityType, level, null);
    }
}
