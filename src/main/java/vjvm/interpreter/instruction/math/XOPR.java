package vjvm.interpreter.instruction.math;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import vjvm.interpreter.instruction.Instruction;
import vjvm.runtime.JThread;
import vjvm.runtime.OperandStack;
import vjvm.runtime.ProgramCounter;
import vjvm.runtime.classdata.MethodInfo;

import java.util.function.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class XOPR<T> extends Instruction {
  private final BinaryOperator<T> opFunc;             // (T, T) -> T
  private final Function<OperandStack, T> popFunc;    // OperandStack -> T
  private final BiConsumer<OperandStack, T> pushFunc; // (OperandStack, T) -> ()
  private String name;

  public static final XOPR<Integer> IADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Integer>(Integer::sum, OperandStack::popInt, OperandStack::pushInt, "iadd");
  }

  public static final XOPR<Long> LADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Long>(Long::sum, OperandStack::popLong, OperandStack::pushLong, "ladd");
  }

  public static final XOPR<Float> FADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Float>(Float::sum, OperandStack::popFloat, OperandStack::pushFloat, "fadd");
  }

  public static final XOPR<Double> DADD(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Double>((Double::sum), OperandStack::popDouble, OperandStack::pushDouble, "dadd");
  }

  public static final XOPR<Integer> ISUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Integer>(((a, b) -> a - b), OperandStack::popInt, OperandStack::pushInt, "isub");
  }

  public static final XOPR<Long> LSUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Long>(((a, b) -> a - b), OperandStack::popLong, OperandStack::pushLong, "lsub");
  }

  public static final XOPR<Float> FSUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Float>(((a, b) -> a - b), OperandStack::popFloat, OperandStack::pushFloat, "fsub");
  }

  public static final XOPR<Double> DSUB(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Double>(((a, b) -> a - b), OperandStack::popDouble, OperandStack::pushDouble, "dsub");
  }

  public static final XOPR<Integer> IMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Integer>(((a, b) -> a * b), OperandStack::popInt, OperandStack::pushInt, "imul");
  }

  public static final XOPR<Long> LMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Long>(((a, b) -> a * b), OperandStack::popLong, OperandStack::pushLong, "lmul");
  }

  public static final XOPR<Float> FMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Float>(((a, b) -> a * b), OperandStack::popFloat, OperandStack::pushFloat, "fmul");
  }

  public static final XOPR<Double> DMUL(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Double>(((a, b) -> a * b), OperandStack::popDouble, OperandStack::pushDouble, "dmul");
  }

  public static final XOPR<Integer> IDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Integer>(((a, b) -> a / b), OperandStack::popInt, OperandStack::pushInt, "idiv");
  }

  public static final XOPR<Long> LDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Long>(((a, b) -> a / b), OperandStack::popLong, OperandStack::pushLong, "ldiv");
  }

  public static final XOPR<Float> FDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Float>(((a, b) -> a / b), OperandStack::popFloat, OperandStack::pushFloat, "fdiv");
  }

  public static final XOPR<Double> DDIV(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Double>(((a, b) -> a / b), OperandStack::popDouble, OperandStack::pushDouble, "ddiv");
  }

  public static final XOPR<Integer> IREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Integer>(((a, b) -> a % b), OperandStack::popInt, OperandStack::pushInt, "irem");
  }

  public static final XOPR<Long> LREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Long>(((a, b) -> a % b), OperandStack::popLong, OperandStack::pushLong, "lrem");
  }

  public static final XOPR<Float> FREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Float>(((a, b) -> a % b), OperandStack::popFloat, OperandStack::pushFloat, "frem");
  }

  public static final XOPR<Double> DREM(ProgramCounter pc, MethodInfo method) {
    return new XOPR<Double>(((a, b) -> a % b), OperandStack::popDouble, OperandStack::pushDouble, "drem");
  }

  @Override
  public void run(JThread thread) {
    OperandStack stack = thread.top().stack();
    T value2 = popFunc.apply(stack);
    T value1 = popFunc.apply(stack);
    pushFunc.accept(stack, opFunc.apply(value1, value2)); // assume
  }

  @Override
  public String toString() {
    return name;
  }
}
