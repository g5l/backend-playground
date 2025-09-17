# Java 23 String Templates (Preview) — POC

This is a small proof‑of‑concept showing Java 23’s String Templates (third preview, JEP 465). It demonstrates the built‑in template processors (`STR`, `FMT`, `RAW`) and a tiny custom processor to illustrate validation/escaping.

Note: String Templates are a preview feature in Java 23. You must enable preview to compile and run.

## What are String Templates?

String Templates provide a concise, safe way to interpolate values into strings with pluggable processors that can validate, transform, or format the result. A template expression looks like this:

```
import static java.lang.StringTemplate.STR;

String name = "Alice";
String msg = STR."Hello, \{name}!"; // -> "Hello, Alice!"
```

Key ideas:
- Template expressions use `\{ ... }` for embedded expressions inside a string literal.
- A processor (e.g., `STR`, `FMT`, `RAW`) receives a `StringTemplate` object containing fragments and values.
- Processors can validate and transform the template before producing the final output. You can also write custom processors.

Included demos:
- `STR` — simple interpolation using `toString()` of embedded values.
- `FMT` — `Formatter`-style formatting integrated with templates.
- `RAW` — access to fragments and values without interpolation.
- `SQL` (custom) — a tiny demo processor that escapes embedded strings and allows whitelisting identifiers, illustrating how processors can enforce rules.

References: JEP 465 (String Templates, Third Preview) in Java 23.

## Requirements

- JDK 23 installed and on your `PATH`.
- Because this uses a preview feature, both compilation and execution must include `--enable-preview`.

## Run with `javac/java` (no build tool)

```
# From the project root
./run.sh
```

What it does:
- Compiles with `--release 23 --enable-preview` into `out/`.
- Runs with `--enable-preview`.

If the script isn’t executable, run `chmod +x run.sh` once.

Manual commands (equivalent):

```
# Compile
javac --release 23 --enable-preview -d out $(find src/main/java -name "*.java")

# Run
java --enable-preview -cp out com.example.stringtemplates.Main
```

## Run with Maven (optional)

A build tool isn’t required for this POC, but if you prefer Maven, use a recent Maven and JDK 23. Example `pom.xml` snippet (not included) would set `maven-compiler-plugin` to `--release 23` with `--enable-preview`, and Surefire to run with `--enable-preview`.

## Output (example)

The program prints examples for:
- STR basic interpolation
- FMT formatted output
- RAW fragments/values and interpolation
- Custom SQL processor producing a safely escaped SQL string (for demo only)

## Caveats

- String Templates are preview in Java 23; syntax/API can change in future releases.
- The `SQL` processor here is intentionally minimal to illustrate the concept; real SQL safety should use parameterized queries (`PreparedStatement`).

## Project Structure

```
.
├── README.md
├── run.sh
└── src
    └── main
        └── java
            └── com
                └── example
                    └── stringtemplates
                        └── Main.java
```
