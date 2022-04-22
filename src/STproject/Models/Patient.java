/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package STproject.Models;

public class Patient {

    private String cprNumber;
    private String name;
    private int age;
    private String gender;

    public Patient(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    public Patient() {
    }

    public String getCprNumber() {
        return cprNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setCprNumber(String cprNumber) {
        this.cprNumber = cprNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
