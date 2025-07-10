package com.jenny.enhancedexplosives.blocks;

import com.jenny.enhancedexplosives.items.BlockItemTooltip;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

public class blocks {
    private static final BlockBehaviour.Properties DEFAULT_PROPS = BlockBehaviour.Properties.of().mapColor(MapColor.FIRE).instabreak().sound(SoundType.GRASS).ignitedByLava();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Block> TNT_8 = BLOCKS.register("tnt_8", () -> new strongerTNTBlock(DEFAULT_PROPS, 8.0f, 80));
    public static final RegistryObject<Item> TNT_8_ITEM = ITEMS.register("tnt_8", () -> new BlockItemTooltip(TNT_8.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_16 = BLOCKS.register("tnt_16", () -> new strongerTNTBlock(DEFAULT_PROPS, 16.0f, 80));
    public static final RegistryObject<Item> TNT_16_ITEM = ITEMS.register("tnt_16", () -> new BlockItemTooltip(TNT_16.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_32 = BLOCKS.register("tnt_32", () -> new strongerTNTBlock(DEFAULT_PROPS, 32.0f, 80));
    public static final RegistryObject<Item> TNT_32_ITEM = ITEMS.register("tnt_32", () -> new BlockItemTooltip(TNT_32.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_64 = BLOCKS.register("tnt_64", () -> new strongerTNTBlock(DEFAULT_PROPS, 64.0f, 80));
    public static final RegistryObject<Item> TNT_64_ITEM = ITEMS.register("tnt_64", () -> new BlockItemTooltip(TNT_64.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_128 = BLOCKS.register("tnt_128", () -> new strongerTNTBlock(DEFAULT_PROPS, 128.0f, 80));
    public static final RegistryObject<Item> TNT_128_ITEM = ITEMS.register("tnt_128", () -> new BlockItemTooltip(TNT_128.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_CLUSTER_2 = BLOCKS.register("tnt_cluster_2", () -> new ClusterTNTBlock(DEFAULT_PROPS, 4.0f, 80, 2, 10));
    public static final RegistryObject<Item> TNT_CLUSTER_2_ITEM = ITEMS.register("tnt_cluster_2", () -> new BlockItemTooltip(TNT_CLUSTER_2.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_CLUSTER_4 = BLOCKS.register("tnt_cluster_4", () -> new ClusterTNTBlock(DEFAULT_PROPS, 4.0f, 80, 4, 10));
    public static final RegistryObject<Item> TNT_CLUSTER_4_ITEM = ITEMS.register("tnt_cluster_4", () -> new BlockItemTooltip(TNT_CLUSTER_4.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_CLUSTER_8 = BLOCKS.register("tnt_cluster_8", () -> new ClusterTNTBlock(DEFAULT_PROPS, 4.0f, 80, 8, 10));
    public static final RegistryObject<Item> TNT_CLUSTER_8_ITEM = ITEMS.register("tnt_cluster_8", () -> new BlockItemTooltip(TNT_CLUSTER_8.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_HOMING = BLOCKS.register("tnt_homing", () -> new homingTNTBlock(DEFAULT_PROPS, 4.0f, 80, 1));
    public static final RegistryObject<Item> TNT_HOMING_ITEM = ITEMS.register("tnt_homing", () -> new BlockItemTooltip(TNT_HOMING.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_BLACK_HOLE = BLOCKS.register("tnt_black_hole", () -> new blackHoleTNTBlock(DEFAULT_PROPS, 4.0f, 80, 1));
    public static final RegistryObject<Item> TNT_BLACK_HOLE_ITEM = ITEMS.register("tnt_black_hole", () -> new BlockItemTooltip(TNT_BLACK_HOLE.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_CLAYMORE = BLOCKS.register("tnt_claymore", () -> new claymoreTNTBlock(DEFAULT_PROPS, 2.0f, 20, 256));
    public static final RegistryObject<Item> TNT_CLAYMORE_ITEM = ITEMS.register("tnt_claymore", () -> new BlockItemTooltip(TNT_CLAYMORE.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_SELECTIVE = BLOCKS.register("tnt_selective", () -> new selectiveTNTBlock(DEFAULT_PROPS, 32.0f, 80));
    public static final RegistryObject<Item> TNT_SELECTIVE_ITEM = ITEMS.register("tnt_selective", () -> new BlockItemTooltip(TNT_SELECTIVE.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_ENDER = BLOCKS.register("tnt_ender", () -> new enderTNTBlock(DEFAULT_PROPS, 4.0f, 80));
    public static final RegistryObject<Item> TNT_ENDER_ITEM = ITEMS.register("tnt_ender", () -> new BlockItemTooltip(TNT_ENDER.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_REPULSIVE = BLOCKS.register("tnt_repulsive", () -> new repulsiveTNTBlock(DEFAULT_PROPS, 4.0f, 80, 1));
    public static final RegistryObject<Item> TNT_REPULSIVE_ITEM = ITEMS.register("tnt_repulsive", () -> new BlockItemTooltip(TNT_REPULSIVE.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_BEDROCK = BLOCKS.register("tnt_bedrock", () -> new bedrockTNTBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED), 4.0f, 80));
    public static final RegistryObject<Item> TNT_BEDROCK_ITEM = ITEMS.register("tnt_bedrock", () -> new BlockItemTooltip(TNT_BEDROCK.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_ENTITY = BLOCKS.register("tnt_entity", () -> new entityTNTBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED), 4.0f, 80));
    public static final RegistryObject<Item> TNT_ENTITY_ITEM = ITEMS.register("tnt_entity", () -> new BlockItemTooltip(TNT_BEDROCK.get(), new Item.Properties()));


    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }
}
