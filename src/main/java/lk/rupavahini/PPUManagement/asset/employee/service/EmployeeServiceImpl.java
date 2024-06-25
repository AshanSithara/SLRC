package lk.rupavahini.PPUManagement.asset.employee.service;


import lk.rupavahini.PPUManagement.asset.commonAsset.model.EmployeeModel;
import lk.rupavahini.PPUManagement.asset.employee.dao.EmployeeDao;
import lk.rupavahini.PPUManagement.asset.employee.dao.EmployeeFilesDao;
import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.employee.entity.EmployeeFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private EmployeeFilesDao employeeFilesDao;
    @Cacheable
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Cacheable
    public Employee findById(Integer id) {
        return employeeDao.getOne(id);
    }

    @Override
    public Employee search(Integer id) {
        Employee no_data_found=null;
        try {
            no_data_found= employeeDao.findById(id).orElseThrow(() -> new Exception("No data Found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no_data_found;
    }

    @Caching(evict = {@CacheEvict(value = "employee", allEntries = true)},
            put = {@CachePut(value = "employee", key = "#employee.id")})
    @Transactional
    public Employee persist(Employee employee) {
        return employeeDao.save(employee);
    }

    /* @CacheEvict(allEntries = true)
     public boolean delete(Integer id) {
         employeeDao.deleteById(id);
         return false;
     }*/
    public boolean delete(Integer id) {
        Employee data_noot_found=null;
        try {
            data_noot_found= employeeDao.findById(id).orElseThrow(()->new Exception("Data noot found"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (data_noot_found==null){
            return false;
        }else{
            employeeFilesDao.deleteEmployeeFile(data_noot_found.getId());
            employeeDao.deleteById(id);
            return true;
        }

    }

    @Cacheable
    public List<Employee> search(Employee employee) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Employee> employeeExample = Example.of(employee, matcher);
        return employeeDao.findAll(employeeExample);
    }

    public boolean isEmployeePresent(Employee employee) {
        return employeeDao.equals(employee);
    }


    public Employee lastEmployee() {
        return employeeDao.findFirstByOrderByIdDesc();
    }

    @Cacheable
    public Employee findByNic(String nic) {
        return employeeDao.findByNic(nic);
    }

    @Override
    public List<EmployeeModel> getemployeesearchdata(int id) {
        return employeeDao.getemployeesearchdata(id);
    }

    @Override
    public String getusernamebyid(int id) {
        return employeeDao.getusernamebyid(id);
    }
}
