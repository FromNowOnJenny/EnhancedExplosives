package com.jenny.enhancedexplosives.datagen;

import com.jenny.enhancedexplosives.blocks.blocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        sideTopBottom(blocks.TNT_8);
        sideTopBottom(blocks.TNT_16);
        sideTopBottom(blocks.TNT_32);
        sideTopBottom(blocks.TNT_64);
        sideTopBottom(blocks.TNT_128);
        sideTopBottom(blocks.TNT_ENDER);
        sideTopBottom(blocks.TNT_CLAYMORE);
        SideTop(blocks.TNT_BLACK_HOLE);
        SideTop(blocks.TNT_REPULSIVE);
        SideOnlyTNT(blocks.TNT_CLUSTER_2);
        SideOnlyTNT(blocks.TNT_CLUSTER_4);
        SideOnlyTNT(blocks.TNT_CLUSTER_8);
        SideOnlyTNT(blocks.TNT_SELECTIVE);
        SideOnlyTNT(blocks.TNT_HOMING);
    }

    private void blockWithItem(@NotNull RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void SideTop(@NotNull RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), topSide(blockRegistryObject.get()));
    }

    private void blockItem(@NotNull RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(MODID +
                ":block/" + Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get())).getPath()));
    }

    private void topBottom2Sides(@NotNull RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), northEastTopBottom(blockRegistryObject.get()));
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private @NotNull String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation extend(@NotNull ResourceLocation rl, String suffix) {
        return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
    }

    public void sideTopBottom(@NotNull RegistryObject<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        ModelFile model = models().cubeBottomTop(name(block), extend(blockTexture(block), "_side"), extend(blockTexture(block), "_bottom"), extend(blockTexture(block), "_top"));
        this.getVariantBuilder(block).forAllStates(blockState -> ConfiguredModel.builder().modelFile(model).build());
        simpleBlockItem(block, model);
    }

    public void SideOnlyTNT(@NotNull RegistryObject<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        ModelFile model = models().cubeBottomTop(name(block), extend(blockTexture(block), "_side"), extend(blockTexture(Blocks.TNT), "_bottom"), extend(blockTexture(Blocks.TNT), "_top"));
        this.getVariantBuilder(block).forAllStates(blockState -> ConfiguredModel.builder().modelFile(model).build());
        simpleBlockItem(block, model);
    }

    public ModelFile northEastTopBottom(Block block) {
        return models().cube(name(block), extend(blockTexture(block), "_bottom"), extend(blockTexture(block), "_top"), extend(blockTexture(block), "_nw"),  extend(blockTexture(block), "_se"),  extend(blockTexture(block), "_se"),  extend(blockTexture(block), "_nw")).texture("particle", extend(blockTexture(block), "_se"));
    }

    public ModelFile cube(Block block) {
        return models().cube(name(block), extend(blockTexture(block), "_bottom"), extend(blockTexture(block), "_top"), extend(blockTexture(block), "_north"),  extend(blockTexture(block), "_south"),  extend(blockTexture(block), "_east"),  extend(blockTexture(block), "_west")).texture("particle", extend(blockTexture(block), "_north"));
    }

    public ModelFile topSide(Block block) {
        return models().cubeColumn(name(block), extend(blockTexture(block), "_side"), extend(blockTexture(block), "_top"));
    }
}