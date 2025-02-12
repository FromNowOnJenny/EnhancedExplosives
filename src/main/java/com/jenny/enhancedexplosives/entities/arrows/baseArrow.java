package com.jenny.enhancedexplosives.entities.arrows;

import com.jenny.enhancedexplosives.config.ConfigClient;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class baseArrow extends AbstractArrow {
    private int tick = 0;

    public baseArrow(EntityType<? extends baseArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, ItemStack.EMPTY);
    }

    public baseArrow(Level pLevel, LivingEntity pShooter, EntityType<? extends baseArrow> pEntityType) {
        super(pEntityType, pShooter, pLevel, ItemStack.EMPTY);
    }

    public baseArrow(Level pLevel, EntityType<? extends baseArrow> pEntityType) {
        super(pEntityType, pLevel, ItemStack.EMPTY);
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            if (!this.inGround && ConfigClient.arrowParticles.get()) {
                spawnParticles(0);
            }
            this.tick++;
        }
    }

    @NotNull
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    protected Vec3 particlePos(double dist) {
        Double speed = getDeltaMovement().length();
        return new Vec3(
                level().getRandom().nextIntBetweenInclusive(-100, 100),
                level().getRandom().nextIntBetweenInclusive(-100, 100),
                level().getRandom().nextIntBetweenInclusive(-100, 100)
        ).normalize().scale(dist + ((double) level().getRandom().nextIntBetweenInclusive(0, 100) / 100)).add(position());
    }

    public void spawnParticles(float partialTicks) {
    }

    public int getTick() {
        return this.tick;
    }
}
