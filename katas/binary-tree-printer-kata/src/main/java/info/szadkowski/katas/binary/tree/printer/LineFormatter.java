package info.szadkowski.katas.binary.tree.printer;

import java.util.List;
import java.util.function.Function;

class LineFormatter {
  private final StringBuilder builder = new StringBuilder();

  private final char connectorChar;
  private final char routeChar;
  private final Function<Node, String> nodeSerializer;

  LineFormatter(char connectorChar, char routeChar, Function<Node, String> nodeSerializer) {
    this.connectorChar = connectorChar;
    this.routeChar = routeChar;
    this.nodeSerializer = nodeSerializer;
  }

  String formatLine(List<Node> nodes, int width) {
    for (Node node : nodes)
      printNode(node);

    fillWith(' ', width);

    String line = builder.toString();
    return !line.trim().isEmpty() ? String.format(" %s \n", line) : "";
  }

  private void printNode(Node node) {
    if (node.left != null)
      printLeftConnector(node);

    fillWith(' ', node.pos);
    printSerializedNode(node);

    if (node.right != null)
      printRightConnector(builder, node);
  }

  private void printLeftConnector(Node node) {
    fillWith(' ', getLeftConnectorPosition(node));
    builder.append(connectorChar);
    fillWith(routeChar, node.pos);
  }

  private int getLeftConnectorPosition(Node node) {
    int floorRoundForce = 1;
    int distance = node.left.serialized.length() + BinaryTreePrinter.nodeParenthesisSize - floorRoundForce;
    return distance / 2 + node.left.pos;
  }

  private void fillWith(char with, int toPosition) {
    while (builder.length() < toPosition)
      builder.append(with);
  }

  private void printSerializedNode(Node node) {
    builder.append(nodeSerializer.apply(node));
  }

  private void printRightConnector(StringBuilder builder, Node node) {
    fillWith(routeChar, getRightConnectorPosition(node));
    builder.append(connectorChar);
  }

  private int getRightConnectorPosition(Node node) {
    int distance = node.right.serialized.length() + BinaryTreePrinter.nodeParenthesisSize;
    return distance / 2 + node.right.pos;
  }
}
