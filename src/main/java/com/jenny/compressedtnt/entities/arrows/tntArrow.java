package com.jenny.compressedtnt.entities.arrows;

import com.jenny.compressedtnt.items.items;
import com.jenny.compressedtnt.entities.entities;

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
        if (this.inGround) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 2, Level.ExplosionInteraction.TNT);
            this.discard();
        }
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity pTarget) {
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 2, Level.ExplosionInteraction.TNT);
    }

    @NotNull
    protected ItemStack getPickupItem() {
        return new ItemStack(items.TNT_ARROW.get());
    }
}
