package com.jenny.enhancedexplosives.items;

import com.jenny.enhancedexplosives.entities.entities;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

public class items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> TNT_ARROW = ITEMS.register("arrow_tnt", () -> new ArrowTNT(new Item.Properties()));
    public static final RegistryObject<Item> CONCUSSIVE_ARROW = ITEMS.register("arrow_concussive", () -> new ArrowConcussive(new Item.Properties()));
    public static final RegistryObject<Item> CARPET_ARROW = ITEMS.register("arrow_carpet", () -> new ArrowCarpet(new Item.Properties()));
    public static final RegistryObject<Item> TUNNEL_ARROW =  ITEMS.register("arrow_tunnel", () -> new ArrowTunnel(new Item.Properties()));
    public static final RegistryObject<Item> DYNAMITE = ITEMS.register("dynamite", () -> new Dynamite(new Item.Properties()));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

    public static void registerDispenser() {
        class ArrowDispenseBehaviour extends DefaultDispenseItemBehavior {
            private final ArrowAbstract.DispenseConfig dispenseConfig;
            private final EntityType<? extends Entity> arrowType;

            public ArrowDispenseBehaviour(@NotNull ProjectileItem arrowItem, EntityType<? extends Entity> arrow) {
                this.dispenseConfig = arrowItem.createDispenseConfig();
                this.arrowType = arrow;
            }

            @Override
            public @NotNull ItemStack execute(@NotNull BlockSource pBlockSource, @NotNull ItemStack pItem) {
                Level level = pBlockSource.level();
                Direction dir = pBlockSource.state().getValue(DispenserBlock.FACING);
                Position pos = this.dispenseConfig.positionFunction().getDispensePosition(pBlockSource, dir);

                Entity arrowEntity = arrowType.create(level);
                assert arrowEntity != null;
                arrowEntity.setPos(new Vec3(pos.x(), pos.y(), pos.z()));
                arrowEntity.setDeltaMovement(dir.getStepX(), dir.getStepY(), dir.getStepZ());
                level.addFreshEntity(arrowEntity);
                pItem.shrink(1);
                return pItem;
            }
        }

        DispenserBlock.registerBehavior(TNT_ARROW.get(), new ArrowDispenseBehaviour(
                (ArrowAbstract) TNT_ARROW.get(), entities.ARROW_TNT.get()));
        DispenserBlock.registerBehavior(CONCUSSIVE_ARROW.get(), new ArrowDispenseBehaviour(
                (ArrowAbstract) TNT_ARROW.get(), entities.ARROW_CONCUSSIVE.get()));
        DispenserBlock.registerBehavior(CARPET_ARROW.get(), new ArrowDispenseBehaviour(
                (ArrowAbstract) CARPET_ARROW.get(), entities.ARROW_CARPET.get()));
        DispenserBlock.registerBehavior(TUNNEL_ARROW.get(), new ArrowDispenseBehaviour(
                (ArrowAbstract) TUNNEL_ARROW.get(), entities.ARROW_TUNNEL.get()));
    }
}
