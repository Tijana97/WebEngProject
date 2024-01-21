package ba.edu.ibu.webengineeringproject.core.service;

import ba.edu.ibu.webengineeringproject.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.webengineeringproject.core.model.User;
import ba.edu.ibu.webengineeringproject.core.repository.UserRepository;
import ba.edu.ibu.webengineeringproject.rest.dto.LoginDTO;
import ba.edu.ibu.webengineeringproject.rest.dto.LoginRequestDTO;
import ba.edu.ibu.webengineeringproject.rest.dto.UserDTO;
import ba.edu.ibu.webengineeringproject.rest.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO signUp(UserRequestDTO userRequestDTO) {
        userRequestDTO.setPassword(
                passwordEncoder.encode(userRequestDTO.getPassword())
        );
        User user = userRepository.save(userRequestDTO.toEntity());

        return new UserDTO(user);
    }

    public UserDTO getMe(String header) {
        String userEmail = jwtService.extractUserName(header);
        System.out.println("User Email: " + userEmail);

        User user = userRepository.findByUsernameOrEmailAddress(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("This user does not exist."));

        return new UserDTO(user);
    }

    public LoginDTO signIn(LoginRequestDTO loginRequestDTO) {
        System.out.println(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
        );
        System.out.println("EMail " + loginRequestDTO.getEmail());
        System.out.println("PW " + loginRequestDTO.getPassword());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
            );
        } catch (BadCredentialsException e) {
            // Log or print exception details
            e.printStackTrace();
            throw e; // Rethrow the exception if needed
        }
        System.out.println("Authentication successful for: " + loginRequestDTO.getEmail());

        User user = userRepository.findFirstByEmailAddress(loginRequestDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("This user does not exist."));
        String jwt = jwtService.generateToken(user);

        return new LoginDTO(jwt, new UserDTO(user));
    }

}