




package lk.rupavahini.PPUManagement.asset.employee.service;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.EmployeeModel;
import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import org.springframework.cache.annotation.*;
import java.util.*;

import javax.transaction.Transactional;

// spring transactional annotation need to tell spring to this method work through the project
//@CacheConfig(cacheNames = "employee")
public interface EmployeeService  {

    @Cacheable
    public List<Employee> findAll();

    @Cacheable
    public Employee findById(Integer id);

    @Caching(evict = {@CacheEvict(value = "employee", allEntries = true)},
            put = {@CachePut(value = "employee", key = "#employee.id")})
    @Transactional
    public Employee persist(Employee employee);

    public Employee search(Integer id);

   /* @CacheEvict(allEntries = true)
    public boolean delete(Integer id) {
        employeeDao.deleteById(id);
        return false;
    }*/
   public boolean delete(Integer id);

    @Cacheable
    public List<Employee> search(Employee employee) ;

    public boolean isEmployeePresent(Employee employee);


    public Employee lastEmployee();

    @Cacheable
    public Employee findByNic(String nic);

    public List<EmployeeModel> getemployeesearchdata(int id);

    public String getusernamebyid(int id);
}
