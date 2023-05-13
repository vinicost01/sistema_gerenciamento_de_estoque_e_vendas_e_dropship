package v.sistema_1;
import java.sql.*;
public class connector {

    static Connection connection;
    public static void startconec(dbsg dbsg) {
        try {
            String URL= dbsg.getURL();
            String USER= dbsg.getUSER();
            String PASSWD= dbsg.getPASSWD();
            connection = DriverManager.getConnection(URL,USER,PASSWD);
            System.out.println("[OK]conexao estabelecida");
            dbsg.setMSG("[OK]conexao estabelecida");
        } catch (SQLException ex) {
            dbsg.setMSG("[ERRO]conexao falhou");
            System.out.println("[ERRO]conexao falhou " + ex);
        }
    }
    public static void finconec() {
        try {
            connection.close();
            System.out.println("[ok]conexao encerrada");
        } catch (SQLException ex) {
            System.out.println("[ERRO]falha ao encerrar conexao " + ex);
            System.exit(404);
        }
    }
}