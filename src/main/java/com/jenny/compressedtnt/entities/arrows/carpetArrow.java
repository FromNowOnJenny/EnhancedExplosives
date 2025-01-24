package com.jenny.compressedtnt.entities.arrows;

import com.jenny.compressedtnt.entities.entities;
import com.jenny.compressedtnt.items.items;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class carpetArrow extends baseArrow {
    private int tick = 0;
    private Vec3 pos = position();
    public final int childCount = 32;

    public carpetArrow(EntityType<carpetArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public carpetArrow(Level pLevel, LivingEntity pShooter) {
        super(pLevel, pShooter, entities.ARROW_CARPET.get());
    }

    @Override
    public void tick() {
        pos = position();
        super.tick();
        if (level().isClientSide()) {
            level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
        tick++;
        if (pos.y > position().y) {
            spawnChildren(childCount);
            discard();
        }
    }

    protected void spawnChildren(int count) {
        Vec3 delta = getDeltaMovement();
        RandomSource rng = level().getRandom();
        for (int i = 0; i < count; i++) {
            concussiveArrow arrow = new concussiveArrow(entities.ARROW_CONCUSSIVE.get(), level());
            Vec3 move = delta.add((float) rng.nextInt(-10, 11) / 10, 0, (float) rng.nextInt(-10, 11) / 10);
            arrow.setPos(position());
            arrow.setDeltaMovement(delta.add(move.multiply(0.2, 0, 0.2)));
            level().addFreshEntity(arrow);
        }
    }

    @NotNull
    protected ItemStack getPickupItem() {
        return new ItemStack(items.CARPET_ARROW.get());
    }
}
