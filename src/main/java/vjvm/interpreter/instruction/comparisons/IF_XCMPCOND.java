package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IF_XCMPCOND extends Instruction {
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

  public static final IF_XCMPCOND IF_ICMPEQ(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND(Type.EQ, pc.short_(), "if_icmpeq");
  }

  public static final IF_XCMPCOND IF_ICMPNE(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND(Type.NE, pc.short_(), "if_icmpne");
  }

  public static final IF_XCMPCOND IF_ICMPLT(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND(Type.LT, pc.short_(), "if_icmplt");
  }

  public static final IF_XCMPCOND IF_ICMPGE(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND(Type.GE, pc.short_(), "if_icmpge");
  }

  public static final IF_XCMPCOND IF_ICMPGT(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND(Type.GT, pc.short_(), "if_icmpgt");
  }

  public static final IF_XCMPCOND IF_ICMPLE(ProgramCounter pc, MethodInfo method) {
    return new IF_XCMPCOND(Type.LE, pc.short_(), "if_icmple");
  }

  @Override
  public void run(JThread thread) {
    OperandStack stack = thread.top().stack();
    int value2 = stack.popInt();
    int value1 = stack.popInt();
    switch (type) {
      case EQ: {
        if (value1 == value2) {
          thread.pc().move(offset - 2); // assume
        }
        break;
      }
      case NE: {
        if (value1 != value2) {
          thread.pc().move(offset - 2);
        }
        break;
      }
      case GE: {
        if (value1 >= value2) {
          thread.pc().move(offset - 2);
        }
        break;
      }
      case GT: {
        if (value1 > value2) {
          thread.pc().move(offset - 2);
        }
        break;
      }
      case LE: {
        if (value1 <= value2) {
          thread.pc().move(offset - 2);
        }
        break;
      }
      case LT: {
        if (value1 < value2) {
          thread.pc().move(offset - 2);
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
