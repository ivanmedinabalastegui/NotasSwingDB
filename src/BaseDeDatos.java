import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDatos {
    
    public void actuRegistros(String actualizar) throws SQLException{
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String urlBD = "jdbc:mariadb://localhost:3306/PUNTU4";
            Connection conexionBD = DriverManager.getConnection(urlBD, "root", "dam2021");
            Statement Consulta = conexionBD.createStatement();
            int registrosAct = Consulta.executeUpdate(actualizar);
            Consulta.close();
            conexionBD.close();
        } catch (ClassNotFoundException | SQLException cnfe) {
            System.out.println(cnfe.getMessage());
        }
    }
    
    public ResultSet introRegistros(String consulta) {
        ResultSet resConsulta = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String urlBD = "jdbc:mariadb://localhost:3306/PUNTU4";
            Connection conexionBD = DriverManager.getConnection(urlBD, "root", "dam2021");
            Statement Consulta = conexionBD.createStatement();
            resConsulta = Consulta.executeQuery(consulta);
            Consulta.close();
            conexionBD.close();
        } catch (Exception cnfe) {
            System.out.println(cnfe.getMessage());
        }
        return resConsulta;
    }
}
