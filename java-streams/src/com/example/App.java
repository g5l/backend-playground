package com.example;

import java.util.*;
import java.util.stream.Collectors

public class App {
  public static void main(String[] args) {
    List<User> users = Arrays.asList(
            new User("Gabriel", true),
            new User("Marcos", false),
            new User("Pedro", true),
            new User("Laura", false)
    );

    List<String> activeUserNames = users.stream()
            .filter(User::isActive)
            .map(user -> user.getName().toUpperCase())
            .collect(Collectors.toList());

    System.out.println("Active Users: " + activeUserNames);
  }
}
