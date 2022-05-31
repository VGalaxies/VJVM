package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.Slots;
import vjvm.runtime.classdata.MethodInfo;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IINC extends Instruction {
  private int index;
  private int value;
  private String name;

  public static final IINC IINC(ProgramCounter pc, MethodInfo method) {
    int index = pc.ubyte();
    int value = pc.byte_();
    return new IINC(index, value, "iinc");
  }

  @Override
  public void run(JThread thread) {
    Slots slots = thread.top().vars();
    slots.int_(index, slots.int_(index) + value);
  }

  @Override
  public String toString() {
    return String.format("%s %d %d", name, index, value);
  }
}
