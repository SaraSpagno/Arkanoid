//ID: 556693885
package thirdpart;

import backgrounds.StandardBackground;
import gameplay.Velocity;
import geometry.Point;
import geometry.Rectangle;
import interfaces.LevelInformation;
import interfaces.Sprite;
import levels.StandardLevel;
import sprites.Block;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * In this class we define the LevelSpecificationReader object.
 */
public class LevelSpecificationReader {

    /**
     * fromReader -- reads and gets data from the level definition file.
     * <p>
     *
     * @param reader - the reader.
     * @param args   - wether the args are present or not.
     * @return List<LevelInformation>.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader, Boolean args) {
        List<LevelInformation> levels = new ArrayList<>();
        LevelInformation standardLevel;
        BufferedReader is = null;
        //InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("level_definitions.txt");

        try {
            is = new BufferedReader(reader);
            String line;
            int numLevels = 0;
            List<String> levelNames = new ArrayList<>();
            List<String> paddleVelocities = new ArrayList<>();
            List<String> paddleWidths = new ArrayList<>();
            List<String> initialBallVelocities2 = new ArrayList<>();
            List<String> blockToRemove = new ArrayList<>();
            List<String> backgrounds = new ArrayList<>();
            List<String> blockXs = new ArrayList<>();
            List<String> blockYs = new ArrayList<>();
            List<String> blockHeights = new ArrayList<>();
            List<String> blockDefinitions = new ArrayList<>();
            List<String> blocksStrings = new ArrayList<>();

            boolean isBlockLine = false;

            while ((line = is.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals("level_name")) {
                    levelNames.add(parts[1]);
                    numLevels++;
                }
                if (parts[0].equals("ball_velocities")) {
                    initialBallVelocities2.add(parts[1]);
                }
                if (parts[0].equals("background")) {
                    backgrounds.add(parts[1]);
                }
                if (parts[0].equals("paddle_speed")) {
                    paddleVelocities.add(parts[1]);
                }
                if (parts[0].equals("paddle_width")) {
                    paddleWidths.add(parts[1]);
                }
                if (parts[0].equals("num_blocks")) {
                    blockToRemove.add(parts[1]);
                }
                if (parts[0].equals("blocks_start_y")) {
                    blockYs.add(parts[1]);
                }
                if (parts[0].equals("blocks_start_x")) {
                    blockXs.add(parts[1]);
                }
                if (parts[0].equals("row_height")) {
                    blockHeights.add(parts[1]);
                }
                if (parts[0].equals("block_definitions")) {
                    blockDefinitions.add(parts[1]);
                }
                if (line.equals("END_BLOCKS")) {
                    isBlockLine = false;
                    blocksStrings.add("stop");
                }
                if (isBlockLine) {
                    blocksStrings.add(line);
                }
                if (line.equals("START_BLOCKS")) {
                    isBlockLine = true;
                }
            }

            /// for each level :
            String levelName;
            Velocity paddleVelocity;
            int paddleWidth;
            int blocksToRemove;
            List<Velocity> initialBallVelocities;
            List<Block> blocks;
            Sprite background;
            for (int i = 0; i < numLevels; i++) {

                ColorsParser colorsParser = new ColorsParser();
                initialBallVelocities = new ArrayList<>();

                // easy stuff
                levelName = levelNames.get(i);
                paddleVelocity = Velocity.fromAngleAndSpeed(90, Integer.parseInt(paddleVelocities.get(i)));
                paddleWidth = Integer.parseInt(paddleWidths.get(i));
                blocksToRemove = Integer.parseInt(blockToRemove.get(i));

                // initial ball velocities
                String[] parts = initialBallVelocities2.get(i).split(" ");
                for (String part : parts) {
                    String[] furtherParts = part.split(",");
                    initialBallVelocities.add(Velocity.fromAngleAndSpeed(Integer.parseInt(furtherParts[0]),
                            Integer.parseInt(furtherParts[1])));
                }

                // background
                background = fill(backgrounds.get(i), colorsParser, args);


                // getting blocks list :
                if (args) {
                    reader = new InputStreamReader(new FileInputStream(blockDefinitions.get(i)));
                } else {
                    reader = new InputStreamReader(
                            ClassLoader.getSystemClassLoader().getResourceAsStream(
                                    blockDefinitions.get(i)));
                }
                BlocksFromSymbolsFactory blocksDefFac = BlocksDefinitionReader.fromReader(reader, args);
                blocks = blocks(blocksStrings, blockXs.get(i), blocksDefFac, blockYs.get(i), blockHeights.get(i));

                // making the level
                standardLevel = new StandardLevel(levelName, paddleVelocity, paddleWidth,
                        background, initialBallVelocities, blocks, blocksToRemove);
                levels.add(standardLevel);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while reading");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }
        return levels;
    }


    /**
     * StandardBackground -- creates a background.
     * <p>
     *
     * @param background   - the string of the background.
     * @param colorsParser - the color parser.
     * @param args         - wether the args are present or not.
     * @return StandardBackground.
     */
    private StandardBackground fill(String background, ColorsParser colorsParser, Boolean args) {
        Color backgroundColor = null;
        Image backgroundImage = null;
        String result = background.substring(background.indexOf("(") + 1,
                background.indexOf(")"));
        if (background.charAt(0) == 'c') {
            backgroundColor = colorsParser.colorFromString(result);
        } else {
            if (args) {
                File pathToFile = new File(result);
                try {
                    backgroundImage = ImageIO.read(pathToFile);
                } catch (IOException e) {
                    System.out.println("The path file you have provided for one of the images has some issues");
                }
            } else {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(result);
                try {
                    backgroundImage = ImageIO.read(is);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new StandardBackground(backgroundColor, backgroundImage);
    }

    /**
     * blocks -- creates the array of blcoks.
     * <p>
     *
     * @param blocksStrings - the blockString list.
     * @param blockYs       - the block starting y.
     * @param blocksDefFac  - all the block information.
     * @param blockXs       - the block starting s.
     * @param blockHeights  - the row heights of the game.
     * @return List.
     */
    private List<Block> blocks(List blocksStrings, String blockXs, BlocksFromSymbolsFactory blocksDefFac,
                               String blockYs, String blockHeights) {
        List<Block> blocks = new ArrayList<>();
        // actually trying to create the blocks
        int countRows = 0;
        int blockWidth;
        int x;
        // copying list (in order to remove from original while iterating)
        List<String> blockStringsCopy = new ArrayList<>(blocksStrings);
        // for all the strings in the blocksStrings UNTIL the stop
        for (String string : blockStringsCopy) {
            x = Integer.parseInt(blockXs);
            blockWidth = 0;
            if (string.equals("stop")) {
                blocksStrings.remove(string);
                break;
            }
            // for all the chars in this string
            for (Character c : string.toCharArray()) {
                String s = c.toString();
                if (blocksDefFac.isBlockSymbol(s)) {
                    // the y of the block
                    int y = Integer.parseInt(blockYs)
                            + Integer.parseInt(blockHeights) * countRows;
                    // the x of the block
                    // this is equal to the x of the previous block in the line, + it's x
                    x = x + blockWidth;
                    // width
                    blockWidth = blocksDefFac.getBlockWidth(s);
                    // height
                    int blockHeight = blocksDefFac.getBlockHeight(s);
                    // the fill
                    Image blockImage = null;
                    Color blockColor = null;
                    Map<Color, Image> fill = blocksDefFac.getBlockFill(s);
                    for (Map.Entry<Color, Image> entry : fill.entrySet()) {
                        blockColor = entry.getKey();
                        blockImage = entry.getValue();
                    }
                    // the stroke
                    Color frame = blocksDefFac.getBlockStroke(s);
                    // building and adding the block
                    Block block = new Block(new Rectangle(new Point(x, y), blockWidth, blockHeight),
                            blockColor, frame, blockImage);
                    blocks.add(block);
                }
                if (blocksDefFac.isSpaceSymbol(s)) {
                    // changing the general x
                    x = x + blockWidth;
                    // changing the general blockWidth
                    blockWidth = blocksDefFac.getSpaceWidth(s);
                }
            }
            blocksStrings.remove(string);
            countRows++;
        }
        return blocks;
    }
}
