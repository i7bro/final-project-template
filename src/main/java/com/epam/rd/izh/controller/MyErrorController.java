package com.epam.rd.izh.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int code = Integer.parseInt(status.toString());

            if (code == HttpStatus.NOT_FOUND.value()) {
                return "error/error-404";
            }

            if (code == HttpStatus.FORBIDDEN.value()) {
                return "error/error-403";
            }
        }

        return "";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
