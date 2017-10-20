package info.szadkowski.katas.tape.equilibrium;

public class TapeEquilibrium {
  public int find(int[] elements) {
    int[] onLeft = sumFromLeft(elements);
    sumFromRight(elements);

    int min = Math.abs(onLeft[0] - elements[1]);

    for (int i = 1; i < elements.length - 2; i++) {
      int current = Math.abs(onLeft[i] - elements[i + 1]);
      min = Math.min(min, current);
    }

    return min;
  }

  private void sumFromRight(int[] elements) {
    for (int i = elements.length - 1; i > 0; i--)
      elements[i - 1] += elements[i];
  }

  private int[] sumFromLeft(int[] elements) {
    int[] onLeft = new int[elements.length];
    onLeft[0] = elements[0];

    for (int i = 1; i < elements.length; i++)
      onLeft[i] = elements[i] + onLeft[i - 1];

    return onLeft;
  }
}
