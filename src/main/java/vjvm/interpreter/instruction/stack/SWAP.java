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
public class SWAP extends Instruction {
  private String name;

  public static final SWAP SWAP(ProgramCounter pc, MethodInfo method) {
    return new SWAP("swap");
  }

  @Override
  public void run(JThread thread) {
    OperandStack stack = thread.top().stack();
    Slots slot1 = stack.popSlots(1);
    Slots slot2 = stack.popSlots(1);
    stack.pushSlots(slot1);
    stack.pushSlots(slot2);
  }

  @Override
  public String toString() {
    return name;
  }
}
