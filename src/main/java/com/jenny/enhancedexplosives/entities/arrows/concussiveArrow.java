package com.jenny.enhancedexplosives.entities.arrows;

import com.jenny.enhancedexplosives.entities.entities;
import com.jenny.enhancedexplosives.items.items;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class concussiveArrow extends baseArrow{
    public concussiveArrow(EntityType<concussiveArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public concussiveArrow(Level pLevel, LivingEntity pShooter) {
        super(pLevel, pShooter, entities.ARROW_CONCUSSIVE.get());
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide()) {
            level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
        if (this.inGround) {
            this.level().explode(this, getX(), getY(), getZ(), 8.0f, Level.ExplosionInteraction.NONE);
            //this.level().explode(this, null, new NilExplosionCalculator(), this.getX(), this.getY(), this.getZ(), 8, false, Level.ExplosionInteraction.NONE);
            this.discard();
        }
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity pTarget) {
        this.level().explode(this, getX(), getY(), getZ(), 8.0f, Level.ExplosionInteraction.NONE);
        //this.level().explode(this, null, new NilExplosionCalculator(), this.getX(), this.getY(), this.getZ(), 8, false, Level.ExplosionInteraction.NONE);
        this.discard();
    }

    @NotNull
    protected ItemStack getPickupItem() {
        return new ItemStack(items.CONCUSSIVE_ARROW.get());
    }

    @Override
    public void spawnParticles() {
        for (int i = 0; i < 2; i++) {
            double x = getX() + (double) level().getRandom().nextInt(-10, 11) / 10;
            double y = getY() + (double) level().getRandom().nextInt(-10, 11) / 10;
            double z = getZ() + (double) level().getRandom().nextInt(-10, 11) / 10;
            level().addParticle(ParticleTypes.WAX_ON, x, y, z, this.getDeltaMovement().x, this.getDeltaMovement().y, this.getDeltaMovement().z);
        }
    }
}
