package com.github.revival.server.dimension.treasure;


import com.github.revival.Revival;
import com.github.revival.server.biome.FABiomeRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderTreasure extends WorldProvider {
    @Override
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(FABiomeRegistry.INSTANCE.treasureBiome, 0);
        this.dimensionId = Revival.CONFIG.dimensionIDTreasure;
        this.hasNoSky = true;
        this.isHellWorld = true;

    }

    @Override
    public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_) {
        return 0.0F;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float p_76562_1_, float p_76562_2_) {
        int i = 10518688;
        float f2 = MathHelper.cos(p_76562_1_ * (float) Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f2 < 0.0F) {
            f2 = 0.0F;
        }

        if (f2 > 1.0F) {
            f2 = 1.0F;
        }

        float f3 = (float) (i >> 16 & 255) / 255.0F;
        float f4 = (float) (i >> 8 & 255) / 255.0F;
        float f5 = (float) (i & 255) / 255.0F;
        f3 *= f2 * 0.0F + 0.15F;
        f4 *= f2 * 0.0F + 0.15F;
        f5 *= f2 * 0.0F + 0.15F;
        return Vec3.createVectorHelper((double) f3, (double) f4, (double) f5);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored() {
        return false;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    /**
     * Creates the light to brightness table
     */
    @Override
    protected void generateLightBrightnessTable() {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0F - (float) i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    /**
     * Returns array with sunrise/sunset colors
     */
    @Override
    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float p_76560_1_, float p_76560_2_) {
        return null;
    }

    @Override
    public String getDimensionName() {
        return "the Ancient Treasure Room";
    }

    @Override
    public String getWelcomeMessage() {
        return "Entering the Ancient Treasure Room";
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderTreasure(worldObj, 0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return 8.0F;
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    @Override
    public boolean canCoordinateBeSpawn(int x, int y) {
        return this.worldObj.getTopBlock(x, y).getMaterial().blocksMovement();
    }

    /**
     * Gets the hard-coded portal location to use when entering this dimension.
     */
    @Override
    public ChunkCoordinates getEntrancePortalLocation() {
        return new ChunkCoordinates(0, 50, 0);
    }

    @Override
    public int getAverageGroundLevel() {
        return 50;
    }

    /**
     * Returns true if the given X,Z coordinate should show environmental fog.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int p_76568_1_, int p_76568_2_) {
        return false;
    }

    @Override
    public int getRespawnDimension(EntityPlayerMP player) {
        return 0;
    }
}
