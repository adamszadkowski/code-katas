package info.szadkowski.katas.cyclic.rotation;

public class CyclicRotation {
  public int[] rotate(int[] elements, int shift) {
    if (elements == null || elements.length == 0) {
      return elements;
    }

    shift %= elements.length;

    int lengthOfFirstPart = elements.length - shift;

    inverseElementsInArray(elements, 0, lengthOfFirstPart - 1);
    inverseElementsInArray(elements, lengthOfFirstPart, elements.length - 1);
    inverseElementsInArray(elements, 0, elements.length - 1);

    return elements;
  }

  public void inverseElementsInArray(int[] elements, int left, int right) {
    for (; left < right; left++, right--) {
      int element = elements[left];
      elements[left] = elements[right];
      elements[right] = element;
    }
  }
}
