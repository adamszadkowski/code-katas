package info.szadkowski.katas.zigzag.recursion;

import info.szadkowski.katas.zigzag.tree.parser.BinaryTreeParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NodeBasedCountZigZagTest {
  private BinaryTreeParser<NodeBasedCountZigZag.Node> parser;
  private NodeBasedCountZigZag zigZag;

  @BeforeEach
  void setUp() {
    parser = new BinaryTreeParser<>((l, r, s) -> new NodeBasedCountZigZag.Node(l, r, Integer.parseInt(s)));
    zigZag = new NodeBasedCountZigZag();
  }

  @Test
  void shouldCompareEqualTrees() {
    String tree = "     +----(0)--+\n" +
                  "  +-(1)     +-(2)----+\n" +
                  " (3)-+     (4)    +-(5)\n" +
                  "    (6)        +-(7)\n" +
                  "              (8)";

    NodeBasedCountZigZag.Node first = parser.parse(tree);
    NodeBasedCountZigZag.Node second = parser.parse(tree);

    assertThat(second).isEqualTo(first);
  }

  @Test
  void shouldCompareDifferentTrees() {
    NodeBasedCountZigZag.Node first = parser.parse("     +----(0)--+\n" +
                                                   "  +-(1)     +-(2)----+\n" +
                                                   " (3)-+     (4)    +-(5)\n" +
                                                   "    (6)        +-(7)\n" +
                                                   "              (8)");
    NodeBasedCountZigZag.Node second = parser.parse("     +----(0)--+\n" +
                                                    "  +-(1)     +-(2)----+\n" +
                                                    " (3)-+     (4)    +-(5)\n" +
                                                    "    (9)        +-(7)\n" +
                                                    "              (8)");

    assertThat(second).isNotEqualTo(first);
  }

  @Test
  void shouldReturnZeroForNullTree() {
    int zigZagCount = zigZag.calculateZigZagCount(null);

    assertThat(zigZagCount).isEqualTo(0);
  }

  @Test
  void shouldFillOneNode() {
    NodeBasedCountZigZag.Node tree = parser.parse("(5)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(0);
    assertTree(tree, "(0)");
  }

  @Test
  void shouldFillRightNodes() {
    NodeBasedCountZigZag.Node tree = parser.parse("(5)--+\n" +
                                                  "    (5)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(0);
    assertTree(tree, "(0)--+\n" +
                     "    (0)");
  }

  @Test
  void shouldFillLeftNodes() {
    NodeBasedCountZigZag.Node tree = parser.parse(" +---(5)\n" +
                                                  "(5)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(0);
    assertTree(tree, " +---(0)\n" +
                     "(0)");
  }

  @Test
  void shouldFillOneZigZagOnRight() {
    NodeBasedCountZigZag.Node tree = parser.parse("(0)--+\n" +
                                                  "  +-(0)\n" +
                                                  " (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(1);
    assertTree(tree, "(1)--+\n" +
                     "  +-(1)\n" +
                     " (0)");
  }

  @Test
  void shouldFillOneZigZagOnLeft() {
    NodeBasedCountZigZag.Node tree = parser.parse(" +----(0)\n" +
                                                  "(0)--+\n" +
                                                  "    (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(1);
    assertTree(tree, " +----(1)\n" +
                     "(1)--+\n" +
                     "    (0)");
  }

  @Test
  void shouldFillOneZigZagFromBothSides() {
    NodeBasedCountZigZag.Node tree = parser.parse(" +----(0)------+\n" +
                                                  "(0)--+     +--(0)\n" +
                                                  "    (0)   (0)--+\n" +
                                                  "              (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(2);
    assertTree(tree, " +----(2)------+\n" +
                     "(1)--+      +-(2)\n" +
                     "    (0)    (1)--+\n" +
                     "               (0)");
  }

  @Test
  void shouldHandleTwiceRightBranch() {
    NodeBasedCountZigZag.Node tree = parser.parse(" +----(0)-+\n" +
                                                  "(0)--+   (0)---+\n" +
                                                  "    (0)     +-(0)\n" +
                                                  "           (0)-+\n" +
                                                  "              (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(2);
    assertTree(tree, " +----(2)-+\n" +
                     "(1)--+   (2)---+\n" +
                     "    (0)     +-(2)\n" +
                     "           (1)-+\n" +
                     "              (0)");
  }

  @Test
  void shouldHandleTwiceLeftBranch() {
    NodeBasedCountZigZag.Node tree = parser.parse("    +----(0)-+\n" +
                                                  " +-(0)      (0)\n" +
                                                  "(0)-+\n" +
                                                  "   (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(1);
    assertTree(tree, "    +----(1)-+\n" +
                     " +-(1)      (0)\n" +
                     "(1)-+\n" +
                     "   (0)");
  }

  @Test
  void shouldChooseRightBranchValues() {
    NodeBasedCountZigZag.Node tree = parser.parse("          +----(0)\n" +
                                                  " +-------(0)-----------+\n" +
                                                  "(0)--+           +----(0)\n" +
                                                  "    (0)         (0)--+\n" +
                                                  "                    (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(3);
    assertTree(tree, "          +----(3)\n" +
                     " +-------(3)-----------+\n" +
                     "(1)--+           +----(2)\n" +
                     "    (0)         (1)--+\n" +
                     "                    (0)");
  }

  @Test
  void shouldChooseLeftBranchValues() {
    NodeBasedCountZigZag.Node tree = parser.parse("          (0)----+\n" +
                                                  " +--------------(0)----+\n" +
                                                  "(0)--+           +----(0)\n" +
                                                  "  +-(0)         (0)\n" +
                                                  " (0)");

    int zigZagCount = zigZag.calculateZigZagCount(tree);

    assertThat(zigZagCount).isEqualTo(3);
    assertTree(tree, "          (3)----+\n" +
                     " +--------------(3)----+\n" +
                     "(2)--+           +----(1)\n" +
                     "  +-(1)         (0)\n" +
                     " (0)");
  }

  private void assertTree(NodeBasedCountZigZag.Node filled, String expected) {
    assertThat(filled).isEqualTo(parser.parse(expected));
  }
}
