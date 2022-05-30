package vjvm.runtime;

import vjvm.classloader.JClassLoader;
import vjvm.runtime.classdata.ConstantPool;
import vjvm.runtime.classdata.FieldInfo;
import vjvm.runtime.classdata.MethodInfo;
import vjvm.runtime.classdata.attribute.Attribute;
import vjvm.runtime.classdata.constant.ClassConstant;
import vjvm.utils.UnimplementedError;
import java.io.DataInput;
import java.io.InvalidClassException;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.var;

import static vjvm.classfiledefs.ClassAccessFlags.*;

public class JClass {
  @Getter
  private final JClassLoader classLoader;
  @Getter
  private final int minorVersion;
  @Getter
  private final int majorVersion;
  @Getter
  private final ConstantPool constantPool;
  @Getter
  private final int accessFlags;

  @Getter
  private final String thisClazz;
  @Getter
  private final String superClazz;
  @Getter
  private final String[] interfaces;

  private final FieldInfo[] fields;
  private final MethodInfo[] methods;
  private final Attribute[] attributes;

  @SneakyThrows
  public JClass(DataInput dataInput, JClassLoader classLoader) {
    this.classLoader = classLoader;

    // check magic number
    var magic = dataInput.readInt();
    if (magic != 0xcafebabe) {
      throw new InvalidClassException(String.format(
        "Wrong magic number, expected: 0xcafebabe, got: 0x%x", magic));
    }

    minorVersion = dataInput.readUnsignedShort();
    majorVersion = dataInput.readUnsignedShort();

    constantPool = new ConstantPool(dataInput, this);
    accessFlags = dataInput.readUnsignedShort();

    // TODO: you need to construct thisClass, superClass, interfaces, fields,
    //  methods, and attributes from dataInput in lab 1.2; remove this `UnimplementedError` for lab 1.1

    thisClazz = ((ClassConstant) constantPool.constant(dataInput.readUnsignedShort())).clazz();
    superClazz = ((ClassConstant) constantPool.constant(dataInput.readUnsignedShort())).clazz();

    interfaces = new String[dataInput.readUnsignedShort()];
    for (int i = 0 ; i < interfaces.length; ++i) {
      interfaces[i] = ((ClassConstant) constantPool.constant(dataInput.readUnsignedShort())).clazz();
    }

    fields = new FieldInfo[dataInput.readUnsignedShort()];
    for (int i = 0 ; i < fields.length; ++i) {
      fields[i] = new FieldInfo(dataInput, this);
    }

    methods = new MethodInfo[dataInput.readUnsignedShort()];
    for (int i = 0; i < methods.length; ++i) {
      methods[i] = new MethodInfo(dataInput, this);
    }

    attributes = new Attribute[dataInput.readUnsignedShort()];
    for (int i = 0; i < attributes.length; ++i) {
      attributes[i] = Attribute.constructFromData(dataInput, constantPool);
    }
  }

  public MethodInfo findMethod(String name, String descriptor) {
    for (var method : methods)
      if (method.name().equals(name) && method.descriptor().equals(descriptor))
        return method;

    return null;
  }

  public boolean public_() {
    return (accessFlags & ACC_PUBLIC) != 0;
  }

  public boolean final_() {
    return (accessFlags & ACC_FINAL) != 0;
  }

  public boolean super_() {
    return (accessFlags & ACC_SUPER) != 0;
  }

  public boolean interface_() {
    return (accessFlags & ACC_INTERFACE) != 0;
  }

  public boolean abstract_() {
    return (accessFlags & ACC_ABSTRACT) != 0;
  }

  public boolean synthetic() {
    return (accessFlags & ACC_SYNTHETIC) != 0;
  }

  public boolean annotation() {
    return (accessFlags & ACC_ANNOTATION) != 0;
  }

  public boolean enum_() {
    return (accessFlags & ACC_ENUM) != 0;
  }

  public boolean module() {
    return (accessFlags & ACC_MODULE) != 0;
  }

  public int fieldsCount() {
    return fields.length;
  }

  public FieldInfo field(int index) {
    return fields[index];
  }

  public int methodsCount() {
    return methods.length;
  }

  public MethodInfo method(int index) {
    return methods[index];
  }

  public String name() {
    // TODO: return class name from thisClass
    return thisClazz;
  }
}
