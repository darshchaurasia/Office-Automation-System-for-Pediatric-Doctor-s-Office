# Office-Automation-System-for-Pediatric-Doctor-s-Office

## Overview

Doctor's Office Automation Application for Nurse, Doctor & Patient.

## Requirements

- Java Development Kit (JDK)
- Eclipse IDE (or any Java IDE of your choice)
- SQLite DB Browser (for database management)
- [sqlite-jdbc-(version).jar](lib/sqlite-jdbc-(version).jar)

## Installation

1. Clone the repository.
2. Open Eclipse IDE.
3. Import the project into Eclipse.
4. Make sure to include `sqlite-jdbc-(version).jar` in the project build path.
5. Ensure SQLite DB Browser is installed for managing the database.

## Usage

1. Compile and run `MainApplication.java` to start the application.
2. Use the login interface to authenticate.
3. Based on the role (Nurse, Doctor, or Patient), the respective dashboard or portal will be displayed.
4. Navigate through the application using the provided UI components.
5. Manage the database using SQLite DB Browser as needed.

## Structure

```
th43/
│
├── src/
│   ├── database/
│   │   └── DatabaseConnector.java
│   │
│   ├── dao/ 
│   │   ├── AllergyDAO.java
│   │   ├── PatientDAO.java
│   │   ├── PrescriptionDAO.java
│   │   ├── VisitDAO.java
│   │   ├── PatientDAO.java
│   │   ├── MessageDAO.java
│   │   └── HealthIssueDAO.java
│   │
│   ├── model/
│   │   ├── Patient.java
│   │   ├── Visit.java
│   │   └── Prescription.java...
│   │
│   ├── ui/
│   │   ├── MainApplication.java
│   │   ├── LoginView.java
│   │   ├── NurseDashboardView.java
│   │   ├── DoctorDashboardView.java
│   │   └── PatientPortalView.java
│   │
│   └── util/
│       └── (Utility classes if any)
│
├── lib/
│   └── sqlite-jdbc-(version).jar
│
├── database/
│   └── th43.db
│
└── resources/
    ├── css/
    │   └── style.css
    └── images/
        └── (any project images)
```

## Database

The SQLite database file `th43.db` is located in the `database` directory. You can use SQLite DB Browser to manage and view the database schema and data.

## Contributing

Feel free to contribute to the project by forking it and submitting pull requests. Bug reports, feature requests, and feedback are always appreciated.

## License

This project is private and all rights are reserved. No part of this project may be reproduced, distributed, or transmitted in any form or by any means, without the prior written permission of the owner.

## Contact

darshchaurasia@gmail.com


