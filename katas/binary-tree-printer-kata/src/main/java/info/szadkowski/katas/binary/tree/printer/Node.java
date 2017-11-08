package info.szadkowski.katas.binary.tree.printer;

import java.util.Iterator;
import java.util.List;

class Node implements Iterable<List<Node>> {
  final Node left;
  final Node right;
  final int pos;
  final String serialized;

  Node(Node left, Node right, int pos, String serialized) {
    this.left = left;
    this.right = right;
    this.pos = pos;
    this.serialized = serialized;
  }

  @Override
  public Iterator<List<Node>> iterator() {
    return new NodeLevelTraversingIterator(this);
  }
}
