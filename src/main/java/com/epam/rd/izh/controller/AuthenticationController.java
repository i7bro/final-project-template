package com.epam.rd.izh.controller;

import com.epam.rd.izh.dao.UserDao;

import javax.validation.Valid;

import com.epam.rd.izh.dto.UserLoginDto;
import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.service.UserService;
import com.epam.rd.izh.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * В аргументы контроллеров, которые обрабатывают запросы, можно указать дополнительные входные параметры: Например:
 * HttpServletRequest и HttpServletResponse. Данные объекты автоматически заполняться данными о реквесте и респонсе. Эти
 * данные можно использовать, например достать куки, сессию, хедеры итд.
 */

@Controller
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Метод, отвечающий за логику авторизации пользователя.
     * /login - определяет URL, по которому пользователь должен перейти, чтобы запустить данный метод-обработчик.
     */
    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String error, @RequestParam(required = false) String success) {
        if (error != null) {
            /**
             * Model представляет из себя Map коллекцию ключ-значения, распознаваемую View элементами MVC.
             * Добавляется String "invalid login or password!", с ключем "error_login_placeholder".
             * При создании View шаблона плейсхолдер ${error_login_placeholder} будет заменен на переданное значение.
             *
             * В класс Model можно передавать любые объекты, необходимые для генерации View.
             */
            model.addAttribute("error_login_placeholder", "invalid login or password!");
        }
        if (success != null) {
            model.addAttribute("success", success);
        }
        return "login";
    }

    /**
     * Метод, отвечающий за логику регистрации пользователя.
     */
    @GetMapping("/registration")
    public String viewRegistration(Model model, @RequestParam(required = false) String error) {

        if (error != null) {
            model.addAttribute("error_login_exists", error);
        }
        if (!model.containsAttribute("registrationForm")) {
            model.addAttribute("registrationForm", new User());
        }
        return "registration";
    }

    /**
     * Метод, отвечающий за подтверждение регистрации пользователя и сохранение данных в репозиторий или DAO.
     */
    @PostMapping("/registration/proceed")
    public String processRegistration(@Valid @ModelAttribute("registrationForm") User registeredUser,
                                      BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (userService.hasSameLogin(registeredUser)) {
            ObjectError error = new ObjectError("login_already_exists", "Login already exists, please, create new login");
            bindingResult.addError(error);
            redirectAttributes.addAttribute("error", "Login already exists, please, create new login");
        }

        if (bindingResult.hasErrors()) {
            return "redirect:/registration";
        }
        /**
         * Здесь происходит присвоение роли пользователю и шифрование пароля.
         * Роль может быть так же определена пользователем на этапе регистрации, либо иным способов, зависящим
         * от темы финального проекта.
         */
        registeredUser.setRole(Role.USER);
        registeredUser.setPassword(passwordEncoder.encode(registeredUser.getPassword()));

        /**
         * Добавление пользователя в репозиторий или в базу данных через CRUD операции DAO.
         * Рекомендуется вынести эту логику на сервисный слой.
         */
        userService.saveUser(registeredUser);
        /**
         * В случае успешной регистрации редирект можно настроить на другой энд пойнт.
         */
        redirectAttributes.addAttribute("success", "Registration is successful. Enter your login and password");
        return "redirect:/login";
    }

}