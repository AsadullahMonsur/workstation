package fcap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.event.AncestorEvent;

public class Laucher extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("App just Started");


        Image image = new Image("/resources/input_file/3d_position.PNG");

        ImageView viewer = new ImageView();
        viewer.setX(100);
        viewer.setY(100);
        viewer.setImage(image);
        viewer.setVisible(true);

        AnchorPane layout = new AnchorPane();
        layout.setPrefWidth(1024);
        layout.setPrefHeight(620);
        layout.getChildren().add(viewer);

        Scene scene = new Scene(layout, 1024, 620);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("App is Loading Libraries");
        OpenCV.loadShared();
        launch(args);
    }

    public static Mat loadImage(String imagePath) {
        return Imgcodecs.imread(imagePath);
    }

    public static void saveImage(Mat imageMatrix, String targetPath) {
        Imgcodecs.imwrite(targetPath, imageMatrix);
    }
}
