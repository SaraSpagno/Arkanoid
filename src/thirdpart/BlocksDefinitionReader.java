//ID: 556693885
package thirdpart;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * In this class we define the BlocksDefinitionReader object.
 */
public class BlocksDefinitionReader {

    /**
     * fromReader -- reads and gets data from the blockdefinition file.
     * <p>
     *
     * @param reader - the reader.
     * @param args   - wether args are present or not.
     * @return BlocksFromSymbolsFactory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader, Boolean args) {
        // default properties
        int defaultHeight = 0;
        int defaultWidth = 0;
        Color defaultStroke = null;
        Color defaultColor = null;
        Image defaultImage = null;

        // lists of keys and symbols
        List<String> keys = new ArrayList<>();
        List<String> symbols = new ArrayList<>();

        // THE HASHMAPS:
        // maps for the key
        //key & height
        Map<String, Integer> width = new HashMap<>();
        // key & height
        Map<String, Integer> height = new HashMap<>();
        // key & stroke
        Map<String, Color> stroke = new HashMap<>();
        // key & fillColor
        Map<String, Color> fillColor = new HashMap<>();
        // key & imagePath
        Map<String, Image> fillImage = new HashMap<>();


        // maps for symbols

        //symbol & width
        Map<String, Integer> spaceWidth = new HashMap<>();
        // symbol & height
        Map<String, Integer> spaceHeight = new HashMap<>();


        ColorsParser colorsParser = new ColorsParser();
        BufferedReader is = null;
        try {
            is = new BufferedReader(reader);
            String line2;
            while ((line2 = is.readLine()) != null) {
                String[] parts4 = line2.split(" ");
                if (parts4[0].equals("default")) {
                    for (int h = 1; h < parts4.length; h++) {
                        String[] furtherParts4 = parts4[h].split(":");
                        if (furtherParts4[0].equals("width")) {
                            defaultWidth = Integer.parseInt(furtherParts4[1]);
                        }
                        if (furtherParts4[0].equals("height")) {
                            defaultHeight = Integer.parseInt(furtherParts4[1]);
                        }
                        if (furtherParts4[0].equals("stroke")) {
                            String answer = furtherParts4[1].substring(furtherParts4[1].indexOf("(") + 1,
                                    furtherParts4[1].indexOf(")"));
                            defaultStroke = colorsParser.colorFromString(answer);
                        }
                        if (furtherParts4[0].equals("fill")) {
                            String answer = furtherParts4[1].substring(furtherParts4[1].indexOf("(") + 1,
                                    furtherParts4[1].indexOf(")"));
                            if (furtherParts4[1].charAt(0) == 'c') {
                                defaultColor = colorsParser.colorFromString(answer);
                            } else {
                                File pathToFile = new File("resources/" + answer);
                                defaultImage = ImageIO.read(pathToFile);
                            }
                        }
                    }
                }

                if (parts4[0].equals("bdef")) {
                    String symbol = "";
                    for (int h = 1; h < parts4.length; h++) {
                        String[] furtherParts4 = parts4[h].split(":");
                        if (furtherParts4[0].equals("symbol")) {
                            symbol = furtherParts4[1];
                            keys.add(symbol);
                        }
                        if (furtherParts4[0].equals("width")) {
                            width.put(symbol, Integer.parseInt(furtherParts4[1]));
                        }
                        if (furtherParts4[0].equals("height")) {
                            height.put(symbol, Integer.parseInt(furtherParts4[1]));
                        }
                        if (furtherParts4[0].equals("stroke")) {
                            String answer = furtherParts4[1].substring(furtherParts4[1].indexOf("(") + 1,
                                    furtherParts4[1].indexOf(")"));
                            stroke.put(symbol, colorsParser.colorFromString(answer));
                        }
                        if (furtherParts4[0].equals("fill")) {
                            String answer = furtherParts4[1].substring(furtherParts4[1].indexOf("(") + 1,
                                    furtherParts4[1].indexOf(")"));
                            if (furtherParts4[1].charAt(0) == 'c') {
                                fillColor.put(symbol, colorsParser.colorFromString(answer));
                            } else {
                                if (args) {
                                    File pathToFile = new File(answer);
                                    Image image = ImageIO.read(pathToFile);
                                    fillImage.put(symbol, image);
                                } else {
                                    InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(answer);
                                    try {
                                        Image image = ImageIO.read(in);
                                        fillImage.put(symbol, image);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                        }


                    }
                }

                if (parts4[0].equals("sdef")) {
                    String symbol = "";
                    for (int h = 1; h < parts4.length; h++) {
                        String[] furtherParts4 = parts4[h].split(":");
                        if (furtherParts4[0].equals("symbol")) {
                            symbol = furtherParts4[1];
                            symbols.add(symbol);
                        }
                        if (furtherParts4[0].equals("width")) {
                            spaceWidth.put(symbol, Integer.parseInt(furtherParts4[1]));
                        }
                        if (furtherParts4[0].equals("height")) {
                            spaceHeight.put(symbol, Integer.parseInt(furtherParts4[1]));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while reading!");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }

        BlocksFromSymbolsFactory blocksFromSymbolsFactory = new BlocksFromSymbolsFactory(defaultHeight, defaultWidth,
                defaultStroke, defaultColor, defaultImage, keys, symbols);
        blocksFromSymbolsFactory.setTheRestFields(width, height, stroke,
                fillColor, fillImage, spaceWidth, spaceHeight);
        return blocksFromSymbolsFactory;
    }
}
