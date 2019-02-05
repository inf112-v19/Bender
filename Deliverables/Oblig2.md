# Deloppgave 1
* Gruppeleder: Rune
* Kundekontakt: Andreas

### Dovydas
* INF100/INF101/INF102
* Digital drawing

### Omar
* INF100/INF101/INF102
* Networks

### Petter
* INF100/INF101/INF102
* Music
* Linux
* Git

### Omar
* INF100/INF101/INF102
* Musikk

### Rune
* INF100/INF101/INF102
* Linux, ssh, Ubuntu server, EHCP

### Andreas
* INF100/INF101/INF102

## Tekniske verktøy
* Maven
* IntelliJ
* JUnit

# Deloppgave 2
## Overordnet mål
* LAN (Multiplayer)
* Fungerende spill med alle orginale regeler 
* Top-Down

## Høynivåkrav (gjennomgått i forelesning)
* Board
    * List of Tiles (med interface)
        * Tile, vegger, referanse til robot, forskjellige typer
        * Flytting av roboter fra tile til tile
    * Flag
    * (x, y) istendefor Location object 
    * Player
    * En Tile har en referanse til en Robot
    * Robot
        * Health
        * Ability to shoot and get hit
* Player
    * Cards
        * Skudd kort
    * Feature cards

## Først iterasjon
* Alle ser en tutorial på LibGDX
* Lage cards opplegg (Petter)
* Grid og tiles (Andreas)
* Lage et lite grid med libgdx (Dovydas)
* Robot interface og object (damage og sånt) (Rune)
* Multiplayer / nettverk (Omar)

# Task 3 - Project methodology
## Kanbanish
* We will use trello.
* We will use four columns: todo, doing, review, done.
* Everyone will be responsible of filling the todo column with new tasks.
* The tasks should be small enough to complete within a relatively short amount of time.
* Bigger tasks should be split into subtasks.
* The team leader (Rune) will decide priorities for the tasks. The 'upper' cards have the highest priorities.
* The WIP (work in progress) limit on *doing* and *review* is 5.
    * One person should only be working on one task at any given moment.
    * If there are 5 tasks in review, these should be reviewed before new tasks are completed.
* The team leader (Rune) can also choose to assign tasks.

## Git usage
* Create a new branch when starting on a new task.
* When the task is completed:
    * Move the card to review.
    * Create a pull request on github.
    * Add one or more reviewers.

## Testing and documentation
* Self-explanatory code should not be documented.
* Most code should be unit tested.

## Meetings and communication
* Initially, we will meet in person evry week in the group sessions.
* Communication between the meetings happen on slack and trello.
* Code, docs and mandatory exercises should be commited to the git repository.
* Other files like charts, word documents, etc .. should be stored on google drive.
