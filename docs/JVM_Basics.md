# JVM Basics

## JDK, JRE, and JVM

```
┌──────────────────────────────────┐
│              JDK                 │
│  ┌────────────────────────────┐  │
│  │           JRE              │  │
│  │  ┌──────────────────────┐  │  │
│  │  │        JVM           │  │  │
│  │  │  (runs bytecode)     │  │  │
│  │  └──────────────────────┘  │  │
│  │  + Standard Libraries      │  │
│  └────────────────────────────┘  │
│  + javac (compiler) + tools      │
└──────────────────────────────────┘
```

- **JVM (Java Virtual Machine):** Executes bytecode. Each OS has its own JVM implementation.
- **JRE (Java Runtime Environment):** JVM + standard libraries. Needed to *run* Java programs.
- **JDK (Java Development Kit):** JRE + compiler (`javac`) + dev tools. Needed to *write and build* Java programs.

---

## What is Bytecode?

When you run `javac HelloWorld.java`, the compiler does not produce machine code for a
specific CPU. It produces `HelloWorld.class` — a file containing **bytecode**, which is a
set of instructions designed for the JVM, not for any particular hardware.

The JVM reads this bytecode and translates it into native machine instructions at runtime.
This translation layer is what makes Java portable.

---

## "Write Once, Run Anywhere"

Because Java compiles to bytecode (not machine code), the same `.class` files can run on
any device or operating system that has a compatible JVM — Windows, macOS, Linux, or
Android — without recompilation.

You write and compile your code once. The JVM on each target platform handles the rest.
This is the meaning of Java's "Write Once, Run Anywhere" principle.
