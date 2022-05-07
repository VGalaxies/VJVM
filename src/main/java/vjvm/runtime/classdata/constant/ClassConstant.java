package vjvm.runtime.classdata.constant;

import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class ClassConstant extends Constant {
  @Getter
  private final int clazzIndex;
  private final JClass self;
  private String clazz;

  @SneakyThrows
  ClassConstant(DataInput input, JClass self) {
    clazzIndex = input.readUnsignedShort();
    this.self = self;
  }

  public String clazz() {
    if (clazz == null) {
      clazz = ((UTF8Constant) self.constantPool().constant(clazzIndex)).value();
    }
    return clazz;
  }

  @Override
  public String toString() {
    return String.format("Class: \"%s\"", clazz());
  }
}
