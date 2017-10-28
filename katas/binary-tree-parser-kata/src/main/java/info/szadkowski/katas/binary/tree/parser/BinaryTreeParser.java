package info.szadkowski.katas.binary.tree.parser;

public class BinaryTreeParser<T> {
  private final Converter<T> converter;

  public BinaryTreeParser(Converter<T> converter) {
    this.converter = converter;
  }

  public T parse(String tree) {
    if (tree == null)
      throw new InvalidInputException("Input cannot be null");

    String[] lines = tree.split("\n", -1);
    int firstNodeLine = findIndexOfRootNodeLine(lines);
    int firstNodePosition = lines[firstNodeLine].indexOf('(');

    return scanInLine(lines, firstNodeLine, firstNodePosition);
  }

  private int findIndexOfRootNodeLine(String[] lines) {
    for (int i = 0; i < lines.length; i++)
      if (lines[i].matches(".*\\(.*?\\).*"))
        return i;

    throw new InvalidInputException("Cannot find any node");
  }

  private T scanInLine(String[] lines, int line, int pos) {
    if (pos == -1)
      return null;

    validateInput(lines, line);

    if (isExtensionLine(lines, line, pos))
      return scanInLine(lines, line + 1, pos);

    return expectNode(lines, line, pos);
  }

  private void validateInput(String[] lines, int line) {
    if (lines.length <= line)
      throw new InvalidInputException(String.format("Expected line %d", line + 1));
  }

  private boolean isExtensionLine(String[] lines, int line, int pos) {
    char[] chars = lines[line].toCharArray();
    return pos < chars.length && chars[pos] == '|';
  }

  private T expectNode(String[] lines, int line, int pos) {
    char[] chars = lines[line].toCharArray();

    int openParen = findOpenParen(line, chars, pos);
    int closeParen = findCloseParen(line, chars, pos);

    int leftNodePos = findLeftNodePosition(line, chars, openParen);
    int rightNodePos = findRightNodePosition(line, chars, closeParen);

    T left = scanInLine(lines, line + 1, leftNodePos);
    T right = scanInLine(lines, line + 1, rightNodePos);
    String arguments = lines[line].substring(openParen + 1, closeParen);

    return converter.convert(left, right, arguments);
  }

  private int findOpenParen(int line, char[] chars, int pos) {
    for (int i = pos; i >= 0; i--)
      if (i < chars.length && chars[i] == '(')
        return i;

    throw new InvalidInputException(String.format("Expected node begin in (line %d, position %d)", line + 1, pos + 1));
  }

  private int findCloseParen(int line, char[] chars, int pos) {
    for (int i = pos; i < chars.length; i++)
      if (chars[i] == ')')
        return i;

    throw new InvalidInputException(String.format("Expected node end in (line %d, position %d)", line + 1, pos + 2));
  }

  private int findLeftNodePosition(int line, char[] chars, int openParen) {
    int pos = openParen - 1;

    if (pos < 0 || chars[pos] != '-')
      return -1;

    while (pos > 0 && chars[pos] == '-')
      pos--;

    if (chars[pos] != '+')
      throw new InvalidInputException(String.format("Expected '+' in (line %d, position %d)", line + 1, pos + 1));

    return pos;
  }

  private int findRightNodePosition(int line, char[] chars, int closeParen) {
    int pos = closeParen + 1;

    if (pos >= chars.length || chars[pos] != '-')
      return -1;

    while (pos < chars.length - 1 && chars[pos] == '-')
      pos++;

    if (chars[pos] != '+')
      throw new InvalidInputException(String.format("Expected '+' in (line %d, position %d)", line + 1, pos + 2));

    return pos;
  }

  public static class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
      super(message);
    }
  }
}
