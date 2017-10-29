package info.szadkowski.katas.binary.tree.traversal;

import info.szadkowski.katas.binary.tree.parser.BinaryTreeParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostOrderRecursiveVisitorTest {
  private String visitedNodes = "";

  private BinaryTreeParser<Node> parser;
  private PostOrderRecursiveVisitor<Node> visitor;

  @BeforeEach
  void setUp() {
    parser = new BinaryTreeParser<>(Node::new);
    visitor = new PostOrderRecursiveVisitor<>(Node::getLeft, Node::getRight, Node::visit);
  }

  @Test
  void givenNull_willNotThrow() {
    visitor.visit(null);
  }

  @Test
  void givenOnlyRootNode_willVisit() {
    Node tree = parser.parse(" (A) ");
    visitor.visit(tree);
    assertThat(visitedNodes).isEqualTo("A");
  }

  @Test
  void givenLeftNode_willVisit() {
    Node tree = parser.parse("  +-(B) \n" +
                             "  |     \n" +
                             " (A)      ");
    visitor.visit(tree);
    assertThat(visitedNodes).isEqualTo("AB");
  }

  @Test
  void givenTwoLeftNode_willVisit() {
    Node tree = parser.parse("     +-(C) \n" +
                             "     |     \n" +
                             "  +-(B)    \n" +
                             "  |        \n" +
                             " (A)         ");
    visitor.visit(tree);
    assertThat(visitedNodes).isEqualTo("ABC");
  }

  @Test
  void givenOnlyRightNode_willVisit() {
    Node tree = parser.parse(" (B)-+  \n" +
                             "     |  \n" +
                             "    (A) ");
    visitor.visit(tree);
    assertThat(visitedNodes).isEqualTo("AB");
  }

  @Test
  void givenLeftAndRightNodes_willVisitLeftFirst() {
    Node tree = parser.parse("  +-(C)-+  \n" +
                             "  |     |  \n" +
                             " (A)   (B) ");
    visitor.visit(tree);
    assertThat(visitedNodes).isEqualTo("ABC");
  }

  @Test
  void givenTwoLeftAndRightNodes_willVisitLeftFirst() {
    Node tree = parser.parse("     +-(D)-+  \n" +
                             "     |     |  \n" +
                             "  +-(B)   (C) \n" +
                             "  |           \n" +
                             " (A)            ");
    visitor.visit(tree);
    assertThat(visitedNodes).isEqualTo("ABCD");
  }

  @Test
  void integration() {
    Node tree = parser.parse("            +------(J)-+         \n" +
                             "            |          |         \n" +
                             "     +-----(E)-+      (I)--+     \n" +
                             "     |         |           |     \n" +
                             "  +-(C)-+     (D)       +-(H)-+  \n" +
                             "  |     |               |     |  \n" +
                             " (A)   (B)             (F)   (G)   ");
    visitor.visit(tree);
    assertThat(visitedNodes).isEqualTo("ABCDEFGHIJ");
  }

  class Node {
    private final Node left;
    private final Node right;
    private final String value;

    Node(Node left, Node right, String value) {
      this.left = left;
      this.right = right;
      this.value = value;
    }

    Node getLeft() {
      return left;
    }

    Node getRight() {
      return right;
    }

    void visit() {
      visitedNodes += value;
    }
  }
}
