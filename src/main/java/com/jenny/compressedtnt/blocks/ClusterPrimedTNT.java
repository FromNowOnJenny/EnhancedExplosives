package com.jenny.compressedtnt.blocks;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class ClusterPrimedTNT extends PrimedTnt {
    final float pRadius;

    public ClusterPrimedTNT (Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float pRadius, int fuseTime, Vec3 move) {
        super(pLevel, pX, pY, pZ, pOwner);
        this.pRadius = pRadius;
        this.setFuse(fuseTime);
        this.addDeltaMovement(move);
    }

    @Override
    protected void explode() {
        float $$0 = pRadius;
        this.level().explode(this, this.getX(), this.getY((double)0.0625F), this.getZ(), pRadius, Level.ExplosionInteraction.TNT);
    }
}
