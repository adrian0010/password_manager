package pass_manager.password_manager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            //password scene
            BorderPane passwords_root = new BorderPane();
            passwords_root.setBorder(new Border(new BorderStroke(Color.web("#336699"),
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


            passwords_root.setStyle("-fx-background-color: #336699;");

            Scene passwords_scene = new Scene(passwords_root,1000, 600);
            TableView<Password> passwordTableView = new TableView<>();

            TableColumn<Password,String> column_site = new TableColumn<>("Site");
            column_site.setCellValueFactory(new PropertyValueFactory<>("site"));

            TableColumn<Password,String> column_username = new TableColumn<>("Username");
            column_username.setCellValueFactory(new PropertyValueFactory<>("username"));

            TableColumn<Password,String> column_password = new TableColumn<>("Password");
            column_password.setSortable(false);
            column_password.setCellValueFactory(new PropertyValueFactory<>("password"));

            passwordTableView.getColumns().addAll(column_site,column_username,column_password);
            passwordTableView.setPrefSize(370,600);

            passwords_root.setLeft(passwordTableView);


            //signup scene
            GridPane signup_grid = new GridPane();
            signup_grid.setStyle("-fx-background-color: #336699;");
            signup_grid.setAlignment(Pos.CENTER);
            signup_grid.setVgap(10);
            signup_grid.setHgap(10);
            GridPane signup_grid1 = new GridPane();
            signup_grid1.setVgap(10);
            signup_grid1.setHgap(10);
            Scene signup_scene = new Scene(signup_grid,500,500);
            Button signup_back = new Button("Back");
            Button signup_signup = new Button("Signup");
            Text signup_username = new Text("Username");
            TextField signup_username_input = new TextField();
            Text signup_password = new Text("Password");
            Text signup_password1 = new Text("Confirm Password");
            TextField signup_pass_input = new PasswordField();
            TextField signup_pass_input1 = new PasswordField();
            Label signup_status = new Label();
            Button signup_delete_all = new Button("Delete ALL Data");
            signup_status.setTextFill(Color.RED);
            signup_grid.add(signup_username, 0,0);
            signup_grid.add(signup_username_input, 1,0);
            signup_grid.add(signup_password, 0,1);
            signup_grid.add(signup_pass_input, 1,1);
            signup_grid.add(signup_password1, 0,2);
            signup_grid.add(signup_pass_input1, 1, 2);
            signup_grid1.add(signup_signup, 0,0);
            signup_grid1.add(signup_back, 1,0);
            signup_grid.add(signup_grid1,1,3);
            signup_grid.add(signup_status,1,4);
            signup_grid.add(signup_delete_all,0,5);


            //login scene
            GridPane login_grid = new GridPane();
            GridPane login_grid1 = new GridPane();
            login_grid.setVgap(10);
            login_grid.setHgap(10);
            login_grid1.setVgap(10);
            login_grid1.setHgap(10);
            login_grid.setStyle("-fx-background-color: #336699;");
            login_grid.setAlignment(Pos.CENTER);
            Scene login_scene = new Scene(login_grid, 500, 500);
            Button login_back = new Button("Back");
            Button login_login = new Button("Log in");
            Text login_username = new Text("Username");
            TextField login_username_input = new TextField();
            Text login_password = new Text("Password");
            TextField login_pass_input = new PasswordField();
            Label login_status = new Label();
            login_status.setTextFill(Color.RED);
            login_grid.add(login_username,0,0);
            login_grid.add(login_username_input,1,0);
            login_grid.add(login_password, 0, 1);
            login_grid.add(login_pass_input, 1,1);
            login_grid.add(login_grid1,1,2);
            login_grid1.add(login_login, 0,0);
            login_grid1.add(login_back,1,0);
            login_grid.add(login_status,1, 4);



            //generator scene
            GridPane generator_grid = new GridPane();
            generator_grid.setStyle("-fx-background-color: #336699;");
            generator_grid.setAlignment(Pos.CENTER_RIGHT);
            generator_grid.setHgap(10);
            generator_grid.setVgap(10);
            Button generator_generate = new Button("Generate Password");

            generator_generate.setPrefSize(125,30);
            TextField generator_password_text = new TextField();
            Button generator_copy = new Button("Copy");
            generator_password_text.setPrefSize(230,30);

            generator_grid.add(generator_password_text,0,0);
            generator_grid.add(generator_copy,0,2);
            generator_grid.add(generator_generate,0,1);



            //add_scene
            GridPane add_grid = new GridPane();
            add_grid.setStyle("-fx-background-color: #336699;");
            add_grid.setAlignment(Pos.CENTER);
            add_grid.setVgap(10);
            add_grid.setHgap(10);
            Button add_add_password = new Button("Save Password");
            Text add_site = new Text("Site");
            TextField add_site_input = new TextField();
            Text add_username = new Text("Username");
            TextField add_username_input = new TextField();
            Text add_password = new Text("Password");
            TextField add_pass_input_text = new TextField();
            PasswordField add_pass_input_pass = new PasswordField();
            CheckBox add_show_password = new CheckBox("Show/hide Password");
            Label add_status = new Label();
            Button add_delete_button = new Button("Delete ALL Password");
            add_status.setTextFill(Color.RED);
            Button add_logout = new Button("Log Out");
            add_pass_input_text.managedProperty().bind(add_show_password.selectedProperty());
            add_pass_input_text.visibleProperty().bind(add_show_password.selectedProperty());
            add_pass_input_pass.managedProperty().bind(add_show_password.selectedProperty().not());
            add_pass_input_pass.visibleProperty().bind(add_show_password.selectedProperty().not());
            add_pass_input_text.textProperty().bindBidirectional(add_pass_input_pass.textProperty());

            add_grid.add(add_site, 0,0);
            add_grid.add(add_site_input,1,0);
            add_grid.add(add_username,0,1);
            add_grid.add(add_username_input,1,1);
            add_grid.add(add_password,0,2);
            add_grid.add(add_pass_input_pass,1,2);
            add_grid.add(add_pass_input_text,1,2);
            add_grid.add(add_add_password,1,3);
            add_grid.add(add_delete_button,1,4);
            add_grid.add(add_logout,2,4);


            add_grid.add(add_status,2,3);
            add_grid.add(add_show_password,2,2);

            passwords_root.setRight(generator_grid);
            passwords_root.setCenter(add_grid);


            //initial scene
            BorderPane initial_root = new BorderPane();
            initial_root.setStyle("-fx-background-color: #336699;");
            VBox initial_VBox = new VBox();
            initial_root.setCenter(initial_VBox);
            initial_root.setTop(new Label());
            initial_root.setLeft(new Label());
            initial_root.setBottom(new Label());
            initial_VBox.setStyle("-fx-background-color: #336699;");
            Scene initial_scene = new Scene(initial_root, 500, 500);
            Button initial_login = new Button("Login");
            Button initial_signup = new Button("Register");
            initial_login.setPrefSize(100, 50);
            initial_signup.setPrefSize(100, 50);
            initial_VBox.setSpacing(8);
            initial_VBox.setAlignment(Pos.CENTER);
            initial_VBox.getChildren().addAll(initial_login, initial_signup);

            //main

            add_logout.setOnAction(actionEvent -> {
                stage.setScene(initial_scene);
                stage.setTitle("Hello!");
            });

            signup_delete_all.setOnAction(actionEvent -> {
                OutputDevice outputDevice = new OutputDevice();
                try {
                    outputDevice.deletePassword();
                    FileOutputStream pass = new FileOutputStream("passwords.txt");
                    outputDevice.emptyFile(pass);
                    FileOutputStream all = new FileOutputStream("allowed.txt");
                    outputDevice.emptyFile(all);
                    signup_status.setTextFill(Color.ORANGE);
                    signup_status.setText("All data was deleted");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            add_delete_button.setOnAction(actionEvent -> {
                OutputDevice outputDevice = new OutputDevice();
                try {
                    outputDevice.deletePassword();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputDevice inputDevice = new InputDevice();
                ArrayList<Password> passwords = null;
                passwordTableView.getItems().clear();
                try {
                    passwords = inputDevice.encryptReadPasswords();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int size = passwords.size();
                while (size>0){
                    passwordTableView.getItems().add(passwords.get(--size));
                }
            });

            add_add_password.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    if (add_site_input.getText().equals("")){
                        add_status.setText("No Site");
                        add_status.setTextFill(Color.RED);
                    }
                    else if (add_username_input.getText().equals("")){
                        add_status.setText("No Username");
                        add_status.setTextFill(Color.RED);
                    }
                    else if (add_pass_input_text.getText().equals("")){
                        add_status.setText("No Password");
                        add_status.setTextFill(Color.RED);
                    }
                    else {
                        add_status.setText("Password added");
                        add_status.setTextFill(Color.GREENYELLOW);
                        OutputDevice outputDevice = new OutputDevice();
                        Password password = new Password(add_site_input.getText(),add_username_input.getText(),add_pass_input_pass.getText());
                        try {
                            outputDevice.encryptWritePassword(password);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        InputDevice inputDevice = new InputDevice();
                        ArrayList<Password> passwords = null;
                        passwordTableView.getItems().clear();
                        try {
                            passwords = inputDevice.encryptReadPasswords();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int size = passwords.size();
                        while (size>0){
                            passwordTableView.getItems().add(passwords.get(--size));
                        }
                    }
                }
            });
            add_site_input.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    add_site_input.clear();
                    add_username_input.clear();
                    add_pass_input_pass.clear();
                    add_pass_input_text.clear();
                    add_status.setText("");
                }
            });

            generator_copy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putString(generator_password_text.getText());
                    clipboard.setContent(content);
                }
            });
            generator_generate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    generator_password_text.setText(PasswordGenerator.password_generator(16));
                }
            });



            signup_signup.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream("allowed.txt");

                    if (signup_username_input.getText().equals("")){
                        signup_status.setText("No Username");
                        signup_status.setTextFill(Color.RED);
                    }
                    else if(signup_pass_input.getText().equals("")){
                        signup_status.setText("No Password");
                        signup_status.setTextFill(Color.RED);
                    }
                    else if (!signup_pass_input.getText().equals(signup_pass_input1.getText())){
                        signup_status.setText("Password not matching");
                        signup_status.setTextFill(Color.RED);
                    }
                    else if (fileInputStream.available()!=0){
                        signup_status.setText("Sign up not allowed");
                        signup_status.setTextFill(Color.RED);
                    }
                    else {
                        signup_status.setText("Signup successful");
                        signup_status.setTextFill(Color.GREENYELLOW);
                        Account account = new Account(signup_username_input.getText(),signup_pass_input.getText());
                        OutputDevice outputDevice = new OutputDevice();
                        try {
                            outputDevice.encryptWrite(account);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            login_login.setOnAction(actionEvent -> {
                InputDevice inputDevice = new InputDevice();
                ArrayList<Password> passwords = null;
                passwordTableView.getItems().clear();
                try {
                    passwords = inputDevice.encryptReadPasswords();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int size = passwords.size();
                while (size>0){
                    passwordTableView.getItems().add(passwords.get(--size));
                }
                if(login_username_input.getText().equals("")){
                    login_status.setText("No Username");
                    login_status.setTextFill(Color.RED);
                }
                else if (login_pass_input.getText().equals("")){
                    login_status.setText("No Password");
                    login_status.setTextFill(Color.RED);
                }
                else{
                    Account account = new Account(login_username_input.getText(),login_pass_input.getText());
                    try {
                        InputDevice in = new InputDevice();
                        Account b = in.encryptReadFirst();
                        if (account.login(b)){
                            login_status.setText("Login Successful");
                            login_status.setTextFill(Color.GREEN);
                                stage.setScene(passwords_scene);
                                stage.setTitle("Passwords");
                        }
                        else {
                            login_status.setText("Account not found");
                            login_status.setTextFill(Color.RED);
                        }
                    } catch (IOException e) {
                        login_status.setText("No account created");
                        e.printStackTrace();
                    }
                }
            });

            login_back.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    login_username_input.clear();
                    login_pass_input.clear();
                    stage.setScene(initial_scene);
                    stage.setTitle("Hello");
                }
            });

            signup_back.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    signup_username_input.clear();
                    signup_pass_input.clear();
                    signup_pass_input1.clear();
                    stage.setScene(initial_scene);
                    stage.setTitle("Hello");
                }
            });
            initial_login.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    login_username_input.clear();
                    login_pass_input.clear();
                    login_status.setText("");
                    stage.setScene(login_scene);
                    stage.setTitle("Log in");
                }
            });
            initial_signup.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    stage.setScene(signup_scene);
                    stage.setTitle("Sign up");
                }
            });
            stage.setTitle("Hello");
            stage.setScene(initial_scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}