package info.szadkowski.katas.zigzag;

import java.util.Objects;

public class NodeBasedZigZag {
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
      int rightValue = right != null ? right.calculateZigZag(Direction.RIGHT) : 0;
      int leftValue = left != null ? left.calculateZigZag(Direction.LEFT) : 0;

      value = Math.max(rightValue, leftValue);

      return value;
    }

    private int calculateZigZag(Direction direction) {
      int sameDirection = 0;
      int oppositeDirection = 0;

      switch (direction) {
        case RIGHT:
          sameDirection = right != null ? right.calculateZigZag(Direction.RIGHT) : 0;
          oppositeDirection = left != null ? left.calculateZigZag(Direction.LEFT) + 1 : 0;
          break;
        case LEFT:
          sameDirection = left != null ? left.calculateZigZag(Direction.LEFT) : 0;
          oppositeDirection = right != null ? right.calculateZigZag(Direction.RIGHT) + 1 : 0;
          break;
      }

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

    private enum Direction {
      RIGHT,
      LEFT
    }
  }
}
