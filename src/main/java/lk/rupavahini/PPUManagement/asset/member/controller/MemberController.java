package lk.rupavahini.PPUManagement.asset.member.controller;

import lk.rupavahini.PPUManagement.asset.commonAsset.model.MemberModel;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.TempGetIDModel;
import lk.rupavahini.PPUManagement.asset.employee.entity.Employee;
import lk.rupavahini.PPUManagement.asset.employee.entity.Enum.Designation;
import lk.rupavahini.PPUManagement.asset.employee.entity.Enum.EmployeeStatus;
import lk.rupavahini.PPUManagement.asset.employee.entity.MemberTeam;
import lk.rupavahini.PPUManagement.asset.employee.service.EmployeeService;
import lk.rupavahini.PPUManagement.asset.member.dao.MemberDao;
import lk.rupavahini.PPUManagement.asset.member.dao.MemberTeamDAO;
import lk.rupavahini.PPUManagement.asset.member.entity.Member;
import lk.rupavahini.PPUManagement.asset.member.service.MemberService;
import lk.rupavahini.PPUManagement.asset.team.entity.Team;
import lk.rupavahini.PPUManagement.asset.team.service.TeamService;
import lk.rupavahini.PPUManagement.auth.service.UserMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final TeamService teamService;
    private final EmployeeService employeeService;

    @Autowired
    private MemberTeamDAO memberTeamDAO;
    @Autowired
    private MemberDao memberDao;

    @Autowired
    private UserMgtService userMgtService;


    @Autowired
    public MemberController(MemberService memberService, EmployeeService employeeService, TeamService teamService
    ) {
        this.memberService = memberService;
        this.employeeService = employeeService;
        this.teamService = teamService;
    }

    @GetMapping
    public String memberPage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("role",userMgtService.usernamebyrole(username));

        model.addAttribute("member", memberService.findAll());
        return "member/member";
    }

    @GetMapping(value = "/{id}")
    public String memberView(@PathVariable("id") Integer id, Model model) {
        Member byId = memberService.findById(id);
        model.addAttribute("memberDetail",byId );
        return "member/member-detail";
    }

    private String commonCode(Model model) {
        model.addAttribute("employeeDetailShow", true);
        model.addAttribute("employeeNotFoundShow", false);
        model.addAttribute("teamList", teamService.findAll());
        /*model.addAttribute("districtUrl", MvcUriComponentsBuilder
                .fromMethodName(WorkingPlaceRestController.class, "getDistrict", "")
                .build()
                .toString());
        model.addAttribute("stationUrl", MvcUriComponentsBuilder
                .fromMethodName(WorkingPlaceRestController.class, "getStation", "")
                .build()
                .toString());*/
        return "member/addMember";
    }

    @GetMapping(value = "/edit/{id}")
    public String editMemberFrom(@PathVariable("id") Integer id, Model model) {
        Member byId = memberService.findById(id);
        MemberTeam memberTeamsByMember = memberTeamDAO.findMemberTeamsByMember(byId);
        List<MemberTeam> memberTeams = new ArrayList<>();
        memberTeams.add(memberTeamsByMember);
        MemberModel memberModel = new
                MemberModel(byId.getId(), byId.getMemberName(), byId.getEmployee(), null, memberTeams);

        model.addAttribute("membermodel", memberModel);
        model.addAttribute("addStatus", false);
        return commonCode(model);
    }

    @GetMapping(value = "/add")
    public String memberAddFrom(Model model) {
        model.addAttribute("addStatus", true);
        model.addAttribute("employeeDetailShow", false);
        model.addAttribute("employee", new Employee());
        return "member/addMember";
    }

    //Send a searched employee to add working place
    @PostMapping(value = "/newTeam")
    public String addMemberEmployeeDetails(@ModelAttribute("employee") Employee employee, Model model) {

        List<Employee> employees = employeeService.search(employee)
                .stream()
                .filter(memberService::findByEmployee)
                .collect(Collectors.toList());

        if (employees.size() == 1) {
            model.addAttribute("member", new Member());
            model.addAttribute("membermodel", new MemberModel());
            model.addAttribute("employee", employees.get(0));
            model.addAttribute("addStatus", true);
            return commonCode(model);
        }
        model.addAttribute("addStatus", true);
        model.addAttribute("employee", new Employee());
        model.addAttribute("employeeDetailShow", false);
        model.addAttribute("employeeNotFoundShow", true);
        model.addAttribute("employeeNotFound", "There is not employee in the system according to the provided details" +
                " or that employee already be a member in the system" +
                " \n Could you please search again !!");

        return "member/addMember";
    }

    // Above method support to send data to front end - All List, update, edit
    //Bellow method support to do back end function save, delete, update, search

    @PostMapping(value = {"/add", "/update"})
    public String addMember(@Valid @ModelAttribute MemberModel memberByEmployee, BindingResult result, Model model) {
        System.out.println(memberByEmployee.toString());
        if (memberByEmployee.getId() == null) {
            Employee byId = employeeService.findById(memberByEmployee.getEmployee().getId());
            Team byId1 = teamService.findById(memberByEmployee.getTeam().getId());


            Member member2 = new Member();
            member2.setEmployee(byId);
            member2.setEnabled(true);
            member2.setMemberName(memberByEmployee.getMemberName());

            MemberTeam memberTeam = new MemberTeam(byId1, member2);
            List<MemberTeam> memberTeams = new ArrayList<>();
            memberTeams.add(memberTeam);
            member2.setMember(memberTeams);

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = null;
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            member2.setCreatedAt(LocalDateTime.now());
            member2.setUpdatedAt(LocalDateTime.now());
            member2.setUpdatedBy(username);
            member2.setCreatedBy(username);


            memberDao.save(member2);
            memberTeamDAO.save(memberTeam);
            return "redirect:/member";
        } else {
            Member member3 = memberService.findById(memberByEmployee.getId());
//            Team team = teamService.findById(memberByEmployee.getTeam().getId());
            MemberTeam memberTeam2 = memberTeamDAO.findMemberTeamsByMemberaa(member3.getId());
            Team byId = teamService.findById(memberByEmployee.getTeam().getId());
            memberTeam2.setTeam(byId);
            memberTeam2.setMember(member3);
            memberTeamDAO.save(memberTeam2);
            return "redirect:/member";
        }

        //MY Code for update


    }
       /* System.out.println(memberByEmployee.toString());
        Member member = memberService.findMemberByEmployee(memberByEmployee.getEmployee());
        if (member != null) {
            ObjectError error = new ObjectError("employee", "This employee already defined as a member");
            result.addError(error);
        }
        if (memberByEmployee.getId() != null) {
            Member dbMember = memberService.findById(member.getEmployee().getId());
            System.out.println(dbMember.toString());
            if (dbMember==null) {
                dbMember.setEnabled(true);
                dbMember.setEmployee(member.getEmployee());
                List<MemberTeam> member1 = member.getMember();
                for (MemberTeam memberTeam : member1) {
                    Team team = memberTeam.getTeam();
                    List<MemberTeam> member2 = dbMember.getMember();
                    for (MemberTeam memberTeam1 : member2) {
                        memberTeam1.setTeam(team);
                        memberTeam1.setMember(dbMember);
                        MemberTeam save = memberTeamDAO.save(memberTeam1);
                        System.out.println(save.toString());
                    }


                }
                memberService.persist(dbMember);
            }
            return "redirect:/member";
        }
        if (result.hasErrors()) {
            System.out.println("result to string    " + result.toString());
            model.addAttribute("addStatus", false);
            model.addAttribute("member", member);
            return commonCode(model);
        }
        //member is super senior office need to provide all working palace to check
        Employee employee = employeeService.findById(member.getEmployee().getId());
        Designation designation = employee.getDesignation();

        // memberService.persist(member);
        if (employee.getEmployeeStatus().equals(EmployeeStatus.WORKING)) {
            member.setEnabled(true);
        } else {
            member.setEnabled(false);
        }
//        //member.setTeam(member.getTeam());
//        member.setEnabled(true);
//        memberService.persist(member);
//        return "redirect:/member";

        */

    /**
     *
     *//*
//        List<MemberTeam> member1 = member.getMember();
//        for (MemberTeam memberTeam : member1) {
//            Team team = memberTeam.getTeam();
//            List<MemberTeam> member2 = member.getMember();
//            for (MemberTeam memberTeam1 : member2) {
//                memberTeam1.setTeam(team);
//            }

          *//*  Member dbMember2 = memberService.findById(member.getId());
            dbMember2.setEnabled(true);
            dbMember2.setEmployee(member.getEmployee());
            List<MemberTeam> member2 = member.getMember();
            for(MemberTeam memberTeam:member2){
                Team team = memberTeam.getTeam();
                List<MemberTeam> member3 = dbMember2.getMember();
                for (MemberTeam memberTeam1:member3){
                    memberTeam1.setTeam(team);
                }
                memberService.persist(dbMember2);
            }*//*


//        }

        return "redirect:/member";*/
//    }
    @GetMapping(value = "/remove/{id}")
    public String removeMember(@PathVariable Integer id) {
        // member can not be deleted
        //memberService.delete(id);
        return "redirect:/member";
    }

    @GetMapping(value = "/search")
    public String search(Model model, Member member) {
        model.addAttribute("memberDetail", memberService.search(member));
        return "member/member-detail";
    }
}

