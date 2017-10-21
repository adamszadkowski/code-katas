package info.szadkowski.katas.max.counters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MaxCountersTest {
  private MaxCounters maxCounters;
  private int counter;

  @BeforeEach
  void setUp() {
    maxCounters = new MaxCounters();
  }

  @Nested
  class OneCounter {

    @BeforeEach
    void setUp() {
      counter = 1;
    }

    @Test
    void givenOneIncrementOperation_willIncrement() {
      int[] operations = {counter};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(1);
    }

    @Test
    void givenOneMaxCounterOperation_willDoNothing() {
      int[] operations = {counter + 1};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(0);
    }

    @Test
    void givenTwoIncrementOperations_willIncrement() {
      int[] operations = {
              counter,
              counter};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(2);
    }

    @Test
    void givenTwoMaxCounterOperations_willDoNothing() {
      int[] operations = {
              counter + 1,
              counter + 1};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(0);
    }

    @Test
    void givenIncrementAndMaxCounterOperations_willIncrementOnce() {
      int[] operations = {
              counter,
              counter + 1};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(1);
    }

  }

  @Nested
  class TwoCounters {

    @BeforeEach
    void setUp() {
      counter = 2;
    }

    @Test
    void givenFirstCounterIncrementOperation_willIncrement() {
      int[] operations = {1};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(1, 0);
    }

    @Test
    void givenFirstCounterTwoIncrementOperations_willIncrement() {
      int[] operations = {1, 1};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(2, 0);
    }

    @Test
    void givenSecondCounterIncrementOperation_willIncrement() {
      int[] operations = {2};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(0, 1);
    }

    @Test
    void givenSecondCounterTwoIncrementOperations_willIncrement() {
      int[] operations = {2, 2};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(0, 2);
    }

    @Test
    void givenOnlyIncrementOperations_willIncrement() {
      int[] operations = {1, 2, 1, 1, 2, 2, 1};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(4, 3);
    }

    @Test
    void givenMaxCounterOperation_willDoNothing() {
      int[] operations = {counter + 1};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(0, 0);
    }

    @Test
    void givenTwoMaxCounterOperations_willDoNothing() {
      int[] operations = {
              counter + 1,
              counter + 1};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(0, 0);
    }

    @Test
    void givenIncrementAndMaxCounterOperations_willIncrementOnce() {
      int[] operations = {
              counter,
              counter + 1};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(1, 1);
    }

    @Test
    void integration() {
      int[] operations = {
              1,
              counter + 1,
              2,
              counter + 1};

      int[] counters = maxCounters.count(counter, operations);

      assertThat(counters).containsExactly(2, 2);
    }
  }

  @Test
  void integration() {
    int[] operations = {3, 4, 4, 6, 1, 4, 4};

    int[] counters = maxCounters.count(5, operations);

    assertThat(counters).containsExactly(3, 2, 2, 4, 2);
  }
}
