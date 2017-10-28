package info.szadkowski.katas.zigzag.tree.parser;

@FunctionalInterface
public interface Converter<T> {
  T convert(T left, T right, String arguments);
}
