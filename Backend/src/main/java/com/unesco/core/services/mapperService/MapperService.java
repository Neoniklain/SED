package com.unesco.core.services.mapperService;

import com.unesco.core.entities.*;
import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.User;
import com.unesco.core.entities.schedule.Pair;
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

        return new Exception("Not found "+entity.getClass().toString() + " entity class");
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


    public PairModel PairToModel(Pair Entity)
    {
        PairModel Model = new PairModel();
        Model.setPairnumber(Entity.getPairNumber());
        Model.setWeektype(Entity.getWeektype().getType());
        Model.setDayofweek(Entity.getDayofweek().getDayofweek());
        Model.setProfessor(Entity.getProfessor().getUser().getUserFIO());
        Model.setRoom(Entity.getRoom().getRoom());
        Model.setDiscipline(Entity.getDiscipline().getName());
        Model.setGroup(GroupToModel(Entity.getGroup()));
        Model.setDepartment("");
        return Model;
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
