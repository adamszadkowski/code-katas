package info.szadkowski.katas.zigzag.iteration;

import info.szadkowski.katas.zigzag.iteration.IterationZigZag;
import info.szadkowski.katas.zigzag.tree.parser.BinaryTreeParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IterationZigZagTest {
  private BinaryTreeParser<IterationZigZag.Node> parser;
  private IterationZigZag zigZag;

  @BeforeEach
  void setUp() {
    parser = new BinaryTreeParser<>(IterationZigZag.Node::new);
    zigZag = new IterationZigZag();
  }

  @Test
  void shouldReturnZeroForNullTree() {
    int zigZagCount = zigZag.calculateZigZagCount(null);

    assertThat(zigZagCount).isEqualTo(0);
  }

  @Test
  void shouldFillOneNode() {
    IterationZigZag.Node tree = parser.parse("(5)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(0);
  }

  @Test
  void shouldFillRightNodes() {
    IterationZigZag.Node tree = parser.parse("(5)--+\n" +
                                             "    (5)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(0);
  }

  @Test
  void shouldFillLeftNodes() {
    IterationZigZag.Node tree = parser.parse(" +---(5)\n" +
                                             "(5)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(0);
  }

  @Test
  void shouldFillOneZigZagOnRight() {
    IterationZigZag.Node tree = parser.parse("(0)--+\n" +
                                             "  +-(0)\n" +
                                             " (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(1);
  }

  @Test
  void shouldFillOneZigZagOnLeft() {
    IterationZigZag.Node tree = parser.parse(" +----(0)\n" +
                                             "(0)--+\n" +
                                             "    (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(1);
  }

  @Test
  void shouldFillOneZigZagFromBothSides() {
    IterationZigZag.Node tree = parser.parse(" +----(0)------+\n" +
                                             "(0)--+     +--(0)\n" +
                                             "    (0)   (0)--+\n" +
                                             "              (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(2);
  }

  @Test
  void shouldHandleTwiceRightBranch() {
    IterationZigZag.Node tree = parser.parse(" +----(0)-+\n" +
                                             "(0)--+   (0)---+\n" +
                                             "    (0)     +-(0)\n" +
                                             "           (0)-+\n" +
                                             "              (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(2);
  }

  @Test
  void shouldHandleTwiceLeftBranch() {
    IterationZigZag.Node tree = parser.parse("    +----(0)-+\n" +
                                             " +-(0)      (0)\n" +
                                             "(0)-+\n" +
                                             "   (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(1);
  }

  @Test
  void shouldChooseRightBranchValues() {
    IterationZigZag.Node tree = parser.parse("          +----(0)\n" +
                                             " +-------(0)-----------+\n" +
                                             "(0)--+           +----(0)\n" +
                                             "    (0)         (0)--+\n" +
                                             "                    (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(3);
  }

  @Test
  void shouldChooseLeftBranchValues() {
    IterationZigZag.Node tree = parser.parse("          (0)----+\n" +
                                             " +--------------(0)----+\n" +
                                             "(0)--+           +----(0)\n" +
                                             "  +-(0)         (0)\n" +
                                             " (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(3);
  }
}
