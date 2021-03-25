package com.farmr.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainController {

	@FXML
	private Button startButton;

	@FXML
	private ComboBox<String> numBots = new ComboBox<>();


	@FXML
	private ComboBox<String> script = new ComboBox<>();

	private final String[] scripts = new String[] {"AIOWoodcutting", "Waterfiends", "CowKiller 2.0"};
	private final String[] botCounter = new String[] {"1", "2", "3", "4", "5"};

	public void initialize() {
		log.info("Initializing FXML UI Controller...");
		numBots.getItems().addAll(botCounter);
		script.getItems().addAll(scripts);

		numBots.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> log.info("{} was selected for the number of bots.", botCounter[newValue.intValue()]));
		script.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> log.info("\"{}\" was selected for the script to run", scripts[newValue.intValue()]));
	}

	@FXML
	private void handleButtonAction(ActionEvent event) {
		log.info("Initializing bot farm...");
	}
}