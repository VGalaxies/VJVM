package vjvm.classloader.searchpath;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class JarSearchPath extends ClassSearchPath {
  private final JarFile jarFile;

  @Override
  public InputStream findClass(String name) {
    InputStream in = null;
    ZipEntry entry = jarFile.getEntry(name);
    if (entry != null) {
      try {
        in = jarFile.getInputStream(entry);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return in;
  }

  public JarSearchPath(String jarPath) {
    JarFile file = null;
    try {
      file = new JarFile(jarPath);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.jarFile = file;
  }

  @Override
  public void close() throws IOException {

  }
}
