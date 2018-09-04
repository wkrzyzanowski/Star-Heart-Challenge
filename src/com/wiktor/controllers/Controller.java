package com.wiktor.controllers;

import com.wiktor.objects.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Controller {

    /* PLAYER 1 X -> gwiazdka
       PLAYER 2 Y -> serce    */


    //PLAYERS
    private Player player1 = new Player("Star");
    private Player player2 = new Player("Heart");

    //PLAY FIELD
    private int CURRENT_PLAYER = 1;
    private int[][] fieldTable = new int[3][3];
    boolean IS_END_GAME = false;
    private ArrayList<Button> buttons_fields = new ArrayList<>();
    private int progressBarStatus = 1;


    //FXML ITEMS
    @FXML
    private Label heartName;
    @FXML
    private Label starName;
    @FXML
    private Label heartPoints;
    @FXML
    private Label starPoints;
    @FXML
    private Label progessLabel;
    @FXML
    private Pane background;
    @FXML
    private Label currentPlayer;
    @FXML
    private Label currentPlayerLabel;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button addNames;
    @FXML
    private Button restartButton;
    @FXML
    private Button Field1x3;
    @FXML
    private Button Field2x2;
    @FXML
    private Button Field3x1;
    @FXML
    private Button Field2x3;
    @FXML
    private Button Field3x2;
    @FXML
    private Button Field1x1;
    @FXML
    private Button Field1x2;
    @FXML
    private Button Field2x1;
    @FXML
    private Button Field3x3;


    @FXML
    void restartGame() {
        progressBarStatus = 0;
        progressBarUpdate();
        buttons_fields.clear();
        addAllButtons();
        for (Button button : buttons_fields) {
            button.getStyleClass().clear();
            button.getStyleClass().addAll("button", "Buttons");
            button.setDisable(false);
        }


        for (int i = 0; i < fieldTable.length; i++) {
            for (int j = 0; j < fieldTable.length; j++) {
                fieldTable[i][j] = 0;
            }
        }

        System.out.println("RESTART GAME TABLE:");

        for (int i = 0; i < fieldTable.length; i++) {
            System.out.println();
            for (int j = 0; j < fieldTable.length; j++) {
                System.out.print(fieldTable[i][j] + " ");
            }
        }

        System.out.println();
        System.out.println("=========================\n");

        changeBackgroundOnHoover();

        restartButton.setVisible(false);


    }

    @FXML
    void initialize() {
        restartButton.setVisible(false);


        addAllButtons();
        for (Button button : buttons_fields) {
            button.getStyleClass().add("Buttons");
        }
        Random random = new Random();
        int random_player = random.nextInt(2) + 1;
        setCURRENT_PLAYER(random_player);
        if (getCURRENT_PLAYER() == 1) {
            currentPlayer.setText(player1.getName());
        } else {
            currentPlayer.setText(player2.getName());
        }

        starPoints.setText(String.valueOf(player1.getPoints()));
        heartPoints.setText(String.valueOf(player2.getPoints()));
        changeBackgroundOnHoover();
    }

    @FXML
    void addPlayerNames() {
        openNamesDialog(player1);
        openNamesDialog(player2);
        System.out.println(player1.getName());
        System.out.println(player2.getName());

        if (getCURRENT_PLAYER() == 1) {
            currentPlayer.setText(player1.getName());
        } else {
            currentPlayer.setText(player2.getName());
        }

        starName.setText(player1.getName() + ":");
        heartName.setText(player2.getName() + ":");

    }

    @FXML
    void play1() {
        progressBarUpdate();
        playerMove(0, 0, getCURRENT_PLAYER());
        Field1x1.setDisable(true);
        changeBackgroundOnClick(Field1x1);
        changePlayer();
    }

    @FXML
    void play2() {
        progressBarUpdate();
        playerMove(1, 0, getCURRENT_PLAYER());
        Field1x2.setDisable(true);
        changeBackgroundOnClick(Field1x2);
        changePlayer();
    }

    @FXML
    void play3() {
        progressBarUpdate();
        playerMove(2, 0, getCURRENT_PLAYER());
        Field1x3.setDisable(true);
        changeBackgroundOnClick(Field1x3);
        changePlayer();
    }

    @FXML
    void play4() {
        progressBarUpdate();
        playerMove(0, 1, getCURRENT_PLAYER());
        Field2x1.setDisable(true);
        changeBackgroundOnClick(Field2x1);
        changePlayer();
    }

    @FXML
    void play5() {
        progressBarUpdate();
        playerMove(1, 1, getCURRENT_PLAYER());
        Field2x2.setDisable(true);
        changeBackgroundOnClick(Field2x2);
        changePlayer();
    }

    @FXML
    void play6() {
        progressBarUpdate();
        playerMove(2, 1, getCURRENT_PLAYER());
        Field2x3.setDisable(true);
        changeBackgroundOnClick(Field2x3);
        changePlayer();
    }

    @FXML
    void play7() {
        progressBarUpdate();
        playerMove(0, 2, getCURRENT_PLAYER());
        Field3x1.setDisable(true);
        changeBackgroundOnClick(Field3x1);
        changePlayer();
    }

    @FXML
    void play8() {
        progressBarUpdate();
        playerMove(1, 2, getCURRENT_PLAYER());
        Field3x2.setDisable(true);
        changeBackgroundOnClick(Field3x2);
        changePlayer();
    }

    @FXML
    void play9() {
        progressBarUpdate();
        playerMove(2, 2, getCURRENT_PLAYER());
        Field3x3.setDisable(true);
        changeBackgroundOnClick(Field3x3);
        changePlayer();
    }

    // NOT FXML METHODS

    private void addAllButtons() {
        buttons_fields.add(Field1x1);
        buttons_fields.add(Field1x2);
        buttons_fields.add(Field1x3);
        buttons_fields.add(Field2x1);
        buttons_fields.add(Field2x2);
        buttons_fields.add(Field2x3);
        buttons_fields.add(Field3x1);
        buttons_fields.add(Field3x2);
        buttons_fields.add(Field3x3);
    }

    // PROGRESS BAR UPDATE FUNCTION
    private void progressBarUpdate() {
        double x = progressBarStatus / 9.0;
        if (x < 1.0) {
            progressBar.setProgress(x);
            progressBarStatus++;
        } else if (x == 1.0) {
            progressBarStatus = 0;
        }


        if (x < 0.50 && x != 0) {
            progessLabel.setStyle("-fx-text-fill: #000000;");
            progressBar.getStyleClass().add("greenBar");

        } else if (x > 0.50 && x < 0.78) {
            progessLabel.setStyle("-fx-text-fill: rgb(0,0,0);");
            progressBar.getStyleClass().add("yellowBar");
        } else if (x > 0.78) {
            progessLabel.setStyle("-fx-text-fill: rgb(255,255,255);");
            progressBar.getStyleClass().add("redBar");
        }

        if (x == 0.0) {
            progessLabel.setStyle("-fx-text-fill: #000000;");
            progressBar.getStyleClass().clear();
            progressBar.getStyleClass().addAll("progress-bar");
            System.out.println("=======================================================");
        }
        progressBar.setProgress(x);
        progessLabel.setText(String.valueOf(Math.round(x * 100.0)) + " %");
    }


    private void playerMove(int field_x, int field_y, int CURRENT_PLAYER) {

        for (int i = 0; i < fieldTable.length; i++) {
            for (int j = 0; j < fieldTable.length; j++) {
                fieldTable[field_y][field_x] = CURRENT_PLAYER;
            }
        }

        for (int i = 0; i < fieldTable.length; i++) {
            System.out.println();
            for (int j = 0; j < fieldTable.length; j++) {
                System.out.print(fieldTable[i][j] + " ");
            }
        }

        System.out.println();

        checkTheWinner(getCURRENT_PLAYER(), IS_END_GAME); // TUTAJ DOBRZE DZIALA WYGRANA


    }

    private void winnerDialog(Player player) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Winner is: ");
        alert.setHeaderText(null);
        alert.setContentText(player.getName());
        alert.showAndWait();
    }


    private void addPoints(int CURRENT_PLAYER, Player player1, Player player2) {

        if (CURRENT_PLAYER == 1) {
            player1.setPoints(player1.getPoints() + 1);
            winnerDialog(player1);
        } else if (CURRENT_PLAYER == 2) {
            player2.setPoints(player2.getPoints() + 1);
            winnerDialog(player2);
        }

        starPoints.setText(String.valueOf(player1.getPoints()));
        heartPoints.setText(String.valueOf(player2.getPoints()));


    }

    private void checkTheWinner(int CURRENT_PLAYER, boolean IS_END_GAME) {

        if (fieldTable[0][0] == CURRENT_PLAYER && fieldTable[0][1] == CURRENT_PLAYER && fieldTable[0][2] == CURRENT_PLAYER) {
            System.out.println("FIRST HORIZONTAL LINE WIN!");
            IS_END_GAME = true;
            addPoints(getCURRENT_PLAYER(), player1, player2);
        } else if (fieldTable[1][0] == CURRENT_PLAYER && fieldTable[1][1] == CURRENT_PLAYER && fieldTable[1][2] == CURRENT_PLAYER) {
            System.out.println("SECOND HORIZONTAL LINE WIN!");
            IS_END_GAME = true;
            addPoints(getCURRENT_PLAYER(), player1, player2);
        } else if (fieldTable[2][0] == CURRENT_PLAYER && fieldTable[2][1] == CURRENT_PLAYER && fieldTable[2][2] == CURRENT_PLAYER) {
            System.out.println("THIRD HORIZONTAL LINE WIN!");
            IS_END_GAME = true;
            addPoints(getCURRENT_PLAYER(), player1, player2);
        } else if (fieldTable[0][0] == CURRENT_PLAYER && fieldTable[1][0] == CURRENT_PLAYER && fieldTable[2][0] == CURRENT_PLAYER) {
            System.out.println("FIRST VERTICAL LINE WIN!");
            IS_END_GAME = true;
            addPoints(getCURRENT_PLAYER(), player1, player2);
        } else if (fieldTable[0][1] == CURRENT_PLAYER && fieldTable[1][1] == CURRENT_PLAYER && fieldTable[2][1] == CURRENT_PLAYER) {
            System.out.println("SECOND VERTICAL LINE WIN!");
            IS_END_GAME = true;
            addPoints(getCURRENT_PLAYER(), player1, player2);
        } else if (fieldTable[0][2] == CURRENT_PLAYER && fieldTable[1][2] == CURRENT_PLAYER && fieldTable[2][2] == CURRENT_PLAYER) {
            System.out.println("THIRD VERTICAL LINE WIN!");
            IS_END_GAME = true;
            addPoints(getCURRENT_PLAYER(), player1, player2);
        } else if (fieldTable[0][0] == CURRENT_PLAYER && fieldTable[1][1] == CURRENT_PLAYER && fieldTable[2][2] == CURRENT_PLAYER) {
            System.out.println("FROM LEFT UPPER TO RIGHT DOWN WIN!");
            IS_END_GAME = true;
            addPoints(getCURRENT_PLAYER(), player1, player2);
        } else if (fieldTable[0][2] == CURRENT_PLAYER && fieldTable[1][1] == CURRENT_PLAYER && fieldTable[2][0] == CURRENT_PLAYER) {
            System.out.println("FROM RIGHT UPPER TO LEFT DOWN WIN!");
            IS_END_GAME = true;
            addPoints(getCURRENT_PLAYER(), player1, player2);
        } else if (fieldTable[0][0] != 0 && fieldTable[0][1] != 0 && fieldTable[0][2] != 0 && fieldTable[1][0] != 0 && fieldTable[1][1] != 0 && fieldTable[1][2] != 0 && fieldTable[2][0] != 0 && fieldTable[2][1] != 0 && fieldTable[2][2] != 0) {
            System.out.println("TIE!!!!!!!!!!!");
            IS_END_GAME = true;
            player1.setPoints(player1.getPoints() + 1);
            player2.setPoints(player2.getPoints() + 1);
            starPoints.setText(String.valueOf(player1.getPoints()));
            heartPoints.setText(String.valueOf(player2.getPoints()));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Winner is: ");
            alert.setHeaderText(null);
            alert.setContentText(player1.getName() + " and " + player2.getName() + " TIE!");
            alert.showAndWait();
        }

        if (IS_END_GAME) {
            System.out.println("PLAYER: " + CURRENT_PLAYER);
            for (Button button : buttons_fields
            ) {
                button.setDisable(true);
            }

            restartButton.setVisible(true);

        }


    }

    private void changePlayer() {
        if (getCURRENT_PLAYER() == 1) {
            setCURRENT_PLAYER(2);
            changeBackgroundOnHoover();
            currentPlayer.setText(player2.getName());
        } else if (getCURRENT_PLAYER() == 2) {
            setCURRENT_PLAYER(1);
            changeBackgroundOnHoover();
            currentPlayer.setText(player1.getName());
        }


    }

    private void changeBackgroundOnHoover() {
        if (getCURRENT_PLAYER() == 1) {
            for (Button buttons : buttons_fields) {
                buttons.getStyleClass().clear();
                buttons.getStyleClass().addAll("button", "Buttons", "player1hover");
            }
        } else if (getCURRENT_PLAYER() == 2) {
            for (Button buttons : buttons_fields) {
                buttons.getStyleClass().clear();
                buttons.getStyleClass().addAll("button", "Buttons", "player2hover");
            }
        }
    }

    private void changeBackgroundOnClick(Button button_id) {
        if (getCURRENT_PLAYER() == 1) {
            button_id.getStyleClass().clear();
            button_id.getStyleClass().addAll("button", "player1");
            buttons_fields.remove(button_id);
        } else if (getCURRENT_PLAYER() == 2) {
            button_id.getStyleClass().clear();
            button_id.getStyleClass().addAll("button", "player2");
            buttons_fields.remove(button_id);
        }
    }


    void openNamesDialog(Player player) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Hello " + player.getName() + "!");
        dialog.setContentText("Please enter your name:");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            player.setName(result.get());
        }
    }


    private int getCURRENT_PLAYER() {
        return CURRENT_PLAYER;
    }

    private void setCURRENT_PLAYER(int CURRENT_PLAYER) {
        this.CURRENT_PLAYER = CURRENT_PLAYER;
    }

}