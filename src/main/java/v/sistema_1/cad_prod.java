package v.sistema_1;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class cad_prod {
    public TextField pn;
    public TextField purl;
    public TextField pvc;
    public TextField pvv;
    public TextArea msga;
    public void cdp(ActionEvent actionEvent) {
        try{
            Integer uni_vend=0;
            String nome=pn.getText();
            String url=purl.getText();
            Double valc=Double.parseDouble(pvc.getText());
            Double valv=Double.parseDouble(pvv.getText());
            cadprod(nome,url,valc,valv,uni_vend);
            msga.setText("[OK]cadastrado com sucesso");
            pn.setText("");
            purl.setText("");
            pvc.setText("");
            pvv.setText("");
        }catch(SQLException ex){
            msga.setText("[ERRO]falha ao cadastrar "+ex);
        }
    }
    public void cadprod(String nome, String url, Double valc, Double valv, Integer univend) throws SQLException {
        String sql = "INSERT INTO produtos(nome, val_c, val_v, url, uni_vend) VALUES " +
                "(?, ?, ?, ?, ?)";
            PreparedStatement statement= connector.connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setDouble(2, valc);
            statement.setDouble(3, valv);
            statement.setString(4, url);
            statement.setInt(5, univend );
            statement.execute();
    }

    public void colar(ActionEvent actionEvent)throws IOException, UnsupportedFlavorException {
            String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            purl.setText(data);
    }
}
