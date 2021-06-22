import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;


public class mainSnakeGame extends Application {

    private static final int width =  1280;
    private static final int height =  680;
    private static final int rows = 17;
    private static final int cube_length = height / rows;
    private static final int columns = width / cube_length;
    private static final int up = 0;
    private static final int down = 1;
    private static final int left = 2;
    private static final int right = 3;

    private int currDir = 0;
    private GraphicsContext gc1;
    private ArrayList<Double> fruitx = new ArrayList();
    private ArrayList<Double> fruity = new ArrayList();
    private ArrayList<Double> snakex = new ArrayList();
    private ArrayList<Double> snakey = new ArrayList();
    private ArrayList<Image> fruitImages = new ArrayList();

    private int newFruitX, newFruitY;
    private Scene startScene, level, gameEnd;
    private Boolean isOver = false, islevel1 = false,
            islevel2 = false, islevel3 = false, isPaused = false;
    private Image fruitImage1 = new Image("fruit.png", 40, 40, false, true);
    private Image fruitImage2 = new Image("fruit.png", 40, 40, false, true);
    private Image fruitImage3 = new Image("fruit.png", 40, 40, false, true);
    private Image fruitImage4 = new Image("fruit.png", 40, 40, false, true);
    private Image fruitImage5 = new Image("fruit.png", 40, 40, false, true);

    private Image fruitImage6 = new Image("fruit.png", 40, 40, false, true);
    private Image fruitImage7 = new Image("fruit.png", 40, 40, false, true);
    private Image fruitImage8 = new Image("fruit.png", 40, 40, false, true);
    private Image fruitImage9 = new Image("fruit.png", 40, 40, false, true);
    private Image fruitImage10 = new Image("fruit.png", 40, 40, false, true);

    private Image fruitImage11 = new Image("fruit.png", 40, 40, false, true);
    private Image fruitImage12 = new Image("fruit.png", 40, 40, false, true);
    private Image fruitImage13 = new Image("fruit.png", 40, 40, false, true);
    private Image fruitImage14 = new Image("fruit.png", 40, 40, false, true);
    private Image fruitImage15 = new Image("fruit.png", 40, 40, false, true);

    private int total_score = 0;
    private int score = 0;
    private int time = 0;
    Timeline timeline, timeline_30_sec;


