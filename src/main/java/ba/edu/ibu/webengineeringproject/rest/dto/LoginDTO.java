package ba.edu.ibu.webengineeringproject.rest.dto;

public class LoginDTO {
    private String jwt;

    private UserDTO userDTO;

    public LoginDTO(String jwt, UserDTO userDTO) {
        this.jwt = jwt;
        this.userDTO = userDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}