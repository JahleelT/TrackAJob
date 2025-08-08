# 🛠️ Java Job Tracker Upgrade Plan

This document outlines the phases to enhance a Java CLI-based job tracker project and prepare it for inclusion in a professional portfolio.

---

## ✅ Phase 1: Core Features – Data Persistence

### 1. Export to XML
- Write job application data to an XML file using file I/O.
- Include proper escaping for special characters.
- Implement the `fromString()` method via an interface to implement some inheritance

### 2. Import from XML (Planned)
- Load previously saved job data at startup.
- Handle malformed or missing XML gracefully.

---

## ✅ Phase 2: Usability & Robustness

### 3. Input Validation
- Prevent empty fields or invalid values from being accepted.
- Sanitize user input where needed.

### 4. Error Handling
- Gracefully handle I/O exceptions (e.g., missing files).
- Catch invalid menu choices and unexpected inputs.

### 5. Logging
- Replace `System.out.println()` with `Logger`.
- Log major actions: add, delete, export, import.

---

## ✅ Phase 3: Structure & Design

### 6. Package Refactoring
- Organize files into logical packages:
  - `model` for data classes
  - `service` for logic
  - `ui` for user interaction

### 7. Use Enums for Status
- Replace string status fields with `enum` types for safety and clarity.

### 8. Improve CLI Menu UX
- Add submenus, clearer instructions, and input prompts.
- Allow repeated actions without restarting the app.

---

## ✅ Phase 4: Testing

### 9. Add Unit Tests
- Use JUnit to test `ApplicationManager` methods.
- Optionally test XML export/import logic.

---

## ✅ Phase 5: Polish & Portfolio Readiness

### 10. Improve README
- Include:
  - Project description
  - How to run it
  - Features list
  - Sample XML
  - Future ideas

### 11. Add Sample Output / Demo GIF
- Provide a visual overview of how the app runs.

### 12. Command-line Arguments (Optional)
- Add support for flags like `--export=apps.xml`.

### 13. Future Feature Ideas (List in README)
- Filters by status
- Sorting
- Interview reminders

---

## 🌟 Bonus Phase (Optional): GUI

### 14. Add a Simple GUI
- Use Swing or JavaFX to create a form-based interface.
- Display application data in a table or list.

### 15. CSV Export Support
- Allow saving to `.csv` format for spreadsheet use.

---

**End Goal**: A polished, professional-level project that shows OOP, file handling, CLI UX, error handling, logging, and optionally GUI skills — ideal for a new grad portfolio.
