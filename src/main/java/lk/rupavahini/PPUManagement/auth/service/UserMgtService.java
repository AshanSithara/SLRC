/*
 *Time   :- 11:56 PM
 *Author :- Uvindu Mohotti
 *Special Thing :-
 */

package lk.rupavahini.PPUManagement.auth.service;

import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.sponsor.entity.Sponsor;
import lk.rupavahini.PPUManagement.auth.entity.UserMgt;

import javax.transaction.Transactional;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 4609734 (Initial commit)
=======
import java.util.List;
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1

public interface UserMgtService {

    public UserMgt addUser(UserMgt userMgt);
    @Transactional
    public Employee addUserMgt(Employee employee,UserMgt userMgt);

    @Transactional
    public UserMgt addSponsorUserMgt(Sponsor sponsor, UserMgt userMgt);


<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    public UserMgt findbyusernameforUserMgt(String username);

    public String usernamebyrole(String username);
    public List<UserMgt> getAlluser();
<<<<<<< HEAD
=======
    public String usernamebyrole(String username);
>>>>>>> 4609734 (Initial commit)
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    public boolean isUserAlreadyPresent(UserMgt userMgt);
    public Employee findByUserName(String username);
    public boolean deleteuser(String username);
}
