package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequestDTO {

    private String account;
    private String email;
    private String address;
    private String password;

    @JsonProperty("phone_number")
    private String phoneNumber;
    // 코드상에서 camel case로 지정하였는데 json에서는 snake case로
    // key값이 와서 null이라는 결과가 나왔다.
    // 해결법 중 하나는 @JsonProperty을 이용해 다른 이름도 허용되도록 하는 것이다.
    // OTP와 같이 camel도 아니고 snake도 아닌 애매한 것들은 JsonProperty("OTP")와
    // 같은 것을 붙여 matching을 시켜준다.

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "PostRequestDTO{" +
                "account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
