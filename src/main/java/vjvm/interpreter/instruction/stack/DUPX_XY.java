package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DUPX_XY extends Instruction {
  private enum Type {
    DUP_X1,
    DUP_X2,
    DUP2_X1,
    DUP2_X2,
  };
  private Type type;
  private String name;

  public static final DUPX_XY DUP_X1(ProgramCounter pc, MethodInfo method) {
    return new DUPX_XY(Type.DUP_X1, "dup_x1");
  }

  public static final DUPX_XY DUP_X2(ProgramCounter pc, MethodInfo method) {
    return new DUPX_XY(Type.DUP_X2, "dup_x2");
  }

  public static final DUPX_XY DUP2_X1(ProgramCounter pc, MethodInfo method) {
    return new DUPX_XY(Type.DUP2_X1, "dup2_x1");
  }

  public static final DUPX_XY DUP2_X2(ProgramCounter pc, MethodInfo method) {
    return new DUPX_XY(Type.DUP2_X2, "dup2_x2");
  }

  @Override
  public void run(JThread thread) {
    OperandStack stack = thread.top().stack();
    switch (type) {
      case DUP_X1: {
        Slots slot1 = stack.popSlots(1);
        Slots slot2 = stack.popSlots(1);
        stack.pushSlots(slot1);
        stack.pushSlots(slot2);
        stack.pushSlots(slot1);
        break;
      }
      case DUP_X2: {
        Slots slot1 = stack.popSlots(1);
        Slots slot2 = stack.popSlots(1);
        Slots slot3 = stack.popSlots(1);
        stack.pushSlots(slot1);
        stack.pushSlots(slot3);
        stack.pushSlots(slot2);
        stack.pushSlots(slot1);
        break;
      }
      case DUP2_X1: {
        Slots slot1 = stack.popSlots(1);
        Slots slot2 = stack.popSlots(1);
        Slots slot3 = stack.popSlots(1);
        stack.pushSlots(slot2);
        stack.pushSlots(slot1);
        stack.pushSlots(slot3);
        stack.pushSlots(slot2);
        stack.pushSlots(slot1);
        break;
      }
      case DUP2_X2: {
        Slots slot1 = stack.popSlots(1);
        Slots slot2 = stack.popSlots(1);
        Slots slot3 = stack.popSlots(1);
        Slots slot4 = stack.popSlots(1);
        stack.pushSlots(slot2);
        stack.pushSlots(slot1);
        stack.pushSlots(slot4);
        stack.pushSlots(slot3);
        stack.pushSlots(slot2);
        stack.pushSlots(slot1);
        break;
      }
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
