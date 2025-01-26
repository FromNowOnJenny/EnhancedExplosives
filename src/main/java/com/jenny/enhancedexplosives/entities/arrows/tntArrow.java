package com.jenny.enhancedexplosives.entities.arrows;

import com.jenny.enhancedexplosives.config.ConfigClient;
import com.jenny.enhancedexplosives.items.items;
import com.jenny.enhancedexplosives.entities.entities;

import com.jenny.enhancedexplosives.particles.particles;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class tntArrow extends baseArrow {
    public tntArrow(EntityType<tntArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public tntArrow(Level pLevel, LivingEntity pShooter) {
        super(pLevel, pShooter, entities.ARROW_TNT.get());
    }

    @Override
    public void tick() {
        super.tick();
        if (this.inGround) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 2, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity pTarget) {
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 2, Level.ExplosionInteraction.TNT);
        this.discard();
    }

    @NotNull
    protected ItemStack getPickupItem() {
        return new ItemStack(items.TNT_ARROW.get());
    }

    @Override
    public void spawnParticles(float partialTicks) {
        for (int i = 1; i <= ConfigClient.calcPCount(3); i++) {
            double m = (double) level().getRandom().nextIntBetweenInclusive(- 100, 100) / 100;
            Vec3 DeltaMovement = getDeltaMovement();
            Vec3 pos = new Vec3(
                    (double) level().getRandom().nextIntBetweenInclusive(-5, 5) / 10,
                    0,
                    (double) level().getRandom().nextIntBetweenInclusive(-5, 5) / 10
            ).normalize().multiply(m, m, m).add(getPosition(partialTicks));
            level().addParticle(particles.TNT_ARROW_PARTICLE.get(), pos.x, pos.y, pos.z, DeltaMovement.x, DeltaMovement.y, DeltaMovement.z);
        }
    }
}
