package lk.rupavahini.PPUManagement.asset.clibrary.service;
import lk.rupavahini.PPUManagement.asset.clibrary.dao.ClibraryDao;
import lk.rupavahini.PPUManagement.asset.clibrary.entity.Clibrary;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.ProgrammeModel;
import lk.rupavahini.PPUManagement.asset.programme.entity.Programme;
import lk.rupavahini.PPUManagement.asset.programme.service.ProgrammeService;
import lk.rupavahini.PPUManagement.util.interfaces.AbstractService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@CacheConfig( cacheNames = "clibrary" )
public class ClibraryService implements AbstractService<Clibrary, Integer> {
    private final ClibraryDao clibraryDao;
    final Random random = new Random();



    public ClibraryService(ClibraryDao clibraryDao) {
        this.clibraryDao = clibraryDao;
    }


    public List<Clibrary> findAll() {
        return clibraryDao.findAll();
    }

    public Clibrary findById(Integer id) {
        return clibraryDao.getOne(id);
    }

    public Clibrary persist(Clibrary clibrary) {
        return clibraryDao.save(clibrary);
    }




    public boolean delete(Integer id) {
        clibraryDao.deleteById(id);
        return false;
    }

    public List<Clibrary> search(Clibrary clibrary) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Clibrary> ClibraryExample = Example.of(clibrary, matcher);
        return clibraryDao.findAll(ClibraryExample);
    }

    public Clibrary lastClibrary(){
        return clibraryDao.findFirstByOrderByIdDesc();
    }


}