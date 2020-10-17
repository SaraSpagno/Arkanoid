//ID: 556693885
package gameplay;

import animations.GameLevel;
import animations.MenuAnimation;
import interfaces.Menu;
import interfaces.Task;
import keypress.KeyPressStoppableAnimation;
import keypress.Lost;
import keypress.Won;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import hittings.Counter;
import interfaces.LevelInformation;
import animations.HighScoresAnimation;
import tasks.PlayGameTask;
import tasks.QuitGameTask;
import tasks.ShowHiScoresTask;

import java.util.List;


/**
 * In this class we define the GameFlow class.
 */
public class GameFlow {
    // score
    private List<LevelInformation> levels;
    private GUI gui = new GUI("Arkanoid", 800, 600);
    private KeyboardSensor keyboard = gui.getKeyboardSensor();
    private AnimationRunner runner = new AnimationRunner(gui, 60);
    private ShowHiScoresTask showHiScoresTask = new ShowHiScoresTask(keyboard, runner, new HighScoresAnimation(this));


    /**
     * Constructor.
     * <p>
     *
     * @param levels - the list of level to run.
     */
    public GameFlow(List<LevelInformation> levels) {
        this.levels = levels;
    }


    /**
     * runLevels - run the levels in the list.
     * <p>
     */
    public void runLevels() {
        int i=0;
        Counter score = new Counter(0);
        for (LevelInformation levelInfo : levels) {
            i++;
            GameLevel level = new GameLevel(levelInfo, score, gui, keyboard, runner);
            level.initialize();
            level.playOneTurn();
            if (i==4) {
                Highscore highscore = new Highscore(score.getValue());
                highscore.read();
                this.showHiScoresTask = new ShowHiScoresTask(keyboard, runner, new HighScoresAnimation(this));
                runner.run(new KeyPressStoppableAnimation(keyboard, "space", new Lost(score, showHiScoresTask)), false);
                break;
            }
            /*if (level.getNumBalls() == 0) {
                Highscore highscore = new Highscore(score.getValue());
                highscore.read();
                this.showHiScoresTask = new ShowHiScoresTask(keyboard, runner, new HighScoresAnimation(this));
                runner.run(new KeyPressStoppableAnimation(keyboard, "space", new Lost(score, showHiScoresTask)), false);
                break;
            }
             */
        }
        int maxScore = 0;
        for (LevelInformation level : levels) {
            maxScore += level.numberOfBlocksToRemove() * 5 + 100;
        }
        if (score.getValue() == maxScore) {
            Highscore highscore = new Highscore(score.getValue());
            highscore.read();
            this.showHiScoresTask = new ShowHiScoresTask(keyboard, runner, new HighScoresAnimation(this));
            runner.run(new KeyPressStoppableAnimation(keyboard, "space", new Won(score, showHiScoresTask)), false);
        }
        Highscore highscore = new Highscore(score.getValue());
        highscore.read();
    }

    /**
     * runMenu - run the menu.
     * <p>
     */
    public void runMenu() {
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard);
        menu.addSelection("q", "Quit Game", new QuitGameTask(this.gui));
        menu.addSelection("h", "High score", showHiScoresTask);
        menu.addSelection("s", "Play Game", new PlayGameTask(this));

        while (true) {
            runner.run(menu, false);
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }
}
