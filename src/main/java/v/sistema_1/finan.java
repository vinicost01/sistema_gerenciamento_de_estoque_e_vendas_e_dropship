package v.sistema_1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class finan {
    public TextField txtreceita;
    public TextField txtgastos;
    public Label labreceita;
    public Label lablucro;
    public Label labgastos;
    public TableView<finanx>xx;
    public TableColumn<finanx, Double> rendaextra;
    public TableColumn<finanx, Double> gastosextra;
    public TextField idfinan;
    public TextArea txtfin;
    public TableColumn<finanx, String> nomefinan;
    public TextField nometx;


    public static ObservableList<finanx> getfinanx(){
        ObservableList<finanx>list= FXCollections.observableArrayList();
        try {
            PreparedStatement ps=connector.connection.prepareStatement("select * from finan");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                list.add(new finanx(Double.parseDouble(rs.getString("receita_extra")),
                        Double.parseDouble(rs.getString("gasto_extra")),
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("nome")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    ObservableList<finanx> listm;
    int i=-1;
    ResultSet rs=null;
    PreparedStatement pst=null;
    public void atfinanx(){
        rendaextra.setCellValueFactory(new PropertyValueFactory<finanx,Double>("receita_extra"));
        gastosextra.setCellValueFactory(new PropertyValueFactory<finanx,Double>("gasto_extra"));
        nomefinan.setCellValueFactory(new PropertyValueFactory<finanx,String>("nome"));
        listm= getfinanx();
        xx.setItems(listm);
    }



    public void atualizarfinan(ActionEvent actionEvent) throws SQLException {
        atfinanx();
        labreceita.setText(String.valueOf((receita()+receitax())));
        labgastos.setText(String.valueOf((gastos()+gastosx())));
        lablucro.setText(String.valueOf(((receita()+receitax())-(gastos()+gastosx()))));
        txtgastos.setText("0");
        txtreceita.setText("0");
        nometx.setText("");
        idfinan.setText("");
    }
    public void cadfinan(Double rx, Double gx, String nm) throws SQLException {
        String sql = "INSERT INTO finan(receita_extra, gasto_extra, nome) VALUES " +
                "(?, ?, ?)";
        PreparedStatement statement= connector.connection.prepareStatement(sql);
        statement.setDouble(1, rx);
        statement.setDouble(2, gx);
        statement.setString(3, nm);

        statement.execute();
    }
    public static Double receita(){
        double r = 0;
        try {
            PreparedStatement ps=connector.connection.prepareStatement("select * from produtos");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                double vv=Double.parseDouble(rs.getString("val_v"));
                int uv= Integer.parseInt(rs.getString("uni_vend"));
                r+=(vv*uv);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return r;
    }
    public static Double gastos(){
        double r = 0;
        try {
            PreparedStatement ps=connector.connection.prepareStatement("select * from produtos");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                double vv=Double.parseDouble(rs.getString("val_c"));
                int uv= Integer.parseInt(rs.getString("uni_vend"));
                r+=(vv*uv);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return r;
    }
    public static Double gastosx(){
        double r = 0;
        try {
            PreparedStatement ps=connector.connection.prepareStatement("select * from finan");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                r+=Double.parseDouble(rs.getString("gasto_extra"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return r;
    }

    public static Double receitax(){
        double r = 0;
        try {
            PreparedStatement ps=connector.connection.prepareStatement("select * from finan");
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                r+=Double.parseDouble(rs.getString("receita_extra"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return r;
    }

    public void addfinanx(ActionEvent actionEvent) throws SQLException {
        add();
    }

    public void slfnx(MouseEvent mouseEvent) {
        int i =xx.getSelectionModel().getSelectedIndex();
        finanx finanx= (finanx)xx.getItems().get(i);
        txtreceita.setText(Double.toString(finanx.getReceita_extra()));
        txtgastos.setText(Double.toString(finanx.getGasto_extra()));
        idfinan.setText(Integer.toString(finanx.getIdfinanx()));
        nometx.setText(finanx.getNome());
    }

    public void removfinax(ActionEvent actionEvent) throws SQLException {
        remov();
    }

    public void editfinan(ActionEvent actionEvent) {
        try{
            add();
            remov();
            txtfin.setText("[OK]editado com sucesso");
        }catch(Exception EX){
            txtfin.setText("[ERRO]falha ao editar "+EX);
        }
    }
    public void add(){
        try {
            double rx;
            double gx;
            String nm;
            rx = Double.parseDouble(txtreceita.getText());
            gx = Double.parseDouble(txtgastos.getText());
            nm= nometx.getText();
            if (rx != 0 || gx != 0) {
                cadfinan(rx, gx, nm);
            }
            atfinanx();
            labreceita.setText(String.valueOf((receita() + receitax())));
            labgastos.setText(String.valueOf((gastos() + gastosx())));
            lablucro.setText(String.valueOf(((receita() + receitax()) - (gastos() + gastosx()))));
            txtgastos.setText("0");
            txtreceita.setText("0");
            nometx.setText("");
            txtfin.setText("[OK]adicionado com sucesso");
        }catch (SQLException ex){
            txtfin.setText("[ERRO]falha ao adicionar "+ex);
        }
    }
    public void remov(){
        try {
            PreparedStatement ps=connector.connection.prepareStatement("DELETE FROM finan WHERE id=?");
            ps.setInt(1, Integer.parseInt( idfinan.getText()));
            ps.executeUpdate();
            txtfin.setText("[OK]removido com sucesso");
        }catch (SQLException ex){
            txtfin.setText("[ERRO]falha ao remover "+ex);
        }
        atfinanx();
        labreceita.setText(String.valueOf((receita()+receitax())));
        labgastos.setText(String.valueOf((gastos()+gastosx())));
        lablucro.setText(String.valueOf(((receita()+receitax())-(gastos()+gastosx()))));
        txtgastos.setText("0");
        txtreceita.setText("0");
        nometx.setText("");
        idfinan.setText("");
    }
}
