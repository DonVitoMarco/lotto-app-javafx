package app.lotto.model;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

public class LottoApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new VBox();
        Pane pane = new Pane();
        Pane btnPane = new Pane();
        Button btn = new Button("L O S U J");
        btnPane.getChildren().add(btn);
        root.getChildren().addAll(pane, btnPane);
        Scene scene = new Scene(root, 400, 590);
        primaryStage.setTitle("LottoApp");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("icons.png"));
        root.setStyle("-fx-background-image: url('background.png')");
        String buttonStyle = "-fx-font-family: 'Consolas Bold';" + "-fx-font-weight: Bold;" +
                "-fx-font-size: 36px;" + "-fx-border-width: 2px;" + "-fx-border-color: ff7f6c;";
        btn.setStyle(buttonStyle + "-fx-background-color: white;" + "-fx-text-fill: ff7f6c;");
        btn.setTranslateX(100);
        pane.setPrefSize(400, 460);
        pane.setPrefWidth(400);

        btn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn.setStyle(buttonStyle + "-fx-background-color: ff7f6c;" + "-fx-text-fill: white;");
            }
        });

        btn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btn.setStyle(buttonStyle + "-fx-background-color: white;" + "-fx-text-fill: ff7f6c;");
            }
        });

        ArrayList<Rectangle> allRectangle = new ArrayList<>();
        ArrayList<Rectangle> randomRectangle = new ArrayList<>();
        createAllRectangle(allRectangle);

        btn.setOnAction((event) -> {
            cleanRectangle(randomRectangle, pane);
            generateRectangle(allRectangle, randomRectangle);
            drawRectangle(randomRectangle, pane);
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void createAllRectangle(ArrayList<Rectangle> allRectangle) {

        int y = 80;
        for(int i = 0; i < 9; i++) {
            int x = 40;
            for(int j = 0; j < 6; j++) {
                if(i == 8) j = 5;
                Rectangle r = new Rectangle();
                r.setHeight(5);
                r.setWidth(15);
                r.setX(x);
                r.setY(y);
                allRectangle.add(r);
                x += 60;
            }
            y += 40;
        }

    }

    private static void drawRectangle(ArrayList<Rectangle> randomRectangles, Pane pane) {

        for(int i = 0; i < randomRectangles.size(); i++) {
            pane.getChildren().add(randomRectangles.get(i));
        }

    }

    private static void cleanRectangle(ArrayList<Rectangle> randomRectangles, Pane pane) {


        if(!randomRectangles.isEmpty()) {
            do {
                pane.getChildren().remove(randomRectangles.get(0));
                randomRectangles.remove(0);
            } while(!randomRectangles.isEmpty());
        }

    }

    private static void generateRectangle(ArrayList<Rectangle> allRectangle, ArrayList<Rectangle> randomRectangle) {

        Random gen = new Random();

        Integer randomNumbers;
        ArrayList<Integer> numbers = new ArrayList<>();

        for(int i = 0; i < 6; i++) {
            do {
                randomNumbers = gen.nextInt(49);
            } while(numbers.indexOf(randomNumbers) != -1);
            numbers.add(randomNumbers);
        }

        for(int i = 0; i < numbers.size(); i++) {
            randomRectangle.add(allRectangle.get(numbers.get(i)));
        }

    }

}
