package lk.rupavahini.PPUManagement.asset.member.service;

import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.member.dao.MemberDao;
import lk.rupavahini.PPUManagement.asset.member.entity.Member;
import lk.rupavahini.PPUManagement.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig( cacheNames = {"member"} ) // tells Spring where to store cache for this class
public class MemberService implements AbstractService<Member, Integer > {
    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(PasswordEncoder passwordEncoder, MemberDao memberDao) {
        this.passwordEncoder = passwordEncoder;
        this.memberDao = memberDao;
    }

    @Cacheable
    public List<Member> findAll() {
        return memberDao.findAll();
    }

    @Cacheable
    @Transactional
    public Member findById(Integer id) {
        try {
            return memberDao.findById(id).orElseThrow(()->new Exception("Data not Found"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Caching( evict = {@CacheEvict( value = "member", allEntries = true )},
            put = {@CachePut( value = "member", key = "#member.id" )} )

    @Transactional
    public Member persist(Member member) {
        member.setMemberName(member.getMemberName().toLowerCase());
        return memberDao.save(member);
    }

    @CacheEvict( allEntries = true )
    public boolean delete(Integer id) {
        //according to this project can not be deleted member
        memberDao.deleteById(id);
        return false;
    }


    @Cacheable
    public List< Member > search(Member member) {
        ExampleMatcher matcher =
                ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example< Member > memberExample = Example.of(member, matcher);
        return memberDao.findAll(memberExample);
    }

    @Cacheable
    public Integer findByMemberIdByMemberName(String memberName) {
        return memberDao.findMemberIdByMemberName(memberName);
    }

    @Cacheable
    @Transactional( readOnly = true )
    public Member findByMemberName(String name) {
        return memberDao.findByMemberName(name);
    }

    @Cacheable
    public Member findMemberByEmployee(Employee employee) {
        return memberDao.findByEmployee(employee);
    }

    @Cacheable
    public boolean findByEmployee(Employee employee) {
        return memberDao.findByEmployee(employee) == null;
    }



}