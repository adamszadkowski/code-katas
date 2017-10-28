package info.szadkowski.katas.binary.tree.parser;

@FunctionalInterface
public interface Converter<T> {
  T convert(T left, T right, String arguments);
}
