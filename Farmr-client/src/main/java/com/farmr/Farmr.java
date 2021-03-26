package com.farmr;

import com.farmr.module.FarmrModule;
import com.farmr.service.AccountCreationService;
import com.farmr.util.Util;
import com.github.lalyos.jfiglet.FigletFont;
import com.google.inject.Guice;
import com.google.inject.Inject;
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
 * Farmr - Main Application entrypoint for the Farmr GUI and Client.
 */
@Slf4j
public class Farmr extends Application {

    @Inject
    private AccountCreationService accountCreationService;

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("src/main/resources/UI.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Runs the client application
     * @param args
     */
    public void run(String[] args) {
        final String originalIp = Util.fetchIp();

        // 1.) Start Socks proxy to Amazon EC2
        // TODO we need a mapping of EC2 instances available to accounts that must be run from the EC2 instances
        // We also need a way to start up the EC2 and make a connection from this machine without PEM file
        // maybe expose the socks proxy service through a domain?

        // 2.) Setup system properties for Chrome driver and proxy
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("socksProxyHost", "127.0.0.1");
        System.getProperties().put("socksProxyPort", "4444");

        // 3.) Ensure proxy is connected by checking IP's to make sure they dont match
        final String proxiedIp = Util.fetchIp();

        log.info("Checking proxy connection. Original IP = {} and proxied IP = {}", originalIp, proxiedIp);
        if(proxiedIp != null && originalIp != null) {
            if(proxiedIp.equals(originalIp)) {
                log.error("Proxy ip and original ip are the same. No proxy is connected. It is Unsafe to connect to RuneScape servers and can compromise legitimate accounts");
                System.exit(1);
            }
        } else {
            log.error("Could not fetch ip address. Ensure you are connected to the internet and restart");
            System.exit(1);
        }

        accountCreationService.create();
        launch(args);
    }

    public static void main(String[] args) {
        try {
            System.out.println(FigletFont.convertOneLine("Farmr"));
            Injector injector = Guice.createInjector(new FarmrModule());
            injector.getInstance(Farmr.class).run(args);
        } catch (IOException e) {
            log.error("IOException thrown while attempting to start application and inject dependencies.", e);
        }

    }


}
