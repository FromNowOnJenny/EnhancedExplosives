package com.jenny.enhancedexplosives.entities.tnt;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class basePrimedTNT extends Entity implements TraceableEntity {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(basePrimedTNT.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DATA_POWER_ID = SynchedEntityData.defineId(basePrimedTNT.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<String> DATA_RENDER_ID = SynchedEntityData.defineId(basePrimedTNT.class, EntityDataSerializers.STRING);

    @Nullable
    private LivingEntity owner;

    private String renderID = "default";

    public basePrimedTNT(EntityType<? extends basePrimedTNT> pEntityType, Level pLevel, @Nullable LivingEntity owner) {
        super(pEntityType, pLevel);
        commonInit(pLevel, owner);
    }

    private void commonInit(Level pLevel, @Nullable LivingEntity owner) {
        double d0 = pLevel.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.blocksBuilding = true;
        this.owner = owner;
    }

    public basePrimedTNT(EntityType<? extends basePrimedTNT> pEntityType, Level pLevel, @Nullable LivingEntity owner, Vec3 pos, int fuse, float power, String renderID) {
        super(pEntityType, pLevel);
        commonInit(pLevel, owner);
        setPos(pos);
        setFuse(fuse);
        setPower(power);
        setRenderID(renderID);
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

    protected Entity.@NotNull MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_FUSE_ID, 80);
        this.entityData.define(DATA_POWER_ID, 4.0f);
        this.entityData.define(DATA_RENDER_ID, "default");
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putShort("Fuse", (short)this.getFuse());
        pCompound.putFloat("Power", (short)this.getPower());
        pCompound.putString("RenderID", getRenderID());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.setFuse(pCompound.getShort("Fuse"));
        this.setPower(pCompound.getFloat("Power"));
        this.setRenderID(pCompound.getString("RenderID"));
    }

    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    public void setOwner(@Nullable LivingEntity owner) {
        this.owner = owner;
    }

    public void setRenderID(String renderID) {
        this.entityData.set(DATA_RENDER_ID, renderID);
    }

    public String getRenderID() {
        return this.entityData.get(DATA_RENDER_ID);
    }

    protected float getEyeHeight(@NotNull Pose pPose, @NotNull EntityDimensions pSize) {
        return 0.15F;
    }
}
