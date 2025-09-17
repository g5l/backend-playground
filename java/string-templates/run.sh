#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR=$(cd "$(dirname "$0")" && pwd)
OUT_DIR="$ROOT_DIR/out"

rm -rf "$OUT_DIR"
mkdir -p "$OUT_DIR"

echo "Compiling (Java 23 preview)..."
javac --release 23 --enable-preview -d "$OUT_DIR" $(find "$ROOT_DIR/src/main/java" -name "*.java")

echo "Running..."
java --enable-preview -cp "$OUT_DIR" com.example.stringtemplates.Main
