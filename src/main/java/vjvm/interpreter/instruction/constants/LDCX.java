package vjvm.interpreter.instruction.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LDCX extends Instruction {
  private int index;
  private String name;

  public static final LDCX LDC(ProgramCounter pc, MethodInfo method) {
    byte index = pc.byte_();
    Constant constant = method.jClass().constantPool().constant(index);
    assert constant instanceof IntegerConstant || constant instanceof FloatConstant;
    return new LDCX(index, "ldc");
  }

  public static final LDCX LDC_W(ProgramCounter pc, MethodInfo method) {
    short index = pc.short_();
    Constant constant = method.jClass().constantPool().constant(index);
    assert constant instanceof IntegerConstant || constant instanceof FloatConstant;
    return new LDCX(index, "ldc_w");
  }

  public static final LDCX LDC2_W(ProgramCounter pc, MethodInfo method) {
    short index = pc.short_();
    Constant constant = method.jClass().constantPool().constant(index);
    assert constant instanceof LongConstant || constant instanceof DoubleConstant;
    return new LDCX(index, "ldc2_w");
  }

  @Override
  public void run(JThread thread) {
    Constant constant = thread.top().jClass().constantPool().constant(this.index);
    OperandStack operandStack = thread.top().stack();
    if (constant instanceof IntegerConstant) {
      operandStack.pushInt(((IntegerConstant) constant).value());
    } else if (constant instanceof FloatConstant) {
      operandStack.pushFloat(((FloatConstant) constant).value());
    } else if (constant instanceof LongConstant) {
      operandStack.pushLong(((LongConstant) constant).value());
    } else if (constant instanceof DoubleConstant) {
      operandStack.pushDouble(((DoubleConstant) constant).value());
    }
  }

  @Override
  public String toString() {
    return name;
  }
}
