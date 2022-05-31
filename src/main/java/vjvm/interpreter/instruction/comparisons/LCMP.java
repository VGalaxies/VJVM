package vjvm.interpreter.instruction.comparisons;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LCMP extends Instruction {
  private String name;
  public static final LCMP LCMP(ProgramCounter pc, MethodInfo method) {
    return new LCMP("lcmp");
  }

  @Override
  public void run(JThread thread) {
    OperandStack stack = thread.top().stack();
    long value2 = stack.popLong();
    long value1 = stack.popLong();
    if (value1 > value2) {
      stack.pushInt(1);
    } else if (value1 == value2) {
      stack.pushInt(0);
    } else {
      stack.pushInt(-1);
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
