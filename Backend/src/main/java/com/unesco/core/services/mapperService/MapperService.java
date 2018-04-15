package com.unesco.core.services.mapperService;

import com.unesco.core.entities.*;
import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.User;
import com.unesco.core.entities.schedule.Pair;
import com.unesco.core.entities.schedule.Room;
import com.unesco.core.entities.workflow.Task;
import com.unesco.core.entities.workflow.TaskDescription;
import com.unesco.core.models.*;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserCreateModel;
import com.unesco.core.models.account.UserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MapperService implements IMapperService {

    public <T> Object toEntity(T model) {

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

        if (model instanceof UserCreateModel)
            return UserCreateToEntity((UserCreateModel) model);

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

        if (model instanceof TaskModel)
            return TaskToEntity((TaskModel) model);


        if (model instanceof TaskDescription)
            return TaskDescriptionToEntity((TaskDescriptionModel) model);

        return new Exception("Not found "+model.getClass().toString() + " model class");
    }

    public <T> Object toModel(T entity) {

        if (entity == null)
            return null;

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

        if (entity instanceof Task)
            return TaskToModel((Task) entity);

        if (entity instanceof TaskDescription)
            return TaskDescriptionToModel((TaskDescription) entity);

        return new Exception("Not found "+entity.getClass().toString() + " entity class");
    }


    public TaskDescriptionModel TaskDescriptionToModel(TaskDescription Entity)
    {
        TaskDescriptionModel Model = new TaskDescriptionModel();
        Model.setId(Entity.getId());
        Model.setCreator(UserToModel(Entity.getCreator()));
        Model.setUsers(new ArrayList<>());
        Model.setDescription(Entity.getDescription());
        Model.setName(Entity.getName());
        List<TaskModel> tasks = new ArrayList<>();
        for (Task t: Entity.getSubTasks()) {
            tasks.add(TaskToModel(t));
        }
        Model.setSubTasks(tasks);
        return Model;
    }
    public TaskDescription TaskDescriptionToEntity(TaskDescriptionModel Model)
    {
        TaskDescription Entity = new TaskDescription();
        Entity.setId(Model.getId());
        Entity.setCreator(UserToEntity(Model.getCreator()));
        Entity.setDescription(Model.getDescription());
        Entity.setName(Model.getName());
        List<Task> tasks = new ArrayList<>();
        for (TaskModel t: Model.getSubTasks()) {
            tasks.add(TaskToEntity(t));
        }
        Entity.setSubTasks(tasks);
        return Entity;
    }

    public TaskModel TaskToModel(Task Entity)
    {
        TaskModel Model = new TaskModel();
        Model.setId(Entity.getId());
        Model.setExecutor((UserModel) UserToModel(Entity.getExecutor()));
        Model.setResponse(Entity.getResponse());
        Model.setStatus(Entity.getStatus());
        Model.setTaskDescriptionId(Entity.getTaskDescription().getId());
        return Model;
    }
    public Task TaskToEntity(TaskModel Model)
    {
        Task Entity = new Task();
        Entity.setId(Model.getId());
        Entity.setExecutor((User) UserToEntity(Model.getExecutor()));
        Entity.setResponse(Model.getResponse());
        Entity.setStatus(Model.getStatus());
        TaskDescription taskDescription = new TaskDescription();
        taskDescription.setId(Model.getTaskDescriptionId());
        Entity.setTaskDescription(taskDescription);
        return Entity;
    }

    public ProfessorModel ProfessorToModel(Professor Entity)
    {
        ProfessorModel Model = new ProfessorModel();
        Model.setId(Entity.getUser().getId());
        Model.setUsername(Entity.getUser().getUsername());
        Model.setEmail(Entity.getUser().getEmail());
        Model.setUserFIO(Entity.getUser().getUserFIO());
        Model.setDepartment((DepartmentModel) toModel(Entity.getDepartment()));
        List<RoleModel> roles = new ArrayList<RoleModel>();
        for (Role role: Entity.getUser().getRoles()) {
            RoleModel roleModel = (RoleModel) toModel(role);
            roles.add(roleModel);
        }
        Model.setRoles(roles);
        return Model;
    }
    public Professor ProfessorToEntity(ProfessorModel Model)
    {
        Professor Entity = new Professor();
        User user = new User();
        user.setId(Model.getId());
        user.setEmail(Model.getEmail());
        user.setUsername(Model.getUsername());
        user.setUserFIO(Model.getUserFIO());
        Set<Role> roles = new HashSet<Role>();
        for (RoleModel role: Model.getRoles()) {
            Role roleEntity = (Role) toEntity(role);
            roles.add(roleEntity);
        }
        user.setRoles(roles);
        Entity.setUser(user);
        Entity.setDepartment((Department) toEntity(Model.getDepartment()));
        return Entity;
    }

    public StudentModel StudentToModel(Student Entity)
    {
        StudentModel Model = new StudentModel();
        Model.setId(Entity.getUser().getId());
        Model.setUsername(Entity.getUser().getUsername());
        Model.setEmail(Entity.getUser().getEmail());
        Model.setUserFIO(Entity.getUser().getUserFIO());
        Model.setGroup((GroupModel) toModel(Entity.getGroup()));
        List<RoleModel> roles = new ArrayList<RoleModel>();
        for (Role role: Entity.getUser().getRoles()) {
            RoleModel roleModel = (RoleModel) toModel(role);
            roles.add(roleModel);
        }
        Model.setRoles(roles);
        return Model;
    }
    public Student StudentToEntity(StudentModel Model)
    {
        Student Entity = new Student();
        User user = new User();
        user.setId(Model.getId());
        user.setEmail(Model.getEmail());
        user.setUsername(Model.getUsername());
        user.setUserFIO(Model.getUserFIO());
        Set<Role> roles = new HashSet<Role>();
        for (RoleModel role: Model.getRoles()) {
            Role roleEntity = (Role) toEntity(role);
            roles.add(roleEntity);
        }
        user.setRoles(roles);
        Entity.setUser(user);
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

    public PairModel PairToModel(Pair Entity)
    {
        PairModel Model = new PairModel();
        Model.setId((int) Entity.getId());
        Model.setPairNumber(Entity.getPairNumber());
        Model.setWeektype(Entity.getWeektype());
        Model.setDayofweek(Entity.getDayofweek());
        Model.setProfessor(ProfessorToModel(Entity.getProfessor()));
        Model.setRoom(RoomToModel(Entity.getRoom()));
        Model.setDiscipline(DisciplineToModel(Entity.getDiscipline()));
        Model.setGroup(GroupToModel(Entity.getGroup()));
        return Model;
    }

    public Pair PairToEntity(PairModel Model)
    {
        Pair Entity = new Pair();
        Entity.setId((int) Model.getId());
        Entity.setPairNumber(Model.getPairNumber());
        Entity.setWeektype(Model.getWeektype());
        Entity.setDayofweek(Model.getDayofweek());
        Entity.setProfessor(ProfessorToEntity(Model.getProfessor()));
        Entity.setRoom(RoomToEntity(Model.getRoom()));
        Entity.setDiscipline(DisciplineToEntity(Model.getDiscipline()));
        Entity.setGroup(GroupToEntity(Model.getGroup()));
        return Entity;
    }

    public User UserCreateToEntity(UserCreateModel Model)
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

        List<RoleModel> roles = new ArrayList<>();
        for (Role role: Entity.getRoles()) {
            RoleModel roleModel = (RoleModel) toModel(role);
            roles.add(roleModel);
        }
        Model.setRoles(roles);
        return Model;
    }
    public User UserToEntity(UserModel Model)
    {
        User Entity = new User();

        Entity.setId(Model.getId());
        Entity.setUsername(Model.getUsername());
        Entity.setEmail(Model.getEmail());
        Entity.setUserFIO(Model.getUserFIO());

        Set<Role> roles = new HashSet<Role>();
        for (RoleModel role: Model.getRoles()) {
            Role roleEntity = (Role) toEntity(role);
            roles.add(roleEntity);
        }
        Entity.setRoles(roles);

        return Entity;
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

}
