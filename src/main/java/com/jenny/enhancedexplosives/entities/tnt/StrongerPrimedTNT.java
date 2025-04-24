package com.jenny.enhancedexplosives.entities.tnt;

import com.jenny.enhancedexplosives.blocks.blocks;
import com.jenny.enhancedexplosives.entities.entities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class StrongerPrimedTNT extends basePrimedTNT {
    public StrongerPrimedTNT (Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float power, int fuse) {
        super(entities.TNT_STRONGER.get(), pLevel, pOwner, new Vec3(pX, pY, pZ), fuse, power);
    }

    public StrongerPrimedTNT(EntityType<StrongerPrimedTNT> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Block renderBlock() {
        return switch ((int) this.getPower()) {
            case 8 -> blocks.TNT_8.get();
            case 16 -> blocks.TNT_16.get();
            case 32 -> blocks.TNT_32.get();
            case 64 -> blocks.TNT_64.get();
            case 128 -> blocks.TNT_128.get();
            default -> Blocks.END_GATEWAY;
        };
    }
 }
