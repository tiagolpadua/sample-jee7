# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build the project
mvn clean install

# Format code (uses Spotless with Google Java Format)
mvn spotless:apply

# Check formatting
mvn spotless:check

# Deploy to GlassFish
./autodeploy-glassfish.sh

# Deploy to WebLogic
./autodeploy-weblogic.sh
```

## Architecture

This is a Java EE 7 WAR application with JAX-RS REST endpoints.

**Key Components:**
- `ApplicationConfig` - JAX-RS application configuration, base path: `/api`
- `BookResource` - REST endpoint at `/api/books` for book operations
- `BookRepository` - Data access layer for books
- `TestResource` - Test endpoint at `/api/test`

**Runtime URLs:**
- Base: `http://localhost:8080/sample-jee7`
- Test endpoint: `http://localhost:8080/sample-jee7/api/test`
- Books endpoint: `http://localhost:8080/sample-jee7/api/books`

## Code Style

- Uses Google Java Format via Spotless Maven plugin
- Import order: java, javax, jakarta, org, com
- Uses Lombok for boilerplate reduction
- Java 8 compatibility required

## Java Best Practices (from Copilot Instructions)

- Favor immutability with `final` fields and `List.of()`/`Map.of()`
- Use `Optional<T>` instead of returning null
- Use Streams API and lambda expressions for collections
- Compare objects with `.equals()` or `Objects.equals()`, not `==`
- Always close resources with try-with-resources
- Keep methods small and parameter lists short
- Extract magic numbers into named constants

