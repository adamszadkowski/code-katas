package info.szadkowski.katas.max.counters;

public class MaxCounters {
  public int[] count(int counter, int[] operations) {
    int[] counters = new int[counter];

    int currentMaxCounter = 0;
    int updateCounter = 0;

    for (int operation : operations) {
      int counterIndex = operation - 1;
      if (isSetMaxCounterOperation(counterIndex, counter))
        updateCounter = currentMaxCounter;
      else {
        if (counters[counterIndex] < updateCounter)
          counters[counterIndex] = updateCounter + 1;
        else
          counters[counterIndex]++;

        currentMaxCounter = Math.max(counters[counterIndex], currentMaxCounter);
      }
    }

    for (int i = 0; i < counter; i++)
      if (counters[i] < updateCounter)
        counters[i] = updateCounter;

    return counters;
  }

  private boolean isSetMaxCounterOperation(int counterIndex, int counter) {
    return counterIndex == counter;
  }
}
