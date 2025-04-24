package com.jenny.enhancedexplosives.entities.tnt;

import com.jenny.enhancedexplosives.config.ConfigClient;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class basePrimedTNT extends Entity {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(basePrimedTNT.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DATA_POWER_ID = SynchedEntityData.defineId(basePrimedTNT.class, EntityDataSerializers.FLOAT);

    @Nullable
    private LivingEntity owner;
    private int fuse = 0;

    public basePrimedTNT(EntityType<? extends basePrimedTNT> pEntityType, @NotNull Level pLevel) {
        super(pEntityType, pLevel);
        this.blocksBuilding = true;
    }

    public basePrimedTNT(EntityType<? extends basePrimedTNT> pEntityType, @NotNull Level pLevel, @Nullable LivingEntity owner, Vec3 pos, int fuse, float power) {
        this(pEntityType, pLevel);
        setPos(pos);
        double d0 = pLevel.random.nextDouble() * (double) ((float) Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double) 0.2F, -Math.cos(d0) * 0.02D);
        this.setOwner(owner);
        this.xo = pos.x;
        this.yo = pos.y;
        this.zo = pos.z;
        setFuse(fuse);
        setPower(power);
    }

    protected void explode() {
        this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), this.getPower(), Explosion.BlockInteraction.BREAK);
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
        if (!level().isClientSide) {
            System.out.println("Server: " + tickCount + "|x=" + (getX() - 0.5F) + "|y=" + getY() + "|z=" + (getZ() - 0.5F));
        } else {
            System.out.println("Client: " + tickCount + "|x=" + (getX() - 0.5F) + "|y=" + getY() + "|z=" + (getZ() - 0.5F));
        }


        if (level().isClientSide) {
            if (ConfigClient.tntParticles.get()) {
                spawnParticles();
            }
        }
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
        if (this.onGround) {
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
        }
    }

    public boolean isPickable() {
        return !this.isRemoved();
    }

    @NotNull
    protected MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
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

    public void setOwner(@Nullable LivingEntity owner) {
        this.owner = owner;
    }

    protected float getEyeHeight(@NotNull Pose pPose, @NotNull EntityDimensions pSize) {
        return 0.15F;
    }

    public Block renderBlock() {
        return Blocks.GLASS;
    }

    public int defaultFuse() {
        return this.fuse;
    }

    public void spawnParticles() {
        level().addParticle(ParticleTypes.SMOKE, getX(), getY(), getZ(), 0, 0, 0);
    }

    public Level level() {
        return level;
    }

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
