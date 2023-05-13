package v.sistema_1;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
public class view {
    @FXML public TableView<prod> view;
    @FXML public TableColumn<prod, Integer> view_id;
    @FXML public TableColumn<prod, String> view_nome;
    @FXML public TableColumn<prod, String> view_url;
    @FXML public TableColumn<prod, Double> view_valc;
    @FXML public TableColumn<prod, Double> view_valv;
    @FXML public TableColumn<prod, Integer> view_vend;
    public TextField valvview;
    public TextField valcview;
    public TextField urlview;
    public TextField nomeview;
    public TextField unvendview;
    public TextArea txtview;
    public TextField pesquisatxt;
    public TextField viewid;
    public static ObservableList<prod>getprod(){
        ObservableList<prod>list=FXCollections.observableArrayList();
        try {
            PreparedStatement ps=connector.connection.prepareStatement("select * from produtos");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                list.add(new prod(Integer.parseInt(rs.getString("id")), rs.getString("nome"),
                        rs.getString("url"),Double.parseDouble(rs.getString("val_c")),
                        Double.parseDouble(rs.getString("val_v")),Integer.parseInt(rs.getString("uni_vend"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    public static ObservableList<prod>getprodp(String nome){
        ObservableList<prod>list=FXCollections.observableArrayList();
        try {
            PreparedStatement ps=connector.connection.prepareStatement("select * from produtos WHERE nome=?");
            ps.setString(1, nome);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                list.add(new prod(Integer.parseInt(rs.getString("id")), rs.getString("nome"),
                        rs.getString("url"),Double.parseDouble(rs.getString("val_c")),
                        Double.parseDouble(rs.getString("val_v")),Integer.parseInt(rs.getString("uni_vend"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    ObservableList<prod> listm;
    int i=-1;
    ResultSet rs=null;
    PreparedStatement pst=null;
    public void atdb(){
        view_id.setCellValueFactory(new PropertyValueFactory<prod,Integer>("id"));
        view_nome.setCellValueFactory(new PropertyValueFactory<prod,String>("nome"));
        view_url.setCellValueFactory(new PropertyValueFactory<prod,String>("url"));
        view_valc.setCellValueFactory(new PropertyValueFactory<prod,Double>("val_c"));
        view_valv.setCellValueFactory(new PropertyValueFactory<prod,Double>("val_v"));
        view_vend.setCellValueFactory(new PropertyValueFactory<prod,Integer>("uni_vend"));
        listm= getprod();
        view.setItems(listm);
    }
    public void atdbps(){
        view_id.setCellValueFactory(new PropertyValueFactory<prod,Integer>("id"));
        view_nome.setCellValueFactory(new PropertyValueFactory<prod,String>("nome"));
        view_url.setCellValueFactory(new PropertyValueFactory<prod,String>("url"));
        view_valc.setCellValueFactory(new PropertyValueFactory<prod,Double>("val_c"));
        view_valv.setCellValueFactory(new PropertyValueFactory<prod,Double>("val_v"));
        view_vend.setCellValueFactory(new PropertyValueFactory<prod,Integer>("uni_vend"));
        listm= getprodp(pesquisatxt.getText());
        view.setItems(listm);
    }
    public void atview(ActionEvent actionEvent) {
        atdb();
        nomeview.setText("");
        urlview.setText("");
        valcview.setText("");
        valvview.setText("");
        unvendview.setText("");
        viewid.setText("");
    }

    public void addview(ActionEvent actionEvent) {
        try {
            cad_prod cad_prod=new cad_prod();
            Integer uni_vend=Integer.parseInt(unvendview.getText());
            String nome=nomeview.getText();
            String url=urlview.getText();
            Double valc=Double.parseDouble(valcview.getText());
            Double valv=Double.parseDouble(valvview.getText());
            cad_prod.cadprod(nome,url,valc,valv,uni_vend);
            txtview.setText("[OK]cadastrado com sucesso");
        }catch (SQLException ex){
            txtview.setText("[ERRO]falha ao cadastrar "+ex);
        }
        nomeview.setText("");
        urlview.setText("");
        valcview.setText("");
        valvview.setText("");
        unvendview.setText("");
        viewid.setText("");
        atdb();
    }
    public void tbclick(MouseEvent mouseEvent) {
        int i =view.getSelectionModel().getSelectedIndex();
        prod prod1= (prod)view.getItems().get(i);
        viewid.setText(Integer.toString(prod1.getId()));
        nomeview.setText(prod1.getNome());
        urlview.setText(prod1.getUrl());
        valcview.setText(Double.toString(prod1.getVal_c()));
        valvview.setText(Double.toString(prod1.getVal_v()));
        unvendview.setText(Integer.toString(prod1.getUni_vend()));

    }
    public void editview(ActionEvent actionEvent) throws SQLException {
        try {
            try {
                PreparedStatement ps=connector.connection.prepareStatement("DELETE FROM produtos WHERE id=?");
                ps.setInt(1, Integer.parseInt( viewid.getText()));
                ps.executeUpdate();
                txtview.setText("[OK]removido com sucesso");
            }catch (SQLException ex){
                txtview.setText("[ERRO]falha ao remover "+ex);
            }
            cad_prod cad_prod=new cad_prod();
            cad_prod.cadprod(nomeview.getText(),urlview.getText(), Double.parseDouble(valcview.getText()),
                    Double.parseDouble(valvview.getText()),Integer.parseInt(unvendview.getText()));
            txtview.setText("[OK]editado com sucesso");
        }catch (SQLException ex){
            txtview.setText("[ERRO]falha ao editar "+ex);
        }
        atdb();
    }
    public void pesquisaview(ActionEvent actionEvent) {
        atdbps();
    }
    public void removview(ActionEvent actionEvent) throws SQLException {
        try {
            PreparedStatement ps=connector.connection.prepareStatement("DELETE FROM produtos WHERE id=?");
            ps.setInt(1, Integer.parseInt( viewid.getText()));
            ps.executeUpdate();
            txtview.setText("[OK]removido com sucesso");
        }catch (SQLException ex){
            txtview.setText("[ERRO]falha ao remover "+ex);
        }
        atdb();
    }

    public void colarurl(ActionEvent actionEvent) throws IOException, UnsupportedFlavorException {
        String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        urlview.setText(data);
    }
}
