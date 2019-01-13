package filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuickenFilter implements FileFilter {
  @Override
  public List<String> filter(List<String> listToFilter) {
    List<String> filtered1 = keepRelevantLines(listToFilter);
    List<String> filtered2 = removeFirstInstanceOfTab(filtered1);
    return filtered2;
  }

  private List<String> keepRelevantLines(List<String> listToFilter) {
    List<String> filteredList = new ArrayList<>();
    int indexToGrab = 0;
    int indexToStartGrabbingAfter = 0;
    int indexToEndGrabbingBefore = 0;
    int numberOfEntries = listToFilter.size();
    for (int i = 0; i < numberOfEntries; ++i) {

      String entry = listToFilter.get(i);
      if (entry.contains("Category")) {
        indexToGrab = i;
      }
      if (entry.contains("EXPENSES")) {
        indexToStartGrabbingAfter = i + 1;
      }
      if (entry.contains("Net Difference:")) {
        indexToEndGrabbingBefore = i - 2;
      }
    }

    filteredList.add(listToFilter.get(indexToGrab));
    for (int i = indexToStartGrabbingAfter; i <= indexToEndGrabbingBefore; ++i) {
      filteredList.add(listToFilter.get(i));
    }
    return filteredList;
  }

  private List<String> removeFirstInstanceOfTab(List<String> listToFilter) {
    return listToFilter.stream().map(x -> x.replaceFirst("\t", "")).collect(Collectors.toList());
  }
}
