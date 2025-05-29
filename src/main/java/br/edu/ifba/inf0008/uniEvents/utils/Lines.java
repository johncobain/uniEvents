package br.edu.ifba.inf0008.uniEvents.utils;

public class Lines {
  private static final int LINE_WIDTH = 64;
  private static final int TEXT_WIDTH = LINE_WIDTH - 2;

  public static String straightLine() {
    return "-".repeat(LINE_WIDTH);
  }

  public static String doubleLine() {
    return "=".repeat(LINE_WIDTH);
  }

  public static String mixedLines() {
    StringBuilder sb = new StringBuilder(LINE_WIDTH);
    for (int i = 0; i < LINE_WIDTH; i++) {
      sb.append(i % 2 == 0 ? '=' : '-');
    }
    return sb.toString();
  }

  public static String clear(){
    return "\033[H\033[2J";
  }
  public static String multiLineText(String text){
    String[] words = text.split(" ");
    StringBuilder finalLine = new StringBuilder("|");
    int currentLength = 0;
    for (String word : words) {
      if (currentLength + word.length() + 1 > TEXT_WIDTH) {
        finalLine.append(" ".repeat(Math.max(0, TEXT_WIDTH - currentLength)));
        finalLine.append("|\n|");
        currentLength = 0;
      } else if (currentLength > 0) {
        finalLine.append(" ");
        currentLength++;
      }
      finalLine.append(word);
      currentLength += word.length();
    }
      finalLine.append(" ".repeat(Math.max(0, TEXT_WIDTH - currentLength)));
    finalLine.append("|");
    return finalLine.toString();
  }

  public static String centeredMultiLineText(String text) {
    String[] words = text.split(" ");
    StringBuilder result = new StringBuilder();
    StringBuilder line = new StringBuilder();
    int currentLength = 0;
    int maxWidth = TEXT_WIDTH;

    for (String word : words) {
      if (currentLength + word.length() + (currentLength == 0 ? 0 : 1) > maxWidth) {
        int padding = maxWidth - currentLength;
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;
        result.append("|")
            .append(" ".repeat(leftPadding))
            .append(line)
            .append(" ".repeat(rightPadding))
            .append("|\n");
        line.setLength(0);
        line.append(word);
        currentLength = word.length();
      } else {
        if (currentLength > 0) {
          line.append(" ");
          currentLength++;
        }
        line.append(word);
        currentLength += word.length();
      }
    }
    if (currentLength > 0) {
      int padding = maxWidth - currentLength;
      int leftPadding = padding / 2;
      int rightPadding = padding - leftPadding;
      result.append("|")
          .append(" ".repeat(leftPadding))
          .append(line)
          .append(" ".repeat(rightPadding))
          .append("|");
    }
    return result.toString();
  }

  public static String centeredMultiLineText(String text, String color) {
    String[] words = text.split(" ");
    StringBuilder result = new StringBuilder();
    StringBuilder line = new StringBuilder();
    int currentLength = 0;
    int maxWidth = TEXT_WIDTH;

    for (String word : words) {
      if (currentLength + word.length() + (currentLength == 0 ? 0 : 1) > maxWidth) {
        int padding = maxWidth - currentLength;
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;
        result.append("|")
            .append(" ".repeat(leftPadding))
            .append(color)
            .append(line)
            .append(Colors.RESET)
            .append(" ".repeat(rightPadding))
            .append("|\n");
        line.setLength(0);
        line.append(word);
        currentLength = word.length();
      } else {
        if (currentLength > 0) {
          line.append(" ");
          currentLength++;
        }
        line.append(word);
        currentLength += word.length();
      }
    }
    if (currentLength > 0) {
      int padding = maxWidth - currentLength;
      int leftPadding = padding / 2;
      int rightPadding = padding - leftPadding;
      result.append("|")
          .append(" ".repeat(leftPadding))
          .append(color)
          .append(line)
          .append(Colors.RESET)
          .append(" ".repeat(rightPadding))
          .append("|");
    }
    return result.toString();
  }

  public static String errorLine(String text){
    return straightLine() + "\n" +
            centeredMultiLineText(text, Colors.RED_BOLD)+
            "\n" + straightLine();
  }

  public static String successLine(String text){
    return straightLine() + "\n" +
          centeredMultiLineText(text, Colors.GREEN_BOLD)+
          "\n" + straightLine();
  }

  public static String warningLine(String text){
    return straightLine() + "\n" +
            centeredMultiLineText(text, Colors.YELLOW_BOLD)+
            "\n" + straightLine();
  }
}
