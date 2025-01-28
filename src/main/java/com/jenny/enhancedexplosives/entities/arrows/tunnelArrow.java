package com.jenny.enhancedexplosives.entities.arrows;

import com.jenny.enhancedexplosives.config.ConfigClient;
import com.jenny.enhancedexplosives.entities.entities;
import com.jenny.enhancedexplosives.particles.particles;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class tunnelArrow extends baseArrow{
    protected static int explosionCount = 12;
    protected static int spacing = 2;
    protected static float power = 8;

    public tunnelArrow(EntityType<tunnelArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public tunnelArrow(Level pLevel, LivingEntity pShooter) {
        super(pLevel, pShooter, entities.ARROW_TUNNEL.get());
    }

    @Override
    public void tick() {
        super.tick();
        if (inGround) {
            explode();
            discard();
        }
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity pTarget) {
        explode();
        discard();
    }

    protected void explode() {
        sync();
        Vec3 rot = getTargetVec( - getXRot(), - getYRot(), 0);
        for (int i = 0; i < explosionCount; i++) {
            Vec3 pos = position().add(rot.multiply(i * spacing, i * spacing, i * spacing));
            System.out.println(level().isClientSide + "|" + i + "|" + pos + "|" + getXRot() + "|" + getYRot());
            level().explode(this, pos.x, pos.y, pos.z,
                    power, Level.ExplosionInteraction.TNT);
        }
    }

    public static Vec3 getTargetVec(float xRot, float yRot, float zRot) {
        float f = -Mth.sin((float) (yRot * (Math.PI / 180F))) * Mth.cos(xRot * ((float)Math.PI / 180F));
        float f1 = -Mth.sin((xRot + zRot) * ((float)Math.PI / 180F));
        float f2 = Mth.cos(yRot * ((float)Math.PI / 180F)) * Mth.cos(xRot * ((float)Math.PI / 180F));
        return new Vec3(f, f1, f2);
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
            level().addParticle(particles.TUNNEL_ARROW_PARTICLE.get(), pos.x, pos.y, pos.z, DeltaMovement.x, DeltaMovement.y, DeltaMovement.z);
        }
    }

    public void sync() {
        if (!level().isClientSide) {
            setPos(position());
            setRot(getYRot(), getXRot());
            setDeltaMovement(getDeltaMovement());
        }
    }
}
