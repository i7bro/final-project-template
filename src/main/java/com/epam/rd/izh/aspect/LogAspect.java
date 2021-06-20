package com.epam.rd.izh.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
@Slf4j
public class LogAspect {

    @After("execution(* com.epam.rd.izh.service.UserDetailsServiceMapper.loadUserByUsername(..))")
    public void beforeServiceMethods(JoinPoint jp) {
        log.info("user " + jp.getArgs()[0] + " try to login");
    }
}
