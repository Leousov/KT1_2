package com.leo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
public class CUser{
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = true, nullable = false)
    public UUID id;
    @Column(name = "login", updatable = true)
    private String login;
    @Column(name = "name", updatable = true)
    private String name;
    @Column(name = "gender", updatable = true)
    private boolean gender;
    @Column(name = "datebirth", updatable = true, columnDefinition = "Date")
    private LocalDate dateBirth;
    // ID functions
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    // LOGIN functions
    public String getLogin() {
        return login;
    }
    /*public void setLogin(String login) {
        if ((login.length() > 3) && (login.length() < 32)){
            this.login = login;
        }
    }*/
    public void setLogin(String login) {
        this.login = login;
    }
    // NAME functions
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    // GENDER functions
    public boolean getGender(){
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }
    // DATEBIRTH functions
    public LocalDate getDateBirth() {
        return dateBirth;
    }
    /*public void setDateBirth(LocalDate dateBirth) {
        int age = CUser.Age(dateBirth);
        if (age > 6 && age < 150){
            this.dateBirth = dateBirth;
        }
    }*/
    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }
    public static int Age(LocalDate date ){
        LocalDate now = LocalDate.now();
        return now.getYear() - date.getYear();
    }
    public int Age(){
        LocalDate now = LocalDate.now();
        return now.getYear() - this.dateBirth.getYear();
    }
    // CONSTRUCTOR
    public CUser(UUID id, String login, String name, boolean gender, LocalDate dateBirth){
        setId(id);
        setLogin(login);
        setName(name);
        setGender(gender);
        setDateBirth(dateBirth);
    }
    public CUser(){
        setName("a");
        setDateBirth(LocalDate.now());
        setGender(true);
        setLogin("a");
        id = UUID.randomUUID();
    }
}