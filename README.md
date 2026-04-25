# Dependency Injection Frameworks and Object-Oriented Design
**CSCI 5448 — Graduate Research Project**
Trisha Nookala and Soumya Devulapalli
University of Colorado Boulder

---

## Overview

This repository contains the research paper and accompanying Java source code for our study on how dependency injection (DI) frameworks affect object-oriented design quality. We compare two functionally identical systems:

- **Manual system** — dependencies wired by hand using plain Java
- **Guice system** — dependencies managed by Google Guice

---

## Dependencies

| Dependency | Version | Purpose |
|---|---|---|
| Java | 17+ | Language runtime |
| Maven | 3.8+ | Build and dependency management |
| Google Guice | 7.0.0 | Dependency injection framework |
| JUnit 5 | 5.10.0 | Unit testing |

---

## Build Instructions

### 1. Clone the repository
```bash
git clone <your-repo-url>
cd <repo-name>
```

### 2. Make sure Java 17+ is installed
```bash
java -version
```

### 3. Build the project
```bash
mvn compile
```

---

## Test Instructions

### Run all tests
```bash
mvn test
```

### Run only manual system tests
```bash
mvn test -Dtest=ManualGameTest
```

### Run only Guice system tests
```bash
mvn test -Dtest=GuiceGameTest
```

### Run change locality experiment tests
```bash
mvn test -Dtest=ChangeLocalityTest
```

### Expected output
All tests should pass. Example:
```
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

## Running the Applications

### Manual system
```bash
mvn exec:java -Dexec.mainClass="manual.MainManual"
```

### Guice system
```bash
mvn exec:java -Dexec.mainClass="guice.MainDI"
```

---

### Alternatively, in IntelliJ IDEA
1. Right-click the `src/test/java` folder
2. Select **Run 'All Tests'**

## What the Code Demonstrates

| Research Question | Relevant Files |
|---|---|
| Dependency visibility | `manual/Game.java` vs `guice/Game.java` |
| Change locality | `ChangeLocalityTest.java`, `GameModule.java` |
| Encapsulation | Constructor signatures in both `Game.java` files |
| Dependency inversion | `Die.java` interface usage across both systems |
| Cohesion / responsibility | `GameModule.java` (construction vs behavior separation) |