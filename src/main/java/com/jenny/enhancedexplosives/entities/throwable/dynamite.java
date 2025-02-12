package com.jenny.enhancedexplosives.entities.throwable;

import com.jenny.enhancedexplosives.entities.entities;
import com.jenny.enhancedexplosives.entities.tnt.basePrimedTNT;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class dynamite extends basePrimedTNT {
    public dynamite (Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float power, int fuse) {
        super(entities.DYNAMITE.get(), pLevel, pOwner, new Vec3(pX, pY, pZ), fuse, power);
    }

    public dynamite(EntityType<dynamite> entityType, Level level) {
        super(entityType, level, null);
    }

    @Override
    public void tick() {
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }
        Vec3 beforeMove = getDeltaMovement();
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, 0, 0.7D).add(0, (-beforeMove.y) * 0.5, 0));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.level().isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level().isClientSide) {
                spawnParticles();
            }
        }
    }

    public void shootFromRotation(Entity pShooter, float pX, float pY, float pZ, float pVelocity) {
        float f = -Mth.sin(pY * ((float)Math.PI / 180F)) * Mth.cos(pX * ((float)Math.PI / 180F));
        float f1 = -Mth.sin((pX + pZ) * ((float)Math.PI / 180F));
        float f2 = Mth.cos(pY * ((float)Math.PI / 180F)) * Mth.cos(pX * ((float)Math.PI / 180F));
        Vec3 vec = new Vec3(f, f1, f2).multiply(pVelocity, pVelocity, pVelocity);
        this.setDeltaMovement(vec);
        Vec3 vec3 = pShooter.getDeltaMovement();
        this.setDeltaMovement(this.getDeltaMovement().add(vec3.x, pShooter.onGround() ? 0.0D : vec3.y, vec3.z));
    }

    public Block renderBlock() {
        return Blocks.TNT;
    }
}
