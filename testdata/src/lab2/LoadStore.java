package lab2;

public class LoadStore {
  public static void main(String[] args) {
    int a = 1;
    long b = 2L;
    float c = 3F;
    double d = 4.0;
    IOUtil.writeInt(a);
    IOUtil.writeLong(b);
    IOUtil.writeFloat(c);
    IOUtil.writeDouble(d);
  }
}