    @Override
    public void start(Stage stage) throws Exception {
        initfruitImage1();
        initfruitImage2();
        initfruitImage3();
        stage.setTitle("Snake Game");
        initStartScene();
        stage.setResizable(false);
        stage.setScene(startScene);
        stage.show();
        initLevel();
        startScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DIGIT1) {
                initFruit();
                initfruitImage1();
                stage.setScene(level);
                stage.setTitle("Level 1");
                stage.show();
                timeline.play();
                timeline_30_sec.play();
                islevel1 = true;
                System.out.println("key Digit 1 was pressed");

            } else if (e.getCode() == KeyCode.Q) {
                initGameOver();
                stage.setScene(gameEnd);
                stage.setTitle("Game Over");
                stage.show();
                System.out.println("Key Q pressed game over");
                timeline.stop();
                timeline_30_sec.stop();
            } else if (e.getCode() == KeyCode.DIGIT2) {
                initFruit();
                initFruit2();
                islevel2 = true;
                stage.setScene(level);
                stage.setTitle("Level 2");
                stage.show();
                timeline.play();
                timeline_30_sec.play();
                System.out.println("key Digit 2 was pressed");
            } else if (e.getCode() == KeyCode.DIGIT3) {
                initFruit();
                initFruit2();
                initFruit3();
                islevel3 = true;
                /*
                initfruitImage1();
                initfruitImage2();
                initfruitImage3();

                 */
                stage.setScene(level);
                stage.setTitle("Level 3");
                stage.show();
                timeline.play();
                System.out.println("key Digit 3 was pressed");
            }
        });

        initSnake();
        //initFruit();

        timeline = new Timeline(new KeyFrame(Duration.millis(150), e -> run(stage, gc1)));
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline_30_sec = new Timeline(new KeyFrame(Duration.seconds(1), e -> changeTime()));
        timeline_30_sec.setCycleCount(Timeline.INDEFINITE);

        level.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.UP) {
                    if (currDir != down) {
                        currDir = up;
                    }
                } else if (code == KeyCode.DOWN) {
                    if (currDir != up) {
                        currDir = down;
                    }
                } else if (code == KeyCode.RIGHT) {
                    if (currDir != left) {
                        currDir = right;
                    }
                } else if (code == KeyCode.LEFT) {
                    if (currDir != right) {
                        currDir = left;
                    }
                } else if (code == KeyCode.P) {
                    if (isPaused == false) {
                        timeline.pause();
                        isPaused = true;
                    } else {
                        timeline.play();
                        isPaused = false;
                    }
                } else if (code == KeyCode.R) {
                    stage.setScene(startScene);
                    stage.setTitle("Snake Game");
                    stage.show();
                    islevel1 = false;
                } else if (code == KeyCode.Q) {
                    initGameOver();
                    stage.setScene(gameEnd);
                    stage.setTitle("Game Over");
                    stage.show();
                    System.out.println("Key Q pressed game over");
                    timeline.stop();
                } else if (code == KeyCode.DIGIT1) {
                    fruitx.removeAll(fruitx);
                    fruity.removeAll(fruity);
                    initFruit();
                    islevel1 = true;
                    islevel2 = false;
                    islevel3 = false;
                    stage.setScene(level);
                    stage.setTitle("Level 1");
                    stage.show();
                    timeline.play();
                    timeline_30_sec.play();
                    System.out.println("key Digit 1 was pressed");
                } else if (code == KeyCode.DIGIT2) {
                    fruitx.removeAll(fruitx);
                    fruity.removeAll(fruity);
                    initFruit();
                    initFruit2();
                    islevel2 = true;
                    islevel3 = false;
                    islevel1 = false;
                    stage.setScene(level);
                    stage.setTitle("Level 2");
                    stage.show();
                    timeline.play();
                    timeline_30_sec.play();
                    System.out.println("key Digit 2 was pressed");
                } else if (code == KeyCode.DIGIT3) {
                    fruitx.removeAll(fruitx);
                    fruity.removeAll(fruity);
                    initFruit();
                    initFruit2();
                    initFruit3();
                    islevel2 = false;
                    islevel1 = false;
                    islevel3 = true;
                    /*
                    initfruitImage1();
                    initfruitImage2();
                    initfruitImage3();

                     */
                    stage.setScene(level);
                    stage.setTitle("Level 3");
                    stage.show();
                    timeline.play();
                    System.out.println("key Digit 3 was pressed");
                }
            }
        });

    }

    void changeTime() {
        time += 1;
    }

    void initStartScene() {
        String usage = "\nUsage:\n1. Press Digit number 1 to start the game. Press P to pause or un-pause the game.\n" +
                "2. The direction of the snake can be controlled by the arrows keys. " +
                "The snake will move forward unless LEFT or\n" +
                "RIGHT arrow keys are pressed, which will cause the snake to turn in that direction " +
                "(relative to it's current path).\n" +
                "3. The snake can die by eating itself (when it collides with itself) or by hitting the edge of the screen.\n" +
                "4. Try to eat as many fruits as possible! The more fruits the snake eats, the higher score you will get!\n" +
                "5. There are a total of 3 levels. Level 1, 2, 3 has 5, 10, 15 fruits respectively. As the level number increases, the\n" +
                "difficulty level increases, too. The speed of snake moving will increase as level goes up.\n" +
                "6. There is a timer of 30 seconds for both of level 1 and 2, when the time reaches 0, you upgrade to the next\n" +
                "level automatically! However, level 3 does not have a timer. The game ends until you 'die', or press 'X' to\n" +
                "end the game.\n" + "IMPORTANT:The higher the level, the more points you get per fruit the snake eats!\n";
        String enter = "\nNow Press digit number 1 to start the game!\n" +
                "OR, you can press the digit number 1, 2, 3 to jump straight into level1, level2 or level3 respectively, any time during the game!";
        Label label = new Label("Name: Chenxingyu Su\nUserid: c35su\n" + usage + enter);
        label.setFont(new Font("Arial", 24));
        Image i = new Image("Snake Game.jpg", width, 100, false, true);
        ImageView iw = new ImageView(i);
        VBox box = new VBox(iw, label);
        VBox.setMargin(label, new Insets(10.0));
        startScene = new Scene(box, width, height);
    }

    void initSnake() {
        snakex.add((double) 480);
        snakey.add((double) 600);
        snakex.add((double) 480);
        snakey.add((double) 640);
        snakex.add((double) 480);
        snakey.add((double) 680);

    }

    void initLevel() {
        Group root = new Group();
        Canvas canvas= new Canvas(width, height);
        root.getChildren().add(canvas);
        level = new Scene(root);
        gc1 = canvas.getGraphicsContext2D();
    }


    void initfruitImage1() {
        fruitImages.add(fruitImage1);
        fruitImages.add(fruitImage2);
        fruitImages.add(fruitImage3);
        fruitImages.add(fruitImage4);
        fruitImages.add(fruitImage5);
    }
    void initfruitImage2() {
        fruitImages.add(fruitImage6);
        fruitImages.add(fruitImage7);
        fruitImages.add(fruitImage8);
        fruitImages.add(fruitImage9);
        fruitImages.add(fruitImage10);
    }

    void initfruitImage3() {
        fruitImages.add(fruitImage11);
        fruitImages.add(fruitImage12);
        fruitImages.add(fruitImage13);
        fruitImages.add(fruitImage14);
        fruitImages.add(fruitImage15);
    }


    void initGameOver() {
        Label label = new Label("Oops! Game over! Your high score of this game is : " + total_score);
        label.setFont(new Font("Arial", 50));
        Image i = new Image("Snake Game.jpg", width, 100, false, true);
        ImageView iw = new ImageView(i);
        VBox box = new VBox(iw, label);
        VBox.setMargin(label, new Insets(10.0));
        gameEnd = new Scene(box, width, height);
    }




    void setBackground(GraphicsContext gc) {
        for (int i = 0; i < columns; ++i) {
            for (int j = 0; j < rows; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("AAD751"));
                } else {
                    gc.setFill(Color.web("AAD751"));
                }
                gc.fillRect(i*cube_length, j*cube_length, cube_length, cube_length);
            }
        }
    }


    void run(Stage stage, GraphicsContext gc) {
        if (isOver) {
            total_score += score;
            initGameOver();
            stage.setScene(gameEnd);
            stage.setTitle("Game Over");
            stage.show();
            System.out.println("Game over caused by user");
            timeline.stop();
        }
        if (time == 30) {
            if (islevel1) {
                System.out.println("Move to Level 2");
                stage.setTitle("Level 2");
                fruitx.removeAll(fruitx);
                fruity.removeAll(fruity);

                initFruit();
                initFruit2();
                initfruitImage2();

                total_score += score;
                time = 0;
                score = 0;
                islevel1 = false;
                islevel2 = true;

            }
            if (islevel2) {
                System.out.println("Move to Level 3");
                stage.setTitle("Level 3");
                timeline_30_sec.stop();
                fruitx.removeAll(fruitx);
                fruity.removeAll(fruity);
                initFruit();
                initFruit2();
                initFruit3();
                initfruitImage3();
                total_score += score;
                score = 0;
                time = 0;
                islevel2 = false;
                islevel3 = true;
            }
        }

        double factor = 1;

        if (islevel1) factor = 1;
        else if (islevel2) factor = 2;
        else if (islevel3) factor = 3;

        setBackground(gc);
        drawFruit(gc);
        drawSnake(gc);
        drawScore();
        drawTime();
        for (int i = snakex.size() - 1; i >= 1; i--) {
            snakex.set(i, snakex.get(i-1));
            snakey.set(i, snakey.get(i-1));
        }
        switch (currDir) {
            case up:
                snakey.set(0, snakey.get(0) - 40*factor);
                break;
            case down:
                snakey.set(0, snakey.get(0) + 40*factor);
                break;
            case left:
                snakex.set(0, snakex.get(0) - 40*factor);
                break;
            case right:
                snakex.set(0, snakex.get(0) + 40*factor);
                break;

        }
        gameOver();
        eat();
    }


    void initFruit() {
        fruitx.add((double) 40);
        fruitx.add((double) 480);
        fruitx.add((double) 1240);
        fruitx.add((double) 600);
        fruitx.add((double) 360);
        fruity.add((double) 80);
        fruity.add((double) 120);
        fruity.add((double) 640);
        fruity.add((double) 600);
        fruity.add((double) 360);
    }

    void initFruit2() {
        fruitx.add((double) 40);
        fruitx.add((double) 240);
        fruitx.add((double) 1200);
        fruitx.add((double) 560);
        fruitx.add((double) 1120);
        fruity.add((double) 120);
        fruity.add((double) 400);
        fruity.add((double) 160);
        fruity.add((double) 320);
        fruity.add((double) 520);
    }
    void initFruit3() {
        fruitx.add((double) 520);
        fruitx.add((double) 320);
        fruitx.add((double) 160);
        fruitx.add((double) 400);
        fruitx.add((double) 120);
        fruity.add((double) 1120);
        fruity.add((double) 560);
        fruity.add((double) 1200);
        fruity.add((double) 240);
        fruity.add((double) 40);
    }


    void makeFruit(int index){
        start:
        while(true) {
            newFruitX = (int) (Math.random()*columns)*40;
            newFruitY = (int) (Math.random()*rows)*40;

            for(int i = 0; i < snakex.size(); ++i ) {
                if(snakex.get(i) == newFruitX && snakey.get(i) == newFruitY) {
                    continue start;
                }
            }
            fruitx.add((double) newFruitX);
            fruity.add((double) newFruitY);
            switch (index) {
                case 0:
                    fruitImage1 = new Image("fruit.png", 40, 40, false, true);
                    break;
                case 1:
                    fruitImage2 = new Image("fruit.png", 40, 40, false, true);
                    break;
                case 2:
                    fruitImage3 = new Image("fruit.png", 40, 40, false, true);
                    break;
                case 3:
                    fruitImage4 = new Image("fruit.png", 40, 40, false, true);
                    break;
                case 4:
                    fruitImage5 = new Image("fruit.png", 40, 40, false, true);
                    break;

            }
            fruitx.remove(index);
            fruity.remove(index);
            fruitImages.remove(index);
            break;
        }
    }



    void drawFruit(GraphicsContext gc) {
        if (islevel1) {
            gc.drawImage(fruitImage1, fruitx.get(0), fruity.get(0), cube_length, cube_length);
            gc.drawImage(fruitImage2, fruitx.get(1), fruity.get(1), cube_length, cube_length);
            gc.drawImage(fruitImage3, fruitx.get(2), fruity.get(2), cube_length, cube_length);
            gc.drawImage(fruitImage4, fruitx.get(3), fruity.get(3), cube_length, cube_length);
            gc.drawImage(fruitImage5, fruitx.get(4), fruity.get(4), cube_length, cube_length);
        }
        if (islevel2) {
            gc.drawImage(fruitImage1, fruitx.get(0), fruity.get(0), cube_length, cube_length );
            gc.drawImage(fruitImage2, fruitx.get(1), fruity.get(1), cube_length, cube_length );
            gc.drawImage(fruitImage3, fruitx.get(2), fruity.get(2), cube_length, cube_length );
            gc.drawImage(fruitImage4, fruitx.get(3), fruity.get(3), cube_length, cube_length );
            gc.drawImage(fruitImage5, fruitx.get(4), fruity.get(4), cube_length, cube_length );
            gc.drawImage(fruitImage6, fruitx.get(5), fruity.get(5), cube_length, cube_length );
            gc.drawImage(fruitImage7, fruitx.get(6), fruity.get(6), cube_length, cube_length );
            gc.drawImage(fruitImage8, fruitx.get(7), fruity.get(7), cube_length, cube_length );
            gc.drawImage(fruitImage9, fruitx.get(8), fruity.get(8), cube_length, cube_length );
            gc.drawImage(fruitImage10, fruitx.get(9), fruity.get(9), cube_length, cube_length );
        }
        if (islevel3) {
            gc.drawImage(fruitImage1, fruitx.get(0), fruity.get(0), cube_length, cube_length );
            gc.drawImage(fruitImage2, fruitx.get(1), fruity.get(1), cube_length, cube_length );
            gc.drawImage(fruitImage3, fruitx.get(2), fruity.get(2), cube_length, cube_length );
            gc.drawImage(fruitImage4, fruitx.get(3), fruity.get(3), cube_length, cube_length );
            gc.drawImage(fruitImage5, fruitx.get(4), fruity.get(4), cube_length, cube_length );
            gc.drawImage(fruitImage6, fruitx.get(5), fruity.get(5), cube_length, cube_length );
            gc.drawImage(fruitImage7, fruitx.get(6), fruity.get(6), cube_length, cube_length );
            gc.drawImage(fruitImage8, fruitx.get(7), fruity.get(7), cube_length, cube_length );
            gc.drawImage(fruitImage9, fruitx.get(8), fruity.get(8), cube_length, cube_length );
            gc.drawImage(fruitImage10, fruitx.get(9), fruity.get(9), cube_length, cube_length );
            gc.drawImage(fruitImage11, fruitx.get(10), fruity.get(10), cube_length, cube_length );
            gc.drawImage(fruitImage12, fruitx.get(11), fruity.get(11), cube_length, cube_length );
            gc.drawImage(fruitImage13, fruitx.get(12), fruity.get(12), cube_length, cube_length );
            gc.drawImage(fruitImage14, fruitx.get(13), fruity.get(13), cube_length, cube_length );
            gc.drawImage(fruitImage15, fruitx.get(14), fruity.get(14), cube_length, cube_length );
        }

    }


    void drawSnake(GraphicsContext gc) {
        gc.setFill(Color.AQUAMARINE);
        gc.fillRoundRect(snakex.get(0), snakey.get(0),
                cube_length - 1, cube_length - 1, 35, 35);
        for (int i = 1; i < snakex.size(); ++i) {
            gc.fillRoundRect(snakex.get(i), snakey.get(i),
                    cube_length - 1, cube_length - 1, 20, 20);
        }
    }

    public void gameOver() {
        if (snakex.get(0) < 0 || snakex.get(0) >= width || snakey.get(0) < 0 || snakey.get(0) >= height) {
            isOver = true;
        }
        for (int i = 1; i < snakex.size(); ++i) {
            if (snakex.get(0).equals(snakex.get(i)) && snakey.get(0).equals(snakey.get(i))) {
                isOver = true;
                break;
            }
        }
    }

    public void eat() {
        for(int i = 0; i < fruitx.size(); ++i) {
            if (snakex.get(0).equals(fruitx.get(i)) && snakey.get(0).equals(fruity.get(i))) {
                snakex.add((double) snakex.get(0));
                snakey.add((double) snakey.get(0));
                score = score + 10;
                makeFruit(i);
                break;
            }
        }
    }

    void drawTime() {
        gc1.setFill(Color.WHITE);
        gc1.setFont(new Font("Arial", 24));
        gc1.fillText("Time left: " + time + " seconds", 800, 40);
    }

    void drawScore() {
        gc1.setFill(Color.WHITE);
        gc1.setFont(new Font("Arial", 24));
        gc1.fillText("Score: " + score, 10, 40);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
