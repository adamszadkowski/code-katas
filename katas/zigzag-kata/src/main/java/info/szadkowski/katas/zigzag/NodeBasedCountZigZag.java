package info.szadkowski.katas.zigzag;

import java.util.Objects;

public class NodeBasedCountZigZag {
  public int calculateZigZagCount(Node tree) {
    if (tree == null)
      return 0;

    tree.value = Math.max(calculateOnRight(tree.right),
                          calculateOnLeft(tree.left));

    return tree.value;
  }

  private int calculateOnLeft(Node node) {
    if (node == null)
      return 0;

    int zigZagAddition = node.right != null ? 1 : 0;

    node.value = Math.max(calculateOnRight(node.right) + zigZagAddition,
                          calculateOnLeft(node.left));

    return node.value;
  }

  private int calculateOnRight(Node node) {
    if (node == null)
      return 0;

    int zigZagAddition = node.left != null ? 1 : 0;

    node.value = Math.max(calculateOnLeft(node.left) + zigZagAddition,
                          calculateOnRight(node.right));

    return node.value;
  }

  public static class Node {
    private final Node left;
    private final Node right;

    private int value;

    public Node(Node left, Node right, int value) {
      this.left = left;
      this.right = right;
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Node node = (Node) o;

      return value == node.value
             && Objects.equals(left, node.left)
             && Objects.equals(right, node.right);
    }
  }
}
