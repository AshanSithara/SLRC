


package lk.rupavahini.PPUManagement.asset.employee.service;


import lk.rupavahini.PPUManagement.asset.commonAsset.model.FileInfo;
import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.employee.entity.EmployeeFiles;
import org.springframework.cache.annotation.Cacheable;
import java.util.*;


//@CacheConfig(cacheNames = "employeeFiles")
public interface EmployeeFilesService {



    public EmployeeFiles findByName(String filename);

    public void persist(EmployeeFiles storedFile);


    public List<EmployeeFiles> search(EmployeeFiles employeeFiles) ;

    public EmployeeFiles findById(Integer id);

    public EmployeeFiles findByNewID(String filename);

    @Cacheable
    public FileInfo employeeFileDownloadLinks(Employee employee) ;
}
