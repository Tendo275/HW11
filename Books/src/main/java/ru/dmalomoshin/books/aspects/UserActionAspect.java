package ru.dmalomoshin.books.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class UserActionAspect {

    @Before("@annotation(TrackUserAction)")
    public void beforeUserAction(JoinPoint joinPoint) {
        System.out.printf("Пользователь вызывает метод \"%s(%s)\"\n",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()).replaceAll("[\\[\\]]", ""));
    }

    @After("@annotation(TrackUserAction)")
    public void afterUserAction(JoinPoint joinPoint) {
        System.out.printf("Пользователь успешно вызвал метод \"%s(%s)\"\n\n",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()).replaceAll("[\\[\\]]", ""));
    }

}
