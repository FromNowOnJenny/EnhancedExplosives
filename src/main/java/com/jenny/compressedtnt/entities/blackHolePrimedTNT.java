package com.jenny.compressedtnt.entities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class blackHolePrimedTNT extends basePrimedTNT {
    private static final EntityDataAccessor<Float> DATA_SPEED_ID = SynchedEntityData.defineId(blackHolePrimedTNT.class, EntityDataSerializers.FLOAT);

    public blackHolePrimedTNT(Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float power, int fuse, float speed) {
        super(entities.TNT_BLACK_HOLE.get(), pLevel, pOwner);
        this.setRenderID("black_hole");
        this.setPos(pX, pY, pZ);
        this.setOwner(pOwner);
        this.setSpeed(speed);
        this.setPower(power);
        this.setFuse(fuse);
    }

    public blackHolePrimedTNT(EntityType<blackHolePrimedTNT> entityType, Level level) {
        super(entityType, level, null);
        this.setRenderID("black_hole");
    }

    private Vec3 targetVector(Entity target) {
        double dist = getTargetDist(target);
        float speed = getSpeed();
        Vec3 mult = new Vec3(speed / dist, speed / dist, speed / dist);
        Vec3 ret = this.position().subtract(target.position()).normalize().multiply(mult);
        if (dist < this.position().subtract(target.position().add(ret)).length()) {
            // reduce overshoot
            return Vec3.ZERO;
        }
        else {
            return ret;
        }
    }

    public double getTargetDist(Entity target) {
        return target.position().subtract(this.position()).length();
    }

    public List<Entity> findTargets() {
        Vec3 corner1 = this.position().subtract(15, 15, 15);
        Vec3 corner2 = this.position().add(15, 15, 15);
        AABB boundingBox = new AABB(corner1, corner2);
        return this.level().getEntities(this, boundingBox);

    }

    @Override
    public void tick() {
        for (Entity e : findTargets()) {
            e.addDeltaMovement(targetVector(e));
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

    protected float getEyeHeight(@NotNull Pose pPose, @NotNull EntityDimensions pSize) {
        return 0.15F;
    }
}
