package com.github.stellarwitch7.earthguard.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ErrodiumReactorBlock extends BlockWithEntity {
	public ErrodiumReactorBlock(Settings settings) {
		super(settings);
	}
	
	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new ErrodiumReactorBlockEntity(pos, state);
	}
}
