# Blockbuster Service

## Problem Statement

Create a Blockbuster service that manages BluRay Disc copies for movies. Below are the detailed requirements:

### Assumptions:
- The service will have N BluRay Disc copies for M movies.
- Movies should contain the following data:
  - Name
  - Genre
  - Rating
  - Actor
  - Release Year
- Two types of users can access the service:
  - Main website Users: Users who can checkout and return discs.
  - Backoffice Users: Admin users who can add entries to disc copies for any existing or new movie.

### Functional Requirements:
#### Backoffice:
- User can add a new movie and the quantity of discs.
- User can modify the quantity of discs for existing movies.
- When a new movie is added, send a notification via email to Main Website users who have checked out the same Genre movie.
  - Email sending logic can be left abstract, but the target user list should be implemented.

#### Main Website:
- Users can register with an email address.
- Registered users can login.
- Logged-in users can checkout discs.
  - Only one disc per movie is allowed per user.
- Logged-in users can return discs.
- Logged-in users can search for movies based on name.
  - Search results should be ranked based on rating by default.
  - Every search result should have a recommended section with a maximum of 3 movies of the same genre.

### Technologies:
1. Implement the above using Spring Boot with In-Memory H2 DB as the database.
2. Do not use any other data source; try to make this application standalone.
3. Create only REST APIs; UI implementation is not required.

## Sample Data:

| Movie Name                                   | Genre      | Actor              | Rating | Release Year | Quantity |
|----------------------------------------------|------------|--------------------|--------|--------------|----------|
| The Shawshank Redemption                    | Drama      | Tim Robbins        | 9.3    | 1994         | 5        |
| The Godfather                                | Crime      | Daniel Brühl       | 9.2    | 1972         | 6        |
| ...                                          | ...        | ...                | ...    | ...          | ...      |

## Usage

1. Clone the repository.
2. Build and run the Spring Boot application.
3. Access the REST APIs using appropriate endpoints for backoffice and main website functionalities.

## Prerequisite

1. Java 8
2. Postman
 
## Repository Details

The codebase for this Blockbuster service is available on [GitHub](https://github.com/cyberProchnost/qualys-blockbuster-service).

