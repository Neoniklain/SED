package com.unesco.core.controller;

import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.managers.moodle.interfaces.IMoodleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoodleController {

    @Autowired
    IMoodleManager _moodle;

    public ResponseStatusDTO GetAllCourses() {
        ResponseStatusDTO result = new ResponseStatusDTO();
        result.setStatus(StatusTypes.ERROR);
        try{
            result.setData(_moodle.GetAllCourses());
            result.setStatus(StatusTypes.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO GetAllUsers() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.GetAllUsers());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO GetAllGroups() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.GetAllGroups());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO CreateGroups() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.CreateGroups());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO CreateCourses() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.CreateCourses());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO CreateStudents() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.CreateStudents());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }

    public ResponseStatusDTO AssignStudentsOnGroups() {
        ResponseStatusDTO result = new ResponseStatusDTO();

        try{
            result.setData(_moodle.AssignStudentsOnGroups());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setData(e.getMessage());
            result.addErrors("Exception при вызове moodle rest api");
            e.printStackTrace();
        }
        return result;
    }
}
