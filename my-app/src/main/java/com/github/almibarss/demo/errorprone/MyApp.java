package com.github.almibarss.demo.errorprone;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

public class MyApp {

  public static void main(String[] args) {
    printAcronym(args);
  }

  // Uncomment for suppressing DeadException and continue to compileTestJava
  // @SuppressWarnings("DeadException")
  public static void printAcronym(String[] args) {
    if (args.length == 0) {
      new RuntimeException("No arguments provided");
    }
    String acronym = Arrays.stream(args)
            // StringRules.StringIsNullOrEmpty suppressed by config
            .filter(s -> s != null && !s.isEmpty())
            .map(s -> s.substring(0, 1))
            .collect(joining());
    System.out.println(acronym);
  }
}
