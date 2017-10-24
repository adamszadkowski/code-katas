package info.szadkowski.katas.zigzag;

@FunctionalInterface
interface Converter<T> {
  T convert(T left, T right, String arguments);
}
