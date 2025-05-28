# 🕒 Employee Attendance Transformer (Java)

This Java-based application processes a specific type of Romanian employee attendance Excel sheet and reformats it into a legally structured output model (e.g., for ITM compliance). It is tailored to work with structured `.xlsx` files and is ideal for internal HR or payroll systems needing automation of monthly attendance data.

---

## ✅ Features

- Load and process input Excel files using a GUI interface
- Auto-maps and populates attendance fields based on the model template
- Outputs a formatted Excel file (`Foaie colectiva de prezenta_ITM_FEBRUARIE_DUPA_MODEL.xlsx`)
- Uses a predefined model file (`generate.xlsx`) for structuring the output
- Handles month headers, daily markings, and employee-hour tracking

---

## 📁 Input/Output Files

| File Name                                                   | Role                         |
|-------------------------------------------------------------|------------------------------|
| `Foaie colectiva de prezenta_ITM_neprelucrat.xlsx`          | Raw attendance input         |
| `generate.xlsx`                                              | Template/model Excel         |
| `Foaie colectiva de prezenta_ITM_FEBRUARIE_DUPA_MODEL.xlsx` | Final generated attendance   |

---

## ⚙️ Technologies Used

- **Java 8+**
- **Apache POI** — for reading and writing Excel `.xlsx` files
- **Swing** — for graphical user interface (GUI)
- **Maven** — for project and dependency management (`pom.xml`)

---

## 🚀 How to Run

### 1. 📦 Requirements

- Java JDK 8 or higher
- Maven

### 2. 🛠 Build the Project

Navigate to the project root (where `pom.xml` is) and run:

```bash
mvn clean package
