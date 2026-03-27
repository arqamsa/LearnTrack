# Setup Instructions

## JDK Version Used

```
openjdk version "21.0.10" 2026-01-20
OpenJDK Runtime Environment (build 21.0.10+7-Ubuntu-124.04)
```

Any JDK **17 or above** will work.

---

## Install Java (JDK)

### Windows
1. Download from https://adoptium.net
2. Run the installer — it sets `JAVA_HOME` automatically
3. Verify in a new terminal:
   ```
   java -version
   javac -version
   ```

### macOS
```bash
brew install openjdk@21
java -version
```

### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install openjdk-21-jdk
java -version
```

---

## Hello World Verification

Before running LearnTrack, confirm Java works with a simple test.

**HelloWorld.java:**
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

```bash
javac HelloWorld.java
java HelloWorld
```

Expected output:
```
Hello, World!
```

---

## Compile & Run LearnTrack

```bash
cd LearnTrack
find src -name "*.java" > sources.txt
mkdir -p out
javac -d out @sources.txt
java -cp out com.airtribe.learntrack.Main
```
