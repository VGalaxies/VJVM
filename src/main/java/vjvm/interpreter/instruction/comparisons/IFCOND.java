package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IFCOND extends Instruction {
  private enum Type {
    EQ,
    NE,
    LT,
    GE,
    GT,
    LE,
  }
  private Type type;
  private short offset;
  private String name;

  public static final IFCOND IFEQ(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(Type.EQ, pc.short_(), "ifeq");
  }

  public static final IFCOND IFNE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(Type.NE, pc.short_(), "ifne");
  }

  public static final IFCOND IFLT(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(Type.LT, pc.short_(), "iflt");
  }

  public static final IFCOND IFGE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(Type.GE, pc.short_(), "ifge");
  }

  public static final IFCOND IFGT(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(Type.GT, pc.short_(), "ifgt");
  }

  public static final IFCOND IFLE(ProgramCounter pc, MethodInfo method) {
    return new IFCOND(Type.LE, pc.short_(), "ifle");
  }

  @Override
  public void run(JThread thread) {
    int value = thread.top().stack().popInt();
    switch (type) {
      case EQ: {
        if (value == 0) {
          thread.pc().move(offset - 3); // assume
        }
        break;
      }
      case NE: {
        if (value != 0) {
          thread.pc().move(offset - 3);
        }
        break;
      }
      case GE: {
        if (value >= 0) {
          thread.pc().move(offset - 3);
        }
        break;
      }
      case GT: {
        if (value > 0) {
          thread.pc().move(offset - 3);
        }
        break;
      }
      case LE: {
        if (value <= 0) {
          thread.pc().move(offset - 3);
        }
        break;
      }
      case LT: {
        if (value < 0) {
          thread.pc().move(offset - 3);
        }
        break;
      }
    }
  }

  @Override
  public String toString() {
    return String.format("%s %d", name, offset);
  }
}
