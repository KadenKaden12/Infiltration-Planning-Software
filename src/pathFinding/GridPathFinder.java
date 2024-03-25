package pathFinding;

import common.Location;

public interface GridPathFinder {

    Path findPath(Location startLocation, Location endLocation);


    Iterable<Location> getNeighbors(Location location);
}