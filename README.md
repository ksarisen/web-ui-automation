# Test Automation Case Study

This repository contains an end-to-end test automation case study
covering Web UI, Mobile UI, and API layers.

## 📌 Scope
1. Web UI Automation 
2. Mobile UI Automation 
3. API Automation 

Each section is implemented as an independent automation module
using industry best practices.

---

## 1️⃣ Web UI Automation Project

This project is a Web UI Test Automation framework developed as part of a technical case study.

### 🛠 Technologies Used

- **Java 21**
- **Selenium WebDriver**
- **TestNG** (parallel execution)
- **Cucumber BDD**
- **Maven**
- **Docker**
- **Selenium Grid 4**
- **Jenkins**
- **Cucumber Reports (Jenkins Plugin)**

---

### 📁 Project Structure

```text
web-ui
├── pom.xml
└── src
    └── test
        ├── java
        │   └── com
        │       └── vakifbank
        │           ├── hooks
        │           │   └── Hooks.java
        │           ├── pages
        │           │   ├── BasePage.java
        │           │   └── BookStorePage.java
        │           ├── runners
        │           │   └── TestRunner.java
        │           ├── stepdefinitions
        │           │   ├── AlertsSteps.java
        │           │   ├── InvalidNavigationSteps.java
        │           │   ├── PracticeFormSteps.java
        │           │   └── BookStoreSteps.java
        │           └── utils
        │               └── DriverManager.java
        └── resources
            └── features
                ├── alerts.feature
                ├── book_store.feature
                ├── invalid_navigation.feature
                └── practice_form.feature
```

---

### ✅ Implemented Test Scenarios

- Book Store list validation (row count & book details)
- Practice Form validation
  - Only mandatory fields
  - All fields filled
- Alert popup validations
- Invalid menu navigation (negative scenario)

Each scenario includes necessary validations as required by the case.

---

### 🧵 Parallel Execution

- Parallel execution is enabled using **TestNG**
- Each scenario runs with its own WebDriver instance via `ThreadLocal`
- Fully compatible with Selenium Grid 4

---

### 🌐 Selenium Grid 4 (Docker)

Selenium Grid 4 is executed using Docker containers:

- `selenium/hub`
- `selenium/node-chrome`
- `selenium/node-edge`

Tests are executed remotely via `RemoteWebDriver`.

Browser selection is controlled using a Maven parameter:

```bash
mvn verify -Dbrowser=chrome
mvn verify -Dbrowser=edge
```

---

### 🧪 Driver Management
- Centralized DriverManager
- Uses ThreadLocal<WebDriver> for parallel safety
- Supports Chrome and Edge
- No usage of Thread.sleep() (explicit waits only)

---

### 📸 Screenshot on Failure
- Screenshots are captured automatically for failed scenarios
- Implemented using a Cucumber @After hook
- Screenshots are attached directly to the Cucumber scenario
- Visible inside the Cucumber HTML report in Jenkins

📍 Location in report:
    Scenario → Hooks → After → Embedded image (png)

---

### 📊 Reporting
Jenkins Cucumber Report
- Jenkins Cucumber Reports Plugin is used
- Reports are generated from cucumber-report.json
- Includes:
    - Scenario status
    - Step details
    - Charts
    - Embedded screenshots for failed scenarios

---

### 🚀 Jenkins Integration
- Jenkins Freestyle Job
- Pulls latest code from GitHub repository
- Runs tests using Maven (verify phase)
- Executes tests on Selenium Grid 4
- Publishes Cucumber HTML reports automatically

---

### ▶️ How to Run Locally
- Prerequisites:
    - Java 21
    - Maven
    - Docker & Docker Compose

Start Selenium Grid

```bash
docker-compose up -d
```

Run Tests

```bash
mvn verify -Dbrowser=chrome
```

---

### 📝 Notes

- All requirements defined in the case study are fully implemented
- Framework is scalable and easily extendable
- Clean architecture using Page Object Model
- CI-ready with Jenkins and Docker-based Grid

---

## 2️⃣ Mobile UI Automation
This module covers Mobile UI test automation for an Android application
as part of the technical case requirements.

### 🛠 Technologies Used

- **Java 21**
- **Appium**
- **UiAutomator2**
- **Android Emulator**
- **Cucumber BDD**
- **TestNG**
- **Maven**
- **Appium Inspector**

---

### 📁 Project Structure

```text
mobile-ui-automation
├── pom.xml
├── src
│   └── test
│       ├── java
│       │   └── com
│       │       └── akakce
│       │           ├── driver
│       │           │   └── DriverManager.java
│       │           ├── hooks
│       │           │   └── Hooks.java
│       │           ├── pages
│       │           │   ├── BasePage.java
│       │           │   └── AkakcePage.java
│       │           ├── runners
│       │           │   └── TestRunner.java
│       │           └── steps
│       │               └── AkakceSteps.java
│       └── resources
│           └── features
│               └── akakce.feature
```

---

### ✅ Implemented Scenario
- Search for a product (Laptop)
- Apply multiple filters
- Sort results by lowest price
- Select a specific product from the list
- Validate seller button visibility
- Compare listing price with detail page price (partially implemented)

---

### 📸 Screenshot on Failure
- Screenshots are captured automatically for failed scenarios
- Implemented via Cucumber @After hook
- Screenshots are embedded directly into the Cucumber report
- No Thread.sleep() usage (explicit waits only)

---

### ▶️ How to Run

Prerequisites:
- Java 21
- Maven
- Android SDK
- Android Emulator
- Appium Server

Start Appium Server

```bash
appium
```

Run Tests

```bash
mvn clean test
```

---

### 🧪 Appium Inspector

Appium Inspector was used extensively to:
- Inspect dynamic UI hierarchies
- Identify transient UI containers
- Analyze scroll behavior and overlay components
- Validate locator strategies before implementation

---

### ⚠️ Known Limitation (Transparent Disclosure)
Due to dynamic UI rendering and price components being split into
multiple TextView elements in the product detail screen, a fully
reliable price extraction from the detail page could not be finalized
within the given time frame.
- Price digits are rendered across multiple TextViews
- No unique or stable resource-id available for full price value
- Multiple similar TextViews exist across the page
This limitation is documented intentionally to demonstrate
real-world mobile UI automation challenges and decision-making.

---

### 🧵 Parallel Execution
- Parallel execution is supported via TestNG
- Thread-safe driver management using ThreadLocal
- Each scenario runs with an isolated Appium session

---

### 📝 Notes

- Project follows clean architecture principles
- No hard waits (`Thread.sleep`) are used
- Explicit waits and robust locators are preferred
- Known limitations are documented transparently

---

## 3️⃣ API Automation

---

## 👤 Author
Kerem Sarısen