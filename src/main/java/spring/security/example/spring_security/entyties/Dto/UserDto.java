package spring.security.example.spring_security.entyties.Dto;

public class UserDto {
    
    String username;
    String password;
    boolean admin;
    
    
    
    public UserDto() {
    }
    public UserDto(String username, String password,boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
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

    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    

}
