package com.ll;

public class Main {
  public static void main(String[] args) {
    System.out.println("10 - 20 + 30");
    System.out.println("10 - 20 + 30".replaceAll("- ", "+ -"));

    String exp = "-(8 + 2) * -(7 + 3) + 5";

int startPos = 0;
int endPos = 7;

String head = exp.substring(0, startPos);
String body = "(" + exp.substring(startPos + 1, endPos + 1) + " * -1)";
String tail = exp.substring(endPos + 1);

    System.out.println("head : " + head);
    System.out.println("body : " + body);
    System.out.println("tail : " + tail);

    System.out.println("전체 : " + head + body + tail);

  }
}