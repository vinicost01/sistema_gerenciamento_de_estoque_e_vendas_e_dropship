package v.sistema_1;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class configcontroller {
    public TextField url;
    public TextField user;
    public TextField table;
    public TextField passwd;
    public TextArea msgdb;
    public void save_db(ActionEvent actionEvent) {
        dbsg dbsg=new dbsg();
        String ur= url.getText();
        String us= user.getText();
        String ps= passwd.getText();
        dbsg.setURL(ur);
        dbsg.setUSER(us);
        dbsg.setPASSWD(ps);
        url.setText("");
        user.setText("");
        passwd.setText("");
        connector.startconec(dbsg);
        msgdb.setText(dbsg.getMSG());
        dbsg.armzdbconfig(dbsg);
    }
}
