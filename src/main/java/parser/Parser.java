package parser;

import category.YearlyCategory;

import java.util.List;
import java.util.stream.Collectors;

public interface Parser {
  YearlyCategory parse(String categoryTime, String string);

  default List<YearlyCategory> parse(String categoryTime, List<String> strings) {
    List<YearlyCategory> yearlyCategories = strings.stream()
            .map(x -> parse(categoryTime, x))
            .collect(Collectors.toList());
    return yearlyCategories;
  }

}
