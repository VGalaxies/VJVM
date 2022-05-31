package vjvm.interpreter.instruction.stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DUPX extends Instruction {
  private int count;
  private String name;

  public static final DUPX DUP(ProgramCounter pc, MethodInfo method) {
    return new DUPX(1, "dup");
  }

  public static final DUPX DUP2(ProgramCounter pc, MethodInfo method) {
    return new DUPX(2, "dup2");
  }

  @Override
  public void run(JThread thread) {
    Slots slots = thread.top().stack().popSlots(count);
    thread.top().stack().pushSlots(slots);
    thread.top().stack().pushSlots(slots);
  }

  @Override
  public String toString() {
    return name;
  }
}
