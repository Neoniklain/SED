package com.unesco.core.services.dataService.moodleService;

import com.unesco.core.dto.account.StudentDTO;
import com.unesco.core.dto.shedule.GroupDTO;
import com.unesco.core.repositories.moodlerest.*;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MoodleService implements IMoodleService
{
    MoodleService() {
        // Токкен авторизации, для доступа к API Moodle
        MoodleCallRestWebService.setAuth("0696880e69359bb66a713931a44c9974");
        // Адрес Moodle
        MoodleCallRestWebService.setURL("http://localhost/moodle/webservice/rest/server.php");
    }

    public MoodleCourse[] GetAllCourses() {
        MoodleCourse[] result = null;
        try{
            result = MoodleRestCourse.getAllCourses();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public MoodleUser[] GetAllUsers() {
        MoodleUser[] result = null;
        try{
            Criteria[] criteria = new Criteria[1];
            criteria[0] = new Criteria("email","%%");
            result = MoodleRestUser.getUsers(criteria);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public MoodleCohort[] GetAllGroups() {
        MoodleCohort[] result = null;
        try {
            Long[] CohortIds = new Long[0];
            result = MoodleRestCohort.getCohorts(CohortIds);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public MoodleCourse[] CreateCourses() {
        MoodleCourse[] result = null;
        try{
            // 12 - id Иванова
            /*List<PairDTO> pairs = pairDataService.getAllByProfessor(12);
            List<DisciplineDTO> discs = new ArrayList<>();
            for (PairDTO pair: pairs) {
                Optional<DisciplineDTO> matchingObject = discs.stream().
                        filter(p -> p.getId() == pair.getLesson().getDiscipline().getId()).
                        findFirst();
                if(!matchingObject.isPresent()) {
                    discs.add(pair.getLesson().getDiscipline());
                }
            }*/

            MoodleCourse[] allCourses = this.GetAllCourses();
            if(allCourses == null) {
                return result;
            }

            List<MoodleCourse> listCourses = new ArrayList<>(Arrays.asList(allCourses));

            try{
                List<MoodleCourse> courses = new ArrayList<>();
                Long catId = Long.valueOf(2);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                Date startDate = dateFormat.parse("01/09/2018");
                long start = startDate.getTime();
                // Убираем три нуля в концеъ
                start = start/1000;
                // 1546210800 - пример корректной даты

                Date endDate = dateFormat.parse("30/12/2018");
                long end = endDate.getTime();
                // Убираем три нуля в концеъ
                end = end/1000;
                // 1546210800 - пример корректной даты

                /*for (DisciplineDTO disc: forCreate) {
                    MoodleCourse course = new MoodleCourse();
                    course.setFullname(disc.getName());
                    course.setShortname(disc.getName());
                    course.setCategoryId(catId);
                    course.setStartDate(start);
                    course.setEndDate(end);
                    course.setNumSections(1);
                    course.setVisible(true);
                    courses.add(course);
                }*/

                if(courses.size()>0) {
                    MoodleCourse[] forCreateCourses = courses.toArray(new MoodleCourse[courses.size()]);
                    // result = MoodleRestCourse.createCourses(forCreateCourses);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                result = null;
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public MoodleUser[] CreateStudents(List<StudentDTO> students) {
        MoodleUser[] result = null;
        try{
            MoodleUser[] allUsers = this.GetAllUsers();
            List<StudentDTO> notCreatedStudents = new ArrayList<>();
            if(allUsers != null) {
                List<MoodleUser> allUsersFromMoodle = Arrays.asList(allUsers);
                for (StudentDTO student : students) {
                    Optional<MoodleUser> matchingObject = allUsersFromMoodle.stream()
                            .filter(p -> student.getUser().getEmail().toUpperCase().equals(p.getEmail().toUpperCase()))
                            .findFirst();
                    if (!matchingObject.isPresent()) {
                        notCreatedStudents.add(student);
                    }
                }
            }
            else {
                notCreatedStudents = students;
            }

            List<MoodleUser> forCreateUsersList = new ArrayList<>();
            for (StudentDTO student: notCreatedStudents) {
                MoodleUser user = new MoodleUser();
                user.setFullname(student.getUser().getUserFIO());
                user.setFirstname(this.GetFirstName(student.getUser().getUserFIO()));
                user.setLastname(this.GetLastName(student.getUser().getUserFIO()));
                user.setEmail(student.getUser().getEmail());
                user.setPassword("Default!3000");
                user.setUsername(student.getUser().getUsername());
                forCreateUsersList.add(user);
            }

            if(forCreateUsersList.size()>0) {
                // ↓ Построчное создание
                /*List<MoodleUser> resultList = new ArrayList<>();
                for (MoodleUser forCreate: forCreateUsersList) {
                    try {
                        resultList.add(MoodleRestUser.createUser(forCreate));
                        System.out.println(forCreate.getFullname());
                        System.out.println("Нормально создан в Moodle");
                        System.out.println("-----------------------------------------");
                    }
                    catch (Exception e) {
                        System.out.println(forCreate.getFullname());
                        System.out.println(e.getMessage());
                        System.out.println("-----------------------------------------");
                    }
                }
                result = resultList.toArray(new MoodleUser[resultList.size()]);*/
                // ↑ Построчное создание
                MoodleUser[] forCreateUsersArray = forCreateUsersList.toArray(new MoodleUser[forCreateUsersList.size()]);
                result = MoodleRestUser.createUsers(forCreateUsersArray);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public boolean AddUsersToCohorts(List<StudentDTO> students) {
        boolean result = false;
        try{

            if(students == null) {
                return false;
            }
            MoodleCohort[] allCohortsFromMoodle = this.GetAllGroups();
            if(allCohortsFromMoodle == null) {
                return false;
            }
            List<MoodleCohort> moodleCohorts = Arrays.asList(allCohortsFromMoodle);
            MoodleUser[] allUsersFromMoodle = this.GetAllUsers();
            if(allUsersFromMoodle == null) {
                return false;
            }
            List<MoodleUser> allMoodleUsers = Arrays.asList(allUsersFromMoodle);

            List<CohortMember> forAddUsersToCohorts = new ArrayList<>();
            for(StudentDTO student: students) {
                Optional<MoodleCohort> cohortExist = moodleCohorts.stream()
                        .filter(p -> p.getName().toUpperCase().equals(student.getGroup().getName().toUpperCase()))
                        .findFirst();
                if (cohortExist.isPresent()) {
                    Optional<MoodleUser> userExist = allMoodleUsers.stream()
                            .filter(p -> p.getEmail().toUpperCase().equals(student.getUser().getEmail().toUpperCase()))
                            .findFirst();
                    if (userExist.isPresent()) {
                        CohortMember cm = new CohortMember();
                        cm.setCohortTypeId(CohortTypeId.ID_NUMBER);
                        cm.setCohortIdValue(cohortExist.get().getIdNumber());
                        cm.setUserTypeId(UserTypeId.USERNAME);
                        cm.setUserIdValue(student.getUser().getUsername());
                        forAddUsersToCohorts.add(cm);
                    }
                }
            }

            if(forAddUsersToCohorts.size()>0) {
                // ↓ Построчное создание
                /*for (CohortMember forCreate: forAddUsersToCohorts) {
                    try {
                        MoodleRestCohort.addCohortMember(forCreate);
                        System.out.println(forCreate.getUserIdValue());
                        System.out.println("Нормально привязан к группе в Moodle");
                        System.out.println(forCreate.getCohortIdValue());
                        System.out.println("-----------------------------------------");
                    }
                    catch (Exception e) {
                        System.out.println(forCreate.getUserIdValue());
                        System.out.println(e.getMessage());
                        System.out.println("-----------------------------------------");
                    }
                }*/
                // ↑ Построчное создание
                CohortMember[] forCM = forAddUsersToCohorts.toArray(new CohortMember[forAddUsersToCohorts.size()]);
                MoodleRestCohort.addCohortMembers(forCM);
            }

            result = true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    public MoodleCohort[] CreateGroups(List<GroupDTO> groups) {
        MoodleCohort[] result = null;
        try{
            MoodleCohort[] allGroupsFromMoodleArray = this.GetAllGroups();
            List<GroupDTO> notCreatedGroups = new ArrayList<>();
            if(allGroupsFromMoodleArray != null) {
                List<MoodleCohort> allGroupsFromMoodle = Arrays.asList(allGroupsFromMoodleArray);
                for (GroupDTO group : groups) {
                    Optional<MoodleCohort> matchingObject = allGroupsFromMoodle.stream()
                            .filter(p -> group.getName().toUpperCase().equals(p.getName().toUpperCase()))
                            .findFirst();
                    if (!matchingObject.isPresent()) {
                        notCreatedGroups.add(group);
                    }
                }
            }
            else {
                notCreatedGroups = groups;
            }

            List<MoodleCohort> forCreateCohortList = new ArrayList<>();
            for (GroupDTO group: notCreatedGroups) {
                MoodleCohort cohort = new MoodleCohort();
                cohort.setName(group.getName());
                cohort.setIdNumber(group.getName());
                forCreateCohortList.add(cohort);
            }

            if(forCreateCohortList.size()>0) {
                // ↓ Построчное создание
                /*List<MoodleCohort> resultList = new ArrayList<>();
                for (MoodleCohort forCreate: forCreateCohortList) {
                    try {
                        resultList.add(MoodleRestCohort.createCohort(forCreate));
                        System.out.println(forCreate.getName());
                        System.out.println("Нормально создана в Moodle");
                        System.out.println("-----------------------------------------");
                    }
                    catch (Exception e) {
                        System.out.println(forCreate.getName());
                        System.out.println(e.getMessage());
                        System.out.println("-----------------------------------------");
                    }
                }
                result = resultList.toArray(new MoodleCohort[resultList.size()]);*/
                // ↑ Построчное создание
                MoodleCohort[] forCreateCohortArray = forCreateCohortList.toArray(new MoodleCohort[forCreateCohortList.size()]);
                result = MoodleRestCohort.createCohorts(forCreateCohortArray);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Получить имя
     * @param fullname Ф.И.О.
     * @return
     */
    private String GetFirstName(String fullname) {
        String result = "";
        try {
            String[] words = fullname.split(" ");
            if (words.length >= 2) {
                return words[1];
            }
        }
        catch (Exception ex) {
            result = "";
        }
        return result;
    }

    /**
     * Получить фамилию
     * @param fullname Ф.И.О.
     * @return
     */
    private String GetLastName(String fullname) {
        String result = "";
        try {
            String[] words = fullname.split(" ");
            if (words.length > 0) {
                return words[0];
            }
        }
        catch (Exception ex) {
            result = "";
        }
        return result;
    }

    /**
     * Получить отчество
     * @param fullname Ф.И.О.
     * @return
     */
    private String GetMiddleName(String fullname) {
        String result = "";
        try {
            String[] words = fullname.split(" ");
            if (words.length >= 3) {
                return words[2];
            }
        }
        catch (Exception ex) {
            result = "";
        }
        return result;
    }
}
