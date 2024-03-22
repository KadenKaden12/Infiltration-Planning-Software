import java.util.ArrayList;
import java.util.HashMap;
import common.*;
import obstacles.*;


public class example {
    public static void main(String[] args) {

        HashMap<String, ArrayList<String>> parsedArgs = parseArgs(args);
        ArrayList<Obstacle> obstacles = parseObstacles(parsedArgs);
        Map map = new Map(obstacles);

        // Parse the start and target locations
        String startArg = stripParentheses(parsedArgs.get("-start").get(0));
        String targetArg = stripParentheses(parsedArgs.get("-target").get(0));
        Location start = Location.parse(startArg);
        Location target = Location.parse(targetArg);

        // Show the map
        System.out.println(map.getSolvedMap(start, target));
    }

    private static HashMap<String, ArrayList<String>> parseArgs(String[] args) {
        HashMap<String, ArrayList<String>> parsedArgs = new HashMap<>();
        ArrayList<String> argValues = null;
        for (String arg : args) {
            if (arg.startsWith("-")) {
                argValues = new ArrayList<>();
                parsedArgs.put(arg, argValues);
                continue;
            }
            if (argValues != null) {
                argValues.add(arg);
            }
        }
        return parsedArgs;

    }
    private static String stripParentheses(String arg) {
        return arg.substring(1, arg.length() - 1);
    }

    /**
     * Parses the obstacles from the command line arguments
     * @param parsedArgs The parsed arguments
     */
    public static ArrayList<Obstacle> parseObstacles(HashMap<String, ArrayList<String>> parsedArgs) {
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        ObstacleType type = ObstacleType.GUARD;
        String key = "-" + type.getArgumentName();
        ArrayList<String> args = parsedArgs.get(key);
        if (args == null) {
            return obstacles;
        }
        for (String arg : args) {
            // Remove the parentheses from the argument
            String cleanedArg = stripParentheses(arg);
            Obstacle obstacle = Guard.parse(cleanedArg);
            obstacles.add(obstacle);
        }
        return obstacles;
    }
}
