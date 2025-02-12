package com.jenny.enhancedexplosives.entities.arrows;

import com.jenny.enhancedexplosives.config.ConfigClient;
import com.jenny.enhancedexplosives.entities.entities;
import com.jenny.enhancedexplosives.particles.particles;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class carpetArrow extends baseArrow {
    public final int childCount = 32;

    public carpetArrow(EntityType<carpetArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public carpetArrow(Level pLevel, LivingEntity pShooter) {
        super(pLevel, pShooter, entities.ARROW_CARPET.get());
    }

    @Override
    public void tick() {
        super.tick();
        if (getDeltaMovement().y < 0) {
            spawnChildren(childCount);
            discard();
        }
    }

    protected void spawnChildren(int count) {
        RandomSource rng = level().getRandom();
        for (int i = 0; i < count; i++) {
            carpetArrowPart arrow = new carpetArrowPart(entities.ARROW_CARPET_PART.get(), level());
            double r = (double) rng.nextIntBetweenInclusive(-100, 100) / 200;
            Vec3 move = new Vec3(
                    (double) rng.nextIntBetweenInclusive(-100, 100) / 100,
                    0,
                    (double) rng.nextIntBetweenInclusive(-100, 100) / 100).normalize().multiply(r, 0, r);
            arrow.setPos(position());
            arrow.setDeltaMovement(move.add(getDeltaMovement()));
            level().addFreshEntity(arrow);
        }
    }

    @Override
    public void spawnParticles(float partialTicks) {
        for (int i = 1; i <= ConfigClient.calcPCount(5); i++) {
            double m = (double) level().getRandom().nextIntBetweenInclusive(-100, 100) / 100;
            Vec3 DeltaMovement = getDeltaMovement();
            Vec3 pos = particlePos(0.5);
            level().addParticle(particles.CARPET_ARROW_PARTICLE.get(), pos.x, pos.y, pos.z, DeltaMovement.x, DeltaMovement.y, DeltaMovement.z);
        }
    }
}
