# REST API Testing – Rest Assured + Java + Cucumber

This is a sample project for automated testing of REST APIs using **Rest Assured**, **Java**, and **Cucumber with Gherkin syntax**.


## 🔧 Technologies Used
- Java
- Maven
- Rest Assured
- Cucumber (Gherkin)
- JUnit
- JSON Server (for mocking API)

## ✅ Current Features

This project currently supports the following test scenarios for the `Product` API:

- **Add Product** – `POST` request, `GET` request
- **Delete Product** – `DELETE` request

Test scenarios are written in Gherkin format under the `features` folder and implemented using step definitions in Java.

## 🚀 How to Run Tests

### 1. Clone the repository

```bash
git clone <your-repo-url>
cd <project-folder>
```
### 2. Install json-server globally (if not already installed)

```bash
npm install -g json-server
```
### 3.  Start the mock server
```bash
json-server --watch db.json --port 3000
```
### 4.  Run the tests
```bash
mvn test
```
🔔 Make sure the `db.json` file is present in your project root or update the path accordingly.
By default, the mock server will be accessible at `http://localhost:3000`

## 📁 Project Structure
RestAssuredApi  
├── src  
│ ├── main  
│ │ └── java  
│   │ │  ├── pojo  
│ └── test  
│ ├── java  
│ │ ├── features  
│ │ ├── stepdefinitions  
│ │ ├── testRunners  
│ │ ├── resources  
│ │ ├── config  
├── pom.xml  
└── README.md
## 📌 Planned Features
The following APIs and HTTP methods are planned to be added:
- `Product`: `POST`, `GET`, `DELETE`, `PATCH` (Done)
- `User`: `POST`, `GET`, `DELETE`, `PATCH` (Update)

