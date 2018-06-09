package com.unesco.core.services.mapperService;

import com.unesco.core.entities.account.Professor;
import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.Student;
import com.unesco.core.entities.account.User;
import com.unesco.core.entities.journal.LessonEvent;
import com.unesco.core.entities.journal.Point;
import com.unesco.core.entities.journal.PointType;
import com.unesco.core.entities.news.News;
import com.unesco.core.entities.schedule.*;
import com.unesco.core.entities.task.TaskDescriptionFile;
import com.unesco.core.entities.task.TaskUser;
import com.unesco.core.entities.task.TaskDescription;
import com.unesco.core.entities.task.TaskUserFile;
import com.unesco.core.models.task.TaskDescriptionFileModel;
import com.unesco.core.models.task.TaskDescriptionModel;
import com.unesco.core.models.task.TaskUserFileModel;
import com.unesco.core.models.task.TaskUserModel;
import com.unesco.core.models.account.ProfessorModel;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.StudentModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.enums.TaskStatusType;
import com.unesco.core.models.journal.LessonEventModel;
import com.unesco.core.models.journal.PointModel;
import com.unesco.core.models.journal.PointTypeModel;
import com.unesco.core.models.news.NewsModel;
import com.unesco.core.models.plan.DepartmentModel;
import com.unesco.core.models.shedule.*;
import com.unesco.core.repositories.account.ProfessorRepository;
import com.unesco.core.repositories.account.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MapperService implements IMapperService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public <T> Object toEntity(T model) {

        if (model == null)
            return null;

        if (model instanceof LessonEventModel)
            return LessonEventToEntity((LessonEventModel) model);

        if (model instanceof PointModel)
            return PointToEntity((PointModel) model);

        if (model instanceof PointTypeModel)
            return PointTypeToEntity((PointTypeModel) model);

        if (model instanceof LessonModel)
            return LessonToEntity((LessonModel) model);

        if (model instanceof ProfessorModel)
            return ProfessorToEntity((ProfessorModel) model);

        if (model instanceof StudentModel)
            return StudentToEntity((StudentModel) model);

        if (model instanceof PairModel)
            return PairToEntity((PairModel) model);

        if (model instanceof RoleModel)
            return RoleToEntity((RoleModel) model);

        if (model instanceof UserModel)
            return UserToEntity((UserModel) model);

        if (model instanceof InstituteModel)
            return InstituteToEntity((InstituteModel) model);

        if (model instanceof DepartmentModel)
            return DepartmentToEntity((DepartmentModel) model);

        if (model instanceof GroupModel)
            return GroupToEntity((GroupModel) model);

        if (model instanceof DisciplineModel)
            return DisciplineToEntity((DisciplineModel) model);

        if (model instanceof FieldOfKnowledgeModel)
            return FieldOfKnowledgeToEntity((FieldOfKnowledgeModel) model);

        if (model instanceof RoomModel)
            return RoomToEntity((RoomModel) model);

        if (model instanceof TaskUserModel)
            return TaskToEntity((TaskUserModel) model);

        if (model instanceof NewsModel)
            return NewsToEntity((NewsModel) model);

        if (model instanceof TaskDescriptionModel)
            return TaskDescriptionToEntity((TaskDescriptionModel) model);

        if (model instanceof TaskUserFileModel)
            return TaskUserFileToEntity((TaskUserFileModel) model);

        if (model instanceof TaskDescriptionFileModel)
            return TaskDescriptionFileToEntity((TaskDescriptionFileModel) model);

        return new Exception("Not found "+model.getClass().toString() + " model class");
    }

    public <T> Object toModel(T entity) {

        if (entity == null)
            return null;

        if (entity instanceof LessonEvent)
            return LessonEventToModel((LessonEvent) entity);

        if (entity instanceof Point)
            return PointToModel((Point) entity);

        if (entity instanceof PointType)
            return PointTypeToModel((PointType) entity);

        if (entity instanceof Lesson)
            return LessonToModel((Lesson) entity);

        if (entity instanceof Professor)
            return ProfessorToModel((Professor) entity);

        if (entity instanceof Student)
            return StudentToModel((Student) entity);

        if (entity instanceof Pair)
            return PairToModel((Pair) entity);

        if (entity instanceof Role)
            return RoleToModel((Role) entity);

        if (entity instanceof User)
            return UserToModel((User) entity);

        if (entity instanceof Institute)
            return InstituteToModel((Institute) entity);

        if (entity instanceof Department)
            return DepartmentToModel((Department) entity);

        if (entity instanceof Group)
            return GroupToModel((Group) entity);

        if (entity instanceof Discipline)
            return DisciplineToModel((Discipline) entity);

        if (entity instanceof FieldOfKnowledge)
            return FieldOfKnowledgeToModel((FieldOfKnowledge) entity );

        if (entity instanceof Room)
            return RoomToModel((Room) entity);

        if (entity instanceof TaskUser)
            return TaskToModel((TaskUser) entity);

        if (entity instanceof News)
            return NewsToModel((News) entity);

        if (entity instanceof TaskDescription)
            return TaskDescriptionToModel((TaskDescription) entity);

        if (entity instanceof TaskUserFile)
            return TaskUserFileToModel((TaskUserFile) entity);

        if (entity instanceof TaskDescriptionFile)
            return TaskDescriptionFileToModel((TaskDescriptionFile) entity);

        return new Exception("Not found "+entity.getClass().toString() + " entity class");
    }

    public PointModel PointToModel(Point Entity)
    {
        PointModel Model = new PointModel();
        Model.setId(Entity.getId());
        Model.setStudent(StudentToModel(Entity.getStudent()));
        Model.setValue(Entity.getValue());
        Model.setPair(PairToModel(Entity.getPair()));
        Model.setType(PointTypeToModel(Entity.getType()));
        Model.setDate(Entity.getDate());
        return Model;
    }
    public Point PointToEntity(PointModel Model)
    {
        Point Entity = new Point();
        Entity.setId(Model.getId());
        Entity.setStudent(StudentToEntity(Model.getStudent()));
        Entity.setValue(Model.getValue());
        Entity.setPair(PairToEntity(Model.getPair()));
        Entity.setType(PointTypeToEntity(Model.getType()));
        Entity.setDate(Model.getDate());
        return Entity;
    }

    public LessonEventModel LessonEventToModel(LessonEvent Entity)
    {
        LessonEventModel Model = new LessonEventModel();
        Model.setId(Entity.getId());
        Model.setDate(Entity.getDate());
        Model.setComment(Entity.getComment());
        Model.setLesson(LessonToModel(Entity.getLesson()));
        Model.setType(PointTypeToModel(Entity.getType()));
        return Model;
    }
    public LessonEvent LessonEventToEntity(LessonEventModel Model)
    {
        LessonEvent Entity = new LessonEvent();
        Entity.setId(Model.getId());
        Entity.setDate(Model.getDate());
        Entity.setComment(Model.getComment());
        Entity.setLesson(LessonToEntity(Model.getLesson()));
        Entity.setType(PointTypeToEntity(Model.getType()));
        return Entity;
    }

    public PointTypeModel PointTypeToModel(PointType Entity)
    {
        PointTypeModel Model = new PointTypeModel();
        Model.setId(Entity.getId());
        Model.setName(Entity.getName());
        return Model;
    }
    public PointType PointTypeToEntity(PointTypeModel Model)
    {
        PointType Entity = new PointType();
        Entity.setId(Model.getId());
        Entity.setName(Model.getName());
        return Entity;
    }

    public TaskDescriptionModel TaskDescriptionToModel(TaskDescription Entity)
    {
        TaskDescriptionModel Model = new TaskDescriptionModel();
        Model.setId(Entity.getId());
        Model.setCreator(UserToModel(Entity.getCreator()));
        Model.setUsers(new ArrayList<>());
        Model.setDescription(Entity.getDescription());
        Model.setName(Entity.getName());
        Model.setStatus(Entity.getStatus());
        Model.setStatusName(TaskStatusType.values()[Entity.getStatus()].name());
        List<TaskUserModel> tasks = new ArrayList<>();
        /*for (Task t: Entity.getSubTasks()) {
            tasks.add(TaskToModel(t));
        }*/
        Model.setTaskUsers(tasks);
        return Model;
    }
    public TaskDescription TaskDescriptionToEntity(TaskDescriptionModel Model)
    {
        TaskDescription Entity = new TaskDescription();
        Entity.setId(Model.getId());
        Entity.setCreator(UserToEntity(Model.getCreator()));
        Entity.setDescription(Model.getDescription());
        Entity.setName(Model.getName());
        List<TaskUser> tasks = new ArrayList<>();
        for (TaskUserModel t: Model.getTaskUsers()) {
            tasks.add(TaskToEntity(t));
        }
        Entity.setTaskUsers(tasks);
        return Entity;
    }

    public TaskUserModel TaskToModel(TaskUser Entity)
    {
        TaskUserModel Model = new TaskUserModel();
        Model.setId(Entity.getId());
        Model.setExecutor(UserToModel(Entity.getExecutor()));
        Model.setResponse(Entity.getResponse());
        Model.setStatus(Entity.getStatus());
        Model.setStatusName(TaskStatusType.values()[Entity.getStatus()].name());
        Model.setTaskDescriptionId(Entity.getTaskDescription().getId());
        return Model;
    }
    public TaskUser TaskToEntity(TaskUserModel Model)
    {
        TaskUser Entity = new TaskUser();
        Entity.setId(Model.getId());
        Entity.setExecutor(UserToEntity(Model.getExecutor()));
        Entity.setResponse(Model.getResponse());
        Entity.setStatus(Model.getStatus());
        TaskDescription taskDescription = new TaskDescription();
        taskDescription.setId(Model.getTaskDescriptionId());
        Entity.setTaskDescription(taskDescription);
        return Entity;
    }

    public NewsModel NewsToModel(News Entity)
    {
        NewsModel Model = new NewsModel();
        Model.setId(Entity.getId());
        Model.setAuthor(UserToModel(Entity.getAuthor()));
        Model.setDate(Entity.getDate());
        Model.setContent(Entity.getContent());
        Model.setHeader(Entity.getHeader());
        Model.setId(Entity.getId());
        Model.setImage(new String(Entity.getImage(), StandardCharsets.UTF_8));
        Model.setTags(Entity.getTags());
        return Model;
    }
    public News NewsToEntity(NewsModel Model)
    {
        News Entity = new News();
        Entity.setId(Model.getId());
        Entity.setAuthor(UserToEntity(Model.getAuthor()));
        Entity.setDate(Model.getDate());
        Entity.setContent(Model.getContent());
        Entity.setHeader(Model.getHeader());
        Entity.setId(Model.getId());
        Entity.setImage(Model.getImage().getBytes());
        Entity.setTags(Model.getTags());
        return Entity;
    }

    public ProfessorModel ProfessorToModel(Professor Entity)
    {
        ProfessorModel Model = new ProfessorModel();
        Model.setId(Entity.getId());
        Model.setUser(UserToModel(Entity.getUser()));
        Model.setDepartment((DepartmentModel) toModel(Entity.getDepartment()));
        return Model;
    }
    public Professor ProfessorToEntity(ProfessorModel Model)
    {
        Professor Entity = new Professor();
        Entity.setId(Model.getId());
        Entity.setUser(UserToEntity(Model.getUser()));
        Entity.setDepartment((Department) toEntity(Model.getDepartment()));
        return Entity;
    }

    public StudentModel StudentToModel(Student Entity)
    {
        StudentModel Model = new StudentModel();
        Model.setId(Entity.getId());
        Model.setUser(UserToModel(Entity.getUser()));
        Model.setGroup((GroupModel) toModel(Entity.getGroup()));
        return Model;
    }
    public Student StudentToEntity(StudentModel Model)
    {
        Student Entity = new Student();
        Entity.setId(Model.getId());
        Entity.setUser(UserToEntity(Model.getUser()));
        Entity.setGroup((Group) toEntity(Model.getGroup()));
        return Entity;
    }

    public RoomModel RoomToModel(Room Entity)
    {
        RoomModel Model = new RoomModel();
        Model.setId(Entity.getId());
        Model.setRoom(Entity.getRoom());
        return Model;
    }
    public Room RoomToEntity(RoomModel Model)
    {
        Room Entity = new Room();
        Entity.setId(Model.getId());
        Entity.setRoom(Model.getRoom());
        return Entity;
    }

    public LessonModel LessonToModel(Lesson Entity)
    {
        LessonModel Model = new LessonModel();
        Model.setId((int) Entity.getId());
        Model.setDiscipline(DisciplineToModel(Entity.getDiscipline()));
        Model.setGroup(GroupToModel(Entity.getGroup()));
        Model.setProfessor(ProfessorToModel(Entity.getProfessor()));
        return Model;
    }
    public Lesson LessonToEntity(LessonModel Model)
    {
        Lesson Entity = new Lesson();
        Entity.setId(Model.getId());
        Entity.setDiscipline(DisciplineToEntity(Model.getDiscipline()));
        Entity.setGroup(GroupToEntity(Model.getGroup()));
        Entity.setProfessor(ProfessorToEntity(Model.getProfessor()));
        return Entity;
    }

    public PairModel PairToModel(Pair Entity)
    {
        PairModel Model = new PairModel();
        Model.setId((int) Entity.getId());
        Model.setPairNumber(Entity.getPairNumber());
        Model.setWeektype(Entity.getWeektype());
        Model.setDayofweek(Entity.getDayofweek());
        Model.setLesson(LessonToModel(Entity.getLesson()));
        Model.setRoom(RoomToModel(Entity.getRoom()));
        return Model;
    }
    public Pair PairToEntity(PairModel Model)
    {
        Pair Entity = new Pair();
        Entity.setId((int) Model.getId());
        Entity.setPairNumber(Model.getPairNumber());
        Entity.setWeektype(Model.getWeektype());
        Entity.setDayofweek(Model.getDayofweek());
        Entity.setLesson(LessonToEntity(Model.getLesson()));
        Entity.setRoom(RoomToEntity(Model.getRoom()));
        return Entity;
    }

    public User UserToEntity(UserModel Model)
    {
        User Entity = new User();

        Entity.setId(Model.getId());
        Entity.setUsername(Model.getUsername());
        Entity.setEmail(Model.getEmail());
        Entity.setUserFIO(Model.getUserFIO());
        Entity.setPassword(Model.getPassword());

        Set<Role> roles = new HashSet<Role>();
        for (RoleModel role: Model.getRoles()) {
            Role roleEntity = (Role) toEntity(role);
            roles.add(roleEntity);
        }
        Entity.setRoles(roles);

        return Entity;
    }
    public UserModel UserToModel(User Entity)
    {
        UserModel Model = new UserModel();

        Model.setId(Entity.getId());
        Model.setUsername(Entity.getUsername());
        Model.setEmail(Entity.getEmail());
        Model.setUserFIO(Entity.getUserFIO());
        Model.setPassword(Entity.getPassword());

        List<RoleModel> roles = new ArrayList<>();
        for (Role role: Entity.getRoles()) {
            RoleModel roleModel = (RoleModel) toModel(role);
            roles.add(roleModel);
        }
        Model.setRoles(roles);
        return Model;
    }

    public RoleModel RoleToModel(Role Entity)
    {
        RoleModel Model = new RoleModel();
        Model.setId((int) Entity.getId());
        Model.setRoleName(Entity.getRoleName());
        return Model;
    }
    public Role RoleToEntity(RoleModel Model)
    {
        Role Entity = new Role();
        Entity.setId(Model.getId());
        Entity.setRoleName(Model.getRoleName());
        return Entity;
    }

    public InstituteModel InstituteToModel(Institute Entity)
    {
        InstituteModel Model = new InstituteModel();

        Model.setId(Entity.getId());
        Model.setName(Entity.getName());
        return Model;
    }
    public Institute InstituteToEntity(InstituteModel Model)
    {
        Institute Entity = new Institute();

        Entity.setId(Model.getId());
        Entity.setName(Model.getName());
        return Entity;
    }

    public DepartmentModel DepartmentToModel(Department Entity)
    {
        DepartmentModel Model = new DepartmentModel();

        Model.setId(Entity.getId());
        Model.setName(Entity.getName());
        Model.setInstitute((InstituteModel) toModel(Entity.getInstitute()));
        return Model;
    }
    public Department DepartmentToEntity(DepartmentModel Model)
    {
        Department Entity = new Department();
        Entity.setId(Model.getId());
        Entity.setName(Model.getName());
        Entity.setInstitute((Institute) toEntity(Model.getInstitute()));
        return Entity;
    }

    public GroupModel GroupToModel(Group Entity)
    {
        GroupModel Model = new GroupModel();

        Model.setName(Entity.getName());
        Model.setId(Entity.getId());
        Model.setDepartment((DepartmentModel) toModel(Entity.getDepartment()));

        return Model;
    }
    public Group GroupToEntity(GroupModel Model)
    {

        Group Entity = new Group();

        Entity.setName(Model.getName());
        Entity.setId(Model.getId());
        Entity.setDepartment((Department) toEntity(Model.getDepartment()));

        return Entity;
    }

    private DisciplineModel DisciplineToModel(Discipline Entity)
    {
        DisciplineModel Model = new DisciplineModel();

        Model.setId((int) Entity.getId());
        Model.setName(Entity.getName());
        if(Entity.getFieldOfKnowledge() != null)
            Model.setFieldOfKnowledge((FieldOfKnowledgeModel) toModel(Entity.getFieldOfKnowledge()));

        return Model;
    }
    private Discipline DisciplineToEntity(DisciplineModel Model)
    {
        Discipline Entity = new Discipline();

        Entity.setId(Model.getId());
        Entity.setName(Model.getName());
        if(Model.getFieldOfKnowledge() != null) {
            Entity.setFieldOfKnowledge((FieldOfKnowledge) toEntity(Model.getFieldOfKnowledge()));
        }

        return Entity;
    }

    private FieldOfKnowledgeModel FieldOfKnowledgeToModel(FieldOfKnowledge Entity)
    {

        FieldOfKnowledgeModel Model = new FieldOfKnowledgeModel();
        Model.setName(Entity.getName());
        Model.setId(Entity.getId());

        return Model;
    }
    private FieldOfKnowledge FieldOfKnowledgeToEntity(FieldOfKnowledgeModel Model)
    {

        FieldOfKnowledge Entity = new FieldOfKnowledge();

        Entity.setName(Model.getName());
        Entity.setId(Model.getId());

        return Entity;
    }

    private TaskDescriptionFileModel TaskDescriptionFileToModel(TaskDescriptionFile Entity)
    {
        TaskDescriptionFileModel Model = new TaskDescriptionFileModel();
        Model.setData(Entity.getData());
        Model.setFileName(Entity.getFileName());
        Model.setFileType(Entity.getFileType());
        Model.setTaskDescriptionId(Entity.getTaskDescriptionId());
        Model.setId(Entity.getId());
        return Model;
    }
    private TaskDescriptionFile TaskDescriptionFileToEntity(TaskDescriptionFileModel Model)
    {
        TaskDescriptionFile Entity = new TaskDescriptionFile();
        Entity.setFileType(Model.getFileType());
        Entity.setFileName(Model.getFileName());
        Entity.setData(Model.getData());
        Entity.setTaskDescriptionId(Model.getTaskDescriptionId());
        Entity.setId(Model.getId());
        return Entity;
    }

    private TaskUserFileModel TaskUserFileToModel(TaskUserFile Entity)
    {
        TaskUserFileModel Model = new TaskUserFileModel();
        Model.setData(Entity.getData());
        Model.setFileName(Entity.getFileName());
        Model.setFileType(Entity.getFileType());
        Model.setTaskUserId(Entity.getTaskUserId());
        Model.setId(Entity.getId());
        return Model;
    }
    private TaskUserFile TaskUserFileToEntity(TaskUserFileModel Model)
    {
        TaskUserFile Entity = new TaskUserFile();
        Entity.setFileType(Model.getFileType());
        Entity.setFileName(Model.getFileName());
        Entity.setData(Model.getData());
        Entity.setTaskUserId(Model.getTaskUserId());
        Entity.setId(Model.getId());
        return Entity;
    }
}
