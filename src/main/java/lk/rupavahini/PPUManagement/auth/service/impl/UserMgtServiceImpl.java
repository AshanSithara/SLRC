/*
 *Time   :- 11:56 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.auth.service.impl;

import lk.rupavahini.PPUManagement.asset.employee.dao.EmployeeDao;
import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.employee.service.EmployeeService;
import lk.rupavahini.PPUManagement.asset.sponsor.entity.Sponsor;
import lk.rupavahini.PPUManagement.asset.sponsor.service.SponsorService;
import lk.rupavahini.PPUManagement.auth.entity.RoleMgt;
import lk.rupavahini.PPUManagement.auth.entity.UserMgt;
import lk.rupavahini.PPUManagement.auth.repo.RoleMgtRepo;
import lk.rupavahini.PPUManagement.auth.repo.UserMgtRepo;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import lk.rupavahini.PPUManagement.email.model.EmailModel;
import lk.rupavahini.PPUManagement.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 4609734 (Initial commit)

@Service
public class UserMgtServiceImpl implements UserMgtService {

    @Autowired
    private UserMgtRepo userMgtRepo;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    private RoleMgtRepo roleRepository;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmailService emailService;

    @Override
    public UserMgt addUser(UserMgt userMgt) {
        userMgt.setStatus("1");
        userMgt.setPassword(encoder.encode(userMgt.getPassword()));
        RoleMgt userRole = roleRepository.findRoleMgtsByDesc(userMgt.getType());
        userMgt.setRoles(new HashSet<RoleMgt>(Arrays.asList(userRole)));
        UserMgt save = userMgtRepo.save(userMgt);
//        roleRepository.insertauth_user_role(save.getAdminID(), userRole.getId());
        return userMgtRepo.save(userMgt);
    }

    @Override
    public Employee addUserMgt(Employee employee, UserMgt userMgt) {
        UserMgt byUsername=null;
        Employee employee1=null;
        if (employee.getId()!=null){
            employee1= employeeService.search(employee.getId());
        }

        if (employee1 != null) {
            byUsername= userMgtRepo.findByUsername(employee1.getUsername());
        }
        Employee save1 = employeeDao.save(employee);
        if (byUsername == null) {
            userMgt.setPassword(encoder.encode(userMgt.getPassword()));
            RoleMgt userRole = roleRepository.findRoleMgtsByDesc(userMgt.getType());
            userMgt.setRoles(new HashSet<RoleMgt>(Arrays.asList(userRole)));
            UserMgt save = userMgtRepo.save(userMgt);
//                roleRepository.insertauth_user_role(save.getAdminID(), userRole.getId());
             userMgtRepo.save(userMgt);
            emailService.sendEmployeeCreateEmail(new EmailModel(employee.getEmail(),employee.getName(),"","",employee.getMobileOne()));
        } else {
            userMgt.setPassword(encoder.encode(userMgt.getPassword()));
            RoleMgt userRole = roleRepository.findRoleMgtsByDesc(userMgt.getType());
            userMgt.setRoles(new HashSet<RoleMgt>(Arrays.asList(userRole)));
//        roleRepository.insertauth_user_role(save.getAdminID(), userRole.getId());
            userMgt.setAdminID(byUsername.getAdminID());
             userMgtRepo.save(userMgt);
        }
        return save1;

    }

    @Override
    public UserMgt addSponsorUserMgt(Sponsor sponsor, UserMgt userMgt) {
        Sponsor sponsor1 = sponsorService.getoneDetails(sponsor.getId());
        UserMgt byUsername = null;
        if (sponsor1 != null) {
            byUsername = userMgtRepo.findByUsername(sponsor1.getUsername());
        }
        sponsorService.persist(sponsor);
        if (byUsername == null) {
            userMgt.setPassword(encoder.encode(userMgt.getPassword()));
            RoleMgt userRole = roleRepository.findRoleMgtsByDesc(userMgt.getType());
            userMgt.setRoles(new HashSet<RoleMgt>(Arrays.asList(userRole)));
            UserMgt save = userMgtRepo.save(userMgt);
//                roleRepository.insertauth_user_role(save.getAdminID(), userRole.getId());
            emailService.sendSponserCreateEmail(new EmailModel(sponsor.getEmail(),sponsor.getName(),"","",sponsor.getContactOne()));
            return userMgtRepo.save(userMgt);
        } else {
            userMgt.setPassword(encoder.encode(userMgt.getPassword()));
            RoleMgt userRole = roleRepository.findRoleMgtsByDesc(userMgt.getType());
            userMgt.setRoles(new HashSet<RoleMgt>(Arrays.asList(userRole)));
//        roleRepository.insertauth_user_role(save.getAdminID(), userRole.getId());
            userMgt.setAdminID(byUsername.getAdminID());

            return userMgtRepo.save(userMgt);
        }
    }

    @Override
<<<<<<< HEAD
    public UserMgt findbyusernameforUserMgt(String username){
        return userMgtRepo.findUserMgtsByUsername(username);
    }
    @Override
=======
>>>>>>> 4609734 (Initial commit)
    public String usernamebyrole(String username) {
        UserMgt userMgtsByUsername = userMgtRepo.findUserMgtsByUsername(username);
        if (userMgtsByUsername==null){
            return null;
        }else{
            return userMgtsByUsername.getType();
        }
    }

    @Override
<<<<<<< HEAD
    public List<UserMgt> getAlluser() {
        return userMgtRepo.findAll();
    }

    @Override
=======
>>>>>>> 4609734 (Initial commit)
    public boolean isUserAlreadyPresent(UserMgt userMgt) {
        boolean isUserAlreadyExists = false;
        UserMgt byUsername = userMgtRepo.findUserMgtsByUsername(userMgt.getUsername());
        // If user is found in database, then then user already exists.
        if (byUsername != null) {
            isUserAlreadyExists = true;
        }
        return isUserAlreadyExists;
    }

    @Override
    public Employee findByUserName(String username) {
        return employeeDao.findByUsername(username);
    }

    @Override
    public boolean deleteuser(String username) {
        UserMgt mgtRepoByUsername = userMgtRepo.findByUsername(username);
        if (mgtRepoByUsername == null) {
            return false;
        } else {
            userMgtRepo.deleteSystemUser(mgtRepoByUsername.getAdminID());
            roleRepository.deleteauthuserrole(mgtRepoByUsername.getAdminID());
            return true;
        }
    }
}
