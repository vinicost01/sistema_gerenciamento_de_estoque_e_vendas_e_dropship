/*
* author: vinicius costa
*/
package v.sistema_1;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.IOException;
public class menucontroller extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(menucontroller.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 454, 273);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    public void cfg(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("config.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 454, 273);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void cdp(ActionEvent actionEvent) {
        try {
            dbsg dbsg=new dbsg();
            dbsg.dbconfig();
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("cad_prod.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 454, 273);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void dscdb(ActionEvent actionEvent) {
        connector.finconec();
    }
    public void verp(ActionEvent actionEvent) {
        try {
            dbsg dbsg=new dbsg();
            dbsg.dbconfig();
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 900, 558);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ajuda(ActionEvent actionEvent) {
        try {

            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("ajuda.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 454, 273);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void remp(ActionEvent actionEvent) {
        try {
            dbsg dbsg=new dbsg();
            dbsg.dbconfig();
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("rem_prod.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 454, 273);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void finan(ActionEvent actionEvent) {
        try {
            dbsg dbsg=new dbsg();
            dbsg.dbconfig();
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("finan.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 621, 427);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
