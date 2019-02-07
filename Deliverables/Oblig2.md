# Task 1 
* Team leader: Rune
* Customer contact: Andreas
* **Competence:**
    * Everyone has knowledge of java
    * Everyone has taken INF100, INF101 and INF102
    * **Dovydas**: digital drawing
    * **Omar**: Networking, music
    * **Petter**: Music, linux, git
    * **Rune**: Linux, SSH, ubuntu server, ehcp
* **Technical tools**:
    * Java
    * Maven
    * Github
    * JUnit
    * IntelliJ

# Task 2
## Overall goal of the application
* A playable RoboRally games with all original rules implemented
* Multiplayer over LAN

## High level requirements
* Board
* Tiles
    * Different kinds of tiles: empty, rotate, black hole, assembly line
* Flags
* Players
* Robots
    * Lasers (ability to shoot and get hit)
    * Health
* Cards
    * Programming cards
    * Option cards

## First iteration
* Everyone looks at LibGDX (tutorial or similar)
* Make a representation of the programming cards
* Start making the board, tile and robot representations
* Make a grid-demo in LibGDX
* Look into how multiplayer can be achieved (over internet/LAN)

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

# Retrospective
* We decided to switch from trello to github (project board). It was hard to keep track of multiple channels of information (easier with one less).
* We decided to use pull requests for easier code review (instead of pushing directly to master)
* We decided to add a github plugin to slack for notifications about changes in the repository.
