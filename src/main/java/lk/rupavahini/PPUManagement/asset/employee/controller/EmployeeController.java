package lk.rupavahini.PPUManagement.asset.employee.controller;

import lk.rupavahini.PPUManagement.asset.commonAsset.model.EmployeeModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.Enum.CivilStatus;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.Enum.Gender;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.Enum.Title;
import lk.rupavahini.PPUManagement.asset.commonAsset.service.CommonService;
import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.employee.entity.EmployeeFiles;
import lk.rupavahini.PPUManagement.asset.employee.entity.Enum.Designation;
import lk.rupavahini.PPUManagement.asset.employee.entity.Enum.EmployeeStatus;
import lk.rupavahini.PPUManagement.asset.employee.service.EmployeeFilesService;
import lk.rupavahini.PPUManagement.asset.employee.service.EmployeeService;
import lk.rupavahini.PPUManagement.asset.userManagement.entity.User;
import lk.rupavahini.PPUManagement.asset.userManagement.service.UserService;
import lk.rupavahini.PPUManagement.auth.entity.UserMgt;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import lk.rupavahini.PPUManagement.util.service.DateTimeAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/employee")
@Controller
public class EmployeeController {
    @Autowired
    private UserMgtService userMgtService;

    private final EmployeeService employeeService;
    private final EmployeeFilesService employeeFilesService;
    private final DateTimeAgeService dateTimeAgeService;
    private final CommonService commonService;
    private final UserService userService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeFilesService employeeFilesService,
                              DateTimeAgeService dateTimeAgeService, CommonService commonService,
                              UserService userService) {
        this.employeeService = employeeService;
        this.employeeFilesService = employeeFilesService;

        this.dateTimeAgeService = dateTimeAgeService;
        this.commonService = commonService;
        this.userService = userService;
    }
//----> Employee details management - start <----//

    // Common things for an employee add and update
    private String commonThings(Model model) {
        model.addAttribute("title", Title.values());
        model.addAttribute("gender", Gender.values());
        model.addAttribute("designation", Designation.values());
        model.addAttribute("civilStatus", CivilStatus.values());
        model.addAttribute("employeeStatus", EmployeeStatus.values());


        return "employee/addEmployee";
    }

    //When scr called file will send to
    @GetMapping("/file/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("filename") String filename) {
        EmployeeFiles file = employeeFilesService.findByNewID(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getPic());
    }

    //Send all employee data
    @RequestMapping
    public String employeePage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("role", userMgtService.usernamebyrole(username));
        model.addAttribute("username", username);
<<<<<<< HEAD
=======
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("role",userMgtService.usernamebyrole(username));
        model.addAttribute("username",username);
>>>>>>> 4609734 (Initial commit)
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        model.addAttribute("employees", employeeService.findAll());
        return "employee/employee";
    }

    //Send on employee details
    @GetMapping(value = "/{id}")
    public String employeeView(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employeeDetail", employee);
        model.addAttribute("addStatus", false);
        model.addAttribute("files", employeeFilesService.employeeFileDownloadLinks(employee));
        return "employee/employee-detail";
    }

    //Send employee data edit
    @GetMapping(value = "/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("addStatus", false);
        model.addAttribute("files", employeeFilesService.employeeFileDownloadLinks(employee));

        return commonThings(model);
    }

    //Send an employee add form
    @GetMapping(value = {"/add"})
    public String employeeAddForm(Model model) {
        model.addAttribute("addStatus", true);
        model.addAttribute("employee", new Employee());


        return commonThings(model);
    }

    //Employee add and update
    @PostMapping(value = {"/save", "/update"})
    public String addEmployee(@Valid @ModelAttribute Employee employee, BindingResult result, Model model
    ) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
