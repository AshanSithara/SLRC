package lk.rupavahini.PPUManagement.asset.userManagement.dao;

import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.userManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao  {

//    @Query( value = "select id from User where employee_id=?1", nativeQuery = true )
//    Integer findByEmployeeId(@Param("employee_id") Integer id);
//
//    @Query( "select id from UserMgt where username=?1" )
//    Integer findUserIdByUserName(String userName);
//
//    User findByUsername(String name);
//
//    User findByEmployee(Employee employee);


}
