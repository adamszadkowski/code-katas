package info.szadkowski.katas.binary.tree.traversal;

import java.util.function.Consumer;
import java.util.function.Function;

public class InOrderRecursiveVisitor<T> {
  private final Function<T, T> leftSupplier;
  private final Function<T, T> rightSupplier;
  private final Consumer<T> callback;

  public InOrderRecursiveVisitor(Function<T, T> leftSupplier,
                                 Function<T, T> rightSupplier,
                                 Consumer<T> callback) {
    this.leftSupplier = leftSupplier;
    this.rightSupplier = rightSupplier;
    this.callback = callback;
  }

  public void visit(T node) {
    if (node == null)
      return;

    visit(leftSupplier.apply(node));
    callback.accept(node);
    visit(rightSupplier.apply(node));
  }
}
