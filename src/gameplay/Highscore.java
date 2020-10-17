//ID:556693885
package gameplay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * In this class we define the HighScore class.
 */
public class Highscore {
    // field
    private int score;
    private int highscore = 0;


    /**
     * Constructor.
     * <p>
     *
     * @param score - the score to display
     */
    public Highscore(int score) {
        this.score = score;
    }

    /**
     * write - writes the score on the file.
     * <p>
     */
    public void write() {
        OutputStreamWriter os = null;
        try {
            os = new OutputStreamWriter(new FileOutputStream("highscore.txt"));
            os.write("The highest score so far is: " + score);
        } catch (IOException e) {
            System.out.println("Something went wrong while writing!");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the file!");
                }
            }
        }
        this.highscore = score;
    }

    /**
     * read - reads the score from the file.
     * <p>
     */
    public void read() {
        try {
            File f = new File("highscore.txt");
            if (f.exists()) {
                BufferedReader is = null;
                try {
                    is = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream("highscore.txt")));
                    String line;
                    while ((line = is.readLine()) != null) {
                        this.highscore = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
                        if (score > highscore) {
                            this.write();
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
            } else {
                this.write();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
