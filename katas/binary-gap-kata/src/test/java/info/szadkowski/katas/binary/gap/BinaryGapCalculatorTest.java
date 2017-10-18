package info.szadkowski.katas.binary.gap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryGapCalculatorTest {
  private BinaryGapCalculator binaryGapCalculator;

  @BeforeEach
  void setUp() {
    binaryGapCalculator = new BinaryGapCalculator();
  }

  @ParameterizedTest
  @MethodSource("gapData")
  void shouldCalculateGap(int number, int expectedGaps) {
    int gaps = binaryGapCalculator.countGaps(number);

    assertThat(gaps).isEqualTo(expectedGaps);
  }

  private static Stream<Arguments> gapData() {
    return Stream.of(
            Arguments.of(0b0, 0),
            Arguments.of(0b1, 0),
            Arguments.of(0b10, 0),
            Arguments.of(0b110, 0),
            Arguments.of(0b101, 1),
            Arguments.of(0b100101, 2),
            Arguments.of(0b10000100101, 4),
            Arguments.of(0b100001001000001, 5),
            Arguments.of(0b101010101010101, 1),
            Arguments.of(0b101000, 1),
            Arguments.of(0b101001000, 2),
            Arguments.of(0b10000000000000000000000000000001, 30),
            Arguments.of(0b10000000000000000000000000000000, 0),
            Arguments.of(0b11111111111111111111111111111111, 0)
    );
  }
}
