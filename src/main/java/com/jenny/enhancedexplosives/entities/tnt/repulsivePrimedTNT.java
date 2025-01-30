package com.jenny.enhancedexplosives.entities.tnt;

import com.jenny.enhancedexplosives.blocks.blocks;
import com.jenny.enhancedexplosives.entities.entities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class repulsivePrimedTNT extends basePrimedTNT {
    private static final EntityDataAccessor<Float> DATA_SPEED_ID = SynchedEntityData.defineId(repulsivePrimedTNT.class, EntityDataSerializers.FLOAT);

    public repulsivePrimedTNT(Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float power, int fuse, float speed) {
        super(entities.TNT_REPULSIVE.get(), pLevel, pOwner, new Vec3(pX, pY, pZ), fuse, power);
        this.setSpeed(speed);
    }

    public repulsivePrimedTNT(EntityType<repulsivePrimedTNT> entityType, Level level) {
        super(entityType, level, null);
        this.setSpeed(this.getSpeed());
    }

    private Vec3 targetVector(Entity target) {
        double dist = getTargetDist(target);
        float speed = getSpeed();
        Vec3 mult = new Vec3(speed / dist, speed / dist, speed / dist);
        Vec3 ret = target.position().subtract(this.position()).normalize().multiply(mult);
        return ret;
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
    public void explode() {
        for (Entity e : findTargets()) {
            e.addDeltaMovement(targetVector(e).scale(3));
        }
        super.explode();
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
    public Block renderBlock() {
        return blocks.TNT_REPULSIVE.get();
    }
}
