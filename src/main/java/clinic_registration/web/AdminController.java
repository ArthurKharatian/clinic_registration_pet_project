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
    public ResponseEntity<ServiceMessageDto> addAdmin(@RequestBody Admin admin){
         adminService.create(admin);
         return new ResponseEntity<>(new ServiceMessageDto(777,
                 "Admin is created!"), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<ServiceMessageDto> update(@PathVariable Long id, @RequestBody Admin admin){
        adminService.update(id, admin);
        return new ResponseEntity<>(new ServiceMessageDto(555,
                String.format("Admin with id %d is updated!", id)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceMessageDto> delete(@PathVariable("id") Long id){
          adminService.delete(id);
        return new ResponseEntity<>(new ServiceMessageDto(666,
                String.format("Admin with id %d is deleted!", id)),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Admin>> readAll(){
        return new ResponseEntity<>(adminService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> read(@PathVariable("id") Long id){
        Admin admin = adminService.read(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

}
