package com.jenny.enhancedexplosives.entities.tnt;

import com.jenny.enhancedexplosives.entities.entities;
import com.jenny.enhancedexplosives.config.ConfigClient;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class homingPrimedTNT extends basePrimedTNT {
    private static final EntityDataAccessor<Float> DATA_SPEED_ID = SynchedEntityData.defineId(homingPrimedTNT.class, EntityDataSerializers.FLOAT);
    Entity target;

    public homingPrimedTNT (Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float power, int fuse, float speed) {
        super(entities.TNT_HOMING.get(), pLevel, pOwner, new Vec3(pX, pY, pZ), fuse, power);
        this.target = null;
        this.setSpeed(speed);
    }

    public homingPrimedTNT(EntityType<homingPrimedTNT> entityType, Level level) {
        super(entityType, level, null);
    }

    private Vec3 targetVector() {
        double targetDist = getTargetDist();
        Vec3 targetVec = new Vec3(0, 0, 0);
        if (targetDist > 3) {
            float speed = getSpeed();
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

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putFloat("Speed", this.getSpeed());
        super.addAdditionalSaveData(pCompound);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.setSpeed(pCompound.getFloat("Speed"));
        super.readAdditionalSaveData(pCompound);
    }

    public void setSpeed(float speed) {
        this.entityData.set(DATA_SPEED_ID, speed);
    }

    public float getSpeed() {
        return this.entityData.get(DATA_SPEED_ID);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_SPEED_ID, 4.0f);
        super.defineSynchedData();
    }

    @Override
    public void spawnParticles(float partialTicks) {
        for (int i = 1; i <= ConfigClient.calcPCount(30); i++) {
            level().addParticle(ParticleTypes.FALLING_OBSIDIAN_TEAR, getX(), getY(), getZ(), this.getDeltaMovement().x, this.getDeltaMovement().y, this.getDeltaMovement().z);
        }
    }
}
