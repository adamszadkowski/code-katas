package info.szadkowski.katas.zigzag.recursion;

public class ExternalZigZagCount {
  public int calculateZigZagCount(Node tree) {
    if (tree == null)
      return 0;

    return Math.max(calculateOnRight(tree.right),
                    calculateOnLeft(tree.left));
  }

  private int calculateOnRight(Node node) {
    if (node == null)
      return 0;

    int zigZagAddition = node.left != null ? 1 : 0;

    return Math.max(calculateOnLeft(node.left) + zigZagAddition,
                    calculateOnRight(node.right));
  }

  private int calculateOnLeft(Node node) {
    if (node == null)
      return 0;

    int zigZagAddition = node.right != null ? 1 : 0;

    return Math.max(calculateOnRight(node.right) + zigZagAddition,
                    calculateOnLeft(node.left));
  }

  public static class Node {
    private final Node left;
    private final Node right;

    public Node(Node left, Node right, String ignored) {
      this.left = left;
      this.right = right;
    }
  }
}
