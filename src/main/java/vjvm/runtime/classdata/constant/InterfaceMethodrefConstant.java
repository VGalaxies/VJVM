package vjvm.runtime.classdata.constant;

import lombok.SneakyThrows;
import vjvm.runtime.JClass;

import java.io.DataInput;

public class InterfaceMethodrefConstant extends Constant {
  private final int clazzIndex;
  private final int nameAndTypeIndex;
  private final JClass self;
  private String clazz;
  private String name;
  private String type;

  @SneakyThrows
  InterfaceMethodrefConstant(DataInput input, JClass self) {
    clazzIndex = input.readUnsignedShort();
    nameAndTypeIndex = input.readUnsignedShort();
    this.self = self;
  }

  public String clazz() {
    if (clazz == null) {
      clazz = ((ClassConstant) self.constantPool().constant(clazzIndex)).clazz();
    }
    return clazz;
  }

  public String name() {
    if (name == null) {
      name = ((NameAndTypeConstant) self.constantPool().constant(nameAndTypeIndex)).name();
    }
    return name;
  }

  public String type() {
    if (type == null) {
      type = ((NameAndTypeConstant) self.constantPool().constant(nameAndTypeIndex)).type();
    }
    return type;
  }

  @Override
  public String toString() {
    return String.format("InterfaceMethodref: %s.%s:%s", clazz(), name(), type());
  }
}
