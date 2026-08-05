# Todo API database setup

The application connects to PostgreSQL with JDBC. By default it uses:

```text
URL:      jdbc:postgresql://localhost:5433/todo_db
User:     postgres
Password: postgres
```

Start PostgreSQL with Docker from this directory:

```bash
docker run --name todo-postgres \
  -e POSTGRES_DB=todo_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5433:5432 \
  -v "$PWD/database/init.sql:/docker-entrypoint-initdb.d/01-init.sql:ro" \
  -d postgres:17-alpine
```

Then run `TodoApp` from IntelliJ. The SQL in `database/init.sql` creates and seeds the `todos` table the first time the container starts.

To use different credentials, set these environment variables before running the application:

```text
DB_URL
DB_USER
DB_PASSWORD
```
