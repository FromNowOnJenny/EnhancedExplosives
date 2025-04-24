package com.jenny.enhancedexplosives.entities.tnt;

import com.jenny.enhancedexplosives.blocks.blocks;
import com.jenny.enhancedexplosives.entities.entities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class enderPrimedTNT extends basePrimedTNT {

    public enderPrimedTNT(Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float power, int fuse) {
        super(entities.TNT_ENDER.get(), pLevel, pOwner, new Vec3(pX, pY, pZ), fuse, power);
    }

    public enderPrimedTNT(EntityType<enderPrimedTNT> entityType, Level level) {
        super(entityType, level);
    }

    public Block renderBlock() {
        return blocks.TNT_ENDER.get();
    }
}
