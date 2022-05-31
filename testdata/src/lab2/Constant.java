package lab2;

public class Constant {
  public static void main(String[] args) {
    // XCONST_Y
    IOUtil.writeLong(0L);
    IOUtil.writeLong(1L);
    IOUtil.writeFloat(0F);
    IOUtil.writeFloat(1F);
    IOUtil.writeFloat(2F);
    IOUtil.writeDouble(0.0);
    IOUtil.writeDouble(1.0);

    // XPUSH
    IOUtil.writeInt(42);

    // LDCX
    IOUtil.writeInt(123456);
    IOUtil.writeFloat(3.14F);
    IOUtil.writeLong(65535L);
    IOUtil.writeDouble(123.456);
  }
}