<<<<<<< HEAD
=======
        String username=null;
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
>>>>>>> 4609734 (Initial commit)
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        }

        if (result.hasErrors()) {
            model.addAttribute("addStatus", true);
            model.addAttribute("employee", employee);
            return commonThings(model);
        }
        try {
//            employee.setMobileOne(commonService.commonMobileNumberLengthValidator(employee.getMobileOne()));
            employee.setCreatedAt(LocalDateTime.now());
            employee.setUpdatedAt(LocalDateTime.now());
            employee.setUpdatedBy(username);
            employee.setCreatedBy(username);
            //after save employee files and save employee
//            Employee employee1 = employeeService.search(employee.getId());
//            Employee persist = employeeService.persist(employee);
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
            Employee employee2 = null;
            if (employee.getEmployeeStatus().getEmployeeStatus().equalsIgnoreCase("Working")) {
                employee2 = userMgtService.addUserMgt(employee, new UserMgt(employee.getUsername(), employee.getPassword(), employee.getDesignation().getDesignation(), "1", employee.getMobileOne()));
            } else {
                employee2 = userMgtService.addUserMgt(employee, new UserMgt(employee.getUsername(), employee.getPassword(), employee.getDesignation().getDesignation(), "0", employee.getMobileOne()));
<<<<<<< HEAD
=======
            Employee employee2=null;
            if (employee.getEmployeeStatus().getEmployeeStatus().equalsIgnoreCase("Working")){
                employee2= userMgtService.addUserMgt(employee, new UserMgt(employee.getUsername(), employee.getPassword(), employee.getDesignation().getDesignation(), "1"));
            }else{
                employee2=userMgtService.addUserMgt(employee,new UserMgt(employee.getUsername(),employee.getPassword(),employee.getDesignation().getDesignation(),"0"));
>>>>>>> 4609734 (Initial commit)
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
            }
            //if employee state is not working he or she cannot access to the system
//            if (!employee.getEmployeeStatus().equals(EmployeeStatus.WORKING)) {
//                User user = userService.findUserByEmployee(employeeService.findByNic(employee.getNic()));
//                //if employee not a user
//                if (user != null) {
//                    user.setEnabled(false);
//                    userService.persist(user);
//                }
//            }
            //save employee img file

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
            if (employee.getFile().getOriginalFilename() != null) {
                EmployeeFiles employeeFiles = employeeFilesService.findByName(employee.getFile().getOriginalFilename());
                if (employeeFiles != null) {
                    // update new contents
                    employeeFiles.setPic(employee.getFile().getBytes());
                    // Save all to database
                } else {
                    employeeFiles = new EmployeeFiles(employee.getFile().getOriginalFilename() + UUID.randomUUID().toString(),
                            employee.getFile().getContentType(),
                            employee.getFile().getBytes(),
                            employee.getNic().concat("-" + LocalDateTime.now()),
                            UUID.randomUUID().toString().concat("employee"));
                    employeeFiles.setEmployee(employee2);
                }
                employeeFiles.setCreatedAt(LocalDateTime.now());
                employeeFiles.setUpdatedAt(LocalDateTime.now());
                employeeFiles.setUpdatedBy(username);
                employeeFiles.setCreatedBy(username);
                employeeFilesService.persist(employeeFiles);
            }
<<<<<<< HEAD
=======
                if (employee.getFile().getOriginalFilename() != null) {
                    EmployeeFiles employeeFiles = employeeFilesService.findByName(employee.getFile().getOriginalFilename());
                    if (employeeFiles != null) {
                        // update new contents
                        employeeFiles.setPic(employee.getFile().getBytes());
                        // Save all to database
                    } else {
                        employeeFiles = new EmployeeFiles(employee.getFile().getOriginalFilename()+UUID.randomUUID().toString(),
                                employee.getFile().getContentType(),
                                employee.getFile().getBytes(),
                                employee.getNic().concat("-" + LocalDateTime.now()),
                                UUID.randomUUID().toString().concat("employee"));
                        employeeFiles.setEmployee(employee2);
                    }
                    employeeFiles.setCreatedAt(LocalDateTime.now());
                    employeeFiles.setUpdatedAt(LocalDateTime.now());
                    employeeFiles.setUpdatedBy(username);
                    employeeFiles.setCreatedBy(username);
                    employeeFilesService.persist(employeeFiles);
                }
>>>>>>> 4609734 (Initial commit)
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1

            return "redirect:/employee";

        } catch (Exception e) {
            ObjectError error = new ObjectError("employee",
                    "There is already in the system. <br>System message -->" + e.toString());
            result.addError(error);
            model.addAttribute("addStatus", true);
            model.addAttribute("employee", employee);


            return commonThings(model);
        }
    }

    //If need to employee {but not applicable for this }
  /*  @GetMapping(value = "/remove/{id}")
    public String removeEmployee(@PathVariable Integer id) {
        employeeService.delete(id);
        return "redirect:/employee";
    }*/


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        String getusernamebyid = employeeService.getusernamebyid(id);
        boolean deleteuser = userMgtService.deleteuser(getusernamebyid);
