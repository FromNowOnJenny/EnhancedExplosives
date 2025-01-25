package com.jenny.enhancedexplosives.entities.arrows;

import com.jenny.enhancedexplosives.items.items;
import com.jenny.enhancedexplosives.entities.entities;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
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
        if (level().isClientSide()) {
            //spawnParticles();
        }
        if (this.inGround) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 2, Level.ExplosionInteraction.TNT);
            this.discard();
        }
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

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity pTarget) {
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 2, Level.ExplosionInteraction.TNT);
        this.discard();
    }

    @NotNull
    protected ItemStack getPickupItem() {
        return new ItemStack(items.TNT_ARROW.get());
    }
}
