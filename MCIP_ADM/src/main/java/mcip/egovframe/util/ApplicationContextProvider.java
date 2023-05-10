package mcip.egovframe.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
 
public class ApplicationContextProvider implements ApplicationContextAware {
     
    private static ApplicationContext ctx = null;
 
    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
 
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        String[] names = ctx.getBeanDefinitionNames();
        for (int i=0; i<names.length; i++) {
            System.out.println("bean name : " + names[i]);
        }
    }
 
}

