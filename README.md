# TrackAJob

A simple CLI Java application for tracking job applications and their status throughout the hiring process. The goal of this project is to practice some core OOP tenets (abstraction and encapsulation), become more familiar with Java as a programming language, and play around with file I/O. Currently, the project only supports exportation and importation to a fixed `applications.xml`, but in the future there would be wider support, among other things (which can be viewed in the Future Development section). This program supports basic CRUD operations and has a clean interface with user-friendly prompting, restricted input (looping until a satisfying input is received), enums, a helper file, etc. 

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
   javac Main.java
   ```
4. Run the application:
   ```bash
   java Main
   ```

## Usage

When you start the application, you'll see a menu with the following options:

1. **Add a Job** - Input details for a new job application
2. **View Jobs Menu** - Display all stored applications
   1. **View ALL Jobs** - View every currently logged application
   2. **View Jobs at a Certain Stage** - 
3. **View Deletion Menu** - Filter applications by their current status
   1. **Delete a Job** - Delete a singular job by the job number
   2. **Delete ALL Jobs** - Delete every currently logged job
4. **Show Job Count** - Display the total number of applications
5. **File Menu** - Display file options
   1. **Import Jobs** - Import a fixed file `applications.xml`
   2. **Export Logged Jobs** -- Export currently logged jobs to `applications.xml`
6. **Exit** - Close the application

## Example Usage (Importing `applications.xml` and viewing all jobs)
   ```
   Welcome to your job application manager :)
   Please select a number corresponding to an action you would like to take :)
   1. Add a Job
   2. View Jobs Menu
   3. View Deletion Menu
   4. Show Job Count
   5. File Menu
   6. Exit

   5 (<-- this is the user input)
   
   Please enter a number to select an option below:
   1. Import Jobs
   2. Export Logged Jobs

   1 (<-- this is the user input)
   
   Successfully loaded jobs!

   Please select a number corresponding to an action you would like to take :)
   1. Add a Job
   2. View Jobs Menu
   3. View Deletion Menu
   4. Show Job Count
   5. File Menu
   6. Exit

   2 (<-- this is the user input)

   Please enter a number to select an option from below:
   1. View ALL Jobs
   2. View Jobs at a Certain Stage

   1 (<-- this is the user input)

   1. Company: Dev10 | Role: Junior Dev | Location: NYC | Work Format: Hybrid | Payment: 55000 | Payment Type: salary | Stage: fifth | Tracking Link: https://www.google.com/maps | Date of Application: 2025-08-10 | 

   2. Company: Google | Role: Software Engineer | Location: NYC | Work Format: Hybrid | Payment: 54000 | Payment Type: salary | Stage: first | Tracking Link: https://google.com/careers | Date of Application: 2025-08-10 | 

   3. Company: CapGemini | Role: Junior Dev | Location: NYC | Work Format: Hybrid | Payment: 65000 | Payment Type: salary | Stage: first | Tracking Link: https://google.com/careers | Date of Application: 2025-08-10 | 


   Please select a number corresponding to an action you would like to take :)
   1. Add a Job
   2. View Jobs Menu
   3. View Deletion Menu
   4. Show Job Count
   5. File Menu
   6. Exit

   6 (<-- this is the user input)

   Program shutting down, it's been fun! Have a good one :) 
   ```

## Example XML
   ```xml
   <applications>
         <application>
               <company>Amazon</company>
               <role>AI ML Engineer</role>
               <location>NYC</location>
               <workFormat>Hybrid</workFormat>
               <payment>72000/salary</payment>
               <stage>first</stage>
               <trackingLink>https://amazon.com/careers</trackingLink>
               <appliedDate>2025-08-10</appliedDate>
         </application>
   </applications>
   ```

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

The system tracks up to seven stages:
- **First** - First Round
- **Second** - Second Round
- **Third** - Third Round
- **Fourth** - Fourth Round
- **Fifth** - Fifth Round
- **Sixth** - Sixth Round
- **Final** - Final Round

## Project Structure
- Below is the full project structure with brief descriptions corresponding to each file and directory.

```
TrackAJob/
├── LICENSE                    # Project license file
├── PLANNING.md                # Planning notes and development roadmap
├── README.md                  # Main project documentation

├── bin                        # Compiled Java bytecode output
│ ├── app
│ ├── applications.xml         # Serialized/compiled version of application data
│ ├── models
│ ├── service
│ └── ui
│ ├── Main.class               # Compiled main application entry point
│ └── PromptUtils.class        # Compiled input helper utilities

├── lib                        # External libraries and dependencies

├── mbl                        # The "MayBe Later directory" (mix of discarded/old files and files that may be used in the future)
│ ├── ApplicationStatus.java   # Represents application status
│ ├── FileManager.java         # Handles file read/write operations
│ └── README.md                # Documentation for mbl directory

└── src                        # Source code for the application
├── app                        # Application-specific classes
├── applications.xml           # Raw XML data for stored applications
├── models                     # Data models / enums for the app
│ ├── JobApplication.java      # Represents a job application
│ ├── PaymentType.java         # Enum for payment type
│ ├── Stage.java               # Enum for application stages
│ └── WorkFormat.java          # Enum for work formats (remote, onsite, hybrid)
├── service                    # Service layer (business logic)
│ └── ApplicationManager.java  # Core logic for managing job applications
└── ui                         # User interface components
├── Main.java                  # Main application entry point (source)
└── PromptUtils.java           # Utility class for prompting user input
```

## Future Development 
The project is actively under development. Planned updates include the following:
- User sign-in/signup
- A wider array of file extension support (html, JSON, etc)
- Session persistency (without signup/in)
- CLI --> GUI || TUI
- etc...

## Contributing

This is a simple educational project. Feel free to fork and enhance it with additional features like:
- Advanced filtering and search
- Email integration
- CLI --> GUI || TUI

## License

This project is open source and available under the MIT License.