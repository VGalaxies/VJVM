package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XCMPCOND extends Instruction {
  private String name;

  public static final XCMPCOND FCMPL(ProgramCounter pc, MethodInfo method) {
    return new XCMPCOND("fcmpl");
  }

  public static final XCMPCOND FCMPG(ProgramCounter pc, MethodInfo method) {
    return new XCMPCOND("fcmpg");
  }

  public static final XCMPCOND DCMPL(ProgramCounter pc, MethodInfo method) {
    return new XCMPCOND("dcmpl");
  }

  public static final XCMPCOND DCMPG(ProgramCounter pc, MethodInfo method) {
    return new XCMPCOND("dcmpg");
  }

  @Override
  public void run(JThread thread) {
    OperandStack stack = thread.top().stack();
    char prefix = name.charAt(0);
    char suffix = name.charAt(name.length() - 1);
    if (prefix == 'f') {
      float value2 = stack.popFloat();
      float value1 = stack.popFloat();
      if (value1 > value2) {
        stack.pushInt(1);
      } else if (value1 == value2) {
        stack.pushInt(0);
      } else if (value1 < value2) {
        stack.pushInt(-1);
      } else {
        assert Float.isNaN(value1) || Float.isNaN(value2);
        if (suffix == 'g') {
          stack.pushInt(1);
        } else if (suffix == 'l') {
          stack.pushInt(-1);
        }
      }
    } else if (prefix == 'd') {
      double value2 = stack.popFloat();
      double value1 = stack.popFloat();
      if (value1 > value2) {
        stack.pushInt(1);
      } else if (value1 == value2) {
        stack.pushInt(0);
      } else if (value1 < value2) {
        stack.pushInt(-1);
      } else {
        assert Double.isNaN(value1) || Double.isNaN(value2);
        if (suffix == 'g') {
          stack.pushInt(1);
        } else if (suffix == 'l') {
          stack.pushInt(-1);
        }
      }
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
