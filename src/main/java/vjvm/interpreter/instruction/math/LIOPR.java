package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LIOPR extends Instruction {
  private enum Type {
    SHL,
    SHR,
    USHR,
    AND,
    OR,
    XOR,
  };
  private Type type;
  private String name;

  public static final LIOPR ISHL(ProgramCounter pc, MethodInfo method) {
    return new LIOPR(Type.SHL, "ishl");
  }

  public static final LIOPR LSHL(ProgramCounter pc, MethodInfo method) {
    return new LIOPR(Type.SHL, "lshl");
  }

  public static final LIOPR ISHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR(Type.SHR, "ishr");
  }

  public static final LIOPR LSHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR(Type.SHR, "lshr");
  }

  public static final LIOPR IUSHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR(Type.USHR, "iushr");
  }

  public static final LIOPR LUSHR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR(Type.USHR, "lushr");
  }

  public static final LIOPR IAND(ProgramCounter pc, MethodInfo method) {
    return new LIOPR(Type.AND, "iand");
  }

  public static final LIOPR LAND(ProgramCounter pc, MethodInfo method) {
    return new LIOPR(Type.AND, "land");
  }

  public static final LIOPR IOR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR(Type.OR, "ior");
  }

  public static final LIOPR LOR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR(Type.OR, "lor");
  }

  public static final LIOPR IXOR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR(Type.XOR, "ixor");
  }

  public static final LIOPR LXOR(ProgramCounter pc, MethodInfo method) {
    return new LIOPR(Type.XOR, "lxor");
  }

  @Override
  public void run(JThread thread) {
    OperandStack stack = thread.top().stack();
    char prefix = name.charAt(0);
    if (prefix == 'i') {
      switch (type) {
        case SHL: {
          int power = stack.popInt();
          int base = stack.popInt();
          stack.pushInt(base << (power & 0x1f));
          break;
        }
        case SHR: {
          int power = stack.popInt();
          int base = stack.popInt();
          stack.pushInt(base >> (power & 0x1f));
          break;
        }
        case USHR: {
          int power = stack.popInt();
          int base = stack.popInt();
          stack.pushInt(base >>> (power & 0x1f));
          break;
        }
        case AND: {
          int value2 = stack.popInt();
          int value1 = stack.popInt();
          stack.pushInt(value1 & value2);
          break;
        }
        case OR: {
          int value2 = stack.popInt();
          int value1 = stack.popInt();
          stack.pushInt(value1 | value2);
          break;
        }
        case XOR: {
          int value2 = stack.popInt();
          int value1 = stack.popInt();
          stack.pushInt(value1 ^ value2);
          break;
        }
      }
    } else if (prefix == 'l') {
      switch (type) {
        case SHL: {
          int power = stack.popInt();
          long base = stack.popLong();
          stack.pushLong(base << (power & 0x3f));
          break;
        }
        case SHR: {
          int power = stack.popInt();
          long base = stack.popLong();
          stack.pushLong(base >> (power & 0x3f));
          break;
        }
        case USHR: {
          int power = stack.popInt();
          long base = stack.popLong();
          stack.pushLong(base >>> (power & 0x3f));
          break;
        }
        case AND: {
          long value2 = stack.popLong();
          long value1 = stack.popLong();
          stack.pushLong(value1 & value2);
          break;
        }
        case OR: {
          long value2 = stack.popLong();
          long value1 = stack.popLong();
          stack.pushLong(value1 | value2);
          break;
        }
        case XOR: {
          long value2 = stack.popLong();
          long value1 = stack.popLong();
          stack.pushLong(value1 ^ value2);
          break;
        }
      }
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
