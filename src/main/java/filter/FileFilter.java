package filter;

import java.util.List;

public interface FileFilter {
  public List<String> filter(List<String> listToFilter);
}
