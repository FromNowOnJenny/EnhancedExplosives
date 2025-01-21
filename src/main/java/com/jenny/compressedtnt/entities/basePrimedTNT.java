package com.jenny.compressedtnt.entities;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public abstract class basePrimedTNT extends Entity implements TraceableEntity {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(basePrimedTNT.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DATA_POWER_ID = SynchedEntityData.defineId(basePrimedTNT.class, EntityDataSerializers.FLOAT);

    @Nullable
    private LivingEntity owner;

    public basePrimedTNT(EntityType<? extends basePrimedTNT> pEntityType, Level pLevel, @Nullable LivingEntity owner) {
        super(pEntityType, pLevel);
        double d0 = pLevel.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.blocksBuilding = true;
        this.owner = owner;
    }

    protected void explode() {
        this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), this.getPower(), Level.ExplosionInteraction.TNT);
    }

        public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    public void setFuse(int fuse) {
        this.entityData.set(DATA_FUSE_ID, fuse);
    }

    public float getPower() {
        return this.entityData.get(DATA_POWER_ID);
    }

    public void setPower(float power) {
        this.entityData.set(DATA_POWER_ID, power);
    }

    public void tick() {
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.level().isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level().isClientSide) {
                this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public boolean isPickable() {
        return !this.isRemoved();
    }

    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }


    protected void defineSynchedData() {
        this.entityData.define(DATA_FUSE_ID, 80);
        this.entityData.define(DATA_POWER_ID, 4.0f);
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putShort("Fuse", (short)this.getFuse());
        pCompound.putFloat("Power", (short)this.getPower());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.setFuse(pCompound.getShort("Fuse"));
        this.setPower(pCompound.getFloat("Power"));
    }

    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    public void setOwner(LivingEntity owner) {
        this.owner = owner;
    }
}
