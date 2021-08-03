package com.epam.rd.izh.service.impl;

import com.epam.rd.izh.dto.UserPasswordDto;
import com.epam.rd.izh.dto.UserSettingsDto;
import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.service.UserService;
import com.epam.rd.izh.util.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    private static final String FAIL_LOGIN = "11";
    private static final User NEW_USER = User.builder()
            .email("test")
            .firstName("test")
            .lastName("test")
            .login("test")
            .phone("test")
            .role(Role.USER)
            .password("test")
            .build();
    private static final UserSettingsDto USER_SETTINGS_DTO =
            new UserSettingsDto(1, "test1", "test", "test", "test", "test");

    @Test
    void test_getUserByLogin() {
        assertNotNull(userService.getUserByLogin("2"));
        assertThrows(UsernameNotFoundException.class, () -> userService.getUserByLogin(FAIL_LOGIN));
    }

    @Test
    void test_saveUser_with_USER() {
        int size = userService.getAll().size();
        userService.saveUser(NEW_USER);
        assertEquals(size + 1, userService.getAll().size());
        assertEquals(NEW_USER.getLogin(), userService.getUserByLogin(NEW_USER.getLogin()).getLogin());
    }

    @Test
    void testSaveUser_with_User_settings_dto() {
        userService.saveUser(USER_SETTINGS_DTO);
        assertEquals(USER_SETTINGS_DTO.getLogin(), userService.getUserById(USER_SETTINGS_DTO.getId()).getLogin());
    }

    @Test
    void hasSameLogin() {
        assertTrue(userService.hasSameLogin("2"));
    }

    @Test
    void getUserById() {
        assertNotNull(userService.getUserById(1));
    }

    @Test
    void checkPassword() {
        assertTrue(userService.checkPassword(new UserPasswordDto(1, "1", "2")));
    }

    @Test
    void getUserPasswordDto() {
        assertNotNull(userService.getUserPasswordDto(1));
    }

    @Test
    void testGetUserPasswordDto() {
        assertNotNull(userService.getUserPasswordDto("2"));
    }
}
