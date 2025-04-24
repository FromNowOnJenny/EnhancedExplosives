package com.jenny.enhancedexplosives.entities.arrows;

import com.jenny.enhancedexplosives.config.ConfigClient;
import com.jenny.enhancedexplosives.entities.entities;
import com.jenny.enhancedexplosives.items.items;
import com.jenny.enhancedexplosives.particles.particles;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
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
        if (this.inGround) {
            this.level().explode(this, getX(), getY(), getZ(), 8.0f, Explosion.BlockInteraction.NONE);
            this.discard();
        }
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity pTarget) {
        this.level().explode(this, getX(), getY(), getZ(), 8.0f, Explosion.BlockInteraction.NONE);
        this.discard();
    }

    @NotNull
    protected ItemStack getPickupItem() {
        return new ItemStack(items.CONCUSSIVE_ARROW.get());
    }

    @Override
    public void spawnParticles(float partialTicks) {
        for (int i = 1; i <= ConfigClient.calcPCount(5); i++) {
            Vec3 delta = getDeltaMovement();
            Vec3 pos = particlePos(0.5);
            level().addParticle(particles.CONCUSSIVE_ARROW_PARTICLE.get(), pos.x, pos.y, pos.z, delta.x, delta.y, delta.z);
        }
    }
}
