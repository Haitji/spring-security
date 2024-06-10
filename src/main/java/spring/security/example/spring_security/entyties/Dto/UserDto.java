package spring.security.example.spring_security.entyties.Dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {
    
    @NotBlank(message="This field couldn't be null or blank")
    String username;
    @NotBlank(message="This field couldn't be null or blank")
    String password;

    Boolean admin=false;
    
    
    
    public UserDto() {
    }
    public UserDto(String username, String password,Boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin != null ? admin : false;;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isAdmin() {
        return admin;
    }
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    

}
