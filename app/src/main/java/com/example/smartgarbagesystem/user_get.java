package com.example.smartgarbagesystem;

public class user_get {

    public String name;
    public String address;
    public String phone;
    public String email;
    public String pin;
    public String age;
    public int status;
    public int env;

    public user_get(){
             //this.name="Sparshr";
    }

    public user_get(String name, String address, String phone, String email, String pin, String age,int status,int env){

        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.pin = pin;
        this.age = age;
        this.status = status;
        this.env=env;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getStatus(){
        return status;
    }
   public void setStatus(int status){
        this.status = status;
    }
    public int getEnv(){return env;}
    public void setEnv(int env)
    {
        this.env=env;
    }

}