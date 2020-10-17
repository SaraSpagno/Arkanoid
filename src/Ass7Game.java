import gameplay.GameFlow;
import interfaces.LevelInformation;
import thirdpart.LevelSpecificationReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * This is the main class of the assignment.
 */
public class Ass7Game {
    /**
     * Main -- the main function of the assignment.
     * <p>
     *
     * @param args - the args
     */
    public static void main(String[] args) {
        java.io.Reader reader = null;
        if (args == null || args.length == 0) {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(
                    "definitions/level_definitions.txt");
            try {
                reader = new InputStreamReader(is);
            } finally {
                LevelSpecificationReader levelSPecReader = new LevelSpecificationReader();
                List<LevelInformation> levels = levelSPecReader.fromReader(reader, false);
                GameFlow gameFlow = new GameFlow(levels);
                gameFlow.runMenu();
            }
        } else if (args.length >= 1) {
            try {
                reader = new InputStreamReader(new FileInputStream(args[0]));
            } catch (FileNotFoundException e) {
                System.out.println("The path file you have provided has some issues");
            }
            LevelSpecificationReader levelSPecReader = new LevelSpecificationReader();
            List<LevelInformation> levels = levelSPecReader.fromReader(reader, true);
            GameFlow gameFlow = new GameFlow(levels);
            gameFlow.runMenu();
        }
    }
}

