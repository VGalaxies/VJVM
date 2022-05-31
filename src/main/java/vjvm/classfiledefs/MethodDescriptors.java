package vjvm.classfiledefs;

import lombok.var;
import vjvm.utils.UnimplementedError;
import lombok.var;

import static vjvm.classfiledefs.Descriptors.DESC_array;
import static vjvm.classfiledefs.Descriptors.DESC_reference;

public class MethodDescriptors {
  public static int argc(String descriptor) {
    assert descriptor.startsWith("(");

    // TODO: calculate arguments size in slots
    int now = 1;
    int end = descriptor.indexOf(')');

    int size = 0;
    while (now < end) {
      size += Descriptors.size(descriptor.charAt(now));

      // consume [
      char target = descriptor.charAt(now);
      while (target == DESC_array) {
        now += 1;
        target = descriptor.charAt(now);
      }

      if (target == DESC_reference) {
        now = descriptor.indexOf(';', now) + 1;
      } else {
        now = now + 1;
      }
    }

    return size;
  }

  public static char returnType(String descriptor) {
    assert descriptor.startsWith("(");

    var i = descriptor.indexOf(')') + 1;
    assert i < descriptor.length();
    return descriptor.charAt(i);
  }

  // unit test
  public static void main(String[] args) {
    System.out.println(MethodDescriptors.argc("()I")); // 0
    System.out.println(MethodDescriptors.argc("([CII[CIII)I")); // 7
    System.out.println(MethodDescriptors.argc("([[Ljava/lang/String;I)I")); // 2
    System.out.println(MethodDescriptors.argc("([[Ljava/lang/String;[[Ljava/lang/String;)I")); // 2
    System.out.println(MethodDescriptors.argc("(I[JLjava/lang/String;)V")); // 3
  }
}
