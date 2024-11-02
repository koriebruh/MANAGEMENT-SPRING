package koriebruh.dev.management_spring.service;

import koriebruh.dev.management_spring.entity.Admin;
import koriebruh.dev.management_spring.model.LoginRequest;
import koriebruh.dev.management_spring.model.LoginResponse;
import koriebruh.dev.management_spring.model.RegisterRequest;
import koriebruh.dev.management_spring.model.RegisterResponse;
import koriebruh.dev.management_spring.repository.AdminRepository;
import koriebruh.dev.management_spring.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AdminService {

    // debug info
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegisterResponse registerAdmin(RegisterRequest registerRequest) {

        Admin admin = new Admin();
        String encryptPsw = passwordEncoder.encode(registerRequest.getPassword());

        admin.setUsername(registerRequest.getUsername());
        admin.setPassword(encryptPsw);
        admin.setEmail(registerRequest.getEmail());

        adminRepository.save(admin);

        return new RegisterResponse("success! registration new admin");
    }

    @Transactional
    public void deleteAdminById(int id) {
        if (adminRepository.existsById(id)){
            adminRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Admin with id "+ id +" is not found");
        }
    }

    public class LoginFailedException extends RuntimeException {
        public LoginFailedException(String message) {
            super(message);
        }
    }

    public LoginResponse loginAdmin(LoginRequest request) {

        try {

            // Validasi input
            if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
                throw new IllegalArgumentException("Username cannot be empty");
            }
            if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
                throw new IllegalArgumentException("Password cannot be empty");
            }

            System.out.println("INI DARI SERVICE");
            System.out.println("=========================== "+request.getUsername()+" "+request.getPassword());
//             Cari admin berdasarkan username
            Admin admin = adminRepository.findByUsername(request.getUsername().trim())
                    .orElseThrow(() -> new LoginFailedException("Invalid username or password"));


            // Debugging: Lihat nilai username dan password dari admin dan request
            System.out.println("INI DARI SERVICE");
            System.out.println("=========================== " + admin.getUsername() + " " + admin.getPassword());
            System.out.println("=========================== " + request.getUsername() + " " + request.getPassword());


            // eror saat di isini
            if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
                logger.warn("Invalid password attempt for username: {}", request.getUsername());
                throw new LoginFailedException("Invalid username or password");
            }

            // Generate token
            String token = jwtUtil.generateToken(admin.getUsername(), String.valueOf(admin.getId()));

            logger.info("Login successful for user: {}", request.getUsername());
            return new LoginResponse(admin.getId(), token, "Login successful");

        } catch (IllegalArgumentException e) {
            logger.error("Validation error for username {}: {}", request.getUsername(), e.getMessage());
            throw e;
        } catch (LoginFailedException e) {
            logger.error("Authentication error for username {}: {}", request.getUsername(), e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error for username {}: {}", request.getUsername(), e.getMessage());
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }

}
