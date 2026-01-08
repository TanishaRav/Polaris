# 🌟 Polaris

Polaris is a **personal full-stack project** currently under active development.

As a student actively applying to internships and jobs, I found it surprisingly difficult to keep track of applications, interview stages, and outcomes—let alone understand what was actually working. Most existing tools focus on job discovery and preparation, but offer little insight once applications are submitted.

Polaris was created to fill this gap by providing a **centralized, data-driven job application platform** that not only tracks applications, but is designed to evolve into a system that helps users learn from their job search and iteratively improve their approach.

---

## ✨ What Makes Polaris Different

- 📊 Focuses on **post-application tracking and outcomes**, not just job discovery  
- 🔁 Designed around **feedback loops** and iteration, not static advice  
- 🤖 Built with **future AI-powered insights** in mind to support smarter decision-making  

---

## 🚧 Current Status

This repository contains the **backend implementation for Phase 1 (v1.0.0)** of Polaris.  
The current focus is on **core data modeling, API data contracts, and foundational security**.

---

## 🧱 Phase 1 – v1.0.0  
### Database Models & DTO Foundation

### ✅ Implemented Features

#### 🧑‍💻 Core Data Models
- User accounts with secure authentication data
- Company records scoped per user
- Job application tracking with rich metadata
- Enum-based state management for consistency
- Automatic timestamp tracking for creation and updates

#### 📌 Application Tracking Capabilities
- Track applications by company and role
- Application status management (Draft, Applied, Interview, Offer, Rejected, etc.)
- Interview type tracking (Phone, Video, Recorded Video, In-Person)
- Location classification (Remote, Hybrid, Onsite)
- Salary range storage
- Job posting links and personal notes

#### 🔄 API Data Contracts (DTOs)
- Clean separation between database entities and API input/output
- Structured request and response models for:
  - User registration and login
  - Application creation, updates, and retrieval
- Joined response models that return complete application context

#### 🔐 Security & Data Integrity
- UUID-based identifiers for all entities
- BCrypt password encryption
- Email uniqueness enforcement
- User-level data isolation via ownership linking
- Input validation on all user-submitted data

---

## 🔮 Future Work

Planned features for upcoming phases include:

- 📈 Application-level analytics and resume performance tracking
- 🧠 AI-powered resume feedback and job alignment insights
- ✍️ Tailored cover letter and follow-up email generation
- 🎤 Interview preparation assistance based on role and job description
- 📊 Visualization dashboards for application trends and outcomes

---

## © Copyright

© 2026 **Tanisha Ravindran**. All rights reserved.
