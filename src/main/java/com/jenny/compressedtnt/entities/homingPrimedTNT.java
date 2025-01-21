package com.jenny.compressedtnt.entities;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class homingPrimedTNT extends BasePrimedTNT {
    float pRadius = 0;
    float speed = 0;
    Entity target;

    @Nullable
    private LivingEntity owner;

    public homingPrimedTNT (Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float power, int fuse, float speed) {
        this(entities.TNT_HOMING.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.owner = pOwner;
        this.pRadius = power;
        this.speed = speed;
        this.target = null;
        this.setPower(power);
        this.setFuse(fuse);
    }

    public homingPrimedTNT(EntityType<homingPrimedTNT> entityType, Level level) {
        super(entityType, level);
    }

    private Vec3 targetVector() {
        double targetDist = getTargetDist();
        Vec3 targetVec = new Vec3(0, 0, 0);
        if (targetDist > 3) {
            targetVec = new Vec3(target.getX() - this.getX(), target.getY() - this.getY(), target.getZ() - this.getZ()).normalize().multiply(speed, speed, speed);
            if (targetDist < 10) {
                targetVec.multiply(targetDist / 10, targetDist / 10, targetDist / 10);
            }
        }
        return targetVec;
    }

    public double getTargetDist() {
        return new Vec3(target.getX(), target.getY(), target.getZ()).subtract(this.getX(), this.getY(), this.getZ()).length();
    }

    public void findTarget() {
        Vec3 corner1 = this.position().subtract(15, 15, 15);
        Vec3 corner2 = this.position().add(15, 15, 15);
        AABB boundingBox = new AABB(corner1, corner2);
        target = this.level().getNearestEntity(LivingEntity.class, TargetingConditions.forNonCombat(), null, this.getX(), this.getY(), this.getZ(), boundingBox);

    }

    @Override
    public void tick() {
        if (target == null) {
            findTarget();
        }
        else {
            if (getTargetDist() > 15) {target = null;} else {addDeltaMovement(targetVector());}
        }

        super.tick();
    }


    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    protected float getEyeHeight(@NotNull Pose pPose, @NotNull EntityDimensions pSize) {
        return 0.15F;
    }
}
