package info.szadkowski.katas.binary.tree.printer;

import java.util.*;

class NodeLevelTraversingIterator implements Iterator<List<Node>> {
  private final Queue<LeveledNode> queue = new LinkedList<>();

  NodeLevelTraversingIterator(Node node) {
    queue.add(new LeveledNode(node, 0));
  }

  @Override
  public boolean hasNext() {
    return !queue.isEmpty();
  }

  @Override
  public List<Node> next() {
    List<Node> result = new ArrayList<>();
    int currentLevel = queue.peek().level;

    while (!queue.isEmpty() && currentLevel == queue.peek().level) {
      Node node = queue.poll().node;

      result.add(node);

      if (node.left != null)
        queue.add(new LeveledNode(node.left, currentLevel + 1));

      if (node.right != null)
        queue.add(new LeveledNode(node.right, currentLevel + 1));
    }

    return result;
  }

  private static class LeveledNode {
    private Node node;
    private int level;

    private LeveledNode(Node node, int level) {
      this.node = node;
      this.level = level;
    }
  }
}
