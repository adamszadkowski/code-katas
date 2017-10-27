package info.szadkowski.katas.zigzag.iteration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IterationZigZag {
  public int calculateZigZagCount(Node tree) {
    if (tree == null)
      return 0;

    return Math.max(calculate(tree.right, Direction.RIGHT),
                    calculate(tree.left, Direction.LEFT));
  }

  private int calculate(Node root, Direction direction) {
    if (root == null)
      return 0;

    return findEndingNodes(root, direction).stream()
            .mapToInt(this::calculateZigZagForRoute)
            .max()
            .orElse(0);
  }

  private List<NodeWrapper> findEndingNodes(Node root, Direction direction) {
    Queue<NodeWrapper> elements = new LinkedList<>();
    elements.add(NodeWrapper.of(null, root, direction));

    List<NodeWrapper> endingNodes = new ArrayList<>();

    do {
      NodeWrapper current = elements.poll();
      Node right = current.node.right;
      Node left = current.node.left;

      if (right != null)
        elements.add(NodeWrapper.of(current, right, Direction.RIGHT));

      if (left != null)
        elements.add(NodeWrapper.of(current, left, Direction.LEFT));

      if (left == null && right == null)
        endingNodes.add(current);
    } while (!elements.isEmpty());

    return endingNodes;
  }

  private int calculateZigZagForRoute(NodeWrapper endNode) {
    int zigZagCount = 0;

    NodeWrapper currentNode = endNode;
    Direction previousDirection = endNode.direction;

    do {
      if (currentNode.direction != previousDirection)
        zigZagCount++;

      previousDirection = currentNode.direction;
      currentNode = currentNode.parent;
    } while (currentNode != null);

    return zigZagCount;
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

