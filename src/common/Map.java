package common;

import obstacles.Obstacle;

import java.util.ArrayList;

public class Map {

    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private final int PADDING = 2;

    public Map(ArrayList<Obstacle> obstacles) {
        this.obstacles.addAll(obstacles);
    }

    private Obstacle getObstacleAtLocation(int x, int y) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.isLocationObstructed(x, y)) {
                return obstacle;
            }
        }
        return null;
    }

    private String matrixToString(char[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : matrix) {
            for (char symbol : row) {
                sb.append(symbol);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getSolvedMap(Location start, Location target) {
        Location topLeft, bottomRight;
        int maxX, maxY, minX, minY;
        maxX = Math.max(start.getX(), target.getX());
        maxY = Math.max(start.getY(), target.getY());
        minX = Math.min(start.getX(), target.getX());
        minY = Math.min(start.getX(), target.getY());

        topLeft = new Location(minX - PADDING, minY - PADDING);
        bottomRight = new Location(maxX + PADDING, maxY + PADDING);

        char[][] solvedMap = new char[bottomRight.getY() - topLeft.getY() + 1][bottomRight.getX() - topLeft.getX() + 1];
        for (int y = topLeft.getY(); y <= bottomRight.getY(); y++) {
            for (int x = topLeft.getX(); x <= bottomRight.getX(); x++) {
                if (x == start.getX() && y == start.getY()) {
                    solvedMap[y - topLeft.getY()][x - topLeft.getX()] = 'S';
                    continue;
                }
                if (x == target.getX() && y == target.getY()) {
                    solvedMap[y - topLeft.getY()][x - topLeft.getX()] = 'E';
                    continue;
                }

                Obstacle obstructingObstacle = getObstacleAtLocation(x, y);

                int j = y - topLeft.getY();
                int i = x - topLeft.getX();
                if (obstructingObstacle != null) {
                    solvedMap[j][i] = obstructingObstacle.getSymbol();
                    continue;
                }
                solvedMap[j][i] = '.';

            }

        }
        return matrixToString(solvedMap);
    }
}


