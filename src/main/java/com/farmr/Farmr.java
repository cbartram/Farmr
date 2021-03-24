package com.farmr;


import com.farmr.account.module.AccountCreationModule;
import com.github.lalyos.jfiglet.FigletFont;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * App - Main
 */
@Slf4j
public class Farmr extends Application {

//    @Inject
//    private AccountCreationService accountCreationService;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("src/main/resources/UI.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void run(String[] args) {
        launch(args);
    }

    public static void main(String[] args) {
        try {
            System.out.println(FigletFont.convertOneLine("Farmr"));
            Injector injector = Guice.createInjector(
                    new AccountCreationModule()
            );

            injector.getInstance(Farmr.class).run(args);
        } catch (IOException e) {
            log.error("Failed to render Figlet. If this happened you are just having a bad day lol. ", e);
        }
    }


}
