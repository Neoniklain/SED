package com.unesco.core.services.mapperService;

import com.unesco.core.entities.account.ProfessorEntity;
import com.unesco.core.entities.account.RoleEntity;
import com.unesco.core.entities.account.StudentEntity;
import com.unesco.core.entities.account.UserEntity;
import com.unesco.core.entities.file.FileByteCode;
import com.unesco.core.entities.file.FileDescription;
import com.unesco.core.entities.journal.LessonEventEntity;
import com.unesco.core.entities.journal.PointEntity;
import com.unesco.core.entities.journal.PointTypeEntity;
import com.unesco.core.entities.news.NewsEntity;
import com.unesco.core.entities.schedule.*;
import com.unesco.core.entities.task.TaskDescription;
import com.unesco.core.entities.task.TaskUser;
import com.unesco.core.models.account.ProfessorDTO;
import com.unesco.core.models.account.RoleDTO;
import com.unesco.core.models.account.StudentDTO;
import com.unesco.core.models.account.UserDTO;
import com.unesco.core.models.enums.TaskStatusType;
import com.unesco.core.models.file.FileByteCodeModel;
import com.unesco.core.models.file.FileDescriptionModel;
import com.unesco.core.models.journal.LessonEventDTO;
import com.unesco.core.models.journal.PointDTO;
import com.unesco.core.models.journal.PointTypeDTO;
import com.unesco.core.models.news.NewsDTO;
import com.unesco.core.models.plan.DepartmentDTO;
import com.unesco.core.models.shedule.*;
import com.unesco.core.models.task.TaskDescriptionModel;
import com.unesco.core.models.task.TaskUserModel;
import com.unesco.core.repositories.account.ProfessorRepository;
import com.unesco.core.repositories.account.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.*;

@Service
public class MapperService implements IMapperService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public <T> Object toEntity(T model) {

        if (model == null)
            return null;

        if (model instanceof LessonEventDTO)
            return LessonEventToEntity((LessonEventDTO) model);

        if (model instanceof PointDTO)
            return PointToEntity((PointDTO) model);

        if (model instanceof PointTypeDTO)
            return PointTypeToEntity((PointTypeDTO) model);

        if (model instanceof LessonDTO)
            return LessonToEntity((LessonDTO) model);

        if (model instanceof ProfessorDTO)
            return ProfessorToEntity((ProfessorDTO) model);

        if (model instanceof StudentDTO)
            return StudentToEntity((StudentDTO) model);

        if (model instanceof PairDTO)
            return PairToEntity((PairDTO) model);

        if (model instanceof RoleDTO)
            return RoleToEntity((RoleDTO) model);

        if (model instanceof UserDTO)
            return UserToEntity((UserDTO) model);

        if (model instanceof InstituteDTO)
            return InstituteToEntity((InstituteDTO) model);

        if (model instanceof DepartmentDTO)
            return DepartmentToEntity((DepartmentDTO) model);

        if (model instanceof GroupDTO)
            return GroupToEntity((GroupDTO) model);

        if (model instanceof DisciplineDTO)
            return DisciplineToEntity((DisciplineDTO) model);

        if (model instanceof FieldOfKnowledgeDTO)
            return FieldOfKnowledgeToEntity((FieldOfKnowledgeDTO) model);

        if (model instanceof RoomDTO)
            return RoomToEntity((RoomDTO) model);

        if (model instanceof TaskUserModel)
            return TaskUserToEntity((TaskUserModel) model);

        if (model instanceof NewsDTO)
            return NewsToEntity((NewsDTO) model);

        if (model instanceof TaskDescriptionModel)
            return TaskDescriptionToEntity((TaskDescriptionModel) model);

        if (model instanceof FileByteCodeModel)
            return FileByteCodeToEntity((FileByteCodeModel) model);

        if (model instanceof FileDescriptionModel)
            return FileDescriptionToEntity((FileDescriptionModel) model);

