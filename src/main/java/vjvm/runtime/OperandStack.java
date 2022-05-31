package vjvm.runtime;

import lombok.var;
import vjvm.utils.UnimplementedError;
import lombok.Getter;

public class OperandStack {
  @Getter
  private final Slots slots;

  @Getter
  private int top;

  public OperandStack(int stackSize) {
    // TODO: initialize data structures
    slots = new Slots(stackSize);
    top = 0;
  }

  public void pushInt(int value) {
    // TODO: push value
    slots.int_(top, value);
    top += 1;
  }

  public int popInt() {
    // TODO: pop value
    top -= 1;
    int res = slots.int_(top);
    slots.makeEmpty(top, 1);
    return res;
  }

  public void pushFloat(float value) {
    // TODO: push value
    slots.float_(top, value);
    top += 1;
  }

  public float popFloat() {
    // TODO: pop value
    top -= 1;
    float res = slots.float_(top);
    slots.makeEmpty(top, 1);
    return res;
  }

  public void pushLong(long value) {
    // TODO: push value
    slots.long_(top, value);
    top += 2;
  }

  public long popLong() {
    // TODO: pop value
    top -= 2;
    long res = slots.long_(top);
    slots.makeEmpty(top, 2);
    return res;
  }

  public void pushDouble(double value) {
    // TODO: push value
    slots.double_(top, value);
    top += 2;
  }

  public double popDouble() {
    // TODO: pop value
    top -= 2;
    double res = slots.double_(top);
    slots.makeEmpty(top, 2);
    return res;
  }

  public void pushByte(byte value) {
    // TODO: push value
    slots.byte_(top, value);
    top += 1;
  }

  public byte popByte() {
    // TODO: pop value
    top -= 1;
    byte res = slots.byte_(top);
    slots.makeEmpty(top, 1);
    return res;
  }

  public void pushChar(char value) {
    // TODO: push value
    slots.char_(top, value);
    top += 1;
  }

  public char popChar() {
    // TODO: pop value
    top -= 1;
    char res = slots.char_(top);
    slots.makeEmpty(top, 1);
    return res;
  }

  public void pushShort(short value) {
    // TODO: push value
    slots.short_(top, value);
    top += 1;
  }

  public short popShort() {
    // TODO: pop value
    top -= 1;
    short res = slots.short_(top);
    slots.makeEmpty(top, 1);
    return res;
  }

  public void pushSlots(Slots slots) {
    // TODO: push slots
    int size = slots.size();
    slots.copyTo(0, size, this.slots, top);
    top += size;
  }

  public Slots popSlots(int count) {
    // TODO: pop count slots and return
    Slots res = new Slots(count);
    top -= count;
    this.slots.copyTo(top, count, res, 0); // assume non-reverse
    this.slots.makeEmpty(top, count);
    return res;
  }

  public void clear() {
    // TODO: pop all slots
    this.slots.makeEmpty(0, top);
    top = 0;
  }
}
