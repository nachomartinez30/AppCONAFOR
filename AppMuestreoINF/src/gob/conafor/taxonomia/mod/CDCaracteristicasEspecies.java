package gob.conafor.taxonomia.mod;

import gob.conafor.conn.dat.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CDCaracteristicasEspecies {

    private final List<CatEAgenteDanio> listAgenteDanio = new ArrayList();
    private final CatEAgenteDanio agenteDanio = new CatEAgenteDanio();
    private final List<CatETipoVigor> ListVigorArbolado = new ArrayList<>();
    private final CatETipoVigor vigorArbolado = new CatETipoVigor();
    private String query;

    public List<CatEAgenteDanio> getAgentesDanio() {
        query = "SELECT AgenteDanioID, Clave, Agente, Descripcion FROM CAT_AgenteDanio";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                agenteDanio.setAgenteDanioID(rs.getInt("AgenteDanioID"));
                agenteDanio.setClave(rs.getString("Clave"));
                agenteDanio.setAgente(rs.getString("Agente"));
                agenteDanio.setDescripcion(rs.getString("Descripcion"));
                listAgenteDanio.add(agenteDanio);
            }
            st.close();
            rs.close();
            return listAgenteDanio;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de agente daño ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al cerrar la base de datos al generar el listado de agente daño",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

  /*  public List<CatETipoVigor> getVigorArbolado() {

        query = "SELECT NivelVigorID, Descripcion FROM CAT_NivelVigorArbolado";
        Connection conn = LocalConnection.getConnection();

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                vigorArbolado.setVigorID(rs.getInt("NivelVigorID"));
                vigorArbolado.setDescripcion(rs.getString("Descripcion"));

                ListVigorArbolado.add(vigorArbolado);
            }

            st.close();
            rs.close();
            return ListVigorArbolado;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error! al obtener el catálogo de vigor de arbolado "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);

            return null;
        } finally {

            try {

                conn.close();

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null,
                        "Error! al cerrar la base de datos al generar el listado de vigor de arbolado"
                        + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }*/

}
