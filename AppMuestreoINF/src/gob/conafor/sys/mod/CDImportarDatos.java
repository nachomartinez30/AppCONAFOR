/*package gob.conafor.sys.mod;


import gob.conafor.conn.dat.LocalConnection;
import gob.conafor.conn.dat.ServerConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDImportarDatos {
    
     private Connection baseDatosLocal;
     private Connection servidor;

    public void importarMalla() {
        String selectQuery = "SELECT UPMID, ConglomeradoID, LatDms, LongDms, X, Y, Estado, Region, PROYECTO, A, B, C, D, E, F, G, H, "
                + "SecuenciaID, Ciclo, ProveedorID, Municipio, MunicipioID FROM dbo.Malla2016";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.servidor = ServerConnection.getConnection();
        try {
            Statement ps = this.baseDatosLocal.createStatement();
            Statement stServidor = this.servidor.createStatement();
            ResultSet rs = stServidor.executeQuery(selectQuery);

            while (rs.next()) {
                Integer UPMID = rs.getInt("UPMID");
                Integer conglomeradoID = rs.getInt("ConglomeradoID");
                String latDms = rs.getString("LatDms");
                String longDms = rs.getString("LongDms");
                Float x = rs.getFloat("X");
                Float y = rs.getFloat("Y");
                String estado = rs.getString("Estado");
                String region = rs.getString("Region");
                String proyecto = rs.getString("PROYECTO");
                Integer A = rs.getInt("A");
                Integer B = rs.getInt("B");
                Integer C = rs.getInt("C");
                Integer D = rs.getInt("D");
                Integer E = rs.getInt("E");
                Integer F = rs.getInt("F");
                Integer G = rs.getInt("G");
                Integer H = rs.getInt("H");
                Integer secuenciaID = rs.getInt("SecuenciaID");
                Integer ciclo = rs.getInt("Ciclo");
                Integer provedorID = rs.getInt("ProveedorID");
                String municipio = rs.getString("Municipio");
                Integer municipioID = rs.getInt("MunicipioID");
                ps.executeUpdate("INSERT INTO UPM_MallaPuntos(UPMID, ConglomeradoID, LatDms, LongDms, X, Y, Estado, Region, PROYECTO, A, B, C, D,"
                        + " E, F, G, H, SecuenciaID, Ciclo, ProveedorID,  Municipio, MunicipioID)VALUES(" + UPMID + ", " + conglomeradoID + ", '" + latDms + "', '" + longDms + "', " + x
                        + ", " + y + ", '" + estado + "', '" + region + "', '" + proyecto + "', " + A + ", " + B + ", " + C + ", " + D + ", " + E + ", " + F + ", " + G
                        + ", " + H + ", " + secuenciaID + ", " + ciclo + ", " + provedorID + ", '" + municipio + "', " + municipioID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            stServidor.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla de malla de puntos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                servidor.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla de malla de puntos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void importarFamilias() {
        String selectQuery = "SELECT IdFamilia, Familia FROM Catalogos.CatFamilia";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.servidor = ServerConnection.getConnection();
        try {
            Statement ps = this.baseDatosLocal.createStatement();
            Statement stServidor = this.servidor.createStatement();
            ResultSet rs = stServidor.executeQuery(selectQuery);

            while (rs.next()) {
                Integer familiaID = rs.getInt("IdFamilia");
                String Familia = rs.getString("Familia");
                
                ps.executeUpdate("INSERT INTO CAT_FamiliaEspecie(FamiliaID, Nombre)VALUES(" + familiaID + ", '" + Familia + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            stServidor.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla de malla de puntos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                servidor.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla de malla de puntos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void importarGeneros() {
        String selectQuery = "SELECT IdGenero, Genero, IdFamilia FROM Catalogos.CatGenero";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.servidor = ServerConnection.getConnection();
        try {
            Statement ps = this.baseDatosLocal.createStatement();
            Statement stServidor = this.servidor.createStatement();
            ResultSet rs = stServidor.executeQuery(selectQuery);

            while (rs.next()) {
                Integer generoID = rs.getInt("IdGenero");
                String genero = rs.getString("Genero");
                Integer familiaID = rs.getInt("IdFamilia");
                ps.executeUpdate("INSERT INTO CAT_Genero(GeneroID, Nombre, FamiliaID)VALUES(" + generoID + ",'" + genero + "', " + familiaID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            stServidor.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla de malla de puntos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                servidor.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla de malla de puntos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void importarEspecies(){
        String selectQuery = "SELECT IdEspecie, Especie, IdGenero FROM Catalogos.CatEspecie";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.servidor = ServerConnection.getConnection();
        try {
            Statement ps = this.baseDatosLocal.createStatement();
            Statement stServidor = this.servidor.createStatement();
            ResultSet rs = stServidor.executeQuery(selectQuery);

            while (rs.next()) {
                Integer especieID = rs.getInt("IdEspecie");
                String especie = rs.getString("Especie");
                Integer generoID = rs.getInt("IdGenero");
                ps.executeUpdate("INSERT INTO CAT_Especie(EspecieID, Nombre, GeneroID)VALUES(" + especieID + ",'" + especie + "', " + generoID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            stServidor.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla de malla de puntos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                servidor.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla de malla de puntos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void importarInfraespecies(){
        String selectQuery = "SELECT IdInfraespecie, Infraespecie, IdEspecie FROM Catalogos.CatInfraespecie";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.servidor = ServerConnection.getConnection();
        try {
            Statement ps = this.baseDatosLocal.createStatement();
            Statement stServidor = this.servidor.createStatement();
            ResultSet rs = stServidor.executeQuery(selectQuery);
            while (rs.next()) {
                Integer infraespecieID = rs.getInt("IdInfraespecie");
                String infraespecie = rs.getString("Infraespecie");
                Integer especieID = rs.getInt("IdEspecie");
                ps.executeUpdate("INSERT INTO CAT_Infraespecie(InfraespecieID, Nombre, EspecieID)VALUES(" + infraespecieID + ",'" + infraespecie + "', " + especieID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            stServidor.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla de malla de puntos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                servidor.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla de malla de puntos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void exportarMallaMuestreo() {
        String selectQuery = "SELECT UPMID, ConglomeradoID, LatDms, LongDms, X, Y, Estado, Region, PROYECTO, A, B, C, D, E, F, G, H, "
                + "SecuenciaID, Ciclo, ProveedorID, Municipio, MunicipioID FROM UPM_MallaPuntos";
        Connection sqlcon = LocalConnection.getConnection();
        Connection h2con = H2Conexion.getConnection();
        System.out.println(h2con);
        try {
            Statement stSql = sqlcon.createStatement();
            Statement stH2 = h2con.createStatement();
            ResultSet rs = stSql.executeQuery(selectQuery);
            while (rs.next()) {
                Integer UPMID = rs.getInt("UPMID");
                Integer conglomeradoID = rs.getInt("ConglomeradoID");
                String latDms = rs.getString("LatDms");
                String longDms = rs.getString("LongDms");
                Float x = rs.getFloat("X");
                Float y = rs.getFloat("Y");
                String estado = rs.getString("Estado");
                String region = rs.getString("Region");
                String proyecto = rs.getString("PROYECTO");
                Integer A = rs.getInt("A");
                Integer B = rs.getInt("B");
                Integer C = rs.getInt("C");
                Integer D = rs.getInt("D");
                Integer E = rs.getInt("E");
                Integer F = rs.getInt("F");
                Integer G = rs.getInt("G");
                Integer H = rs.getInt("H");
                Integer secuenciaID = rs.getInt("SecuenciaID");
                Integer ciclo = rs.getInt("Ciclo");
                Integer provedorID = rs.getInt("ProveedorID");
                String municipio = rs.getString("Municipio");
                Integer municipioID = rs.getInt("MunicipioID");
                stH2.executeUpdate("INSERT INTO UPM_MallaPuntos(UPMID, ConglomeradoID, LatDms, LongDms, X, Y, Estado, Region, PROYECTO, A, B, C, D,"
                        + " E, F, G, H, SecuenciaID, Ciclo, ProveedorID,  Municipio, MunicipioID)VALUES(" + UPMID + ", " + conglomeradoID + ", '" + latDms + "', '" + longDms + "', " + x
                        + ", " + y + ", '" + estado + "', '" + region + "', '" + proyecto + "', " + A + ", " + B + ", " + C + ", " + D + ", " + E + ", " + F + ", " + G
                        + ", " + H + ", " + secuenciaID + ", " + ciclo + ", " + provedorID + ", '" + municipio + "', " + municipioID + ")");

            }
            stH2.close();
            stSql.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla de malla de puntos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                sqlcon.close();
                h2con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla de malla de puntos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void exportarBrigadistas(){
        String selectQuery = "SELECT BrigadistaID, Nombre, ApellidoPaterno, ApellidoMaterno, PuestoID, Activo, EmpresaID FROM BRIGADA_Brigadistas";
        Connection sqlcon = LocalConnection.getConnection();
        Connection h2con = H2Conexion.getConnection();
        System.out.println(h2con);
        try {
            Statement stSql = sqlcon.createStatement();
            Statement stH2 = h2con.createStatement();
            ResultSet rs = stSql.executeQuery(selectQuery);
            while (rs.next()) {
                Integer brigadistaID = rs.getInt("BrigadistaID");
                String nombre = rs.getString("Nombre");
                String apellidoPaterno = rs.getString("ApellidoPaterno");
                String apellidoMaterno = rs.getString("ApellidoMaterno");
                Integer puestoID = rs.getInt("PuestoID");
                Integer activo = rs.getInt("Activo");
                Integer empresaID = rs.getInt("EmpresaID");
               
                stH2.executeUpdate("INSERT INTO BRIGADA_Brigadistas(BrigadistaID, Nombre, ApellidoPaterno, ApellidoMaterno, PuestoID, Activo, EmpresaID)VALUES(" + brigadistaID + ", '" + nombre + "', '" + apellidoPaterno + "', '" + apellidoMaterno + "', " + puestoID
                        + ", " + activo + ", " + empresaID + ")");
            }
            stH2.close();
            stSql.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla de Brigadistas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                sqlcon.close();
                h2con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla de Brigadistas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void exportarClaveVegetacion(){
        String selectQuery = "SELECT ClaveSerieVID, Ecosistema, Formacion, TipoVegetacion, Clave, EsForestal FROM CAT_ClaveSerieV";
        Connection sqlcon = LocalConnection.getConnection();
        Connection h2con = H2Conexion.getConnection();
        System.out.println(h2con);
        try {
            Statement stSql = sqlcon.createStatement();
            Statement stH2 = h2con.createStatement();
            ResultSet rs = stSql.executeQuery(selectQuery);
            while (rs.next()) {
                Integer claveSerieVID = rs.getInt("ClaveSerieVID");
                String ecosistema = rs.getString("Ecosistema");
                String formacion = rs.getString("Formacion");
                String tipoVegetacion = rs.getString("TipoVegetacion");
                String clave = rs.getString("Clave");
                Integer esForestal = rs.getInt("EsForestal");
               
                stH2.executeUpdate("INSERT INTO CAT_ClaveSerieV(ClaveSerieVID, Ecosistema, Formacion, TipoVegetacion, Clave, EsForestal)VALUES(" + claveSerieVID + ", '" + ecosistema + "', '" + formacion + "', '" + tipoVegetacion + "', '" + clave
                        + "', " + esForestal + ")");
            }
            stH2.close();
            stSql.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla de Brigadistas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                sqlcon.close();
                h2con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla de Brigadistas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void exportarFamiliaEspecie(){
        String selectQuery = "SELECT FamiliaID, Nombre, ClaseID FROM CAT_FamiliaEspecie";
        Connection sqlcon = LocalConnection.getConnection();
        Connection h2con = H2Conexion.getConnection();
        System.out.println(h2con);
        try {
            Statement stSql = sqlcon.createStatement();
            Statement stH2 = h2con.createStatement();
            ResultSet rs = stSql.executeQuery(selectQuery);
            while (rs.next()) {
                Integer familiaID = rs.getInt("FamiliaID");
                String nombre = rs.getString("Nombre");
                String claseID = rs.getString("ClaseID");
               
                stH2.executeUpdate("INSERT INTO CAT_FamiliaEspecie(FamiliaID, Nombre, ClaseID)VALUES(" + familiaID + ", '" + nombre + "', " + claseID + ")");
            }
            stH2.close();
            stSql.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla de Familia especie ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                sqlcon.close();
                h2con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla de Familia especie ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void exportarGenero() {
        String selectQuery = "SELECT GeneroID, Nombre, FamiliaID FROM CAT_Genero";
        Connection sqlcon = LocalConnection.getConnection();
        Connection h2con = H2Conexion.getConnection();
        System.out.println(h2con);
        try {
            Statement stSql = sqlcon.createStatement();
            Statement stH2 = h2con.createStatement();
            ResultSet rs = stSql.executeQuery(selectQuery);
            while (rs.next()) {
                Integer generoID = rs.getInt("GeneroID");
                String nombre = rs.getString("Nombre");
                String familiaID = rs.getString("FamiliaID");

                stH2.executeUpdate("INSERT INTO CAT_Genero(GeneroID, Nombre, FamiliaID)VALUES(" + generoID + ", '" + nombre + "', " + familiaID + ")");
            }
            stH2.close();
            stSql.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla de Genero ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                sqlcon.close();
                h2con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla de Genero ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void exportEspecie() {
        String selectQuery = "SELECT EspecieID, Nombre, GeneroID FROM CAT_Especie";
        Connection sqlcon = LocalConnection.getConnection();
        Connection h2con = H2Conexion.getConnection();
        System.out.println(h2con);
        try {
            Statement stSql = sqlcon.createStatement();
            Statement stH2 = h2con.createStatement();
            ResultSet rs = stSql.executeQuery(selectQuery);
            while (rs.next()) {
                Integer especieID = rs.getInt("EspecieID");
                String nombre = rs.getString("Nombre");
                String generoID = rs.getString("GeneroID");

                stH2.executeUpdate("INSERT INTO CAT_Especie(EspecieID, Nombre, GeneroID)VALUES(" + especieID + ", '" + nombre + "', " + generoID + ")");
            }
            stH2.close();
            stSql.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla de Genero ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                sqlcon.close();
                h2con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla de Genero ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void exportFormatos() {
        String selectQuery = "SELECT FormatoID, Formulario, Formato, Modulo FROM SYS_Formatos";
        Connection sqlcon = LocalConnection.getConnection();
        Connection h2con = H2Conexion.getConnection();
        System.out.println(h2con);
        try {
            Statement stSql = sqlcon.createStatement();
            Statement stH2 = h2con.createStatement();
            ResultSet rs = stSql.executeQuery(selectQuery);
            while (rs.next()) {
                Integer formatoID = rs.getInt("FormatoID");
                String formulario = rs.getString("Formulario");
                String formato = rs.getString("Formato");
                String modulo = rs.getString("Modulo");
                stH2.executeUpdate("INSERT INTO SYS_Formatos(FormatoID, Formulario, Formato, modulo)VALUES(" + formatoID + ", '" + formulario + "', '" + formato + "', '" + modulo + "')");
            }
            stH2.close();
            stSql.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información del catalogo de formatos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                sqlcon.close();
                h2con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla de formatos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void exportSecciones(){
         String selectQuery = "SELECT SeccionID, Seccion, SubSeccion, Formulario, Modulo FROM SYS_Seccion";
        Connection sqlcon = LocalConnection.getConnection();
        Connection h2con = H2Conexion.getConnection();
        System.out.println(h2con);
        try {
            Statement stSql = sqlcon.createStatement();
            Statement stH2 = h2con.createStatement();
            ResultSet rs = stSql.executeQuery(selectQuery);
            while (rs.next()) {
                Integer seccionID = rs.getInt("SeccionID");
                String seccion = rs.getString("Seccion");
                String subSeccion = rs.getString("SubSeccion");
                String formulario = rs.getString("Formulario");
                String modulo = rs.getString("Modulo");
                
                stH2.executeUpdate("INSERT INTO SYS_Seccion(SeccionID, Seccion, SubSeccion, Formulario, Modulo)VALUES(" + seccionID + ", '" + seccion + "', '" + subSeccion + "', '" + formulario + "','" + modulo + "')");
            }
            stH2.close();
            stSql.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información del catalogo de secciones ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                sqlcon.close();
                h2con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla de secciones ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
   /* 
   public static void main(String args[]){
        CDImportarDatos exportacion = new CDImportarDatos();
        //exportacion.exportarMallaMuestreo();
        //exportacion.exportarBrigadistas();
        //exportacion.exportarClaveVegetacion();
        //exportacion.exportarFamiliaEspecie();
        //exportacion.exportarGenero();
       // exportacion.exportEspecie();
       //exportacion.exportFormatos();
       //exportacion.exportSecciones();
       //exportacion.importarFamilias();
       //exportacion.importarGeneros();
       //exportacion.importarEspecies();
       exportacion.importarInfraespecies();
    }

}
*/