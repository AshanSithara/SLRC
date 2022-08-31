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
import java.util.List;

public interface UserMgtService {

    public UserMgt addUser(UserMgt userMgt);
    @Transactional
    public Employee addUserMgt(Employee employee,UserMgt userMgt);

    @Transactional
    public UserMgt addSponsorUserMgt(Sponsor sponsor, UserMgt userMgt);


    public UserMgt findbyusernameforUserMgt(String username);

    public String usernamebyrole(String username);
    public List<UserMgt> getAlluser();
    public boolean isUserAlreadyPresent(UserMgt userMgt);
    public Employee findByUserName(String username);
    public boolean deleteuser(String username);
}
