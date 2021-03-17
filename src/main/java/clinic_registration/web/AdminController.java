package clinic_registration.web;


import clinic_registration.dto.Admin;
import clinic_registration.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Admin> addClient(@RequestBody Admin admin){
         adminService.create(admin);
         return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<Admin> update(@PathVariable Long id, @RequestBody Admin admin){
        adminService.update(id, admin);
        return new ResponseEntity<>(admin, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Admin> delete(@PathVariable("id") Long id){
          adminService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Admin> readAll(){
        return adminService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> read(@PathVariable("id") Long id){
        Admin admin = adminService.read(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

}
