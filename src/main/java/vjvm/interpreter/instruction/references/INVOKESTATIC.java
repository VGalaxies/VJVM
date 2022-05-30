package vjvm.interpreter.instruction.references;

import lombok.var;
import vjvm.classfiledefs.Descriptors;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JClass;
import vjvm.runtime.JThread;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.constant.Constant;
import vjvm.runtime.classdata.constant.MethodrefConstant;
import vjvm.utils.UnimplementedError;

public class INVOKESTATIC extends Instruction {
  private final MethodInfo method;

  public INVOKESTATIC(ProgramCounter pc, MethodInfo method) {
    // TODO: decode invokestatic
    JClass thisClass = method.jClass();

    ConstantPool constantPool = thisClass.constantPool();
    short index = pc.short_();
    Constant methodRef = constantPool.constant(index);
    assert methodRef instanceof MethodrefConstant;

    String methodRefClassName = ((MethodrefConstant) methodRef).clazz();
    JClass methodRefClass = thisClass.classLoader().loadClass(Descriptors.of(methodRefClassName));
    assert methodRefClass != null;

    this.method = methodRefClass.findMethod(
      ((MethodrefConstant) methodRef).name(),
      ((MethodrefConstant) methodRef).type());

    assert this.method != null;
  }

  @Override
  public void run(JThread thread) {
    var stack = thread.top().stack();
    var args = stack.popSlots(method.argc());

    thread.context().interpreter().invoke(method, thread, args);
  }

  @Override
  public String toString() {
    return String.format("invokestatic %s:%s:%s", method.jClass().name(), method.name(), method.descriptor());
  }
}
