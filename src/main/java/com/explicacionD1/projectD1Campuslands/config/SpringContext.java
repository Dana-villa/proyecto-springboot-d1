package com.explicacionD1.projectD1Campuslands.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    // Este método nos permitirá sacar cualquier Repositorio o Servicio en clases donde no funciona @Autowired
    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}