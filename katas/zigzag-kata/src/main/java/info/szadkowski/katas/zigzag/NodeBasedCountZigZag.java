package info.szadkowski.katas.zigzag;

import java.util.Objects;

public class NodeBasedCountZigZag {
  public int calculateZigZagCount(Node tree) {
    int rightValue = tree.right != null ? calculateZigZag(tree.right, Node.Direction.RIGHT) : 0;
    int leftValue = tree.left != null ? calculateZigZag(tree.left, Node.Direction.LEFT) : 0;

    tree.value = Math.max(rightValue, leftValue);

    return tree.value;
  }

  private int calculateZigZag(Node tree, Node.Direction direction) {
    int sameDirection = 0;
    int oppositeDirection = 0;

    switch (direction) {
      case RIGHT:
        sameDirection = tree.right != null ? calculateZigZag(tree.right, Node.Direction.RIGHT) : 0;
        oppositeDirection = tree.left != null ? calculateZigZag(tree.left, Node.Direction.LEFT) + 1 : 0;
        break;
      case LEFT:
        sameDirection = tree.left != null ? calculateZigZag(tree.left, Node.Direction.LEFT) : 0;
        oppositeDirection = tree.right != null ? calculateZigZag(tree.right, Node.Direction.RIGHT) + 1 : 0;
        break;
    }

    tree.value = Math.max(oppositeDirection, sameDirection);

    return tree.value;
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

    private enum Direction {
      RIGHT,
      LEFT
    }
  }
}
