package clinic_registration.web;


import clinic_registration.dto.Admin;
import clinic_registration.services.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public String addClient(@RequestBody Admin admin){
        return adminService.create(admin);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Admin admin){
        return adminService.update(id, admin);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return adminService.delete(id);
    }

    @GetMapping("/all")
    public List<Admin> readAll(){
        return adminService.readAll();
    }

    @GetMapping("/{id}")
    public Admin read(@PathVariable("id") Long id){
        return adminService.read(id);
    }

}
