package inf112.skeleton.app.core.board;

import inf112.skeleton.app.core.board.events.Event;
import inf112.skeleton.app.core.board.events.MoveEvent;
import inf112.skeleton.app.core.board.events.RemoveRobotEvent;
import inf112.skeleton.app.core.board.events.RotateEvent;
import inf112.skeleton.app.core.cards.MoveCard;
import inf112.skeleton.app.core.cards.RotateCard;
import inf112.skeleton.app.core.position.Position;
import inf112.skeleton.app.core.cards.IProgramCard;
import inf112.skeleton.app.core.enums.Direction;
import inf112.skeleton.app.core.robot.IRobot;
import inf112.skeleton.app.core.tiles.*;

import java.io.File;
import java.util.*;

public class Board implements IBoard {

    private int width;
    private int height;
    private static int numberOfFlags;

    private ITile[][] grid;
    private HashMap<IRobot, Position> robots;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new ITile[width][height];
        this.robots = new HashMap<>();
    }

    public Board(int width, int height, ITile[][] grid, HashMap<IRobot, Position> robots) {
        this.width = width;
        this.height = height;
        this.grid = grid;
        this.robots = robots;
    }

    public Board(String type, int width, int height) {
        if (type.equals("empty")) {
            this.width = width;
            this.height = height;
            this.robots = new HashMap<>();
            this.grid = emptyGrid(width, height);
        } else if (type.equals("test1")) {
            this.height = 10;
            this.width = 10;
            this.robots = new HashMap<>();
            try {
                System.out.println("Working Directory = " +
                        System.getProperty("user.dir"));
                this.grid = BoardLoader.loadBoard(new File("src/main/java/inf112/skeleton/app/core/board/VaultBoard_Corr_25apr.csv"));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("something went wring while loading the board");
            }
        } else {
            throw new IllegalArgumentException("no map of type: " + type);
        }
    }

    public Board copy() {
        ITile[][] newGrid = new ITile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newGrid[i][j] = grid[i][j].copy();
            }
        }

        HashMap<IRobot, Position> newRobots = new HashMap<>();
        for (IRobot robot : robots.keySet()) {
            Position p = robots.get(robot);
            newRobots.put(robot.copy(), p);
        }

        Board newBoard = new Board(width, height, newGrid, newRobots);
        return newBoard;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private static ITile[][] emptyGrid(int w, int h) {
        ITile[][] grid = new ITile[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                grid[i][j] = new Tile();
            }
        }
        return grid;
    }

    public Queue<List<Event>> round() {
        Queue<List<Event>> events = new ArrayDeque<>();
        while (cardsLeft()) {
            events.addAll(step());
        }
        return events;
    }

    private boolean cardsLeft() {
        for (IRobot robot : robots.keySet()) {
            if (robot.peekCard() != null) return true;
        }
        return false;
    }

    public Queue<List<Event>> step() {
        Queue<List<Event>> tilesEvents = this.stepTiles();
        Queue<List<Event>> pcEvents = this.stepProgramCards();
        tilesEvents.addAll(pcEvents);
        return tilesEvents;
    }

    @Override
    public Queue<List<Event>> stepProgramCards() {
        Queue<List<Event>> programCardEvents = new ArrayDeque<>();

        // Order the robots by their program card priorities
        List<IRobot> executionOrder = new ArrayList<>();
        executionOrder.addAll(robots.keySet());
        executionOrder.sort(new Comparator<IRobot>() {
            @Override
            public int compare(IRobot r1, IRobot r2) {
                if (r1.peekCard() == null || r2.peekCard() == null) return 1;
                return r2.peekCard().priority() - r1.peekCard().priority();
            }
        });

        for (IRobot robot : executionOrder) {
            IProgramCard card = robot.drawCard();
            if (card != null) {
                Queue<List<Event>> events = moveRobot(robot, card);
                programCardEvents.addAll(events);
            }
        }

        return programCardEvents;
    }

    public Queue<List<Event>> stepTiles() {
        Queue<List<Event>> tilesEvents = new ArrayDeque<>();

        this.robots.forEach((robot, pos) -> {
            ITile tile = this.getTile(pos);
            if(tile instanceof TileAssemblyLine) {

                TileAssemblyLine amsTile = (TileAssemblyLine) tile;
                Queue<List<Event>> events = this.moveRobot(robot, amsTile.getDirection(), amsTile.getExpress() ? 1 : 2);
                tilesEvents.addAll(events);

            }else if(tile instanceof TileBlackhole) {
                tilesEvents.add(new ArrayList<>());
                tilesEvents.peek().add(new RemoveRobotEvent(robot));
                // Removed robot from board
                this.robots.remove(robot);

            }else if(tile instanceof TileGear) {

                TileGear grTile = (TileGear) tile;
                tilesEvents.add(new ArrayList<>());
                tilesEvents.peek().add(new RotateEvent(grTile.getAngle()));
                switch (grTile.getAngle()) {
                    case RIGHT:
                        robot.setDirection(robot.getDirection().getRight());
                        break;
                    case LEFT:
                        robot.setDirection(robot.getDirection().getLeft());
                        break;
                    case UTURN:
                        robot.setDirection(robot.getDirection().getOpposite());
                        break;
                    default:
                        throw new Error("TYPE ERROR: Tile angle is not valid");
                }

            }else if(tile instanceof Tile) {

                return;

            }else{
                throw new Error("TYPE ERROR: Tile instance is not valid");
            }
        });

        return tilesEvents;
    }

    @Override
    public Queue<List<Event>> moveRobot(IRobot robot, IProgramCard card) {
        Queue<List<Event>> events;
        if (card instanceof RotateCard) {
            RotateCard rotateCard = (RotateCard) card;
            Direction newDirection = rotateCard.getDir(robot.getDirection());
            robot.setDirection(newDirection);
            events = new ArrayDeque<>();
            events.add(new ArrayList<>());
            events.peek().add(new RotateEvent(rotateCard.getDirectionChange()));

        } else if (card instanceof MoveCard) {
            MoveCard moveCard = (MoveCard) card;
            Direction dir = robot.getDirection();
            int amount = moveCard.getAmount();
            if (moveCard.movesBackwards()) dir = dir.getOpposite();
            events = moveRobot(robot, dir, amount);
        } else {
            throw new RuntimeException("unimplemented behaviour for this kind of program card");
        }
        return events;
    }

    @Override
    public Queue<List<Event>> moveRobot(IRobot robot, Direction dir, int ammount) {
        Queue<List<Event>> events = new ArrayDeque<>();
        moveRobot(robot, dir, ammount, events);
        return events;
    }

    private boolean moveRobot(IRobot robot, Direction dir, int amount, Queue<List<Event>> queue) {
        if (amount == 0) return true;

        Position currentPosition = robots.get(robot);
        Position newPosition = dir.getNewPosition(currentPosition);

        if (withinBounds(newPosition)) {
            ITile nextTile = getTile(newPosition);
            if (nextTile.canEnter(dir)) {
                if (nextTile.hasRobot()) {
                    if (moveRobot(nextTile.getRobot(), dir, 1, queue)) {
                        Event event = moveRobotToNewTile(currentPosition, newPosition);
                        queue.peek().add(event);
                        return moveRobot(robot, dir, amount - 1, queue);
                    }
                } else {
                    Event event = moveRobotToNewTile(currentPosition, newPosition);
                    List<Event> events = new ArrayList<>();
                    events.add(event);
                    queue.add(events);
                    return moveRobot(robot, dir, amount - 1, queue);
                }
            }
        } else {
            queue.addAll(new ArrayList<>());
            queue.peek().add(new RemoveRobotEvent(robot));
            return true;
        }

        return false;
    }

    @Override
    public void addRobot(IRobot robot, Position position) {
        if (hasRobot(position)) throw new IllegalArgumentException("Tile is occupied.");
        robots.put(robot, position);
        getTile(position).setRobot(robot);
    }

    /**
     * HELPER METHODS
     */

    public Event moveRobotToNewTile(Position from, Position to) {
        ITile fromTile = getTile(from);
        ITile toTile = getTile(to);
        if (!fromTile.hasRobot())
            throw new IllegalArgumentException("can't move a robot from a tile that has no robot");
        IRobot robot = fromTile.getRobot();
        toTile.setRobot(robot);
        fromTile.setRobot(null);
        robots.put(robot, to);
        return new MoveEvent(robot, from, to);
    }

    private boolean withinBounds(Position position) {
        return withinBounds(position.getX(), position.getY());
    }

    private boolean withinBounds(int x, int y) {
        if (x < 0 || y < 0) return false;
        if (x >= this.width || y >= this.height) return false;
        return true;
    }

    @Override
    public ITile getTile(Position position) {
        return getTile(position.getX(), position.getY());
    }

    @Override
    public ITile getTile(int x, int y) {
        if (!withinBounds(x, y)) throw new IllegalArgumentException("coordinates out of bounds");
        return this.grid[x][y];
    }

    @Override
    public boolean hasRobot(Position position) {
        return getTile(position).hasRobot();
    }

    @Override
    public IRobot getRobot(Position position) {
        if (hasRobot(position)) return this.getTile(position).getRobot();
        return null;
    }

    public Position getRobotPosition(IRobot robot) {
        return robots.get(robot);
    }

    public Iterable<IRobot> getRobots() {
        return robots.keySet();
    }

    public static int getNumberOfFlags() {
        return numberOfFlags;
    }
}
