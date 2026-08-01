# Auth Backend Template

A reusable Spring Boot authentication backend template — JWT auth, MySQL, Redis, rate limiting — fully containerized with Docker. Intended as a starting point for new projects; clone and rename to get going quickly.

## Tech Stack

- **Backend:** Spring Boot (Java 21), Spring Data JPA, Spring Security (JWT)
- **Database:** MySQL 8.0
- **Cache:** Redis 7.2
- **Containerization:** Docker, Docker Compose
- **API Docs:** springdoc-openapi (Swagger UI)

## Project Structure

```
project-root/
├── .env.template               # Root-level template (Docker Compose vars)
├── docker-compose.yml          # Full stack (backend, frontend, MySQL, Redis)
├── docker-compose.dev.yml      # Just MySQL + Redis, for local dev without Docker
├── backend/
│   ├── src/
│   ├── .env.template           # Template for backend secrets
│   ├── application.properties
│   ├── application-dev.properties
│   ├── application-docker.properties
│   └── Dockerfile
└── frontend/
    ├── src/
    ├── .env.template           # Template for frontend config
    └── Dockerfile
```

## Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed and running

## Using This Template

Before running anything, replace these placeholders throughout the project (`docker-compose.yml`, `docker-compose.dev.yml`, `application.properties`, README, etc.):

| Placeholder | Replace with |
|---|---|
| `YOUR_APP_NAME` | Your project's name (e.g. `dentalcare`) |
| `YOUR_DB_NAME` | Your database name (e.g. `dental_appointment`) |
| `app-name` (in `spring.application.name`) | Your Spring app's name |

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/rgrmlbn/Template.git
cd <your-project-folder>
```

### 2. Configure root environment variables (for Docker Compose)

```bash
cp .env.template .env
```

Open `.env` and set:

```
DB_PASSWORD=your_own_db_password
```

This feeds `${DB_PASSWORD}` inside `docker-compose.yml`, which sets MySQL's root password when the container starts.

### 3. Configure backend environment variables

```bash
cd backend
cp .env.template .env
```

Open `backend/.env` and fill in your own values:

```
DB_USERNAME=root
DB_PASSWORD=your_own_db_password
JWT_SECRET=a_long_random_string
```

> `DB_PASSWORD` here should match the value you set in the root `.env` — the root file configures MySQL itself, this one tells Spring Boot what password to connect with.

### 4. Configure frontend environment variables (if applicable)

```bash
cd ../frontend
cp .env.template .env
```

### 5. Run the full stack with Docker

From the project root:

```bash
docker-compose up --build
```

This starts:
- MySQL on `localhost:3306`
- Redis on `localhost:6379`
- Backend API on `localhost:8080`
- Frontend on `localhost:5173` (if present)

### 6. Access the app

- Frontend: [http://localhost:5173](http://localhost:5173) (if present)
- Backend API: [http://localhost:8080](http://localhost:8080)
- Swagger docs: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Running the Backend Locally (without Docker)

Useful for active backend development with hot reload / debugging in an IDE.

1. Start just the database and cache:
   
   ```bash
   docker-compose -f docker-compose.dev.yml up
   ```
3. Open the `backend/` folder in your IDE (e.g. IntelliJ) as its own project.
4. Make sure `backend/.env` is filled in — it's read automatically via `spring-dotenv`.
5. Run the Spring Boot application (`dev` profile is active by default).

## Environment Variables Reference

### Root (`.env`)

| Variable | Description |
|---|---|
| `DB_PASSWORD` | MySQL root password (used by Docker Compose to configure the `mysql` service) |

### Backend (`backend/.env`)

| Variable | Description |
|---|---|
| `DB_USERNAME` | MySQL username used by the app to connect |
| `DB_PASSWORD` | MySQL password used by the app to connect (should match root `.env`) |
| `JWT_SECRET` | Secret key used to sign and verify JWT tokens |

## Notes

- Never commit `.env` files — only `.env.template` files are tracked in git.
- `application-dev.properties` and `application-docker.properties` are safe to commit; they only reference environment variables (`${VAR}`), never real values.
- Rate limiting for login attempts is configured via `app.rate-limit.login.*` in the properties files and backed by Redis.
- To reset the database to a clean state, stop containers and remove volumes:
  
  ```bash
  docker-compose down -v
  ```
- Remember to generate a fresh, unique `JWT_SECRET` for every real project you spin off from this template — never reuse the same secret across projects.
