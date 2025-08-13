# ✈️ FlightBooking- Automation Framework

Welcome to the **Flight Booking System - Automation Framework**!  
This repository contains a well-structured test automation suite for validating flight booking workflows. It aims to ensure functional reliability, code modularity, and maintainability for scalable test development.

---

## 📚 Table of Contents

- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [Folder Structure](#folder-structure)
- [Getting Started](#getting-started)
- [Running Tests](#running-tests)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## 🛫 Project Overview

This suite automates end-to-end testing of a flight booking system. Using Java, Gherkin, and Selenium-based frameworks, it covers UI interactions, scenario-based validations, and integrates configuration-driven flexibility.

**Key Features:**
- Cucumber BDD with Gherkin feature files
- Page Object Model for scalable automation
- Configurable test setups and hooks
- Comprehensive reporting

---

## 🛠️ Tech Stack

- **Java** — Test automation logic
- **Gherkin** — Feature specifications (BDD)
- **Selenium** — UI automation
- **Maven** — Build and dependency management
- **HTML/CSS/JavaScript** — UI mockups for testing

---

## 📁 Folder Structure

```
CTS_Sprint_2/
├── .settings/                     # IDE/editor configuration files
├── src/
│   └── test/
│       ├── java/
│       │   ├── features/          # Gherkin feature files (BDD scenarios)
│       │   ├── hook/              # Cucumber/Selenium hooks (setup/teardown)
│       │   ├── pages/             # Page Object Models (POM classes)
│       │   ├── runner/            # Test runner classes
│       │   ├── setup/             # Environment setup utilities
│       │   ├── stepDefinitions/   # Step definition implementations
│       │   └── utils/             # Utility/helper classes
│       └── resources/
│           └── config/            # Configuration files (environment, data)
├── pom.xml                        # Maven project descriptor
```

---

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- Chrome/Firefox browser
- Git

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Saha-Souvik/Testing_Flight_Booking_System.git
   cd Testing_Flight_Booking_System/CTS_Sprint_2
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

---

## 🧪 Running Tests

1. **Configure test parameters:**  
   Edit files in `src/test/resources/config` as needed.

2. **Execute tests:**
   ```bash
   mvn test
   ```

3. **View reports:**  
   You can find detailed reports in target directories after test execution.

---

## 🤝 Contributing

We welcome contributions!

1. Fork the repo.
2. Create your feature branch (`git checkout -b feature/my-feature`)
3. Commit your changes.
4. Push to your branch and open a Pull Request.

See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

---

## 📄 License

This project is licensed under the MIT License.  
See `LICENSE` for details.

---

## 📬 Contact

**Maintainer:** [Saha-Souvik](https://github.com/Saha-Souvik)  
For issues, suggestions, or support, please open an [issue](https://github.com/Saha-Souvik/Testing_Flight_Booking_System/issues).

---

> _“Quality means doing it right when no one is looking.”_  
> — Henry Ford
