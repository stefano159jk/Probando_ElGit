
package pe.edu.upeu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.edu.upeu.coon.ConnS;
import pe.edu.upeu.modelo.ResultadoTO;


public class ResultadoDao implements ResultadoDaoI{
    ConnS intance=ConnS.getInstance();
    Connection conexion=intance.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List listarResultados() {
        List<ResultadoTO> lista=new ArrayList();
        String sql="select * from resultado";
        try {
            ps=conexion.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
               ResultadoTO to=new ResultadoTO();
               to.setIdResultado(rs.getInt("id_resultado"));
               to.setNum1(rs.getInt("num1"));
               to.setNum2(rs.getInt("num2"));
               to.setOperador(rs.getString("operador"));
               to.setResult(rs.getDouble("result"));
            }
            
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public int crearResultado(ResultadoTO re) {
        int exec=0;
        int i=0;
        String sql="insert into resultado(num1, num2, operador, result)"
                + " values(?,?,?,?)";
        try {
            ps=conexion.prepareStatement(sql);
            ps.setInt(++i, re.getNum1());
            ps.setInt(++i, re.getNum2());
            ps.setString(++i, re.getOperador());
            ps.setDouble(++i, re.getResult());
            exec=ps.executeUpdate();
        } catch (Exception e) {
        }
        return exec;
    }
    
}
