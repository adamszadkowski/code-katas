package info.szadkowski.katas.binary.tree.printer;

import java.util.List;
import java.util.function.Function;

public class BinaryTreePrinter<T> {
  static final int nodeParenthesisSize = 2;

  private final Function<T, T> leftAccessor;
  private final Function<T, T> rightAccessor;
  private final Function<T, String> nodeSerializer;

  public BinaryTreePrinter(Function<T, T> leftAccessor,
                           Function<T, T> rightAccessor,
                           Function<T, String> nodeSerializer) {
    this.leftAccessor = leftAccessor;
    this.rightAccessor = rightAccessor;
    this.nodeSerializer = nodeSerializer;
  }

  public String print(T root) {
    if (root == null)
      return "";

    Node tree = convert(root);
    int width = calculateWidth(tree);

    StringBuilder builder = new StringBuilder();
    for (List<Node> nodesInLine : tree) {
      builder.append(formatNodesLine(nodesInLine, width))
              .append(formatConnectorLine(nodesInLine, width));
    }

    return builder.toString();
  }

  private Node convert(T node) {
    return new Converter().convert(node);
  }

  private int calculateWidth(Node tree) {
    Node right = tree;

    while (right.right != null)
      right = right.right;

    return right.pos + right.serialized.length() + nodeParenthesisSize;
  }

  private String formatNodesLine(List<Node> nodes, int width) {
    return new LineFormatter('+', '-', n -> String.format("(%s)", n.serialized)).formatLine(nodes, width);
  }

  private String formatConnectorLine(List<Node> nodes, int width) {
    return new LineFormatter('|', ' ', n -> "").formatLine(nodes, width);
  }

  private class Converter {
    int pos = 0;

    private Node convert(T node) {
      if (node == null)
        return null;

      Node left = convert(leftAccessor.apply(node));
      int currentPos = pos;
      String serialized = nodeSerializer.apply(node);
      pos += nodeParenthesisSize + serialized.length();
      Node right = convert(rightAccessor.apply(node));
      return new Node(left, right, currentPos, serialized);
    }
  }
}
