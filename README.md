# TrackAJob

A simple command-line Java application for tracking job applications and their status throughout the hiring process.

## Features

- **Add Job Applications**: Store details about companies, roles, locations, work format, and salary
- **View All Applications**: Display a numbered list of all your job applications
- **Filter by Stage**: View applications filtered by their current stage in the hiring process
- **Delete Applications**: Remove individual applications or clear all applications at once
- **Job Counter**: Keep track of how many applications you've submitted
- **Application Stages**: Track progress through: Sent → Interviewing → Offered → Accepted → Rejected

## Getting Started

### Prerequisites

- Java 8 or higher
- A Java IDE or terminal with Java compiler

### Running the Application

1. Clone or download the project files
2. Move into the src folder:
   ```bash
   cd src
   ```
3. Compile the Java files:
   ```bash
   javac *.java
   ```
4. Run the application:
   ```bash
   java Main
   ```

## Usage

When you start the application, you'll see a menu with the following options:

1. **Add a Job** - Input details for a new job application
2. **View All Jobs** - Display all stored applications
3. **View Jobs at a Certain Stage** - Filter applications by their current status
4. **Delete a Job** - Remove a specific application by its number
5. **Delete ALL Jobs** - Clear all stored applications
6. **Show Job Count** - Display the total number of applications
7. **Exit** - Close the application

### Adding a Job Application

When adding a job, you'll be prompted to enter:
- Company name
- Job title/role
- Location (city)
- Work format (in-person, hybrid, or remote)
- Salary/payment information
- Link to the application or job posting

All new applications start with the "Sent" stage and automatically have the date applied.

### Application Stages

The system tracks five stages:
- **Sent** (S) - Application submitted
- **Interviewing** (I) - In the interview process
- **Offered** (O) - Job offer received
- **Accepted** (A) - Offer accepted
- **Rejected** (R) - Application rejected

## Project Structure

```
TrackAJob/
├── Main.java              # Main application and user interface
├── ApplicationManager.java # Core logic for managing applications
├── JobApplication.java     # Job application data model
└── README.md              # This file
```

## Future Development 
The project is actively under development. Planned updates include the following:
- Job list exportation (in XML format)
- Enhanced validation and error catching
- Enhanced CRUD operations
- User sign-in/signup
- etc...

## Contributing

This is a simple educational project. Feel free to fork and enhance it with additional features like:
- Data persistence (save to file)
- Application export functionality
- Advanced filtering and search
- Date tracking for applications
- Email integration

## License

This project is open source and available under the MIT License.