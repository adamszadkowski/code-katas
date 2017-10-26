package info.szadkowski.katas.zigzag;

import java.util.Objects;

public class NodeBasedMethodZigZag {
  public int calculateZigZagCount(Node tree) {
    return tree.getZigZagCount();
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

    public int getZigZagCount() {
      int rightValue = right != null ? right.calculateOnRight() : 0;
      int leftValue = left != null ? left.calculateOnLeft() : 0;

      value = Math.max(rightValue, leftValue);

      return value;
    }

    private int calculateOnLeft() {
      int sameDirection = left != null ? left.calculateOnLeft() : 0;
      int oppositeDirection = right != null ? right.calculateOnRight() + 1 : 0;

      value = Math.max(oppositeDirection, sameDirection);

      return value;
    }

    private int calculateOnRight() {
      int sameDirection = right != null ? right.calculateOnRight() : 0;
      int oppositeDirection = left != null ? left.calculateOnLeft() + 1 : 0;

      value = Math.max(oppositeDirection, sameDirection);

      return value;
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
