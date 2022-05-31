package vjvm.runtime;

import java.util.Optional;

import vjvm.utils.UnimplementedError;

/**
 * Slots represents an array of JVM slots as defined in the specification. It
 * supports getting and putting primitive data types, including address.
 */
public class Slots {
  private Object[] dataArray;
  private SlotType[] typeArray;
  private int size;

  private enum SlotType {
    INT,
    LONG,
    FLOAT,
    DOUBLE,
  }

  public Slots(int slotSize) {
    // TODO: initialize data structures
    dataArray = new Object[slotSize];
    typeArray = new SlotType[slotSize];
    size = slotSize;
  }

  private void checkType(int index, SlotType type) {
    assert typeArray[index] == type;
  }

  public void makeEmpty(int index, int length) {
    for (int i = 0; i < length; ++i) {
      dataArray[index + i] = null;
      typeArray[index + i] = null;
    }
  }

  public int int_(int index) {
    // TODO: return the int at index
    checkType(index, SlotType.INT);
    return (int) dataArray[index];
  }

  public void int_(int index, int value) {
    // TODO: set the int at index
    dataArray[index] = value;
    typeArray[index] = SlotType.INT;
  }

  public long long_(int index) {
    // TODO: return the long at index
    checkType(index, SlotType.LONG);
    assert dataArray[index + 1] == null && typeArray[index + 1] == null;
    return (long) dataArray[index];
  }

  public void long_(int index, long value) {
    // TODO: set the long at index
    dataArray[index] = value;
    typeArray[index] = SlotType.LONG;
  }

  public float float_(int index) {
    // TODO: return the float at index
    checkType(index, SlotType.FLOAT);
    return (float) dataArray[index];
  }

  public void float_(int index, float value) {
    // TODO: set the float at index
    dataArray[index] = value;
    typeArray[index] = SlotType.FLOAT;
  }

  public double double_(int index) {
    // TODO: return the double at index
    checkType(index, SlotType.DOUBLE);
    assert dataArray[index + 1] == null && typeArray[index + 1] == null;
    return (double) dataArray[index];
  }

  public void double_(int index, double value) {
    // TODO: set the double at index
    dataArray[index] = value;
    typeArray[index] = SlotType.DOUBLE;
  }

  public byte byte_(int index) {
    // TODO: return the byte at index
    checkType(index, SlotType.INT);
    return (byte) dataArray[index];
  }

  public void byte_(int index, byte value) {
    // TODO: set the byte at index
    dataArray[index] = (int) value;
    typeArray[index] = SlotType.INT;
  }

  public char char_(int index) {
    // TODO: return the char at index
    checkType(index, SlotType.INT);
    int tmp = (int) dataArray[index];
    return (char) tmp;
  }

  public void char_(int index, char value) {
    // TODO: set the char at index
    dataArray[index] = (int) value;
    typeArray[index] = SlotType.INT;
  }

  public short short_(int index) {
    // TODO: return the short at index
    checkType(index, SlotType.INT);
    return (short) dataArray[index];
  }

  public void short_(int index, short value) {
    // TODO: set the short at index
    dataArray[index] = (int) value;
    typeArray[index] = SlotType.INT;
  }

  public Optional<Object> value(int index) {
    // TODO(optional): return the value at index, or null if there is no value stored at index
    return dataArray[index] == null ? Optional.empty() : Optional.of(dataArray[index]);
  }

  public int size() {
    // TODO: return the size in the number of slots
    return size;
  }

  public void copyTo(int begin, int length, Slots dest, int destBegin) {
    // TODO: copy from this:[begin, begin + length) to dest:[destBegin, destBegin + length)
    if (dest == this && destBegin > begin) {
      for (int i = length - 1; i >= 0; --i) {
        dest.dataArray[destBegin + i] = this.dataArray[begin + i];
        dest.typeArray[destBegin + i] = this.typeArray[begin + i];
      }
    } else {
      for (int i = 0 ; i < length; ++i) {
        dest.dataArray[destBegin + i] = this.dataArray[begin + i];
        dest.typeArray[destBegin + i] = this.typeArray[begin + i];
      }
    }
  }
}
