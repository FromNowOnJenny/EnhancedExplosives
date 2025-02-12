package com.jenny.enhancedexplosives.entities.arrows;

import com.jenny.enhancedexplosives.config.ConfigClient;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class baseArrow extends AbstractArrow {
    private int tick = 0;

    public baseArrow(EntityType<? extends baseArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public baseArrow(Level pLevel, LivingEntity pShooter, EntityType<? extends baseArrow> pEntityType) {
        super(pEntityType, pShooter, pLevel);
    }

    public baseArrow(Level pLevel, EntityType<? extends baseArrow> pEntityType) {
        super(pEntityType, pLevel);
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
        return new ItemStack(Items.ARROW);
    }

    public void spawnParticles(float partialTicks) {

    }

    public int getTick() {
        return this.tick;
    }
}
