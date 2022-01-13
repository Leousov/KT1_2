package com.leo.tests;

import com.leo.CUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CUserTest {

    @Test
    void getId() {
        CUser user = new CUser();
        UUID one = UUID.randomUUID();
        user.setId(one);
        Assertions.assertEquals(one, user.getId());
    }

    @Test
    void getLogin() {
        CUser user = new CUser();
        String one = "asdfg";
        user.setLogin(one);
        Assertions.assertEquals(one, user.getLogin());
    }

    @Test
    void getName() {
        CUser user = new CUser();
        String one = "qwert";
        user.setName(one);
        Assertions.assertEquals(one, user.getName());
    }

    @Test
    void getGender() {
        CUser user = new CUser();
        boolean one = true;
        user.setGender(one);
        Assertions.assertEquals(one, user.getGender());
    }

    @Test
    void getDateBirth() {
        CUser user = new CUser();
        LocalDate one = LocalDate.now();
        user.setDateBirth(one);
        Assertions.assertEquals(one, user.getDateBirth());
    }

    @Test
    void age() {
        CUser user = new CUser();
        Assertions.assertEquals(0, user.Age());
    }
}