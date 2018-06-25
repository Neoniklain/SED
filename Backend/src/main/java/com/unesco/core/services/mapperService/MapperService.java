package com.unesco.core.services.mapperService;

import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.enums.TaskStatusType;
import com.unesco.core.dto.file.FileByteCodeModel;
import com.unesco.core.dto.file.FileDescriptionModel;
import com.unesco.core.dto.journal.LessonEventDTO;
import com.unesco.core.dto.journal.PointDTO;
import com.unesco.core.dto.journal.PointTypeDTO;
import com.unesco.core.dto.news.NewsDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.dto.shedule.*;
import com.unesco.core.dto.task.TaskDescriptionModel;
import com.unesco.core.dto.task.TaskUserModel;
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

        if (model instanceof PairTypeDTO)
            return PairTypeToEntity((PairTypeDTO) model);

        return new Exception("Not found " + model.getClass().toString() + " model class");
    }

    public <T> Object toDto(T entity) {

        if (entity == null)
            return null;

        if (entity instanceof LessonEventEntity)
            return LessonEventToDto((LessonEventEntity) entity);

        if (entity instanceof PointEntity)
            return PointToDto((PointEntity) entity);

        if (entity instanceof PointTypeEntity)
            return PointTypeToDto((PointTypeEntity) entity);

        if (entity instanceof LessonEntity)
            return LessonToDto((LessonEntity) entity);

        if (entity instanceof ProfessorEntity)
            return ProfessorToDto((ProfessorEntity) entity);

        if (entity instanceof StudentEntity)
            return StudentToDto((StudentEntity) entity);

        if (entity instanceof PairEntity)
            return PairToDto((PairEntity) entity);

        if (entity instanceof RoleEntity)
            return RoleToDto((RoleEntity) entity);

        if (entity instanceof UserEntity)
            return UserToDto((UserEntity) entity);

        if (entity instanceof InstituteEntity)
            return InstituteToDto((InstituteEntity) entity);

        if (entity instanceof DepartmentEntity)
            return DepartmentToDto((DepartmentEntity) entity);

        if (entity instanceof GroupEntity)
            return GroupToDto((GroupEntity) entity);

        if (entity instanceof DisciplineEntity)
            return DisciplineToDto((DisciplineEntity) entity);

        if (entity instanceof FieldOfKnowledgeEntity)
            return FieldOfKnowledgeToDto((FieldOfKnowledgeEntity) entity );

        if (entity instanceof RoomEntity)
            return RoomToDto((RoomEntity) entity);

        if (entity instanceof TaskUser)
            return TaskUserToDto((TaskUser) entity);

        if (entity instanceof NewsEntity)
            return NewsToDto((NewsEntity) entity);

        if (entity instanceof TaskDescription)
            return TaskDescriptionToDto((TaskDescription) entity);

        if (entity instanceof FileByteCode)
            return FileByteCodeToDto((FileByteCode) entity);

        if (entity instanceof FileDescription)
            return FileDescriptionToDto((FileDescription) entity);

        if (entity instanceof PairTypeEntity)
            return PairTypeToDto((PairTypeEntity) entity);

        return new Exception("Not found " + entity.getClass().toString() + " entity class");
    }

    public PointDTO PointToDto(PointEntity Entity)
    {
        PointDTO Dto = new PointDTO();
        Dto.setId(Entity.getId());
        Dto.setStudent(StudentToDto(Entity.getStudent()));
        Dto.setValue(Entity.getValue());
        Dto.setPair(PairToDto(Entity.getPair()));
        Dto.setType(PointTypeToDto(Entity.getType()));
        Dto.setDate(Entity.getDate());
        return Dto;
    }
    public PointEntity PointToEntity(PointDTO Dto)
    {
        PointEntity Entity = new PointEntity();
        Entity.setId(Dto.getId());
        Entity.setStudent(StudentToEntity(Dto.getStudent()));
        Entity.setValue(Dto.getValue());
        Entity.setPair(PairToEntity(Dto.getPair()));
        Entity.setType(PointTypeToEntity(Dto.getType()));
        Entity.setDate(Dto.getDate());
        return Entity;
    }

    public LessonEventDTO LessonEventToDto(LessonEventEntity Entity)
    {
        LessonEventDTO Dto = new LessonEventDTO();
        Dto.setId(Entity.getId());
        Date Dt = new Date((long)Entity.getDate().getTime());
        Dto.setDate(Dt);
        Dto.setComment(Entity.getComment());
        Dto.setLesson(LessonToDto(Entity.getLesson()));
        Dto.setType(PointTypeToDto(Entity.getType()));
        return Dto;
    }
    public LessonEventEntity LessonEventToEntity(LessonEventDTO Dto)
    {
        LessonEventEntity Entity = new LessonEventEntity();
        Entity.setId(Dto.getId());
        Timestamp ts = new Timestamp(Dto.getDate().getTime());
        Entity.setDate(ts);
        Entity.setComment(Dto.getComment());
        Entity.setLesson(LessonToEntity(Dto.getLesson()));
        Entity.setType(PointTypeToEntity(Dto.getType()));
        return Entity;
    }

    public PointTypeDTO PointTypeToDto(PointTypeEntity Entity)
    {
        PointTypeDTO Dto = new PointTypeDTO();
        Dto.setId(Entity.getId());
        Dto.setName(Entity.getName());
        return Dto;
    }
    public PointTypeEntity PointTypeToEntity(PointTypeDTO Dto)
    {
        PointTypeEntity Entity = new PointTypeEntity();
        Entity.setId(Dto.getId());
        Entity.setName(Dto.getName());
        return Entity;
    }

    public TaskDescriptionModel TaskDescriptionToDto(TaskDescription Entity) {
        TaskDescriptionModel Dto = new TaskDescriptionModel();
        Dto.setId(Entity.getId());
        Dto.setCreator(UserToDto(Entity.getCreator()));
        Dto.setUsers(new ArrayList<>());
        Dto.setDescription(Entity.getDescription());
        Dto.setName(Entity.getName());
        Dto.setStatus(Entity.getStatus());
        Dto.setStatusName(TaskStatusType.values()[Entity.getStatus()].name());
        List<TaskUserModel> tasks = new ArrayList<>();
        /*for (Task t: Entity.getSubTasks()) {
            tasks.add(TaskToDto(t));
        }*/
        Dto.setTaskUsers(tasks);
        List<FileDescriptionModel> files = new ArrayList<>();
        for (FileDescription t: Entity.getFiles()) {
            files.add(FileDescriptionToDto(t));
        }
        Dto.setFiles(files);
        return Dto;
    }

    public TaskDescription TaskDescriptionToEntity(TaskDescriptionModel Dto) {
        TaskDescription Entity = new TaskDescription();
        Entity.setId(Dto.getId());
        Entity.setCreator(UserToEntity(Dto.getCreator()));
        Entity.setDescription(Dto.getDescription());
        Entity.setName(Dto.getName());
        List<TaskUser> tasks = new ArrayList<>();
        for (TaskUserModel t : Dto.getTaskUsers()) {
            tasks.add(TaskUserToEntity(t));
        }
        Entity.setTaskUsers(tasks);

        Set<FileDescription> files = new HashSet<>();
        for (FileDescriptionModel t : Dto.getFiles()) {
            files.add(FileDescriptionToEntity(t));
        }
        Entity.setFiles(files);
        return Entity;
    }

    public TaskUserModel TaskUserToDto(TaskUser Entity) {
        TaskUserModel Dto = new TaskUserModel();
        Dto.setId(Entity.getId());
        Dto.setExecutor(UserToDto(Entity.getExecutor()));
        Dto.setResponse(Entity.getResponse());
        Dto.setStatus(Entity.getStatus());
        Dto.setStatusName(TaskStatusType.values()[Entity.getStatus()].name());
        Dto.setTaskDescriptionId(Entity.getTaskDescription().getId());
        List<FileDescriptionModel> files = new ArrayList<>();
        for (FileDescription t: Entity.getFiles()) {
            files.add(FileDescriptionToDto(t));
        }
        Dto.setFiles(files);
        return Dto;
    }

    public TaskUser TaskUserToEntity(TaskUserModel Dto) {
        TaskUser Entity = new TaskUser();
        Entity.setId(Dto.getId());
        Entity.setExecutor(UserToEntity(Dto.getExecutor()));
        Entity.setResponse(Dto.getResponse());
        Entity.setStatus(Dto.getStatus());
        TaskDescription taskDescription = new TaskDescription();
        taskDescription.setId(Dto.getTaskDescriptionId());
        Entity.setTaskDescription(taskDescription);
        Set<FileDescription> files = new HashSet<>();
        for (FileDescriptionModel t : Dto.getFiles()) {
            files.add(FileDescriptionToEntity(t));
        }
        Entity.setFiles(files);
        return Entity;
    }

    public NewsDTO NewsToDto(NewsEntity Entity)
    {
        NewsDTO Dto = new NewsDTO();
        Dto.setId(Entity.getId());
        Dto.setAuthor(UserToDto(Entity.getAuthor()));
        Dto.setDate(Entity.getDate());
        Dto.setContent(Entity.getContent());
        Dto.setHeader(Entity.getHeader());
        Dto.setId(Entity.getId());
        Dto.setImage(new String(Entity.getImage(), StandardCharsets.UTF_8));
        Dto.setTags(Entity.getTags());
        return Dto;
    }
    public NewsEntity NewsToEntity(NewsDTO Dto)
    {
        NewsEntity Entity = new NewsEntity();
        Entity.setId(Dto.getId());
        Entity.setAuthor(UserToEntity(Dto.getAuthor()));
        Entity.setDate(Dto.getDate());
        Entity.setContent(Dto.getContent());
        Entity.setHeader(Dto.getHeader());
        Entity.setId(Dto.getId());
        Entity.setImage(Dto.getImage().getBytes());
        Entity.setTags(Dto.getTags());
        return Entity;
    }

    public ProfessorDTO ProfessorToDto(ProfessorEntity Entity)
    {
        ProfessorDTO Dto = new ProfessorDTO();
        Dto.setId(Entity.getId());
        Dto.setUser(UserToDto(Entity.getUser()));
        Dto.setDepartment((DepartmentDTO) toDto(Entity.getDepartment()));
        return Dto;
    }
    public ProfessorEntity ProfessorToEntity(ProfessorDTO Dto)
    {
        ProfessorEntity Entity = new ProfessorEntity();
        Entity.setId(Dto.getId());
        Entity.setUser(UserToEntity(Dto.getUser()));
        Entity.setDepartment((DepartmentEntity) toEntity(Dto.getDepartment()));
        return Entity;
    }

    public StudentDTO StudentToDto(StudentEntity Entity)
    {
        StudentDTO Dto = new StudentDTO();
        Dto.setId(Entity.getId());
        Dto.setUser(UserToDto(Entity.getUser()));
        Dto.setGroup((GroupDTO) toDto(Entity.getGroup()));
        return Dto;
    }
    public StudentEntity StudentToEntity(StudentDTO Dto)
    {
        StudentEntity Entity = new StudentEntity();
        Entity.setId(Dto.getId());
        Entity.setUser(UserToEntity(Dto.getUser()));
        Entity.setGroup((GroupEntity) toEntity(Dto.getGroup()));
        return Entity;
    }

    public RoomDTO RoomToDto(RoomEntity Entity)
    {
        RoomDTO Dto = new RoomDTO();
        Dto.setId(Entity.getId());
        Dto.setRoom(Entity.getRoom());
        return Dto;
    }
    public RoomEntity RoomToEntity(RoomDTO Dto)
    {
        RoomEntity Entity = new RoomEntity();
        Entity.setId(Dto.getId());
        Entity.setRoom(Dto.getRoom());
        return Entity;
    }

    public LessonDTO LessonToDto(LessonEntity Entity)
    {
        LessonDTO Dto = new LessonDTO();
        Dto.setId((int) Entity.getId());
        Dto.setDiscipline(DisciplineToDto(Entity.getDiscipline()));
        Dto.setGroup(GroupToDto(Entity.getGroup()));
        Dto.setProfessor(ProfessorToDto(Entity.getProfessor()));
        return Dto;
    }
    public LessonEntity LessonToEntity(LessonDTO Dto)
    {
        LessonEntity Entity = new LessonEntity();
        Entity.setId(Dto.getId());
        Entity.setDiscipline(DisciplineToEntity(Dto.getDiscipline()));
        Entity.setGroup(GroupToEntity(Dto.getGroup()));
        Entity.setProfessor(ProfessorToEntity(Dto.getProfessor()));
        return Entity;
    }

    public PairDTO PairToDto(PairEntity Entity)
    {
        PairDTO Dto = new PairDTO();
        Dto.setId((int) Entity.getId());
        Dto.setPairNumber(Entity.getPairNumber());
        Dto.setWeektype(Entity.getWeektype());
        Dto.setDayofweek(Entity.getDayofweek());
        Dto.setLesson(LessonToDto(Entity.getLesson()));
        Dto.setRoom(RoomToDto(Entity.getRoom()));
        Dto.setPairType(PairTypeToDto(Entity.getPairType()));
        return Dto;
    }
    public PairEntity PairToEntity(PairDTO Dto)
    {
        PairEntity Entity = new PairEntity();
        Entity.setId((int) Dto.getId());
        Entity.setPairNumber(Dto.getPairNumber());
        Entity.setWeektype(Dto.getWeektype());
        Entity.setDayofweek(Dto.getDayofweek());
        Entity.setLesson(LessonToEntity(Dto.getLesson()));
        Entity.setRoom(RoomToEntity(Dto.getRoom()));
        Entity.setPairType(PairTypeToEntity(Dto.getPairType()));
        return Entity;
    }
    
    public PairTypeDTO PairTypeToDto(PairTypeEntity Entity)
    {
        PairTypeDTO Dto = new PairTypeDTO();
        Dto.setId(Entity.getId());
        Dto.setType(Entity.getType());
        return Dto;
    }
    public PairTypeEntity PairTypeToEntity(PairTypeDTO Dto)
    {
        PairTypeEntity Entity = new PairTypeEntity();
        Entity.setId(Dto.getId());
        Entity.setType(Dto.getType());
        return Entity;
    }

    public UserEntity UserToEntity(UserDTO Dto)
    {
        UserEntity Entity = new UserEntity();

        Entity.setId(Dto.getId());
        Entity.setUsername(Dto.getUsername());
        Entity.setEmail(Dto.getEmail());
        Entity.setUserFIO(Dto.getUserFIO());
        Entity.setPassword(Dto.getPassword());
        byte[] photo = null;
        if(Dto.getPhoto() != null)
            photo = Dto.getPhoto().getBytes();
        Entity.setPhoto(photo);
        Set<RoleEntity> roleEntities = new HashSet<RoleEntity>();
        for (RoleDTO role: Dto.getRoles()) {
            RoleEntity roleEntityEntity = (RoleEntity) toEntity(role);
            roleEntities.add(roleEntityEntity);
        }
        Entity.setRole(roleEntities);

        return Entity;
    }
    public UserDTO UserToDto(UserEntity Entity)
    {
        UserDTO Dto = new UserDTO();

        Dto.setId(Entity.getId());
        Dto.setUsername(Entity.getUsername());
        Dto.setEmail(Entity.getEmail());
        Dto.setUserFIO(Entity.getUserFIO());
        Dto.setPassword(Entity.getPassword());
        String photo = "";
        if(Entity.getPhoto() != null)
            photo = new String(Entity.getPhoto(), StandardCharsets.UTF_8);
        Dto.setPhoto(photo);
        List<RoleDTO> roles = new ArrayList<>();
        for (RoleEntity roleEntity : Entity.getRole()) {
            RoleDTO roleDTO = (RoleDTO) toDto(roleEntity);
            roles.add(roleDTO);
        }
        Dto.setRoles(roles);
        return Dto;
    }

    public RoleDTO RoleToDto(RoleEntity Entity)
    {
        RoleDTO Dto = new RoleDTO();
        Dto.setId((int) Entity.getId());
        Dto.setRoleName(Entity.getRoleName());
        return Dto;
    }
    public RoleEntity RoleToEntity(RoleDTO Dto)
    {
        RoleEntity Entity = new RoleEntity();
        Entity.setId(Dto.getId());
        Entity.setRoleName(Dto.getRoleName());
        return Entity;
    }

    public InstituteDTO InstituteToDto(InstituteEntity Entity)
    {
        InstituteDTO Dto = new InstituteDTO();

        Dto.setId(Entity.getId());
        Dto.setName(Entity.getName());
        return Dto;
    }
    public InstituteEntity InstituteToEntity(InstituteDTO Dto)
    {
        InstituteEntity Entity = new InstituteEntity();

        Entity.setId(Dto.getId());
        Entity.setName(Dto.getName());
        return Entity;
    }

    public DepartmentDTO DepartmentToDto(DepartmentEntity Entity)
    {
        DepartmentDTO Dto = new DepartmentDTO();

        Dto.setId(Entity.getId());
        Dto.setName(Entity.getName());
        Dto.setInstitute((InstituteDTO) toDto(Entity.getInstitute()));
        return Dto;
    }
    public DepartmentEntity DepartmentToEntity(DepartmentDTO Dto)
    {
        DepartmentEntity Entity = new DepartmentEntity();
        Entity.setId(Dto.getId());
        Entity.setName(Dto.getName());
        Entity.setInstitute((InstituteEntity) toEntity(Dto.getInstitute()));
        return Entity;
    }

    public GroupDTO GroupToDto(GroupEntity Entity)
    {
        GroupDTO Dto = new GroupDTO();

        Dto.setName(Entity.getName());
        Dto.setId(Entity.getId());
        Dto.setDepartment((DepartmentDTO) toDto(Entity.getDepartment()));

        return Dto;
    }
    public GroupEntity GroupToEntity(GroupDTO Dto)
    {

        GroupEntity Entity = new GroupEntity();

        Entity.setName(Dto.getName());
        Entity.setId(Dto.getId());
        Entity.setDepartment((DepartmentEntity) toEntity(Dto.getDepartment()));

        return Entity;
    }

    private DisciplineDTO DisciplineToDto(DisciplineEntity Entity)
    {
        DisciplineDTO Dto = new DisciplineDTO();

        Dto.setId((int) Entity.getId());
        Dto.setName(Entity.getName());
        if(Entity.getFieldOfKnowledge() != null)
            Dto.setFieldOfKnowledge((FieldOfKnowledgeDTO) toDto(Entity.getFieldOfKnowledge()));

        return Dto;
    }
    private DisciplineEntity DisciplineToEntity(DisciplineDTO Dto)
    {
        DisciplineEntity Entity = new DisciplineEntity();

        Entity.setId(Dto.getId());
        Entity.setName(Dto.getName());
        if (Dto.getFieldOfKnowledge() != null) {
            Entity.setFieldOfKnowledge((FieldOfKnowledgeEntity) toEntity(Dto.getFieldOfKnowledge()));
        }

        return Entity;
    }

    private FieldOfKnowledgeDTO FieldOfKnowledgeToDto(FieldOfKnowledgeEntity Entity)
    {

        FieldOfKnowledgeDTO Dto = new FieldOfKnowledgeDTO();
        Dto.setName(Entity.getName());
        Dto.setId(Entity.getId());

        return Dto;
    }
    private FieldOfKnowledgeEntity FieldOfKnowledgeToEntity(FieldOfKnowledgeDTO Dto)
    {

        FieldOfKnowledgeEntity Entity = new FieldOfKnowledgeEntity();

        Entity.setName(Dto.getName());
        Entity.setId(Dto.getId());

        return Entity;
    }

    private FileByteCodeModel FileByteCodeToDto(FileByteCode entity){
        FileByteCodeModel Dto = new FileByteCodeModel();
        Dto.setData(entity.getData());
        Dto.setFileDescription((FileDescriptionModel) toDto(entity.getFileDescription()));
        Dto.setId(entity.getId());
        return Dto;
    }

    private FileByteCode FileByteCodeToEntity(FileByteCodeModel model) {
        FileByteCode Entity = new FileByteCode();
        Entity.setData(model.getData());
        Entity.setId(model.getId());
        Entity.setFileDescription((FileDescription) toEntity(model.getFileDescription()));
        return Entity;
    }

    private FileDescriptionModel FileDescriptionToDto(FileDescription entity){
        FileDescriptionModel Dto = new FileDescriptionModel();
        Dto.setFileName(entity.getFileName());
        Dto.setFileType(entity.getFileType());
        Dto.setId(entity.getId());
        return Dto;
    }

    private FileDescription FileDescriptionToEntity(FileDescriptionModel model) {
        FileDescription Entity = new FileDescription();
        Entity.setFileName(model.getFileName());
        Entity.setFileType(model.getFileType());
        Entity.setId(model.getId());
        return Entity;
    }
}
