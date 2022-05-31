package vjvm.interpreter.instruction.conversions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class X2Y extends Instruction {
  private enum Type {
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    BYTE,
    CHAR,
    SHORT,
  }
  private Type from;
  private Type to;
  private String name;

  public static final X2Y I2L(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.INT, Type.LONG, "i2l");
  }

  public static final X2Y I2F(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.INT, Type.FLOAT, "i2f");
  }

  public static final X2Y I2D(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.INT, Type.DOUBLE, "i2d");
  }

  public static final X2Y L2I(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.LONG, Type.INT, "l2i");
  }

  public static final X2Y L2F(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.LONG, Type.FLOAT, "l2f");
  }

  public static final X2Y L2D(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.LONG, Type.DOUBLE, "l2d");
  }

  public static final X2Y F2I(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.FLOAT, Type.INT, "f2i");
  }

  public static final X2Y F2L(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.FLOAT, Type.LONG, "f2l");
  }

  public static final X2Y F2D(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.FLOAT, Type.DOUBLE, "f2d");
  }

  public static final X2Y D2I(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.DOUBLE, Type.INT, "d2i");
  }

  public static final X2Y D2L(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.DOUBLE, Type.LONG, "d2l");
  }

  public static final X2Y D2F(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.DOUBLE, Type.FLOAT, "d2f");
  }

  public static final X2Y I2B(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.INT, Type.BYTE, "i2b");
  }

  public static final X2Y I2C(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.INT, Type.CHAR, "i2c");
  }

  public static final X2Y I2S(ProgramCounter pc, MethodInfo method) {
    return new X2Y(Type.INT, Type.SHORT, "i2s");
  }

  @Override
  public void run(JThread thread) {
    OperandStack stack = thread.top().stack();
    switch (from) {
      case INT: {
        int value = stack.popInt();
        switch (to) {
          case LONG: {
            stack.pushLong(value);
            break;
          }
          case FLOAT: {
            stack.pushFloat(value);
            break;
          }
          case DOUBLE: {
            stack.pushDouble(value);
            break;
          }
          case BYTE: {
            stack.pushByte((byte) value);
            break;
          }
          case CHAR: {
            stack.pushChar((char) value);
            break;
          }
          case SHORT: {
            stack.pushShort((short) value);
            break;
          }
        }
        break;
      }
      case LONG: {
        long value = stack.popLong();
        switch (to) {
          case INT: {
            stack.pushInt((int) value);
            break;
          }
          case FLOAT: {
            stack.pushFloat((float) value);
            break;
          }
          case DOUBLE: {
            stack.pushDouble((double) value);
            break;
          }
        }
        break;
      }
      case FLOAT: {
        float value = stack.popFloat();
        switch (to) {
          case INT: {
            stack.pushInt((int) value);
            break;
          }
          case LONG: {
            stack.pushLong((long) value);
            break;
          }
          case DOUBLE: {
            stack.pushDouble(value);
            break;
          }
        }
        break;
      }
      case DOUBLE: {
        double value = stack.popDouble();
        switch (to) {
          case INT: {
            stack.pushInt((int) value);
            break;
          }
          case LONG: {
            stack.pushLong((long) value);
            break;
          }
          case FLOAT: {
            stack.pushFloat((float) value);
            break;
          }
        }
        break;
      }
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
