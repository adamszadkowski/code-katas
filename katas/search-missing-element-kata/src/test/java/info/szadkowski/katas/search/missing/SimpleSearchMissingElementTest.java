package info.szadkowski.katas.search.missing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimpleSearchMissingElementTest {
  private SimpleSearchMissingElement searcher;

  @BeforeEach
  void setUp() {
    searcher = new SimpleSearchMissingElement();
  }

  @Test
  void givenNull_willThrow() {
    assertThrows(IllegalArgumentException.class, () -> {
      searcher.search(null);
    });
  }

  @Test
  void givenEmptyList_willReturn() {
    int[] elements = {};

    int missing = searcher.search(elements);

    assertThat(missing).isEqualTo(1);
  }

  @Test
  void givenOnlyOne_willReturnIncrementedValue() {
    int[] elements = {1};

    int missing = searcher.search(elements);

    assertThat(missing).isEqualTo(2);
  }

  @Test
  void givenOnlyTwo_willReturnFirstValue() {
    int[] elements = {2};

    int missing = searcher.search(elements);

    assertThat(missing).isEqualTo(1);
  }

  @Test
  void givenOnlyThree_willThrow() {
    int[] elements = {3};

    assertThrows(IllegalArgumentException.class, () -> {
      searcher.search(elements);
    });
  }

  @ParameterizedTest
  @MethodSource("sortedElements")
  void givenSortedElements_willReturn(int[] elements, int expectedMissing) {
    int missing = searcher.search(elements);
    assertThat(missing).isEqualTo(expectedMissing);
  }

  private static Stream<Arguments> sortedElements() {
    return Stream.of(Arguments.of(new int[]{1, 2}, 3),
                     Arguments.of(new int[]{1, 2, 4}, 3),
                     Arguments.of(new int[]{2, 3, 4}, 1));
  }

  @ParameterizedTest
  @MethodSource("randomElements")
  void givenRandomElements_willReturn(int[] elements, int expectedMissing) {
    int missing = searcher.search(elements);
    assertThat(missing).isEqualTo(expectedMissing);
  }

  private static Stream<Arguments> randomElements() {
    return Stream.of(Arguments.of(new int[]{2, 1}, 3),
                     Arguments.of(new int[]{4, 2, 1}, 3),
                     Arguments.of(new int[]{2, 4, 3}, 1));
  }
}
