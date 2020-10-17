//ID: 556693885
package thirdpart;


import sprites.Block;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In this class we define the BlocksFromSymbolsFactory object.
 */
public class BlocksFromSymbolsFactory {


    // fields


    // default properties
    private int defaultHeight;
    private int defaultWidth;
    private Color defaultStroke;
    private Color defaultColor;
    private Image defaultImage;


    // lists of keys and symbols
    private List<String> keys;
    private List<String> symbols;


    // THE HASHMAPS:

    // maps for the key

    //key & height
    private Map<String, Integer> width;
    // key & height
    private Map<String, Integer> height;
    // key & stroke
    private Map<String, Color> stroke;
    // key & fillColor
    private Map<String, Color> fillColor;
    // key & imagePath
    private Map<String, Image> fillImage;


    // maps for symbols

    //symbol & width
    private Map<String, Integer> spaceWidth;
    // symbol & height
    private Map<String, Integer> spaceHeight;


    /**
     * Constructors.
     * <p>
     *
     * @param defaultHeight - the defaultHeight.
     * @param defaultWidth  - the defaultWidth
     * @param defaultStroke - the defaultStroke
     * @param defaultColor  - the defaultColor
     * @param defaultImage  - the defaultImage
     * @param keys          - the keys
     * @param symbols       - the symbols
     */
    public BlocksFromSymbolsFactory(int defaultHeight, int defaultWidth, Color defaultStroke, Color defaultColor,
                                    Image defaultImage, List<String> keys, List<String> symbols) {
        this.defaultHeight = defaultHeight;
        this.defaultWidth = defaultWidth;
        this.defaultStroke = defaultStroke;
        this.defaultColor = defaultColor;
        this.defaultImage = defaultImage;
        this.keys = keys;
        this.symbols = symbols;
    }

    /**
     * setTheRestFields - sets the rest of the fields.
     * <p>
     *
     * @param width1       - the width
     * @param height1      - the height
     * @param stroke1      - the stroke
     * @param fillColor1   - the fillColor
     * @param fillImage1   - the fillImage
     * @param spaceHeight1 - the spaceHeight
     * @param spaceWidth1  - the spaceWidth
     */
    public void setTheRestFields(Map<String, Integer> width1, Map<String, Integer> height1, Map<String, Color> stroke1,
                                 Map<String, Color> fillColor1, Map<String, Image> fillImage1, Map<String,
            Integer> spaceWidth1, Map<String, Integer> spaceHeight1) {
        this.width = width1;
        this.height = height1;
        this.stroke = stroke1;
        this.fillColor = fillColor1;
        this.fillImage = fillImage1;
        this.spaceWidth = spaceWidth1;
        this.spaceHeight = spaceHeight1;
    }


    /**
     * isSpaceSymbol - checks if a symbol is a space.
     * <p>
     *
     * @param s - the string symbol
     * @return boolean - true if is a space.
     */
    public boolean isSpaceSymbol(String s) {
        for (String symbol : symbols) {
            if (symbol.equals(s)) {
                return true;
            }
        }
        return false;
    }


    /**
     * isSpaceSymbol - checks if a symbol is a block.
     * <p>
     *
     * @param s - the string symbol
     * @return boolean - true if is a block.
     */
    public boolean isBlockSymbol(String s) {
        for (String key : keys) {
            if (key.equals(s)) {
                return true;
            }
        }
        return false;
    }


    /**
     * getBlock - gets a block.
     * <p>
     *
     * @param s    - the string symbol
     * @param xpos - the x of the block.
     * @param ypos - the y of the block.
     * @return Block - the block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return null;
    }


    /**
     * getBlockWidth - gets a block width.
     * <p>
     *
     * @param s - the string symbol
     * @return int - the width.
     */
    public int getBlockWidth(String s) {
        // check if the width exists
        if (width.containsKey(s)) {
            return width.get(s);
        } else if (defaultWidth != 0) {
            return defaultWidth;
        } else {
            System.out.println("ERROR, NO WIDTH DECLARED");
        }
        return 0;
    }

    /**
     * getBlockHeight - gets a block height.
     * <p>
     *
     * @param s - the string symbol
     * @return int - the height.
     */
    public int getBlockHeight(String s) {
        // check if the width exists
        if (height.containsKey(s)) {
            return height.get(s);
        } else if (defaultHeight != 0) {
            return defaultHeight;
        } else {
            System.out.println("ERROR, NO HEIGHT DECLARED");
        }
        return 0;
    }

    /**
     * getBlockStroke - gets a block Stroke.
     * <p>
     *
     * @param s - the string symbol
     * @return int - the Stroke.
     */
    public Color getBlockStroke(String s) {
        if (stroke.containsKey(s)) {
            return stroke.get(s);
        } else if (defaultStroke != null) {
            return defaultStroke;
        }
        return null;
    }

    /**
     * getBlockFill - gets a block Fill.
     * <p>
     *
     * @param s - the string symbol
     * @return Map - the Fill.
     */
    public Map<Color, Image> getBlockFill(String s) {
        HashMap<Color, Image> result = new HashMap<>();
        // the fill
        Color blockColor = null;
        Image blockImage = null;

        // if image
        if (fillImage.containsKey(s)) {
            blockImage = fillImage.get(s);
            // if color
        } else if (fillColor.containsKey(s)) {
            blockColor = fillColor.get(s);
            //if default image
        } else if (defaultImage != null) {
            blockImage = defaultImage;
            //if default color
        } else if (defaultColor != null) {
            blockColor = defaultColor;
        } else {
            System.out.println("ERROR, NO FILL DECLARED");
            return null;
        }
        result.put(blockColor, blockImage);
        return result;
    }


    /**
     * getSpaceHeight - gets a Space height.
     * <p>
     *
     * @param s - the string symbol
     * @return int - the height.
     */
    public int getSpaceWidth(String s) {
        if (spaceWidth.containsKey(s)) {
            return spaceWidth.get(s);
        } else if (defaultWidth != 0) {
            return defaultWidth;
        } else {
            System.out.println("ERROR, NO WIDTH DECLARED");
        }
        return 0;
    }
}