package info.szadkowski.katas.cyclic.rotation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CyclicRotationTest {
  private CyclicRotation rotator;

  @BeforeEach
  void setUp() {
    rotator = new CyclicRotation();
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2, 3, 4})
  void givenEmptyElements_willNotRotate(int shift) {
    int[] elements = {};

    int[] rotated = rotator.rotate(elements, shift);

    assertThat(rotated).isEqualTo(elements);
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, 2, 3, 4})
  void givenOneElement_willNotRotate(int shift) {
    int[] elements = {1};

    int[] rotated = rotator.rotate(elements, shift);

    assertThat(rotated).isEqualTo(elements);
  }

  @Test
  void givenOneRotation_willNotRotate() {
    int[] elements = {1, 2, 3, 4};

    int[] rotated = rotator.rotate(elements, 1);

    assertThat(rotated).isEqualTo(elements);
  }

  @ParameterizedTest
  @ValueSource(ints = {4, 8, 12, 16})
  void givenRotationAsElementsLengthMultiplicationPlusOne_willNotRotate(int shift) {
    int[] elements = {1, 2, 3, 4};

    int[] rotated = rotator.rotate(elements, shift);

    assertThat(rotated).isEqualTo(elements);
  }

  @Test
  void givenTwoElements_andRotationBy1_willRotate() {
    int[] elements = {1, 2};

    int[] rotated = rotator.rotate(elements, 1);

    assertThat(rotated).containsExactly(2, 1);
  }

  @Test
  void givenTwoElements_andRotationBy3_willRotate() {
    int[] elements = {1, 2};

    int[] rotated = rotator.rotate(elements, 3);

    assertThat(rotated).containsExactly(2, 1);
  }

  @Test
  void givenThreeElements_andRotationBy2_willRotate() {
    int[] elements = {1, 2, 3};

    int[] rotated = rotator.rotate(elements, 1);

    assertThat(rotated).containsExactly(3, 1, 2);
  }

  @Test
  void givenFourElements_andRotationBy1_willRotate() {
    int[] elements = {1, 2, 3, 4};

    int[] rotated = rotator.rotate(elements, 1);

    assertThat(rotated).containsExactly(4, 1, 2, 3);
  }

  @Test
  void givenFiveElements_andRotationBy2_willRotate() {
    int[] elements = {1, 2, 3, 4, 5};

    int[] rotated = rotator.rotate(elements, 2);

    assertThat(rotated).containsExactly(4, 5, 1, 2, 3);
  }

  @Test
  void givenFiveElements_andRotationBy8_willRotate() {
    int[] elements = {1, 2, 3, 4, 5, 6};

    int[] rotated = rotator.rotate(elements, 10);

    assertThat(rotated).containsExactly(3, 4, 5, 6, 1, 2);
  }
}