<<<<<<< HEAD
<<<<<<< HEAD
        if (deleteuser) {
=======
        if (deleteuser){
>>>>>>> 4609734 (Initial commit)
=======
        if (deleteuser) {
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
            employeeService.delete(id);
        }
        return "redirect:/employee";
    }

    //To search employee any giving employee parameter
    @GetMapping(value = "/search")
    public String search(Model model, Employee employee) {
        model.addAttribute("employeeDetail", employeeService.search(employee));
        return "employee/employee-detail";
    }

    @GetMapping(value = "/all")
    public ResponseEntity employeeall() {
        List<Employee> all = employeeService.findAll();
<<<<<<< HEAD
<<<<<<< HEAD
        List<EmployeeModel> employeeModels = new ArrayList<>();
        for (Employee employee : all) {
            employeeModels.add(new EmployeeModel(employee.getId(), employee.getName()));
=======
        List<EmployeeModel> employeeModels=new ArrayList<>();
        for (Employee employee:all){
            employeeModels.add(new EmployeeModel(employee.getId(),employee.getName()));
>>>>>>> 4609734 (Initial commit)
=======
        List<EmployeeModel> employeeModels = new ArrayList<>();
        for (Employee employee : all) {
            employeeModels.add(new EmployeeModel(employee.getId(), employee.getName()));
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
        }
        return new ResponseEntity(employeeModels, HttpStatus.OK);
    }


<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1





<<<<<<< HEAD
=======
>>>>>>> 4609734 (Initial commit)
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
//----> Employee details management - end <----//
    //````````````````````````````````````````````````````````````````````````````//
//----> EmployeeWorkingPlace - details management - start <----//


<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 4609734 (Initial commit)
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    //Send a searched employee to add working place
/*
    @PostMapping( value = "/workingPlace" )
    public String addWorkingPlaceEmployeeDetails(@ModelAttribute( "employee" ) Employee employee, Model model) {

        List< Employee > employees = employeeService.search(employee);
        if ( employees.size() == 1 ) {
            model.addAttribute("employeeDetailShow", true);
            model.addAttribute("employeeNotFoundShow", false);
            model.addAttribute("employeeDetail", employees.get(0));
            model.addAttribute("files", employeeFilesService.employeeFileDownloadLinks(employee).get(0));
            model.addAttribute("employeeWorkingPlaceHistoryObject", new EmployeeWorkingPlaceHistory());
            model.addAttribute("workingPlaceChangeReason", WorkingPlaceChangeReason.values());
            model.addAttribute("province", Province.values());
            model.addAttribute("districtUrl", MvcUriComponentsBuilder
                    .fromMethodName(WorkingPlaceRestController.class, "getDistrict", "")
                    .build()
                    .toString());
            model.addAttribute("stationUrl", MvcUriComponentsBuilder
                    .fromMethodName(WorkingPlaceRestController.class, "getStation", "")
                    .build()
                    .toString());
            return "employeeWorkingPlace/addEmployeeWorkingPlace";
        }
        model.addAttribute("employee", new Employee());
        model.addAttribute("employeeDetailShow", false);
        model.addAttribute("employeeNotFoundShow", true);
        model.addAttribute("employeeNotFound", "There is not employee in the system according to the provided details" +
                " \n Could you please search again !!");

        return "employeeWorkingPlace/addEmployeeWorkingPlace";
    }

    @PostMapping( value = "/workingPlace/add" )
    public String addWorkingPlaceEmployee(@ModelAttribute( "employeeWorkingPlaceHistory" ) EmployeeWorkingPlaceHistory employeeWorkingPlaceHistory, Model model) {
        System.out.println(employeeWorkingPlaceHistory.toString());
        // -> need to write validation before the save working place
        //before saving set employee current working palace
        WorkingPlace workingPlace = employeeWorkingPlaceHistory.getWorkingPlace();

        employeeWorkingPlaceHistory.setWorkingPlace(employeeWorkingPlaceHistory.getEmployee().getWorkingPlace());
        employeeWorkingPlaceHistory.getEmployee().setWorkingPlace(workingPlace);

        employeeWorkingPlaceHistory.setWorkingDuration(dateTimeAgeService.dateDifference(employeeWorkingPlaceHistory.getFrom_place(), employeeWorkingPlaceHistory.getTo_place()));
        employeeWorkingPlaceHistoryService.persist(employeeWorkingPlaceHistory);
        return "redirect:/employee";
    }
*/

//----> EmployeeWorkingPlace - details management - end <----//

}
/*
 try {
            List< FileModel > storedFile = new ArrayList< FileModel >();

            for ( MultipartFile file : files ) {
                FileModel fileModel = fileRepository.findByName(file.getOriginalFilename());
                if ( fileModel != null ) {
                    // update new contents
                    fileModel.setPic(file.getBytes());
                } else {
                    fileModel = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
                }

                fileNames.add(file.getOriginalFilename());
                storedFile.add(fileModel);
            }

            // Save all Files to database
            fileRepository.saveAll(storedFile);

            model.addAttribute("message", "Files uploaded successfully!");
            model.addAttribute("files", fileNames);
        } catch ( Exception e ) {
            model.addAttribute("message", "Fail!");
            model.addAttribute("files", fileNames);
        }

* */

/*
 public String addEmployee(@Valid @ModelAttribute Employee employee, BindingResult result, Model model,
 RedirectAttributes redirectAttributes) {

        * String newEmployeeNumber = "";
        String input;
        if (employeeService.lastEmployee() != null) {
            input = employeeService.lastEmployee().getNumber();
            int employeeNumber = Integer.valueOf(input.replaceAll("[^0-9]+", "")).intValue() + 1;

            if ((employeeNumber < 10) && (employeeNumber > 0)) {
                newEmployeeNumber = "KL000" + employeeNumber;
            }
            if ((employeeNumber < 100) && (employeeNumber > 10)) {
                newEmployeeNumber = "KL00" + employeeNumber;
            }
            if ((employeeNumber < 1000) && (employeeNumber > 100)) {
                newEmployeeNumber = "KL0" + employeeNumber;
            }
            if (employeeNumber > 10000) {
                newEmployeeNumber = "KL" + employeeNumber;
            }
        } else {
            newEmployeeNumber = "KL0001";
            input = "KL0000";
        }


        if (dateTimeAgeService.getAge(employee.getDateOfBirth()) < 18) {
            ObjectError error = new ObjectError("dateOfBirth", "Employee must be 18 old ");
            result.addError(error);
        }
        if (result.hasErrors()) {
                System.out.println("i m here");
                model.addAttribute("addStatus", true);
            if (employeeService.lastEmployee() != null) {
                model.addAttribute("lastEmployee", employeeService.lastEmployee().getPayRoleNumber());
            }


                model.addAttribute("addStatus", true);
                CommonThings(model);
                redirectAttributes.addFlashAttribute("employee", employee);
                redirectAttributes.addFlashAttribute("files", employee.getFiles());
                return "employee/addEmployee";
                }

      if (employeeService.isEmployeePresent(employee)) {
            System.out.println("already on ");
            User user = userService.findById(userService.findByEmployeeId(employee.getId()));
            if (employee.getEmployeeStatus() != EmployeeStatus.WORKING) {
                user.setEnabled(false);
                employeeService.persist(employee);
            }
            System.out.println("update working");
            user.setEnabled(true);
            employeeService.persist(employee);
            return "redirect:/employee";
        }
        if (employee.getId() != null) {
            redirectAttributes.addFlashAttribute("message", "Successfully Add but Email was not sent.");
            redirectAttributes.addFlashAttribute("alertStatus", false);

            employeeService.persist(employee);
        }


        System.out.println("save no id");
                System.out.println("Employee come "+employee.toString());
                //employeeService.persist(employee);
                return "redirect:/employee";
                }

 */
