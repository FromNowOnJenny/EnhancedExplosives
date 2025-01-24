package com.jenny.compressedtnt.entities.tnt;

import com.jenny.compressedtnt.entities.entities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class claymorePrimedTNT extends basePrimedTNT {
    private static final EntityDataAccessor<Integer> DATA_PCOUNT_ID = SynchedEntityData.defineId(claymorePrimedTNT.class, EntityDataSerializers.INT);

    public claymorePrimedTNT(Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float power, int fuse, int projectileCount) {
        super(entities.TNT_CLAYMORE.get(), pLevel, pOwner, new Vec3(pX, pY, pZ), fuse, power, "tnt_claymore");
        setPCount(projectileCount);
    }

    public claymorePrimedTNT(EntityType<claymorePrimedTNT> entityType, Level level) {
        super(entityType, level, null);
        setRenderID("tnt_claymore");
    }

    public Vec3 targetVector(RandomSource rng) {
        return new Vec3(
                (double) rng.nextInt(-10, 11) / 10,
                (double) rng.nextInt( 11) / 10,
                (double) rng.nextInt(-10, 11) / 10);
    }

    @Override
    public void explode() {
        super.explode();
        RandomSource rng = level().getRandom();
        for (int i = 0; i < getPCount(); i++) {
            Vec3 target = targetVector(rng);
            Vec3 pos = position().add(target);
            Projectile e = new Arrow(level(),pos.x, pos.y + 1, pos.z);
            e.setDeltaMovement(target.multiply(5, 0.1, 5));
            level().addFreshEntity(e);
        }

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putFloat("ProjectileCount", this.getPCount());
        super.addAdditionalSaveData(pCompound);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.setPCount(pCompound.getInt("ProjectileCount"));
        super.readAdditionalSaveData(pCompound);
    }

    public void setPCount(int pCount) {
        this.entityData.set(DATA_PCOUNT_ID, pCount);
    }

    public int getPCount() {
        return this.entityData.get(DATA_PCOUNT_ID);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_PCOUNT_ID, 16);
        super.defineSynchedData();
    }
}
