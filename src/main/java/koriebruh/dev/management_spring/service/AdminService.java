package koriebruh.dev.management_spring.service;

import koriebruh.dev.management_spring.entity.Admin;
import koriebruh.dev.management_spring.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Transactional
    public void deleteAdminById(int id) {
        if (adminRepository.existsById(id)){
            adminRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Admin with id "+ id +" is not found");
        }
    }

}
