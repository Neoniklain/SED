package com.unesco.core.controllerWeb;

import com.unesco.core.controller.AccountController;
import com.unesco.core.controller.DictionaryController;
import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.RoleType;
import com.unesco.core.dto.journal.LessonEventDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.dto.shedule.*;
import com.unesco.core.services.account.professorService.IProfessorDataService;
import com.unesco.core.services.account.roleService.IRoleDataService;
import com.unesco.core.services.account.studentService.IStudentDataService;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.services.journal.lessonEvent.ILessonEventDataService;
import com.unesco.core.services.journal.point.IPointDataService;
import com.unesco.core.services.journal.pointType.IPointTypeDataService;
import com.unesco.core.services.schedule.departmentService.IDepartmentDataService;
import com.unesco.core.services.schedule.disciplineService.IDisciplineDataService;
import com.unesco.core.services.schedule.fieldOfKnowledgeService.IFieldOfKnowledgeDataService;
import com.unesco.core.services.schedule.groupService.IGroupDataService;
import com.unesco.core.services.schedule.instituteService.IInstituteDataService;
import com.unesco.core.services.schedule.lessonService.ILessonDataService;
import com.unesco.core.services.schedule.pairService.IPairDataService;
import com.unesco.core.services.schedule.roomService.IRoomDataService;
import com.unesco.core.utils.StatusTypes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictionaryControllerWebTest extends Assert {

    @Autowired
    private AccountController accountController;
    @Autowired
    private DictionaryController dictionaryController;
    @Autowired
    private IRoleDataService roleDataService;
    @Autowired
    private IUserDataService userDataService;
    @Autowired
    private IDepartmentDataService departmentDataService;
    @Autowired
    private IInstituteDataService instituteDataService;
    @Autowired
    private IFieldOfKnowledgeDataService fieldOfKnowledgeDataService;
    @Autowired
    private IDisciplineDataService disciplineDataService;
    @Autowired
    private IProfessorDataService professorDataService;
    @Autowired
    private IGroupDataService groupDataService;
    @Autowired
    private IStudentDataService studentDataService;
    @Autowired
    private IRoomDataService roomDataService;
    @Autowired
    private ILessonDataService lessonDataService;
    @Autowired
    private IPairDataService pairDataService;
    @Autowired
    private IPointDataService pointDataService;
    @Autowired
    private ILessonEventDataService lessonEventDataService;
    @Autowired
    private IPointTypeDataService pointTypeDataService;

    private InstituteDTO inst = new InstituteDTO();
    private DepartmentDTO dep = new DepartmentDTO();
    private GroupDTO gr = new GroupDTO();
    private UserDTO testUser = new UserDTO();
    private FieldOfKnowledgeDTO fok = new FieldOfKnowledgeDTO();
    private DisciplineDTO dis = new DisciplineDTO();
    private ProfessorDTO prof = new ProfessorDTO();
    private StudentDTO stud = new StudentDTO();
    private RoomDTO room = new RoomDTO();
    private LessonDTO lesson = new LessonDTO();
    private PairDTO pair = new PairDTO();
    private LessonEventDTO lesev = new LessonEventDTO();
    private FilterQueryDTO filter = new FilterQueryDTO();


    @Before
    public void setUp() throws Exception {

        filter.setFirst(1);
        filter.setRows(10);

        testUser = getTestUser();
        // Создание преподавателя - профессора
        List<RoleDTO> roles = new ArrayList<>();
        roles.add(roleDataService.GetByName(RoleType.ADMIN.toString()));
        roles.add(roleDataService.GetByName(RoleType.PROFESSOR.toString()));
        roles.add(roleDataService.GetByName(RoleType.STUDENT.toString()));
        testUser.setRoles(roles);
        accountController.Registration(testUser);
        testUser = userDataService.GetByUsername(testUser.getUsername());

        // Создание института
        inst.setName(Math.random()+"");
        instituteDataService.Save(inst);
        inst = instituteDataService.GetByName(inst.getName());

        // Создание департамента
        dep.setName(Math.random()+"");
        dep.setInstitute(inst);
        departmentDataService.Save(dep);
        dep = departmentDataService.GetByName(dep.getName());

        // Создание группы
        gr.setName(Math.random()+"");
        gr.setDepartment(dep);
        groupDataService.Save(gr);
        gr = groupDataService.GetByName(gr.getName());

        accountController.setProfessorDepartment(testUser.getId(), dep.getId());
        accountController.setStudentGroup(testUser.getId(), gr.getId());

        prof = professorDataService.GetByUser(userDataService.GetByUsername(testUser.getUsername()).getId());
        stud = studentDataService.GetByUser(userDataService.GetByUsername(testUser.getUsername()).getId());

        // Создание раздела знаний
        fok.setName(Math.random()+"");
        fieldOfKnowledgeDataService.Save(fok);
        fok = fieldOfKnowledgeDataService.GetByName(fok.getName());

        // Создание предмета
        dis.setName(Math.random()+"");
        dis.setFieldOfKnowledge(fok);
        disciplineDataService.Save(dis);
        dis = disciplineDataService.GetByName(dis.getName());

        // Создание комнаты
        room.setRoom(Math.random()+"");
        roomDataService.Save(room);
        room = roomDataService.GetByRoom(room.getRoom());

        // Создание урока
        lesson.setDiscipline(dis);
        lesson.setGroup(gr);
        lesson.setProfessor(prof);
        lessonDataService.Save(lesson);
        lesson = lessonDataService.GetDisciplineIdAndGroupIdAndProfessorId(dis.getId(), gr.getId(), prof.getId());

        // Создание События
        lesev.setComment("test");
        lesev.setDate(new Date());
        lesev.setLesson(lesson);
        lesev.setType(pointTypeDataService.GetAll().get(0));
        lessonEventDataService.Save(lesev);
        lesev = lessonEventDataService.GetByLesson(lesson.getId()).get(0);

        // Создание пары
        pair.setLesson(lesson);
        pair.setDayofweek("Понедельник");
        pair.setPairNumber(1);
        pair.setRoom(room);
        pair.setWeektype("Все");
        pairDataService.Save(pair);
        pair = pairDataService.GetAllByLesson(lesson.getId()).get(0);

    }

    @After
    public void tearDown() throws Exception {
        // Удаление пары
        if(pairDataService.Get(pair.getId())!=null)
            pairDataService.Delete(pair.getId());
        // Удаление события
        for(LessonEventDTO ev: lessonEventDataService.GetByLesson(lesson.getId())) {
            if(lessonEventDataService.Get(ev.getId())!=null)
                lessonEventDataService.Delete(ev.getId());
        }
        // Удаление урока
        if(lessonDataService.Get(lesson.getId())!=null)
            lessonDataService.Delete(lesson.getId());
        // Удаление комнаты
        if(roomDataService.Get(room.getId())!=null)
            roomDataService.Delete(room.getId());
        // Удаление предмета
        if(disciplineDataService.Get(dis.getId())!=null)
            disciplineDataService.Delete(dis.getId());
        // Удаление раздела знаний
        if(fieldOfKnowledgeDataService.Get(fok.getId())!=null)
            fieldOfKnowledgeDataService.Delete(fok.getId());

        if(userDataService.GetByUsername(testUser.getUsername())!=null)
        {
            // Удаление студента
            if(professorDataService.GetByUser(userDataService.GetByUsername(testUser.getUsername()).getId())!=null)
                professorDataService.Delete(professorDataService.GetByUser(userDataService.GetByUsername(testUser.getUsername()).getId()).getId());
            // Удаление преподавателя
            if(studentDataService.GetByUser(userDataService.GetByUsername(testUser.getUsername()).getId())!=null)
                studentDataService.Delete(studentDataService.GetByUser(userDataService.GetByUsername(testUser.getUsername()).getId()).getId());
        }
        // Удаление пользователя
        if(userDataService.GetByUsername(testUser.getUsername())!=null)
            userDataService.Delete(userDataService.GetByUsername(testUser.getUsername()).getId());
        // Удаление группы
        if(groupDataService.Get(gr.getId())!=null)
            groupDataService.Delete(gr.getId());
        // Удаление департамента
        if(departmentDataService.Get(dep.getId())!=null)
            departmentDataService.Delete(dep.getId());
        // Удаление института
        if(instituteDataService.Get(inst.getId())!=null)
            instituteDataService.Delete(inst.getId());
    }

    @Test
    public void getProfessorList() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            dictionaryController.GetProfessorList(filter);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getUserList() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            dictionaryController.GetUserList(filter);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getRoomList() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            dictionaryController.GetRoleList(filter);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void addRoom() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = dictionaryController.AddRoom(room);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void deleteRoom() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = dictionaryController.DeleteRoom(room.getId());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getFieldOfKnowledgeList() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            dictionaryController.GetFieldOfKnowledgeList(filter);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void addFieldOfKnowledge() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = dictionaryController.AddFieldOfKnowledge(fok);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void deleteFieldOfKnowledge() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try{
            resp = dictionaryController.DeleteFieldOfKnowledge(fok.getId());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getRoleList() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            dictionaryController.GetRoleList(filter);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void addRole() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        RoleDTO role = new RoleDTO();
        try {
            role.setRoleName("tt");
            resp = dictionaryController.AddRole(role);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        roleDataService.Delete(roleDataService.GetByName(role.getRoleName()).getId());
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void deleteRole() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        RoleDTO role = new RoleDTO();
        try {
            role.setRoleName("tt");
            role = roleDataService.Save(role);
            resp = dictionaryController.DeleteRole(role.getId());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getGroupList() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            dictionaryController.GetGroupList(filter);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void addGroup() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            resp = dictionaryController.AddGroup(gr);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void deleteGroup() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            resp = dictionaryController.DeleteGroup(gr.getId());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getDepartmentList() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            dictionaryController.GetDepartmentList(filter);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void addDepartment() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            resp = dictionaryController.AddDepartment(dep);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void deleteDepartment() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            resp = dictionaryController.DeleteDepartment(dep.getId());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getInstituteList() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            dictionaryController.GetInstituteList(filter);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void addInstitute() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            dictionaryController.AddInstitute(inst);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void deleteInstitute() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            dictionaryController.DeleteInstitute(inst.getId());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getDisciplineList() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            dictionaryController.DeleteInstitute(inst.getId());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void addDiscipline() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            dictionaryController.AddDiscipline(dis);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void deleteDiscipline() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            dictionaryController.DeleteDiscipline(dis.getId());
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    @Test
    public void getPointTypeList() {
        ResponseStatusDTO resp = new ResponseStatusDTO(StatusTypes.OK);
        try {
            dictionaryController.GetPointTypeList(filter);
        } catch (Exception e) {
            resp.setStatus(StatusTypes.ERROR);
            resp.addErrors(e.getMessage());
        }
        System.out.println(resp.getErrors());
        System.out.println(resp.getMessage());
        System.out.println(resp.getWarnings());
        assertEquals(resp.getStatus(), StatusTypes.OK);
    }

    UserDTO getTestUser() {
        UserDTO testUser = new UserDTO();
        testUser.setPassword("12345");
        List<RoleDTO> roles = new ArrayList<>();
        roles.add(roleDataService.GetByName(RoleType.ADMIN.toString()));
        testUser.setRoles(roles);
        testUser.setEmail(Math.random()+"@mail.ru");
        testUser.setPhoto("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhISEhMWFRUVFRUVFRUVFRUVFRUVFRUWFhUVFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDg0OGhAQGy0fHR8tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLSstLy0rLS0tLS0tLS0tLS0tK//AABEIALMBGQMBIgACEQEDEQH/x");
        testUser.setUserFIO("TEst User Testovich");
        testUser.setUsername(Math.random()+"");
        return testUser;
    }
}