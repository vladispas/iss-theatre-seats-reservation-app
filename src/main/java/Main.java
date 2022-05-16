import controller.SigninContoller;
import repository.*;
import service.*;
import controller.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import repository.database.DatabaseRepositoryShow;
import repository.database.DatabaseRepositorySpectator;
import utils.FxUtils;

public class Main extends Application {

    private Controller controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("db.config"));
        } catch (IOException e) {
            System.out.println("Cannot find db.config " + e);
        }

        IRepositorySpectator repositorySpectator = new DatabaseRepositorySpectator(properties);
        IRepositoryShow repositoryShow = new DatabaseRepositoryShow(properties);

        ServiceSpectator serviceSpectator = new ServiceSpectator(repositorySpectator);
        ServiceShow serviceShow = new ServiceShow(repositoryShow);

        controller = new Controller(serviceSpectator, serviceShow);

        initView(primaryStage);
        primaryStage.show();
    }

    private void initView(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("signin_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        FxUtils.makeWindowDraggable(scene, primaryStage);
        FxUtils.setScenePosition(scene, primaryStage);

        SigninContoller signinContoller = fxmlLoader.getController();
        signinContoller.setNonGUIController(controller);
    }
}
