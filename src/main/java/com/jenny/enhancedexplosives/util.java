package com.jenny.enhancedexplosives;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class util {
    public static void addDeltaMovement(Entity entity, Vec3 movement) {
        entity.setDeltaMovement(movement.add(entity.getDeltaMovement()));
    }
}
