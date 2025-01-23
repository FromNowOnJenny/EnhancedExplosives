package com.jenny.compressedtnt.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class ClusterPrimedTNT extends basePrimedTNT {

    public ClusterPrimedTNT (Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float power, int fuse, Vec3 move) {
        super(entities.TNT_CLUSTER.get(), pLevel, pOwner, new Vec3(pX, pY, pZ), fuse, power, "cluster");
        this.addDeltaMovement(move);
    }

    public ClusterPrimedTNT(EntityType<ClusterPrimedTNT> entityType, Level level) {
        super(entityType, level, null);
        this.setRenderID("cluster");
    }
}
