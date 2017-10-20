package info.szadkowski.katas.tape.equilibrium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TapeEquilibriumTest {
  private TapeEquilibrium finder;

  @BeforeEach
  void setUp() {
    finder = new TapeEquilibrium();
  }

  @ParameterizedTest(name = "{index} ==> For array {0} equilibrium should be {1}")
  @MethodSource("data")
  void givenTwoElementArray_willReturn(int[] elements, int expectedEquilibrium) {
    int equilibrium = finder.find(elements);

    assertThat(equilibrium).isEqualTo(expectedEquilibrium);
  }

  private static Stream<Arguments> data() {
    return Stream.of(Arguments.of(new int[]{0, 0}, 0),
                     Arguments.of(new int[]{1, 1}, 0),
                     Arguments.of(new int[]{0, 1}, 1),
                     Arguments.of(new int[]{-1, 0}, 1),
                     Arguments.of(new int[]{-10, 2}, 12),
                     Arguments.of(new int[]{0, 0, 0}, 0),
                     Arguments.of(new int[]{1, 0, 0}, 1),
                     Arguments.of(new int[]{0, 1, 0}, 1),
                     Arguments.of(new int[]{0, 0, 1}, 1),
                     Arguments.of(new int[]{3, 1, 2, 4, 3}, 1));
  }
}
