package com.jenny.enhancedexplosives.entities.tnt;

import com.jenny.enhancedexplosives.blocks.blocks;
import com.jenny.enhancedexplosives.config.ConfigClient;
import com.jenny.enhancedexplosives.config.ConfigServer;
import com.jenny.enhancedexplosives.entities.entities;
import net.minecraft.core.particles.ParticleTypes;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

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
        target = closestEntity(getEntities());
    }

    private LivingEntity closestEntity(List<LivingEntity> entities) {
        double dist = Double.MAX_VALUE;
        LivingEntity target = null;
        for (LivingEntity e : entities) {
            double newDist = e.position().distanceTo(position());
            if (newDist < dist) {
                target = e;
                dist = newDist;
            }
        }
        return target;
    }

    protected List<LivingEntity> getEntities() {
        List<LivingEntity> ret_list = new ArrayList<>();
        Vec3 corner1 = this.position().subtract(15, 15, 15);
        Vec3 corner2 = this.position().add(15, 15, 15);
        AABB boundingBox = new AABB(corner1, corner2);

        for (LivingEntity entity : level().getEntitiesOfClass(LivingEntity.class, boundingBox)) {
            if (level().getPlayerByUUID(entity.getUUID()) == null || ConfigServer.homingAtPlayers.get()) { // prevent aiming at player if config says so
                ret_list.add(entity);
            }
        }
        return ret_list;
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
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        builder.define(DATA_SPEED_ID, 4.0f);
        super.defineSynchedData(builder);
    }

    @Override
    public void spawnParticles() {
        for (int i = 1; i <= ConfigClient.calcPCount(1); i++) {
            level().addParticle(ParticleTypes.FLAME, getX(), getY(), getZ(), 0, 0, 0);
        }
    }

    @Override
    public Block renderBlock() {
        return blocks.TNT_HOMING.get();
    }
}
