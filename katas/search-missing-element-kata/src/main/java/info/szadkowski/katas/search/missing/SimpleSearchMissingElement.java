package info.szadkowski.katas.search.missing;

public class SimpleSearchMissingElement {
  public int search(int[] elements) {
    if (elements == null)
      throw new IllegalArgumentException("");

    int actual = 0;
    for (int element : elements) {
      if (element > elements.length + 1)
        throw new IllegalArgumentException("");
      actual ^= element;
    }

    int expected = 0;
    for (int i = 1; i < elements.length + 2; i++)
      expected ^= i;

    return actual ^ expected;
  }
}
