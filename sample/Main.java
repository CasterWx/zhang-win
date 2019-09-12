package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class Main extends Application {

    private double xOffset=0;
    private double yOffset=0;
    @Override
    public void start(Stage primaryStage){

        BorderPane borderPane=new BorderPane();
        Image image=new Image("a1.png");
        ImageView imageView=new ImageView(image);

        borderPane.setCenter(imageView);

        /**
         * Mouse Clicked
         * 点击事件暂无
         * */
        imageView.setOnMouseClicked(event1 -> {

        });

        EventHandler<ActionEvent> eventHandler= event -> {
        };

        KeyFrame keyFrame=new KeyFrame(Duration.millis(500),eventHandler);
        Timeline animation=new Timeline(keyFrame);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene=new Scene(borderPane);
        scene.setFill(null);
        scene.setOnMousePressed((MouseEvent event)->{
            event.consume();
            xOffset=event.getSceneX();
            yOffset=event.getSceneY();
        });

        new Thread(()->{
            while (true) {
                Image imageChange;
                for (int i = 1; i <= 6; i++) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    imageChange = new Image("a" + i + ".png");
                    double xChange = -10 + Math.random() * (10 + 10);
                    double yChange = -10 + Math.random() * (10 + 10);
                    System.out.println(xChange+"  "+yChange);
                    primaryStage.setX(primaryStage.getX()-xChange);
                    primaryStage.setY(primaryStage.getY()-yChange);
                    imageView.setImage(imageChange);
                }
            }
        }).start();

        // 鼠标拖动
        scene.setOnMouseDragged((MouseEvent event)->{
            event.consume();
            primaryStage.setX(event.getScreenX()-xOffset);
            primaryStage.setY(event.getScreenY()-yOffset);
        });
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
