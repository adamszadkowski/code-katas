package info.szadkowski.katas.binary.gap;

public class BinaryGapCalculator {
  public int countGaps(int n) {
    return new CalculatorMethod(n).countGaps();
  }

  private static class CalculatorMethod {
    private int number;

    private CalculatorMethod(int number) {
      this.number = number;
    }

    private int countGaps() {
      skipLeadingZeros();

      int gap = 0;

      while (canMove()) {
        int gapCandidate = moveToOnes();
        gap = Math.max(gap, gapCandidate);
      }

      return gap;
    }

    private void skipLeadingZeros() {
      moveToOnes();
    }

    private boolean canMove() {
      number = ~number;
      int moves = moveToOnes();
      number = ~number;
      return moves != 0;
    }

    private int moveToOnes() {
      int moves = 0;
      if (number != 0) {
        while ((number & 0b1) != 0b1) {
          number >>= 1;
          moves++;
        }
      }
      return moves;
    }
  }
}
