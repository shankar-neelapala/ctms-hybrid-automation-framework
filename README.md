# CTMS Automation Framework

A Java-based Selenium WebDriver end-to-end test automation framework for the **CTMS (Clinical Trial Management System)** — a custom-built MERN stack web application. The automation project (`com.openctms.demo`) targets the CTMS frontend running locally and covers login, studies, sites, subjects, and patient registration workflows.

---

## Table of Contents

- [Prerequisites](#prerequisites)
- [Prerequisite — CTMS Application](#prerequisite--ctms-application)
- [Setting Up the Automation Project](#setting-up-the-automation-project)
  - [Tech Stack](#tech-stack)
  - [Project Structure](#project-structure)
  - [Installation](#installation)
  - [Configuration](#configuration)
  - [Test Data Files](#test-data-files)
- [Test Cases](#test-cases)
- [Running Tests](#running-tests)
  - [Full Suite](#full-suite)
  - [Smoke Tests Only](#smoke-tests-only)
  - [Regression Tests Only](#regression-tests-only)
  - [Data-Driven Login Tests](#data-driven-login-tests)
  - [Cross-Browser Tests](#cross-browser-tests)
- [Test Suites Overview](#test-suites-overview)
- [Reporting](#reporting)
- [Screenshots](#screenshots)
- [Logs](#logs)
- [Framework Architecture](#framework-architecture)
- [CTMS Application — Module Reference](#ctms-application--module-reference)


---

## Prerequisites

Make sure the following are installed before doing anything else:

| Requirement          | Version       | Purpose                          |
|----------------------|---------------|----------------------------------|
| Java JDK             | 8 or higher   | Automation framework runtime     |
| Maven                | 3.x           | Build and dependency management  |
| Node.js              | 16 or higher  | CTMS backend & frontend runtime  |
| MongoDB              | 5.x or higher | CTMS database                    |
| Google Chrome / Edge | Latest        | Browser for test execution       |

---

## Prerequisite — CTMS Application

> **The automation tests cannot run without the CTMS app running locally. Complete these steps before running any tests.**

**Download the CTMS application:** [ctms.zip](https://github.com/shankar-neelapala/Clinical-Trail-Management-System-MERN.git)

Once downloaded, extract the zip and run the following commands:

**Terminal 1 — Start MongoDB:**
```bash
mongod
```

**Terminal 2 — Start the backend** (runs on http://localhost:5000):
```bash
cd ctms/backend
npm install
npm run dev
```

**Terminal 3 — Start the frontend** (runs on http://localhost:5173):
```bash
cd ctms/frontend
npm install
npm run dev
```

Keep all three running while executing the automation tests. On first backend startup, the following accounts are seeded automatically:

| Role        | Email               | Password    |
|-------------|---------------------|-------------|
| Admin       | admin@ctms.com      | Admin@123   |
| Doctor      | doctor@ctms.com     | Doctor@123  |
| Nurse       | nurse@ctms.com      | Nurse@123   |
| Coordinator | coord@ctms.com      | Coord@123   |

---

## Setting Up the Automation Project

### Tech Stack

| Tool / Library         | Version  | Purpose                                        |
|------------------------|----------|------------------------------------------------|
| Java                   | 8+       | Programming language                           |
| Selenium WebDriver     | 4.40.0   | Browser automation                             |
| TestNG                 | 7.12.0   | Test framework and runner                      |
| Maven                  | 3.x      | Build and dependency management                |
| Apache POI             | 5.5.1    | Reading Excel files for data-driven tests      |
| ExtentReports          | 5.1.2    | HTML test reporting                            |
| Log4j2                 | 2.25.3   | Structured logging                             |
| JavaFaker              | 1.0.2    | Random test data generation                    |
| Apache Commons IO      | 2.21.0   | File utilities                                 |
| Apache Commons Lang3   | 3.20.0   | Utility functions                              |

### Project Structure

```
com.openctms.demo/
├── src/test/java/
│   ├── bases/
│   │   ├── BasePage.java                    # PageFactory init base
│   │   └── BaseTest.java                    # WebDriver setup, config loading, teardown
│   ├── pages/
│   │   ├── LoginPage.java
│   │   ├── DashBoardPage.java
│   │   ├── patientpages/
│   │   │   ├── PatientPage.java
│   │   │   └── RegisterPatientPage.java
│   │   ├── sitepages/
│   │   │   ├── CreateSitePage.java
│   │   │   └── SitesPage.java
│   │   ├── studypages/
│   │   │   ├── CreateStudyPage.java
│   │   │   └── StudiesPage.java
│   │   └── subjects/
│   │       ├── CreateSubjectPage.java
│   │       └── SubjectsPage.java
│   ├── pojo/
│   │   ├── Study.java
│   │   ├── Site.java
│   │   ├── Subject.java
│   │   └── Patient.java
│   ├── tests/
│   │   ├── login/
│   │   │   ├── ValidLogin.java
│   │   │   ├── InvalidLogin.java
│   │   │   ├── EmptyFieldsValidation.java
│   │   │   ├── LogoutValidation.java
│   │   │   ├── ProtectedRouteRedirectToLogin.java
│   │   │   ├── LoginDataDriven.java
│   │   │   └── RoleBasedLogin.java
│   │   ├── studies/
│   │   │   ├── CreateStudy.java
│   │   │   ├── DeleteStudy.java
│   │   │   ├── DuplicateStudyId.java
│   │   │   └── SearchStudy.java
│   │   ├── sites/
│   │   │   ├── CreateSite.java
│   │   │   ├── EditSiteStatus.java
│   │   │   ├── InvalidEmailValidation.java
│   │   │   └── SearchSite.java
│   │   ├── subjects/
│   │   │   ├── CreateSubject.java
│   │   │   ├── EditSubject.java
│   │   │   ├── AgeBoundaryValidation.java
│   │   │   └── CancelDelete.java
│   │   └── patients/
│   │       └── RegisterPatient.java
│   └── utilities/
│       ├── RandomDataUtil.java              # JavaFaker-based test data generator
│       ├── LoginHelper.java                 # Reusable login action
│       ├── DataProviders.java               # TestNG DataProviders (Excel)
│       ├── ExcelUtilities.java              # Apache POI Excel reader
│       ├── ExtentReportManager.java         # ITestListener for HTML reports
│       ├── ScreenshotUtil.java              # Screenshot capture utility
│       └── Alertutil.java                   # Bootstrap alert element helper
├── src/test/resources/
│   ├── config.properties
│   └── log4j2.xml
├── testdata/
│   ├── logindata.xlsx                       # Email, Password, ExpectedResult
│   └── roles.xlsx                           # Email, Password, ExpectedRole
├── reports/                                 # Auto-generated HTML reports
├── screenshots/                             # Failure screenshots
├── logs/                                    # automation.log
├── master.xml
├── smoke.xml
├── regression.xml
├── cross-browser-testing.xml
├── data-driven-testing.xml
└── pom.xml
```

### Installation

```bash
cd com.openctms.demo
mvn clean install -DskipTests
```

### Configuration

Edit `src/test/resources/config.properties`:

```properties
# Application URL (CTMS frontend login page)
aut=http://localhost:5173/login
dashboard.url=http://localhost:5173/dashboard

# Valid credentials (matches seeded admin account)
valid.email=admin@ctms.com
valid.password=Admin@123

# Invalid credentials (for negative tests)
invalid.email=demo@ctms.com
invalid.password=Demo@123

# Pre-existing test data IDs (must exist in the DB before regression tests)
study.id=CTMS-473
site.location=Chennai
site.name=Care Hospital
site.invalid.email=admin123.com
subject.id=CTMS-520
subject.invalid.age=0
```

> **Important:** The values for `study.id`, `site.name`, `site.location`, and `subject.id` must correspond to records that already exist in your CTMS database. Create them manually via the CTMS UI before running regression tests that depend on these values (e.g., `SearchStudy`, `DeleteStudy`, `EditSiteStatus`, `EditSubject`, `CancelDelete`).

### Test Data Files

Two Excel files are required in the `testdata/` directory:

**`testdata/logindata.xlsx`** — Used by `LoginDataDriven` test (Sheet1):

| Email               | Password   | ExpectedResult |
|---------------------|------------|----------------|
| admin@ctms.com      | Admin@123  | valid          |
| demo@ctms.com       | Demo@123   | invalid        |

**`testdata/roles.xlsx`** — Used by `RoleBasedLogin` test (Sheet1):

| Email               | Password    | ExpectedRole |
|---------------------|-------------|--------------|
| admin@ctms.com      | Admin@123   | admin        |
| doctor@ctms.com     | Doctor@123  | doctor       |
| nurse@ctms.com      | Nurse@123   | nurse        |
| coord@ctms.com      | Coord@123   | coordinator  |

---

## Test Cases

### Login Module

| Test Class                       | Group              | Description                                                                 |
|----------------------------------|--------------------|-----------------------------------------------------------------------------|
| `ValidLogin`                     | smoke, Login       | Logs in with valid admin credentials and verifies the dashboard loads       |
| `InvalidLogin`                   | Regression, Login  | Attempts login with wrong credentials and verifies the error message        |
| `EmptyFieldsValidation`          | Regression, Login  | Submits the login form empty and validates "Email is required" / "Password is required" messages |
| `LogoutValidation`               | smoke, Login       | Logs in and clicks logout, verifies redirect back to login page             |
| `ProtectedRouteRedirectToLogin`  | smoke, Login       | Navigates directly to `/dashboard` without logging in and verifies redirect |
| `LoginDataDriven`                | Regression, Login  | Data-driven login test using credentials from `logindata.xlsx`              |
| `RoleBasedLogin`                 | smoke, Login       | Logs in as each role from `roles.xlsx` and verifies the displayed role name |

### Studies Module

| Test Class         | Group               | Description                                                                   |
|--------------------|---------------------|-------------------------------------------------------------------------------|
| `CreateStudy`      | Smoke, Studies      | Creates a new study with random data (via JavaFaker) and verifies it in the table |
| `SearchStudy`      | Regression, Studies | Searches for a study by `study.id` from config and asserts it appears in table    |
| `DeleteStudy`      | Regression, Studies | Finds a study by ID, clicks delete, confirms in modal, verifies removal           |
| `DuplicateStudyId` | Regression, Studies | Attempts to create a study with an existing ID and verifies the "already exists" error |

### Sites Module

| Test Class               | Group             | Description                                                               |
|--------------------------|-------------------|---------------------------------------------------------------------------|
| `CreateSite`             | Smoke, Sites      | Creates a new site with random data and verifies success alert and table  |
| `SearchSite`             | Regression, Sites | Searches for a site by location from config and verifies results          |
| `EditSiteStatus`         | Regression, Sites | Finds an existing site by name, edits it to Inactive, verifies update     |
| `InvalidEmailValidation` | Regression, Sites | Submits create-site form with a malformed email and verifies validation   |

### Subjects Module

| Test Class               | Group                | Description                                                               |
|--------------------------|----------------------|---------------------------------------------------------------------------|
| `CreateSubject`          | Smoke, Subjects      | Creates a new subject with random data and verifies success alert         |
| `EditSubject`            | Regression, Subjects | Finds subject by ID, edits status to Active, verifies update alert        |
| `AgeBoundaryValidation`  | Regression, Subjects | Submits create-subject form with age = 0 and verifies validation error    |
| `CancelDelete`           | Regression, Subjects | Clicks delete on a subject but cancels — verifies subject still exists    |

### Patients Module

| Test Class        | Group           | Description                                                                         |
|-------------------|-----------------|-------------------------------------------------------------------------------------|
| `RegisterPatient` | smoke, Patients | Registers a new patient with random data (with retry on duplicate ID), then verifies by search |

---

## Running Tests

> Make sure the CTMS app (backend + frontend) is fully running before executing any tests.

### Full Suite

Runs all smoke and regression tests:

```bash
mvn test
```

This uses `master.xml` (configured in `pom.xml` as the default suite file). The browser defaults to `chrome`.

### Smoke Tests Only

```bash
mvn test -Dsurefire.suiteXmlFiles=smoke.xml
```

Covers: `ValidLogin`, `LogoutValidation`, `ProtectedRouteRedirectToLogin`, `RoleBasedLogin`, `CreateStudy`, `CreateSite`, `CreateSubject`, `RegisterPatient`.

### Regression Tests Only

```bash
mvn test -Dsurefire.suiteXmlFiles=regression.xml
```

Covers all login, studies, sites, and subjects regression test classes.

### Data-Driven Login Tests

```bash
mvn test -Dsurefire.suiteXmlFiles=data-driven-testing.xml
```

Runs only the `LoginDataDriven` test using credentials from `testdata/logindata.xlsx`.

### Cross-Browser Tests

```bash
mvn test -Dsurefire.suiteXmlFiles=cross-browser-testing.xml
```

Runs `ValidLogin` in parallel on Chrome and Edge (thread-count=5, parallel="tests").

### Run a Specific Test Class

```bash
mvn test -Dtest=tests.studies.CreateStudy
```

### Switch Browser

The browser is set via a TestNG `<parameter>` in each suite XML file. To run with Edge instead of Chrome, edit the relevant XML:

```xml
<parameter name="browser" value="edge"/>
```

---

## Test Suites Overview

| Suite File                  | Suite Name         | Contents                                                        |
|-----------------------------|--------------------|-----------------------------------------------------------------|
| `master.xml`                | Full Suite         | Includes `smoke.xml` + `regression.xml` as nested suites       |
| `smoke.xml`                 | Smoke Suite        | 8 smoke test classes across Login, Studies, Sites, Subjects, Patients |
| `regression.xml`            | Regression Suite   | 10 regression test classes across Login, Studies, Sites, Subjects |
| `data-driven-testing.xml`   | Suite              | `LoginDataDriven` test only                                     |
| `cross-browser-testing.xml` | Suite (parallel)   | `ValidLogin` run in parallel on Chrome + Edge                   |

---

## Reporting

HTML reports are auto-generated in the `reports/` directory after each test run via the `ExtentReportManager` TestNG listener.

- **Format:** HTML (ExtentReports Spark theme — Standard)
- **Naming:** `Test-Report-yy.MM.dd.HH.mm.ss.html`
- **Contents:** Pass / Fail / Skip per test, group/category tags, system info (browser, environment), failure message, and embedded screenshot on failure
- The report opens automatically in your default browser when the run finishes

---

## Screenshots

Failure screenshots are captured automatically and saved to the `screenshots/` directory.

- **Naming:** `<testMethodName>dd.MM.yy.HH.mm.ss.png`
- Screenshots are embedded directly into the ExtentReport HTML for easy review

---

## Logs

Log output is written to `logs/automation.log` (rolling) via Log4j2.

- Log configuration is in `src/test/resources/log4j2.xml`
- Each test class uses `LogManager.getLogger(this.getClass())` for scoped logging
- Log levels used: `INFO` for flow steps, `WARN` for retries, `ERROR` for failures

---

## Framework Architecture

```
┌──────────────────────────────────────────────────┐
│                  Test Classes                     │
│  tests/login  │  tests/studies  │  tests/sites   │
│  tests/subjects │ tests/patients                  │
└─────────────────────┬────────────────────────────┘
                      │ extends
┌─────────────────────▼────────────────────────────┐
│                   BaseTest                        │
│  • ThreadLocal<WebDriver> (parallel-safe)         │
│  • config.properties loading                      │
│  • Browser setup (Chrome/Edge + incognito mode)   │
│  • Implicit wait (10s) + maximize window          │
│  • @BeforeClass / @AfterClass lifecycle           │
└─────────────────────┬────────────────────────────┘
                      │ uses
┌─────────────────────▼────────────────────────────┐
│              Page Object Classes                  │
│  • BasePage (PageFactory.initElements)            │
│  • LoginPage, DashBoardPage                       │
│  • CreateStudyPage, StudiesPage                   │
│  • CreateSitePage, SitesPage                      │
│  • CreateSubjectPage, SubjectsPage                │
│  • RegisterPatientPage, PatientPage               │
└─────────────────────┬────────────────────────────┘
                      │ uses
┌─────────────────────▼────────────────────────────┐
│               POJO Data Models                    │
│  Study  │  Site  │  Subject  │  Patient           │
│  (plain Java objects with getters/setters)        │
└─────────────────────┬────────────────────────────┘
                      │
┌─────────────────────▼────────────────────────────┐
│              Utilities Layer                      │
│  • RandomDataUtil    — JavaFaker random data      │
│  • LoginHelper       — reusable login shortcut    │
│  • DataProviders     — Excel → @DataProvider      │
│  • ExcelUtilities    — Apache POI Excel reader    │
│  • ExtentReportManager — ITestListener (reports)  │
│  • ScreenshotUtil    — failure screenshot capture │
│  • Alertutil         — Bootstrap alert helper     │
└──────────────────────────────────────────────────┘
```

**Key design decisions:**

- **ThreadLocal WebDriver** ensures each test class gets its own isolated browser session, enabling safe parallel execution.
- **Page Object Model (POM)** with `PageFactory` keeps all locators and interactions separate from test logic.
- **POJO + JavaFaker** — data models (`Study`, `Site`, `Subject`, `Patient`) are populated with realistic random values by `RandomDataUtil`, removing hardcoded test data from test classes.
- **`LoginHelper`** is a shared utility called by tests that need a pre-authenticated session, avoiding duplicated login steps across tests.
- **`ITestListener`** in `ExtentReportManager` hooks into TestNG lifecycle events (`onStart`, `onTestSuccess`, `onTestFailure`, `onTestSkipped`, `onFinish`) so reporting is fully automatic.
- **Retry logic in `RegisterPatient`** handles potential duplicate patient ID collisions from JavaFaker gracefully with up to 3 attempts before failing.
- **Incognito mode** is enabled in both Chrome and Edge browser options to ensure a clean session with no cached state between test classes.

---

## CTMS Application — Module Reference

The CTMS app is a full-stack clinical trial management platform built with React + Vite (frontend) and Node.js + Express + MongoDB (backend). The automated tests cover the following modules:

| Module       | Backend Route         | Frontend Component              | Description                                                    |
|--------------|-----------------------|---------------------------------|----------------------------------------------------------------|
| Auth         | `/api/auth`           | `Auth/Login.jsx`                | JWT-based login, role management, user CRUD (admin only)       |
| Studies      | `/api/studies`        | `Studies/Studies.jsx`           | Create, list, search, and delete clinical studies              |
| Sites        | `/api/sites`          | `Sites/Sites.jsx`               | Create and manage research site locations                      |
| Subjects     | `/api/subjects`       | `Subjects/Subjects.jsx`         | Enroll and manage trial subjects linked to studies             |
| Patients     | `/api/patients`       | `Patients/Patients.jsx`         | Register and manage patients assigned to studies and sites     |
| Appointments | `/api/appointments`   | `Appointments/Appointments.jsx` | Schedule and manage patient appointments                       |
| Reports      | `/api/reports`        | `Reports/Reports.jsx`           | View clinical trial reports and statistics                     |
| Users        | `/api/auth/users`     | `Users/UserManagement.jsx`      | Admin-only user management (create, edit, deactivate users)    |
