//ID: 556693885
package thirdpart;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * In this class we define the ColorsParser object.
 */
public class ColorsParser {

    /**
     * colorFromString - gets a string and return a color.
     * <p>
     *
     * @param s - the string
     * @return Color - the color.
     */
    public java.awt.Color colorFromString(String s) {
        if (s.charAt(0) == 'R') {
            // get the three RGB ints
            String furtherAnswer = s.substring(s.indexOf("(") + 1);
            String[] furtherAnswerParts = furtherAnswer.split(",");
            return new Color(Integer.parseInt(furtherAnswerParts[0]),
                    Integer.parseInt(furtherAnswerParts[1]), Integer.parseInt(furtherAnswerParts[2]));
        } else {
            try {
                Field field = Class.forName("java.awt.Color").getField(s);
                return (Color) field.get(null);
            } catch (Exception e) {
                return null; // Not defined
            }
        }
    }
}