package info.szadkowski.katas.binary.tree.printer;

import info.szadkowski.katas.binary.tree.parser.BinaryTreeParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryTreePrinterTest {
  private BinaryTreePrinter<Node> printer;
  private BinaryTreeParser<Node> parser;

  @BeforeEach
  void setUp() {
    printer = new BinaryTreePrinter<>(Node::getLeft, Node::getRight, Node::getValue);
    parser = new BinaryTreeParser<>(Node::new);
  }

  @Test
  void shouldPrintEmptyTree() {
    String tree = printer.print(null);
    assertThat(tree).isEqualTo("");
  }

  @ParameterizedTest
  @MethodSource("trees")
  void shouldPrint(String visualized) {
    Node node = parser.parse(visualized);

    String tree = printer.print(node);

    assertThat(tree).isEqualTo(visualized);
  }

  private static Stream<String> trees() {
    return Stream.of(
            " () \n",

            " (c) \n",

            " (center) \n",

            " +-() \n" +
            " |    \n" +
            " ()   \n",

            "  +-(c) \n" +
            "  |     \n" +
            " (l)    \n",

            "  +--(cc) \n" +
            "  |       \n" +
            " (ll)     \n",

            " ()-+ \n" +
            "    | \n" +
            "   () \n",

            " (c)-+  \n" +
            "     |  \n" +
            "    (r) \n",

            " (cc)--+  \n" +
            "       |  \n" +
            "     (rr) \n",

            " +-()-+ \n" +
            " |    | \n" +
            " ()  () \n",

            "  +-(c)-+  \n" +
            "  |     |  \n" +
            " (l)   (r) \n",

            "  +--(cc)--+  \n" +
            "  |        |  \n" +
            " (ll)    (rr) \n",

            "   +---()-+ \n" +
            "   |      | \n" +
            " +-()-+  () \n" +
            " |    |     \n" +
            " ()  ()     \n",

            "      +-----(c)-+  \n" +
            "      |         |  \n" +
            "  +--(l)--+    (r) \n" +
            "  |       |        \n" +
            " (ll)   (rr)       \n",

            "   +---()---+   \n" +
            "   |        |   \n" +
            " +-()-+  +-()-+ \n" +
            " |    |  |    | \n" +
            " ()  ()  ()  () \n",

            "      +-----(c)------+       \n" +
            "      |              |       \n" +
            "  +--(l)--+      +--(r)--+   \n" +
            "  |       |      |       |   \n" +
            " (ll)   (rr)   (lll)   (rrr) \n",

            "   +----------(integration)-+                   \n" +
            "   |                        |                   \n" +
            " (left)---+                (a)---------+        \n" +
            "          |                            |        \n" +
            "       (right)                  +---(next)--+   \n" +
            "                                |           |   \n" +
            "                              (node)      (end) \n"
    );
  }

  private class Node {
    private final Node left;
    private final Node right;
    private final String value;

    private Node(Node left, Node right, String value) {
      this.left = left;
      this.right = right;
      this.value = value;
    }

    private Node getLeft() {
      return left;
    }

    private Node getRight() {
      return right;
    }

    private String getValue() {
      return value;
    }
  }
}
