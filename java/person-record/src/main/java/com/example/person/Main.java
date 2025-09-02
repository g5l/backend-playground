package com.example.person;

public class Main {
    public static void main(String[] args) {

        Person p = new Person("Alice", 30, "alice@example.com");
        Person p1 = new Person("Alice", 30, "alice@example.com");
        Person p2 = new Person("Bob", 25, "bob@example.com");

        System.out.println("Name: " + p.name());
        System.out.println("Age: " + p.age());
        System.out.println("Email: " + p.email());

        System.out.println("\n--- equals() ---");
        System.out.println("person equals person1? " + p.equals(p1));
        System.out.println("person equals person2? " + p.equals(p2));

        System.out.println("\n--- hashCode() ---");
        System.out.println("p.hashCode() == p1.hashCode()? " +
                (p.hashCode() == p1.hashCode()));
        System.out.println("p.hashCode() == p2.hashCode()? " +
                (p.hashCode() == p2.hashCode()));

        System.out.println("\n--- toString() ---");
        System.out.println("p: " + p);
        System.out.println("person2: " + p2);
    }
}
