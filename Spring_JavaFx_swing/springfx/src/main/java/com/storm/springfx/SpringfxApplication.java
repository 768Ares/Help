package com.storm.springfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringfxApplication extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent root;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(SpringfxApplication.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/startWindow.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        root = fxmlLoader.load();
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Start window");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }

    public static void main(String[] args) {
//        SpringApplication.run(SpringfxApplication.class, args);
        launch(SpringfxApplication.class, args);
        System.out.println();
    }
}
