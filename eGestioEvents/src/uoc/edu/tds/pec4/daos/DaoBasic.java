package uoc.edu.tds.pec4.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DaoBasic {
	
	protected Connection con = null;
	
	public DaoBasic(Connection con) {
		this.con = con;
	}
	
	protected final void close(Statement statement, ResultSet rs) {
    	close(rs);
        close(statement);
    }
	
	/**
     * Cierra el resultset que se le pase
     * @param ResultSet
     */
	protected final void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    /**
     * Cierra el statement que se le pase
     * @param statement
     */
	protected final void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }	
	
	protected final java.sql.Date getDateSql(java.util.Date valor) {
		if(valor!=null) {
			return new java.sql.Date(valor.getTime());
		}
		else {
			return null;
		}
	}
}
