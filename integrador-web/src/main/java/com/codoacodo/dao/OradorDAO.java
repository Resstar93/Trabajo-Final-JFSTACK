package com.codoacodo.dao;
import static com.codoacodo.dao.Conexion.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.codoacodo.model.Orador;
import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OradorDAO 
{    
    static Logger logger = LoggerFactory.getLogger(OradorDAO.class); 
    private static final String SQL_SELECT = "SELECT * FROM oradores";
    private static final String SQL_INSERT = "INSERT INTO oradores(nombre, apellido, mail, tema) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE oradores SET nombre = ?, apellido = ?, mail = ?, tema = ? WHERE ID_ORADOR = ?";
    private static final String SQL_DELETE = "DELETE FROM oradores WHERE ID_ORADOR = ?";
    
    public Orador obtenerPorId(Long id)
    {
		String sql = "SELECT * FROM PRODUCTO WHERE ID="+id;
                
                //Connection
		Connection con = Conexion.getConexion();
	
                    Orador prodFromDb = null;
		
		//Statement
		try {
			Statement st = con.createStatement();
			
			//resultset
			ResultSet rs = st.executeQuery(sql);
			
			//VIENE UN SOLO REGISTRO!!!
			
			if(rs.next()) {//si existe, hay uno solo
				// rs > sacando los datos
				Long idProducto = rs.getLong(1);//tomar la primer columna
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String mail = rs.getString(4);
				String tema = rs.getString(5);
				Timestamp fechaAlta = rs.getTimestamp(6);
				
				
				//campos crear un objeto????
				prodFromDb = new Orador(idProducto,nombre,apellido,mail,tema,fechaAlta);
			}			
		} catch (SQLException e) {
			// ERRORES
			e.printStackTrace();
		}
		return prodFromDb;
    }
    public List<Orador> selectOradores() throws SQLException , NullPointerException , 
           ClassNotFoundException , InstantiationException , IllegalAccessException 
    {    
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Orador orador = null;
        List<Orador> oradores = new ArrayList();
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try 
        {
            conn = getConexion();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL_SELECT);    
            while(rs.next()) 
            {    
                Long idOrador = rs.getLong(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                String mail = rs.getString(4);
                String tema = rs.getString(5);
                Timestamp fechaAlta = rs.getTimestamp(6);
                
                orador = new Orador(idOrador, nombre, apellido, mail, tema , fechaAlta);
                oradores.add(orador);
            }
        } 
        catch(SQLException | NullPointerException ex) 
            {
            ex.printStackTrace(System.out);
            ex.getMessage(); 
             } 
     
        return oradores;
    }
    public List<Orador> listarOradores() 
    {
		String sql = "SELECT * FROM Orador ";
		
		//Connection
		Connection con = Conexion.getConexion();
	
		List<Orador> list = new ArrayList<>();
		
		//Statement
		try {
			Statement st = con.createStatement();
			
			//resultset
			ResultSet rs = st.executeQuery(sql);
			
			//VIENE UN SOLO REGISTRO!!!
			
			while(rs.next()) {//
				// rs > sacando los datos
				Long idOrador = rs.getLong(1);//tomar la primer columna
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String mail = rs.getString(4);
				String tema = rs.getString(5);
				Timestamp fechaAlta = rs.getTimestamp(6);
				
				//campos crear un objeto????
				Orador prodFromDb = new Orador(idOrador,nombre,apellido,mail,tema,fechaAlta);
				
				//agrego a la lista 
				list.add(prodFromDb);
			}			
			
			//cierro la conexion
			con.close();
		} catch (SQLException e) {
			// ERRORES
			e.printStackTrace();
		}
		return list;
    }
    public int insertarOrador(Orador ora) throws SQLException , NullPointerException , ClassNotFoundException ,
                InstantiationException , IllegalAccessException 
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try 
        {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, ora.getNombre());
            stmt.setString(2, ora.getApellido());
            stmt.setString(3, ora.getMail());
            stmt.setString(4, ora.getTema());
            registros = stmt.executeUpdate();
        } 
        catch(SQLException ex) {
            ex.printStackTrace(System.out);
        } 
      
        return registros;
    }
    public int actualizarOrador(Orador ora) 
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;  
        try
        {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, ora.getNombre());
            stmt.setString(2, ora.getApellido());
            stmt.setString(3, ora.getMail());
            stmt.setString(4, ora.getTema());
            stmt.setLong(5, ora.getId());
            registros = stmt.executeUpdate();
        } 
        catch(SQLException | NullPointerException ex) 
        {
            ex.printStackTrace(System.out);
        } 
        finally 
        {
            try 
            {
                stmt.close();
                conn.close();
            } 
            catch(SQLException | NullPointerException ex) 
            {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    public void crearOrador(String nombre, String apellido, String mail, String tema, String fechaAlta) 
    {
		Connection con = Conexion.getConexion();
		
		if(con != null) { 
			// insert en la db > SQL: INSERT INTO....
			String sql = "INSERT INTO PRODUCTO (nombre, apellido,mail,tema,fechaAlta) ";
			sql += "VALUES('"+nombre+"',"+apellido+",'"+mail+"','"+tema+"','"+fechaAlta+"')";
			
			//control de errores
			try {
				Statement st = con.createStatement();			
				st.execute(sql);
				
				//cierre de conexion
				con.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
    public void actualizarOrador(String dni, String nombre, String apellido, String fechaAlta) 
    {
		Connection con = Conexion.getConexion();
		if(con != null) 
                { 
			String sql = "UPDATE Orador "
					+ " set nombre='"+nombre+"',"
					+ " apellido='"+apellido+"',"
					+ " fecha_Alta='"+ fechaAlta +"'"
					+ " WHERE dni = '"+dni+"'";		
			try {
				Statement st = con.createStatement();			
				st.executeUpdate(sql);
				con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
    public List<Orador> buscar(String clave)
    {
		String sql = "SELECT * FROM PRODUCTO WHERE nombre LIKE '%"+clave+"%' ";
		
		//Connection
		Connection con = Conexion.getConexion();
	
		List<Orador> listado = new ArrayList<Orador>();
		
		//Statement
		try {
			Statement st = con.createStatement();
			
			//resultset
			ResultSet rs = st.executeQuery(sql);
			
			//VIENE UN SOLO REGISTRO!!!
			
			while(rs.next()) {//si existe, hay uno solo
				// rs > sacando los datos
				Long idOrador = rs.getLong(1);//tomar la primer columna
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String mail = rs.getString(4);
				String tema = rs.getString(5);
				Timestamp fechaAlta = rs.getTimestamp(6);
				
				//campos crear un objeto????
				Orador prodFromDb = new Orador(idOrador,nombre,apellido,mail,tema,fechaAlta);
				
				listado.add(prodFromDb);
			}	
		} catch (SQLException e) {
			// ERRORES
			e.printStackTrace();
		}
		
		return listado;
	}
    public int eliminarOrador(int id) 
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try
        {   conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            registros = stmt.executeUpdate();
        } 
        catch(SQLException | NullPointerException ex) 
        {
            ex.printStackTrace(System.out);
        } 
        finally 
        {
            try 
            {
                stmt.close();
                conn.close();
            } 
            catch(SQLException | NullPointerException ex) 
            {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
}
