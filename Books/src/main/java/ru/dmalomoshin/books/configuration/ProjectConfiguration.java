package ru.dmalomoshin.books.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "ru.dmalomoshin.books")
@EnableAspectJAutoProxy
public class ProjectConfiguration {
}
