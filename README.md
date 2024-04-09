Project Structure:

th43/
│
├── src/
│   ├── database/
│   │   └── DatabaseConnector.java
│   │
│   ├──dao/ 
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

