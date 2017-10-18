package info.szadkowski.katas.binary.gap;

public class BinaryGapCalculator {
  public int countGaps(int n) {
    long minValue = findFirstOne(n);

    int gap = 0;
    int counter = 0;

    for (long i = minValue; i != 0x100000000L; i <<= 1) {
      if ((n & i) == i) {
        gap = Math.max(gap, counter);
        counter = 0;
      } else {
        counter++;
      }
    }

    return gap;
  }

  private long findFirstOne(int n) {
    for (long i = 0b1; i != 0x100000000L; i <<= 1) {
      if ((n & i) == i) {
        return i;
      }
    }

    return 0x100000000L;
  }
}
