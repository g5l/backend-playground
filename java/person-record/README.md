# Person Record POC (Java 23)

## 📌 Overview
This project demonstrates the use of **Java Records**, **Text Blocks**, and **Pattern Matching (`instanceof`)** in a simple example.  

We define a `Person` record and show how Java automatically provides useful methods like constructor, getters, `equals`, `hashCode`, and `toString`.  
The project also includes a sample JSON-like **text block** and demonstrates **pattern matching** with `instanceof`.

---

## 🚀 Features
- **Record**: `Person(String name, int age, String email)`
- **Text Blocks**: Multiline string to represent JSON-like data.
- **Pattern Matching**: Cleaner `instanceof` checks.

---

## 🛠️ Project Structure
```
person-poc/
 ├── pom.xml
 ├── README.md
 └── src/main/java/com/example/person/
     ├── Main.java
     └── Person.java
```

---

## ▶️ How to Run

1. Compile the project:
   ```bash
   mvn compile
   ```

2. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.person.Main"
   ```

---

## ✅ Example Output
```
Name: Alice
Age: 30
Email: alice@example.com

Original JSON-like text block:
{
  "name": "Alice",
  "age": 30,
  "email": "alice@example.com"
}
```

---

## 📚 Learning Goals
- Learn how **records** reduce boilerplate code.
- Use **text blocks** for cleaner, multiline strings.
- Practice **pattern matching** with `instanceof`.

---

## 🔧 Requirements
- **Java 23** (or any Java 16+ for records support)
- **Maven** for build and run
