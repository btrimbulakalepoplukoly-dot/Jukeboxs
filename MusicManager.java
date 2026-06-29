package com.chaoscube.blockentity;

import com.chaoscube.network.ModNetwork;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ChaosCubeBlockEntity extends BlockEntity {

    private String youtubeUrl = "";
    private boolean isPlaying = false;
    private int tickCounter = 0;
    public static final int SOUND_RANGE = 30;

    public ChaosCubeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHAOS_CUBE_BE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, ChaosCubeBlockEntity be) {
        if (world.isClient()) return;
        be.tickCounter++;
        // Re-sync every 5 seconds for players who moved into range
        if (be.tickCounter % 100 == 0 && be.isPlaying) {
            be.broadcastState((ServerWorld) world);
        }
    }

    public String getYoutubeUrl() { return youtubeUrl; }
    public boolean isPlaying() { return isPlaying; }

    public void setUrlAndPlay(String url, World world) {
        this.youtubeUrl = url;
        this.isPlaying = !url.isEmpty();
        markDirty();
        if (world instanceof ServerWorld sw) {
            broadcastState(sw);
        }
    }

    public void stopPlaying(World world, BlockPos pos) {
        this.isPlaying = false;
        this.youtubeUrl = "";
        markDirty();
        if (world instanceof ServerWorld sw) {
            broadcastState(sw);
        }
    }

    private void broadcastState(ServerWorld world) {
        ModNetwork.sendMusicSync(world, pos, youtubeUrl, isPlaying, SOUND_RANGE);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString("youtube_url", youtubeUrl);
        nbt.putBoolean("is_playing", isPlaying);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        youtubeUrl = nbt.getString("youtube_url");
        isPlaying = nbt.getBoolean("is_playing");
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbt = new NbtCompound();
        writeNbt(nbt);
        return nbt;
    }
}
