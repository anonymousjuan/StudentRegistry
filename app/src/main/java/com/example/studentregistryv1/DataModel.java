package com.example.studentregistryv1;

public class DataModel {
    private Integer keyID;
    private String studentName;
    private String studentID;
    private String sex;

    public DataModel(Integer keyID, String studentName, String studentID, String sex) {
        this.keyID = keyID;
        this.studentName = studentName;
        this.studentID = studentID;
        this.sex = sex;
    }

    public DataModel() {
    }

    public int getKeyID() {
        return keyID;
    }

    public void setKeyID(int keyID) {
        this.keyID = keyID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "keyID=" + keyID +
                ", studentName='" + studentName + '\'' +
                ", studentID='" + studentID + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
