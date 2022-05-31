package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XNEG extends Instruction {
  private String name;

  public static final XNEG INEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG("ineg");
  }

  public static final XNEG LNEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG("lneg");
  }

  public static final XNEG FNEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG("fneg");
  }

  public static final XNEG DNEG(ProgramCounter pc, MethodInfo method) {
    return new XNEG("dneg");
  }

  @Override
  public void run(JThread thread) {
    OperandStack operandStack = thread.top().stack();
    switch (name.charAt(0)) {
      case 'i': {
        int value = operandStack.popInt();
        operandStack.pushInt(-value);
        break;
      }
      case 'l': {
        long value = operandStack.popLong();
        operandStack.pushLong(-value);
        break;
      }
      case 'f': {
        float value = operandStack.popFloat();
        operandStack.pushFloat(-value); // assume
        break;
      }
      case 'd': {
        double value = operandStack.popDouble();
        operandStack.pushDouble(-value); // assume
        break;
      }
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
