package br.edu.ifba.inf0008.uniEvents.utils;

public class Lines {
  public static String straightLine(){
    return "----------------------------------------------------------------";
  }
  public static String doubleLine(){
    return "================================================================";
  }
  public static String mixedLines(){
    return "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
  }

  public static String clear(){
    return "\033[H\033[2J";
  }

  public static String titleLine(String title){
    int totalLength = 62 - title.length();
    int leftPadding = totalLength/2;
    int rightPadding = totalLength - leftPadding;
    return "|" + " ".repeat(Math.max(0, leftPadding)) +
            title + " ".repeat(Math.max(0, rightPadding)) + "|";
  }

  public static String titleLine(String title, String color){
    int totalLength = 62 - title.length();
    int leftPadding = totalLength/2;
    int rightPadding = totalLength - leftPadding;
    return "|" + " ".repeat(Math.max(0, leftPadding)) +
            color + title + Colors.RESET +
            " ".repeat(Math.max(0, rightPadding)) + "|";
  }

  public static String multiLineText(String text){
    String[] words = text.split(" ");
    StringBuilder finalLine = new StringBuilder("|");
    int currentLength = 0;
    for (String word : words) {
      if (currentLength + word.length() + 1 > 62) {
          finalLine.append(" ".repeat(Math.max(0, 62 - currentLength)));
        finalLine.append("|\n|");
        currentLength = 0;
      } else if (currentLength > 0) {
        finalLine.append(" ");
        currentLength++;
      }
      finalLine.append(word);
      currentLength += word.length();
    }
      finalLine.append(" ".repeat(Math.max(0, 62 - currentLength)));
    finalLine.append("|");
    return finalLine.toString();
  }

  public static String errorLine(String text){
    int totalLength = 62 - text.length();
    int leftPadding = totalLength/2;
    int rightPadding = totalLength - leftPadding;
    return straightLine() + "\n|" +
            " ".repeat(Math.max(0, leftPadding)) +
            Colors.RED_BOLD + text + Colors.RESET +
            " ".repeat(Math.max(0, rightPadding)) +
            "|\n" + straightLine();
  }

  public static String successLine(String text){
    int totalLength = 62 - text.length();
    int leftPadding = totalLength/2;
    int rightPadding = totalLength - leftPadding;
    return straightLine() + "\n|" +
          " ".repeat(Math.max(0, leftPadding)) +
          Colors.GREEN_BOLD + text + Colors.RESET +
          " ".repeat(Math.max(0, rightPadding)) +
          "|\n" + straightLine();
  }

  public static String warningLine(String text){
    int totalLength = 62 - text.length();
    int leftPadding = totalLength/2;
    int rightPadding = totalLength - leftPadding;
    return straightLine() + "\n|" +
            " ".repeat(Math.max(0, leftPadding)) +
            Colors.YELLOW_BOLD + text + Colors.RESET +
            " ".repeat(Math.max(0, rightPadding)) +
            "|\n" + straightLine();
  }
}
