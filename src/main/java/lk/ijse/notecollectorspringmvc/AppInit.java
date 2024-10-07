package lk.ijse.notecollectorspringmvc;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import lk.ijse.notecollectorspringmvc.config.WebAppConfig;
import lk.ijse.notecollectorspringmvc.config.WebAppRootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer { // extending this class, application context is defined

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebAppRootConfig.class}; // register the configuration class to the application context
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class}; // register the configuration class to the application context
    }

    @Override
    protected String[] getServletMappings() { // use this for creating dispatcher servlet
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {  // use this for handling multiple parts of client's requests
        registration.setMultipartConfig(new MultipartConfigElement("/tmp"));
        // multipart form data ekaka enne huge size data ekak.
        // ethakota e data process karanna ape file system eka athule folder ekak apita support karanava.
        // methana api denne e folder name eka. location eka "/tmp" denava
    }
}
