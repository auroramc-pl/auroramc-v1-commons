package pl.auroramc.commons.bukkit.block;

public record BlockPos(int x, int y, int z) {

  public static BlockPos fromEntityId(long entityId) {
    int x = (int) ((entityId >> 22) & 0x1FFF);
    int y = (int) ((entityId >> 13) & 0x1FF);
    int z = (int) (entityId & 0x1FFF);

    // x, z = 2^13 = x, z has to be greater than -4096 and lesser than 4096
    // y = 2^9 = y has to be greater than -256 and lesser than 256
    if (x >= 0x1000) {
      x -= 0x2000;
    }

    if (y >= 0x100) {
      y -= 0x200;
    }

    if (z >= 0x1000) {
      z -= 0x2000;
    }

    return new BlockPos(x, y, z);
  }

  public long getBlockEntityId() {
    final long shiftedX = x & 0x1FFF;
    final long shiftedY = y & 0x1FF;
    final long shiftedZ = z & 0x1FFF;
    return (shiftedX << 22) | (shiftedY << 13) | shiftedZ;
  }
}
