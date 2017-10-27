package info.szadkowski.katas.zigzag.recursion;

import info.szadkowski.katas.zigzag.tree.parser.BinaryTreeParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExternalZigZagCountTest {
  private BinaryTreeParser<ExternalZigZagCount.Node> parser;
  private ExternalZigZagCount zigZag;

  @BeforeEach
  void setUp() {
    parser = new BinaryTreeParser<>(ExternalZigZagCount.Node::new);
    zigZag = new ExternalZigZagCount();
  }

  @Test
  void shouldReturnZeroForNullTree() {
    int zigZagCount = zigZag.calculateZigZagCount(null);

    assertThat(zigZagCount).isEqualTo(0);
  }

  @Test
  void shouldFillOneNode() {
    ExternalZigZagCount.Node tree = parser.parse("(5)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(0);
  }

  @Test
  void shouldFillRightNodes() {
    ExternalZigZagCount.Node tree = parser.parse("(5)--+\n" +
                                                 "    (5)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(0);
  }

  @Test
  void shouldFillLeftNodes() {
    ExternalZigZagCount.Node tree = parser.parse(" +---(5)\n" +
                                                 "(5)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(0);
  }

  @Test
  void shouldFillOneZigZagOnRight() {
    ExternalZigZagCount.Node tree = parser.parse("(0)--+\n" +
                                                 "  +-(0)\n" +
                                                 " (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(1);
  }

  @Test
  void shouldFillOneZigZagOnLeft() {
    ExternalZigZagCount.Node tree = parser.parse(" +----(0)\n" +
                                                 "(0)--+\n" +
                                                 "    (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(1);
  }

  @Test
  void shouldFillOneZigZagFromBothSides() {
    ExternalZigZagCount.Node tree = parser.parse(" +----(0)------+\n" +
                                                 "(0)--+     +--(0)\n" +
                                                 "    (0)   (0)--+\n" +
                                                 "              (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(2);
  }

  @Test
  void shouldHandleTwiceRightBranch() {
    ExternalZigZagCount.Node tree = parser.parse(" +----(0)-+\n" +
                                                 "(0)--+   (0)---+\n" +
                                                 "    (0)     +-(0)\n" +
                                                 "           (0)-+\n" +
                                                 "              (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(2);
  }

  @Test
  void shouldHandleTwiceLeftBranch() {
    ExternalZigZagCount.Node tree = parser.parse("    +----(0)-+\n" +
                                                 " +-(0)      (0)\n" +
                                                 "(0)-+\n" +
                                                 "   (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(1);
  }

  @Test
  void shouldChooseRightBranchValues() {
    ExternalZigZagCount.Node tree = parser.parse("          +----(0)\n" +
                                                 " +-------(0)-----------+\n" +
                                                 "(0)--+           +----(0)\n" +
                                                 "    (0)         (0)--+\n" +
                                                 "                    (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(3);
  }

  @Test
  void shouldChooseLeftBranchValues() {
    ExternalZigZagCount.Node tree = parser.parse("          (0)----+\n" +
                                                 " +--------------(0)----+\n" +
                                                 "(0)--+           +----(0)\n" +
                                                 "  +-(0)         (0)\n" +
                                                 " (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(3);
  }
}
