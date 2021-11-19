package practice.DTO;

public class CustomerDTO {
   int code;
   String name, email, tel;
   double weight;
   
   public CustomerDTO() {
      
   }
   
   public CustomerDTO(int code, String name, String email, String tel, double weight) {
      this.code = code;
      this.name = name;
      this.email = email;
      this.tel = tel;
      this.weight = weight;
   }
   
   public int getCode() {
      return code;
   }
   
   public void setCode(int code) {
      this.code = code;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public String getEmail() {
      return email;
   }
   
   public void setEmail(String email) {
      this.email = email;
   }
   
   public String getTel() {
      return tel;
   }
   
   public void setTel(String tel) {
      this.tel = tel;
   }
   
   public double getWeight() {
      return weight;
   }
   
   public void setWeight(double weight) {
      this.weight = weight;
   }
   
}