        return new Exception("Not found " + model.getClass().toString() + " model class");
    }

    public <T> Object toModel(T entity) {

        if (entity == null)
            return null;

        if (entity instanceof LessonEventEntity)
            return LessonEventToModel((LessonEventEntity) entity);

        if (entity instanceof PointEntity)
            return PointToModel((PointEntity) entity);

        if (entity instanceof PointTypeEntity)
            return PointTypeToModel((PointTypeEntity) entity);

        if (entity instanceof LessonEntity)
            return LessonToModel((LessonEntity) entity);

        if (entity instanceof ProfessorEntity)
            return ProfessorToModel((ProfessorEntity) entity);

        if (entity instanceof StudentEntity)
            return StudentToModel((StudentEntity) entity);

        if (entity instanceof PairEntity)
            return PairToModel((PairEntity) entity);

        if (entity instanceof RoleEntity)
            return RoleToModel((RoleEntity) entity);

        if (entity instanceof UserEntity)
            return UserToModel((UserEntity) entity);

        if (entity instanceof InstituteEntity)
            return InstituteToModel((InstituteEntity) entity);

        if (entity instanceof DepartmentEntity)
            return DepartmentToModel((DepartmentEntity) entity);

        if (entity instanceof GroupEntity)
            return GroupToModel((GroupEntity) entity);

        if (entity instanceof DisciplineEntity)
            return DisciplineToModel((DisciplineEntity) entity);

        if (entity instanceof FieldOfKnowledgeEntity)
            return FieldOfKnowledgeToModel((FieldOfKnowledgeEntity) entity );

        if (entity instanceof RoomEntity)
            return RoomToModel((RoomEntity) entity);

        if (entity instanceof TaskUser)
            return TaskUserToModel((TaskUser) entity);

        if (entity instanceof NewsEntity)
            return NewsToModel((NewsEntity) entity);

        if (entity instanceof TaskDescription)
            return TaskDescriptionToModel((TaskDescription) entity);

        if (entity instanceof FileByteCode)
            return FileByteCodeToModel((FileByteCode) entity);

        if (entity instanceof FileDescription)
            return FileDescriptionToModel((FileDescription) entity);


        return new Exception("Not found " + entity.getClass().toString() + " entity class");
    }

    public PointDTO PointToModel(PointEntity Entity)
    {
        PointDTO Model = new PointDTO();
        Model.setId(Entity.getId());
        Model.setStudent(StudentToModel(Entity.getStudentEntity()));
        Model.setValue(Entity.getValue());
        Model.setPair(PairToModel(Entity.getPairEntity()));
        Model.setType(PointTypeToModel(Entity.getType()));
        Model.setDate(Entity.getDate());
        return Model;
    }
    public PointEntity PointToEntity(PointDTO Model)
    {
        PointEntity Entity = new PointEntity();
        Entity.setId(Model.getId());
        Entity.setStudentEntity(StudentToEntity(Model.getStudent()));
        Entity.setValue(Model.getValue());
        Entity.setPairEntity(PairToEntity(Model.getPair()));
        Entity.setType(PointTypeToEntity(Model.getType()));
        Entity.setDate(Model.getDate());
        return Entity;
    }

    public LessonEventDTO LessonEventToModel(LessonEventEntity Entity)
    {
        LessonEventDTO Model = new LessonEventDTO();
        Model.setId(Entity.getId());
        Date Dt = new Date((long)Entity.getDate().getTime());
        Model.setDate(Dt);
        Model.setComment(Entity.getComment());
        Model.setLesson(LessonToModel(Entity.getLessonEntity()));
        Model.setType(PointTypeToModel(Entity.getType()));
        return Model;
    }
    public LessonEventEntity LessonEventToEntity(LessonEventDTO Model)
    {
        LessonEventEntity Entity = new LessonEventEntity();
        Entity.setId(Model.getId());
        Timestamp ts = new Timestamp(Model.getDate().getTime());
        Entity.setDate(ts);
        Entity.setComment(Model.getComment());
        Entity.setLessonEntity(LessonToEntity(Model.getLesson()));
        Entity.setType(PointTypeToEntity(Model.getType()));
        return Entity;
    }

    public PointTypeDTO PointTypeToModel(PointTypeEntity Entity)
    {
        PointTypeDTO Model = new PointTypeDTO();
        Model.setId(Entity.getId());
        Model.setName(Entity.getName());
        return Model;
    }
    public PointTypeEntity PointTypeToEntity(PointTypeDTO Model)
    {
        PointTypeEntity Entity = new PointTypeEntity();
        Entity.setId(Model.getId());
        Entity.setName(Model.getName());
        return Entity;
    }

    public TaskDescriptionModel TaskDescriptionToModel(TaskDescription Entity) {
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
        List<FileDescriptionModel> files = new ArrayList<>();
        for (FileDescription t: Entity.getFiles()) {
            files.add(FileDescriptionToModel(t));
        }
        Model.setFiles(files);
        return Model;
    }

    public TaskDescription TaskDescriptionToEntity(TaskDescriptionModel Model) {
        TaskDescription Entity = new TaskDescription();
        Entity.setId(Model.getId());
        Entity.setCreator(UserToEntity(Model.getCreator()));
        Entity.setDescription(Model.getDescription());
        Entity.setName(Model.getName());
        List<TaskUser> tasks = new ArrayList<>();
        for (TaskUserModel t : Model.getTaskUsers()) {
            tasks.add(TaskUserToEntity(t));
        }
        Entity.setTaskUsers(tasks);

        Set<FileDescription> files = new HashSet<>();
        for (FileDescriptionModel t : Model.getFiles()) {
            files.add(FileDescriptionToEntity(t));
        }
        Entity.setFiles(files);
        return Entity;
    }

    public TaskUserModel TaskUserToModel(TaskUser Entity) {
        TaskUserModel Model = new TaskUserModel();
        Model.setId(Entity.getId());
        Model.setExecutor(UserToModel(Entity.getExecutor()));
        Model.setResponse(Entity.getResponse());
        Model.setStatus(Entity.getStatus());
        Model.setStatusName(TaskStatusType.values()[Entity.getStatus()].name());
        Model.setTaskDescriptionId(Entity.getTaskDescription().getId());
        List<FileDescriptionModel> files = new ArrayList<>();
        for (FileDescription t: Entity.getFiles()) {
            files.add(FileDescriptionToModel(t));
        }
        Model.setFiles(files);
        return Model;
    }

    public TaskUser TaskUserToEntity(TaskUserModel Model) {
        TaskUser Entity = new TaskUser();
        Entity.setId(Model.getId());
        Entity.setExecutor(UserToEntity(Model.getExecutor()));
        Entity.setResponse(Model.getResponse());
        Entity.setStatus(Model.getStatus());
        TaskDescription taskDescription = new TaskDescription();
        taskDescription.setId(Model.getTaskDescriptionId());
        Entity.setTaskDescription(taskDescription);
        Set<FileDescription> files = new HashSet<>();
        for (FileDescriptionModel t : Model.getFiles()) {
            files.add(FileDescriptionToEntity(t));
        }
        Entity.setFiles(files);
        return Entity;
    }

    public NewsDTO NewsToModel(NewsEntity Entity)
    {
        NewsDTO Model = new NewsDTO();
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
    public NewsEntity NewsToEntity(NewsDTO Model)
    {
        NewsEntity Entity = new NewsEntity();
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

    public ProfessorDTO ProfessorToModel(ProfessorEntity Entity)
    {
        ProfessorDTO Model = new ProfessorDTO();
        Model.setId(Entity.getId());
        Model.setUser(UserToModel(Entity.getUserEntity()));
        Model.setDepartment((DepartmentDTO) toModel(Entity.getDepartmentEntity()));
        return Model;
    }
    public ProfessorEntity ProfessorToEntity(ProfessorDTO Model)
    {
        ProfessorEntity Entity = new ProfessorEntity();
        Entity.setId(Model.getId());
        Entity.setUserEntity(UserToEntity(Model.getUser()));
        Entity.setDepartmentEntity((DepartmentEntity) toEntity(Model.getDepartment()));
        return Entity;
    }

    public StudentDTO StudentToModel(StudentEntity Entity)
    {
        StudentDTO Model = new StudentDTO();
        Model.setId(Entity.getId());
        Model.setUser(UserToModel(Entity.getUserEntity()));
        Model.setGroup((GroupDTO) toModel(Entity.getGroupEntity()));
        return Model;
    }
    public StudentEntity StudentToEntity(StudentDTO Model)
    {
        StudentEntity Entity = new StudentEntity();
        Entity.setId(Model.getId());
        Entity.setUserEntity(UserToEntity(Model.getUser()));
        Entity.setGroupEntity((GroupEntity) toEntity(Model.getGroup()));
        return Entity;
    }

    public RoomDTO RoomToModel(RoomEntity Entity)
    {
        RoomDTO Model = new RoomDTO();
        Model.setId(Entity.getId());
        Model.setRoom(Entity.getRoom());
        return Model;
    }
    public RoomEntity RoomToEntity(RoomDTO Model)
    {
        RoomEntity Entity = new RoomEntity();
        Entity.setId(Model.getId());
        Entity.setRoom(Model.getRoom());
        return Entity;
    }

    public LessonDTO LessonToModel(LessonEntity Entity)
    {
        LessonDTO Model = new LessonDTO();
        Model.setId((int) Entity.getId());
        Model.setDiscipline(DisciplineToModel(Entity.getDisciplineEntity()));
        Model.setGroup(GroupToModel(Entity.getGroupEntity()));
        Model.setProfessor(ProfessorToModel(Entity.getProfessorEntity()));
        return Model;
    }
    public LessonEntity LessonToEntity(LessonDTO Model)
    {
        LessonEntity Entity = new LessonEntity();
        Entity.setId(Model.getId());
        Entity.setDisciplineEntity(DisciplineToEntity(Model.getDiscipline()));
        Entity.setGroupEntity(GroupToEntity(Model.getGroup()));
        Entity.setProfessorEntity(ProfessorToEntity(Model.getProfessor()));
        return Entity;
    }

    public PairDTO PairToModel(PairEntity Entity)
    {
        PairDTO Model = new PairDTO();
        Model.setId((int) Entity.getId());
        Model.setPairNumber(Entity.getPairNumber());
        Model.setWeektype(Entity.getWeektype());
        Model.setDayofweek(Entity.getDayofweek());
        Model.setLesson(LessonToModel(Entity.getLessonEntity()));
        Model.setRoom(RoomToModel(Entity.getRoomEntity()));
        return Model;
    }
    public PairEntity PairToEntity(PairDTO Model)
    {
        PairEntity Entity = new PairEntity();
        Entity.setId((int) Model.getId());
        Entity.setPairNumber(Model.getPairNumber());
        Entity.setWeektype(Model.getWeektype());
        Entity.setDayofweek(Model.getDayofweek());
        Entity.setLessonEntity(LessonToEntity(Model.getLesson()));
        Entity.setRoomEntity(RoomToEntity(Model.getRoom()));
        return Entity;
    }

    public UserEntity UserToEntity(UserDTO Model)
    {
        UserEntity Entity = new UserEntity();

        Entity.setId(Model.getId());
        Entity.setUsername(Model.getUsername());
        Entity.setEmail(Model.getEmail());
        Entity.setUserFIO(Model.getUserFIO());
        Entity.setPassword(Model.getPassword());
        byte[] photo = null;
        if(Entity.getPhoto() != null)
            photo = Model.getPhoto().getBytes();
        Entity.setPhoto(photo);
        Set<RoleEntity> roleEntities = new HashSet<RoleEntity>();
        for (RoleDTO role: Model.getRoles()) {
            RoleEntity roleEntityEntity = (RoleEntity) toEntity(role);
            roleEntities.add(roleEntityEntity);
        }
        Entity.setRoleEntities(roleEntities);

        return Entity;
    }
    public UserDTO UserToModel(UserEntity Entity)
    {
        UserDTO Model = new UserDTO();

        Model.setId(Entity.getId());
        Model.setUsername(Entity.getUsername());
        Model.setEmail(Entity.getEmail());
        Model.setUserFIO(Entity.getUserFIO());
        Model.setPassword(Entity.getPassword());
        String photo = "";
        if(Entity.getPhoto() != null)
            photo = new String(Entity.getPhoto(), StandardCharsets.UTF_8);
        Model.setPhoto(photo);
        List<RoleDTO> roles = new ArrayList<>();
        for (RoleEntity roleEntity : Entity.getRoleEntities()) {
            RoleDTO roleDTO = (RoleDTO) toModel(roleEntity);
            roles.add(roleDTO);
        }
        Model.setRoles(roles);
        return Model;
    }

    public RoleDTO RoleToModel(RoleEntity Entity)
    {
        RoleDTO Model = new RoleDTO();
        Model.setId((int) Entity.getId());
        Model.setRoleName(Entity.getRoleName());
        return Model;
    }
    public RoleEntity RoleToEntity(RoleDTO Model)
    {
        RoleEntity Entity = new RoleEntity();
        Entity.setId(Model.getId());
        Entity.setRoleName(Model.getRoleName());
        return Entity;
    }

    public InstituteDTO InstituteToModel(InstituteEntity Entity)
    {
        InstituteDTO Model = new InstituteDTO();

        Model.setId(Entity.getId());
        Model.setName(Entity.getName());
        return Model;
    }
    public InstituteEntity InstituteToEntity(InstituteDTO Model)
    {
        InstituteEntity Entity = new InstituteEntity();

        Entity.setId(Model.getId());
        Entity.setName(Model.getName());
        return Entity;
    }

    public DepartmentDTO DepartmentToModel(DepartmentEntity Entity)
    {
        DepartmentDTO Model = new DepartmentDTO();

        Model.setId(Entity.getId());
        Model.setName(Entity.getName());
        Model.setInstitute((InstituteDTO) toModel(Entity.getInstituteEntity()));
        return Model;
    }
    public DepartmentEntity DepartmentToEntity(DepartmentDTO Model)
    {
        DepartmentEntity Entity = new DepartmentEntity();
        Entity.setId(Model.getId());
        Entity.setName(Model.getName());
        Entity.setInstituteEntity((InstituteEntity) toEntity(Model.getInstitute()));
        return Entity;
    }

    public GroupDTO GroupToModel(GroupEntity Entity)
    {
        GroupDTO Model = new GroupDTO();

        Model.setName(Entity.getName());
        Model.setId(Entity.getId());
        Model.setDepartment((DepartmentDTO) toModel(Entity.getDepartmentEntity()));

        return Model;
    }
    public GroupEntity GroupToEntity(GroupDTO Model)
    {

        GroupEntity Entity = new GroupEntity();

        Entity.setName(Model.getName());
        Entity.setId(Model.getId());
        Entity.setDepartmentEntity((DepartmentEntity) toEntity(Model.getDepartment()));

        return Entity;
    }

    private DisciplineDTO DisciplineToModel(DisciplineEntity Entity)
    {
        DisciplineDTO Model = new DisciplineDTO();

        Model.setId((int) Entity.getId());
        Model.setName(Entity.getName());
        if(Entity.getFieldOfKnowledgeEntity() != null)
            Model.setFieldOfKnowledge((FieldOfKnowledgeDTO) toModel(Entity.getFieldOfKnowledgeEntity()));

        return Model;
    }
    private DisciplineEntity DisciplineToEntity(DisciplineDTO Model)
    {
        DisciplineEntity Entity = new DisciplineEntity();

        Entity.setId(Model.getId());
        Entity.setName(Model.getName());
        if (Model.getFieldOfKnowledge() != null) {
            Entity.setFieldOfKnowledgeEntity((FieldOfKnowledgeEntity) toEntity(Model.getFieldOfKnowledge()));
        }

        return Entity;
    }

    private FieldOfKnowledgeDTO FieldOfKnowledgeToModel(FieldOfKnowledgeEntity Entity)
    {

        FieldOfKnowledgeDTO Model = new FieldOfKnowledgeDTO();
        Model.setName(Entity.getName());
        Model.setId(Entity.getId());

        return Model;
    }
    private FieldOfKnowledgeEntity FieldOfKnowledgeToEntity(FieldOfKnowledgeDTO Model)
    {

        FieldOfKnowledgeEntity Entity = new FieldOfKnowledgeEntity();

        Entity.setName(Model.getName());
        Entity.setId(Model.getId());

        return Entity;
    }

    private FileByteCodeModel FileByteCodeToModel(FileByteCode entity){
        FileByteCodeModel Model = new FileByteCodeModel();
        Model.setData(entity.getData());
        Model.setFileDescription((FileDescriptionModel) toModel(entity.getFileDescription()));
        Model.setId(entity.getId());
        return Model;
    }

    private FileByteCode FileByteCodeToEntity(FileByteCodeModel model) {
        FileByteCode Entity = new FileByteCode();
        Entity.setData(model.getData());
        Entity.setId(model.getId());
        Entity.setFileDescription((FileDescription) toEntity(model.getFileDescription()));
        return Entity;
    }

    private FileDescriptionModel FileDescriptionToModel(FileDescription entity){
        FileDescriptionModel Model = new FileDescriptionModel();
        Model.setFileName(entity.getFileName());
        Model.setFileType(entity.getFileType());
        Model.setId(entity.getId());
        return Model;
    }

    private FileDescription FileDescriptionToEntity(FileDescriptionModel model) {
        FileDescription Entity = new FileDescription();
        Entity.setFileName(model.getFileName());
        Entity.setFileType(model.getFileType());
        Entity.setId(model.getId());
        return Entity;
    }
}
