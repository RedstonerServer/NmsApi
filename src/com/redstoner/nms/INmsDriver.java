package com.redstoner.nms;

import org.bukkit.World;
import org.bukkit.block.Block;

public interface INmsDriver
{

    /**
     * Update comparators adjacent to the given block
     *
     * @param block the block
     */
    void updateAdjacentComparators(Block block);

    /**
     * Update comparators adjacent to the given coordinates
     *
     * @param world the world
     * @param blockX x coord
     * @param blockY y coord
     * @param blockZ z coord
     */
    void updateAdjacentComparators(World world, int blockX, int blockY, int blockZ);

}
