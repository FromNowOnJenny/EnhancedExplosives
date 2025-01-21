package com.jenny.compressedtnt.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class StrongerPrimedTNT extends basePrimedTNT {
    public StrongerPrimedTNT (Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float power, int fuse) {
        super(entities.TNT_STRONGER.get(), pLevel, pOwner);
        this.setPos(pX, pY, pZ);
        this.setFuse(fuse);
        this.setPower(power);
        this.setRenderID(evalRenderID());
    }

    public StrongerPrimedTNT(EntityType<StrongerPrimedTNT> entityType, Level level) {
        super(entityType, level, null);
        this.setRenderID(evalRenderID());
    }

    public String evalRenderID() {
        int a = (int) this.getPower();
        return switch ((int) this.getPower()) {
            case 8 -> "stronger_8";
            case 16 -> "stronger_16";
            case 32 -> "stronger_32";
            case 64 -> "stronger_64";
            case 128 -> "stronger_128";
            default -> "default";
        };
    }
 }
