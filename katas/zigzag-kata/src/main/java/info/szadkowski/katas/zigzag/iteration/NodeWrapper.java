package info.szadkowski.katas.zigzag.iteration;

class NodeWrapper {
  NodeWrapper parent;
  IterationZigZag.Node node;
  Direction direction;

  private NodeWrapper(NodeWrapper parent, IterationZigZag.Node node, Direction direction) {
    this.parent = parent;
    this.node = node;
    this.direction = direction;
  }

  static NodeWrapper of(NodeWrapper parent, IterationZigZag.Node node, Direction direction) {
    return new NodeWrapper(parent, node, direction);
  }
}
