package com.minemaarten.signals.block;

import net.minecraft.block.BlockRailPowered;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import com.minemaarten.signals.client.CreativeTabSignals;
import com.minemaarten.signals.init.ModBlocks;

public class BlockLimiterRail extends BlockRailPowered {

	public BlockLimiterRail() {
		setUnlocalizedName("limiter_rail");
        setCreativeTab(CreativeTabSignals.getInstance());
        ModBlocks.registerBlock(this);
	}
	
	@Override
	public void onMinecartPass(World world, EntityMinecart cart, BlockPos pos) {
		super.onMinecartPass(world, cart, pos);
		Vec3d vec = new Vec3d(cart.motionX, cart.motionY, cart.motionZ);
		double maxSpeed = 0.1D;
		double maxDeceleration = 0.2D;
		double vecLength = vec.lengthVector();
		if(vecLength > maxSpeed){
			vecLength = Math.max(vecLength - maxDeceleration, maxSpeed);
			vec = vec.normalize().scale(vecLength);
			cart.motionX = vec.xCoord;
			cart.motionY = vec.yCoord;
			cart.motionZ = vec.zCoord;
		}
	}
}
