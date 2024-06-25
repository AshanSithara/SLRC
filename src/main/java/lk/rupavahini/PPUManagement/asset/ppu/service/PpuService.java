package lk.rupavahini.PPUManagement.asset.ppu.service;


<<<<<<< HEAD
<<<<<<< HEAD
import lk.rupavahini.PPUManagement.asset.commonAsset.model.PpuModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.StudioModel;
=======
>>>>>>> 4609734 (Initial commit)
=======
import lk.rupavahini.PPUManagement.asset.commonAsset.model.PpuModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.StudioModel;
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
import lk.rupavahini.PPUManagement.asset.ppu.dao.PpuDao;
import lk.rupavahini.PPUManagement.asset.ppu.entity.Ppu;
import lk.rupavahini.PPUManagement.asset.studio.dao.StudioDao;
import lk.rupavahini.PPUManagement.asset.studio.entity.Studio;
import lk.rupavahini.PPUManagement.util.interfaces.AbstractService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 4609734 (Initial commit)
=======
import java.util.ArrayList;
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
import java.util.List;

@Service
@CacheConfig( cacheNames = "ppu" )
public class PpuService implements AbstractService<Ppu, Integer> {
    private final PpuDao ppuDao;

    public PpuService(PpuDao ppuDao) {
        this.ppuDao = ppuDao;
    }


    public List<Ppu> findAll() {
        return ppuDao.findAll();
    }

    public Ppu findById(Integer id) {
        return ppuDao.getOne(id);
    }



    public Ppu persist(Ppu ppu) {
        return ppuDao.save(ppu);
    }

    public boolean delete(Integer id) {
        ppuDao.deleteById(id);
        return false;
    }


<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1
    public List<PpuModel> getallPpu(){
        List<Ppu> all = ppuDao.findAll();
        List<PpuModel> ppuModels=new ArrayList<>();
        for (Ppu ppu:all){
            ppuModels.add(new PpuModel(ppu.getId(),ppu.getPPUname()));
        }
        return ppuModels;
    }

<<<<<<< HEAD
=======
>>>>>>> 4609734 (Initial commit)
=======
>>>>>>> 7335958cefe530feb0545b663d077ba7fef2d0b1

    public List<Ppu> search(Ppu ppu) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Ppu> SponserExample = Example.of(ppu, matcher);
        return ppuDao.findAll(SponserExample);
    }

    public Ppu lastPpu(){
        return ppuDao.findFirstByOrderByIdDesc();
    }
}
