package com.choosedormitory;

import org.json.JSONObject;

/**
 * Created by amoshua on 20/12/2017.
 */

public class Student {
    /**
     * errcode : 0
     * data : {"studentid":"1301210899","name":"暂时保密","gender":"女","vcode":"asdf12","room":"5101","building":"5","location":"大兴","grade":"2013"}
     */

    private int errcode;
    private DataBean data;

    public static Student getStudent(String jsonObject){
        return Util.GetGsonInstance().fromJson(jsonObject,Student.class);
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * studentid : 1301210899
         * name : 暂时保密
         * gender : 女
         * vcode : asdf12
         * room : 5101
         * building : 5
         * location : 大兴
         * grade : 2013
         */

        private String studentid;
        private String name;
        private String gender;
        private String vcode;
        private String room;
        private String building;
        private String location;
        private String grade;

        public String getStudentid() {
            return studentid;
        }

        public void setStudentid(String studentid) {
            this.studentid = studentid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getVcode() {
            return vcode;
        }

        public void setVcode(String vcode) {
            this.vcode = vcode;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }
}
