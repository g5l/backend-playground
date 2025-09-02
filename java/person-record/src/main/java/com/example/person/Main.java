package com.example.person;

public class Main {
    public static void main(String[] args) {
        // Using a text block to format JSON-like data
        String json = """
            {
              "name": "Alice",
              "age": 30,
              "email": "alice@example.com"
            }
            """;

        Person person = new Person("Alice", 30, "alice@example.com");

        System.out.println("Name: " + p.name());
        System.out.println("Age: " + p.age());
        System.out.println("Email: " + p.email());

        System.out.println("\nOriginal JSON-like text block:");
        System.out.println(json);
    }
}
