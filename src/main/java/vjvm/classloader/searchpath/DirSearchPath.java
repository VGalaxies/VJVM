package vjvm.classloader.searchpath;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class DirSearchPath extends ClassSearchPath {
  private final Path path;

  @Override
  public InputStream findClass(String name) {
    InputStream in = null;
    try {
      for (Path path : Files.walk(path).collect(Collectors.toList())) {
        if (path.endsWith(name)) {
            in = new FileInputStream(path.toString());
            break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return in;
  }

  public DirSearchPath(String dirPath) {
    this.path = Paths.get(dirPath);
  }

  @Override
  public void close() throws IOException {

  }
}
