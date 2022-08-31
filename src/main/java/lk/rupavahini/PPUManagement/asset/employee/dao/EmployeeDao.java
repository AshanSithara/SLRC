package lk.rupavahini.PPUManagement.asset.employee.dao;



import lk.rupavahini.PPUManagement.asset.commonAsset.model.EmployeeModel;
import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    Employee findFirstByOrderByIdDesc();

    Employee findByNic(String nic);

    Employee findByUsername(String username);

    @Query(value = "select username from employee  where id =:id",
            nativeQuery = true)
    String getusernamebyid(@Param("id") int id);

    //Employee Search Report Query
    @Query(value = "SELECT new lk.rupavahini.PPUManagement.asset.commonAsset.model.EmployeeModel(e.id,e.name,e.nic,e.email,e.title,e.designation) from Employee e,Team t,Member m,MemberTeam mt where e=m.employee and m=mt.member and mt.team=t and t.id=:searchcombo ",
            nativeQuery = false)
    List<EmployeeModel> getemployeesearchdata(
            @Param("searchcombo") int searchcombo);


}

