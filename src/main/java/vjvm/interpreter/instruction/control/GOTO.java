package vjvm.interpreter.instruction.control;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GOTO extends Instruction {
  private short offset;
  private String name;

  public static final GOTO GOTO(ProgramCounter pc, MethodInfo method) {
    return new GOTO(pc.short_(), "goto");
  }

  @Override
  public void run(JThread thread) {
    thread.pc().move(offset - 3);
  }

  @Override
  public String toString() {
    return String.format("%s %d", name, offset);
  }
}
