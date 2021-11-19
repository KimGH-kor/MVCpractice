package practice.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import practice.DTO.CustomerDTO;

public class CustDAO {

   Connection con;
   PreparedStatement pstmt;
   ResultSet rs;

   public CustDAO() {
      dbConnect();
   }

   public void dbConnect() {
      String driver = "oracle.jdbc.driver.OracleDriver";
      String url = "jdbc:oracle:thin:@localhost:1521:XE";
      String user = "dev";
      String password = "123456";

      try {
         Class.forName(driver);
         System.out.println("Driver Loading Sucess!");

         con = DriverManager.getConnection(url, user, password);
         System.out.println("DB Connected...");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public ArrayList<CustomerDTO> sltMulti(int code) {
      ArrayList<CustomerDTO> custList = new ArrayList<CustomerDTO>();

      try {
         String sql = "SELECT * FROM CUSTOMER WHERE CODE >= ? ORDER BY CODE";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, code);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            CustomerDTO cust = new CustomerDTO();

            cust.setCode(rs.getInt("CODE"));
            cust.setName(rs.getString("NAME"));
            cust.setEmail(rs.getString("EMAIL"));
            cust.setTel(rs.getString("TEL"));
            cust.setWeight(rs.getDouble("WEIGHT"));

            custList.add(cust);
         }

         rs.close();
         pstmt.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      return custList;

   }

   public CustomerDTO sltOne(int code) {
      CustomerDTO c = null;
      try {
         String sql = "SELECT * FROM CUSTOMER WHERE CODE = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, code);
         rs = pstmt.executeQuery();

         if (rs.next()) {
            c = new CustomerDTO();

            c.setCode(rs.getInt("CODE"));
            c.setName(rs.getString("NAME"));
            c.setEmail(rs.getString("EMAIL"));
            c.setTel(rs.getString("TEL"));
            c.setWeight(rs.getDouble("WEIGHT"));

         }

         rs.close();
         pstmt.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      return c;
   }

   public int insertCst(CustomerDTO cst) {
      int cnt = 0;
      try {

         String sql = "INSERT INTO CUSTOMER VALUES(?, ?, ?, ?, ?)";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, cst.getCode());
         pstmt.setString(2, cst.getName());
         pstmt.setString(3, cst.getEmail());
         pstmt.setString(4, cst.getTel());
         pstmt.setDouble(5, cst.getWeight());

         cnt = pstmt.executeUpdate();

         rs.close();
         pstmt.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      return cnt;
   }

   public int updateCst(CustomerDTO cst) {
      int cnt = 0;
      try {

         String sql = "UPDATE CUSTOMER SET name = ?, email = ?, tel = ?, weight = ? WHERE code = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, cst.getName());
         pstmt.setString(2, cst.getEmail());
         pstmt.setString(3, cst.getTel());
         pstmt.setDouble(4, cst.getWeight());
         pstmt.setInt(5, cst.getCode());

         cnt = pstmt.executeUpdate();

         rs.close();
         pstmt.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      return cnt;
   }

   public int deleteCst(int code) {
      int cnt = 0;
      try {

         String sql = "DELETE FROM CUSTOMER WHERE CODE = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, code);

         cnt = pstmt.executeUpdate();

         rs.close();
         pstmt.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return cnt;
   }

   public void conClosed() {
      try {
         if (!con.isClosed()) {
            con.close();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}