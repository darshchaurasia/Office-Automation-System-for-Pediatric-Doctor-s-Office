# Office-Automation-System-for-Pediatric-Doctor-s-Office

## Overview

Doctor's Office Automation Application for Nurse, Doctor & Patient.

## Requirements

- Java Development Kit (JDK)
- Eclipse IDE (or any Java IDE of your choice)
- SQLite DB Browser (for database management)
- [sqlite-jdbc-3.45.2.0.jar](lib/sqlite-jdbc-3.45.2.0.jar)

## Installation

1. Clone the repository.
2. Open Eclipse IDE.
3. Import the project into Eclipse.
4. Make sure to include `sqlite-jdbc-3.45.2.0.jar`,`sIf4j-simple-2.0.12.jar` and `sif4j-api-2.0.12.jar` in the project build path.
5. Ensure SQLite DB Browser is installed for managing the database.

## Usage

1. Compile and run `main.java` to start the application.
2. Use the login interface to authenticate.
3. Based on the role (Nurse, Doctor, or Patient), the respective dashboard or portal will be displayed.
4. Navigate through the application using the provided UI components.
5. Manage the database using SQLite DB Browser as needed.

## Structure

```
th43/
│
├── src/
│   ├── db/
│   │   └── DatabaseConnector.java
│   │
│   ├── dao/ 
│   │   ├── AllergyDAO.java
│   │   ├── PatientDAO.java
│   │   ├── PrescriptionDAO.java
│   │   ├── VisitDAO.java
│   │   ├── PatientDAO.java
│   │   ├── MessageDAO.java
│   │   ├── HealthIssueDAO.java
│   │   └── ImmunizationDAO.java       
│   │
│   ├── model/
│   │   ├── Allergy.java
│   │   ├── Patient.java
│   │   ├── Prescription.java
│   │   ├── Visit.java
│   │   ├── Patient.java
│   │   ├── Message.java
│   │   ├── HealthIssue.java
│   │   └── Immunization.java   
│   │
│   ├── ui/
│   │   ├── main.java
│   │   ├── PatientDetailScreen.java
│   │   ├── NurseViewScreen.java
│   │   ├── NurseDocDashboardScreen.java
│   │   ├── MessagesScreen.java
│   │   ├── Health|ssueListScreen.java
│   │   ├── AllergyEditScreen.java
│   │   ├── Health|ssueEditScreen.java
│   │   ├── DoctorViewScreen.java
│   │   ├── AllergyListScreen.java
│   │   ├── VisitListScreen.java
│   │   ├── VisitEditScreen.java
│   │   ├── SuccessfulLogin.java
│   │   ├── PrescriptionListScreen.java
│   │   ├── PrescriptionEditScreen.java
│   │   ├── PatientViewScreen.java
│   │   ├── PatientRegistration.java
│   │   ├── PatientListScreen.java
│   │   └── PatientEditScreen.java
│   │
│   └── util/
│       ├── module-info.java
│       └── UlHelper.java
│
├── lib/
│   ├── sif4j-api-2.0.12.jar
│   ├── sIf4j-simple-2.0.12.jar
│   └── sqlite-jdbc-3.45.2.0.jar
│
└── database/
    └── th43.db
 
```

## Database

The SQLite database file `th43.db` is located in the `database` directory. You can use SQLite DB Browser to manage and view the database schema and data.

## Contributing

Feel free to contribute to the project by forking it and submitting pull requests. Bug reports, feature requests, and feedback are always appreciated.

## License

This project is private and all rights are reserved. No part of this project may be reproduced, distributed, or transmitted in any form or by any means, without the prior written permission of the owner.

## Contact

darshchaurasia@gmail.com


