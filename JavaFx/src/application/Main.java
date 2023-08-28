package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Random;

public class Main extends Application {
	private int secretNumber;
	private int numberOfGuesses;
	private Label promptLabel;
	private TextField guessField;
	private Button guessButton;
	private Label resultLabel;

	public static void main(String args[]) {
		launch(args);
	}
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		secretNumber = generateRandomNumber();
		numberOfGuesses = 0;

		primaryStage.setTitle("Number Guessing Game");

		promptLabel = new Label("Guess a number between 1 and 100:");
		guessField = new TextField();
		guessButton = new Button("Guess");
		resultLabel = new Label();

		guessButton.setOnAction(event -> checkGuess());

		VBox root = new VBox(10);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(promptLabel, guessField, guessButton, resultLabel);

		Scene scene = new Scene(root, 400, 300);
		primaryStage.setScene(scene);

		primaryStage.show();
	}

	private int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(100) + 1;
	}

	private void checkGuess() {
		int userGuess;
		try {
			userGuess = Integer.parseInt(guessField.getText());
		} catch (NumberFormatException ex) {
			resultLabel.setText("Invalid input. Please enter a valid number.");
			return;
		}

		numberOfGuesses++;

		if (userGuess < secretNumber) {
			resultLabel.setText("Guess #" + numberOfGuesses + ": Too low");
		} else if (userGuess > secretNumber) {
			resultLabel.setText("Guess #" + numberOfGuesses + ": Too high");
		} else {
			resultLabel.setText(
					"Congratulations! You guessed the number " + secretNumber + " in " + numberOfGuesses + " guesses.");
			guessButton.setDisable(true);
		}
		guessField.clear();
	}
	
	}
