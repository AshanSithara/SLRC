package lk.rupavahini.PPUManagement;


import lk.rupavahini.PPUManagement.asset.commonAsset.model.Enum.CivilStatus;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.Enum.Gender;

import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.employee.entity.Enum.Designation;
import lk.rupavahini.PPUManagement.asset.employee.entity.Enum.EmployeeStatus;
import lk.rupavahini.PPUManagement.asset.employee.service.EmployeeService;


import lk.rupavahini.PPUManagement.auth.entity.UserMgt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
public class ApplicationCreateRestController {

    private final EmployeeService employeeService;


    @Autowired
    public ApplicationCreateRestController(
                                           EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping( "/select/user" )
    public String saveUser() {
        //roles list start
//        String[] roles = {"ADMIN"};
//        for ( String s : roles ) {
//            RoleMgt role = new RoleMgt();
//            role.setDesc(s);
//            roleService.persist(role);
//        }

//Employee
        Employee employee = new Employee();

        employee.setName("Admin User");
        employee.setName("908670000V");
        employee.setMobileOne("0750000000");
        employee.setGender(Gender.MALE);
        employee.setDesignation(Designation.PRODUCER);
        employee.setCivilStatus(CivilStatus.UNMARRIED);
        employee.setEmployeeStatus(EmployeeStatus.WORKING);
        employee.setDateOfBirth(LocalDate.now().minusYears(18));
        Employee employeeDb = employeeService.persist(employee);


        //admin user one
        UserMgt user = new UserMgt();
//        user.setEmployee(employeeDb);
        user.setUsername("admin");
        /*user.setUsername("user");*/
        user.setPassword("admin");
        /*user.setPassword("1234");*/
        String message = "Username:- " + user.getUsername() + "\n Password:- " + user.getPassword();
//        user.setEnabled(true);
//        user.setRoles(roleService.findAll()
//                              .stream()
//                              .filter(role -> role.getRoleName().equals("ADMIN"))
//                              .collect(Collectors.toList()));
//        userService.persist(user);

        return message;


    }


}
