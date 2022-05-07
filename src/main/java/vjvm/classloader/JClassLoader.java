package vjvm.classloader;

import lombok.var;
import lombok.Getter;
import lombok.SneakyThrows;
import vjvm.classloader.searchpath.ClassSearchPath;
import vjvm.runtime.JClass;
import vjvm.vm.VMContext;
import vjvm.utils.UnimplementedError;

import java.io.*;
import java.util.HashMap;

public class JClassLoader implements Closeable {
  private final JClassLoader parent;
  private final ClassSearchPath[] searchPaths;
  private final HashMap<String, JClass> definedClass = new HashMap<>();
  @Getter
  private final VMContext context;

  public JClassLoader(JClassLoader parent, ClassSearchPath[] searchPaths, VMContext context) {
    this.context = context;
    this.parent = parent;
    this.searchPaths = searchPaths;
  }

  /**
   * Load class
   *
   * If a class is found, construct it using the data returned by ClassSearchPath.findClass and return it.
   *
   * Otherwise, return null.
   */
  public JClass loadClass(String descriptor) {
//    throw new UnimplementedError("TODO: load class");
    if (definedClass.containsKey(descriptor)) {
      return definedClass.get(descriptor);
    }

    String target = descriptor.substring(1, descriptor.length() - 1) + ".class"; // assume

    if (parent != null) {
      JClass jClass = parent.loadClass(descriptor);
      if (jClass != null) {
        return jClass;
      }
    }

    for (ClassSearchPath path : searchPaths) {
      InputStream istream_from_file = path.findClass(target);
      if (istream_from_file != null) {
        // To construct a JClass, use the following constructor
        JClass jClass = new JClass(new DataInputStream(istream_from_file), this);
        definedClass.put(descriptor, jClass);
        return jClass;
      }
    }

    return null;
  }

  @Override
  @SneakyThrows
  public void close() {
    for (var s : searchPaths)
      s.close();
  }
}
