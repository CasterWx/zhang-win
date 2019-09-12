package sample;

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

import java.awt.*;


public class Main extends Application {

    private double xOffset=0;
    private double yOffset=0;
    @Override
    public void start(Stage primaryStage){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        BorderPane borderPane=new BorderPane();
        Image image=new Image("a1.png");
        ImageView imageView=new ImageView(image);

        primaryStage.setX(screen.width-100);
        primaryStage.setY(screen.height);
        borderPane.setBottom(imageView);
        /**
         * Mouse Clicked
         * 点击事件暂无
         * */
        imageView.setOnMouseClicked(event1 -> {

        });

        EventHandler<ActionEvent> eventHandler= event -> {
        };

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
                for (int j = 1; j <= 6; j++) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    imageChange = new Image("a" + j + ".png");
//                    double xChange =  Math.random() * (-10) + Math.random() * (10);
//                    double yChange = -3 + Math.random() * (20 + 3);
//                    System.out.println(xChange+"  "+yChange);
//                    primaryStage.setX(primaryStage.getX()-xChange);
                    primaryStage.setY(primaryStage.getY()-20);
                    imageView.setImage(imageChange);
                    if (primaryStage.getY()<=-20){
                        primaryStage.setX( Math.random() * (screen.width));
                        System.out.println(Math.random() * (screen.width));
                        primaryStage.setY(screen.height);
                    }
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

    public void newLaunch(){
        launch();
    }

}
