package com.farmr.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {

	@FXML
	public Button button;

	@FXML
	private Label label;

	public void initialize() {

	}

	@FXML
	private void handleButtonAction(ActionEvent event) {
		label.setText("Hello World!");
	}
}