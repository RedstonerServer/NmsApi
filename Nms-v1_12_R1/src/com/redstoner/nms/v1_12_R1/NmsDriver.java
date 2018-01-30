package com.redstoner.nms.v1_12_R1;

import com.redstoner.nms.INmsDriver;
import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.IBlockData;
import net.minecraft.server.v1_12_R1.WorldServer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;

final class NmsDriver implements INmsDriver
{

    @Override
    public void updateAdjacentComparators(Block block)
    {
        updateAdjacentComparators(block.getWorld(), block.getX(), block.getY(), block.getZ());
    }

    @Override
    public void updateAdjacentComparators(World world, int blockX, int blockY, int blockZ)
    {
        WorldServer handle = ((CraftWorld) world).getHandle();
        BlockPosition bp = new BlockPosition(blockX, blockY, blockZ);
        IBlockData type = handle.c(bp);
        handle.updateAdjacentComparators(bp, type.getBlock());
    }

}
