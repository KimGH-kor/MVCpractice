package practice;

import java.util.ArrayList;
import java.util.HashMap;

import practice.DAO.CustDAO;
import practice.DTO.CustomerDTO;

public class Service {
   CustDAO dao = new CustDAO();

   public HashMap<String, Object> cstMulti(int code) {
      HashMap<String, Object> hm = new HashMap<String, Object>();

      ArrayList<CustomerDTO> cstList = dao.sltMulti(code);

      if (cstList.size() == 0) {
         hm.put("MSG", "해당 자료가 없습니다.");
      } else {
         hm.put("MSG", "정상 처리 되었습니다");
      }
      hm.put("LIST", cstList);

      return hm;
   }

   public HashMap<String, Object> cstInsert(CustomerDTO cst) {
      HashMap<String, Object> hm = new HashMap<String, Object>();
      CustomerDTO cstDTO = dao.sltOne(cst.getCode());

      if (cstDTO == null) {
         int c = dao.insertCst(cst);

         if (c > 0) {
            hm.put("MSG", "정상처리");
         } else {
            hm.put("MSG", "오류발생");
         }

      } else {
         hm.put("MSG", "해당자료가 있습니다");

      }
      return hm;
   }

   public HashMap<String, Object> cstUpdate(CustomerDTO cst) {
      HashMap<String, Object> hm = new HashMap<String, Object>();
      CustomerDTO cstDTO = dao.sltOne(cst.getCode());

      if (cstDTO != null) {
         hm.put("MSG", "해당자료가 있습니다");
      } else {
         int c = dao.insertCst(cst);

         if (c > 0) {
            hm.put("MSG", "정상처리");
         } else {
            hm.put("MSG", "오류발생");
         }

      }

      return hm;
   }

   public HashMap<String, Object> cstDelete(int code) {
      HashMap<String, Object> hm = new HashMap<String, Object>();
      CustomerDTO cstDTO = dao.sltOne(code);

      if (cstDTO == null) {
         hm.put("MSG", "해당 자료가 없습니다");
      } else {
         int c = dao.deleteCst(code);

         if (c > 0) {
            hm.put("MSG", "정상 처리 되었습니다");
         } else {
            hm.put("MSG", "데이터베이스에서 오류가 발생했습니다.");
         }
      }
      return hm;
   }

   public HashMap<String, Object> cstEnd() {
      HashMap<String, Object> hm = new HashMap<String, Object>();
//      dao.conClosed();
      String msg = "종료합니다.";
      hm.put("MSG", msg);
      hm.put("END", "END");
      return hm;
   }
}