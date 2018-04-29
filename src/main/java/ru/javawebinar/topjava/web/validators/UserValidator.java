package ru.javawebinar.topjava.web.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;


@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserTo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserTo user = (UserTo) o;

        User existedUser = userService.getByEmail(user.getEmail());
        if (existedUser != null) {
            errors.rejectValue("email", "existed.user.email");
        }
    }
}
