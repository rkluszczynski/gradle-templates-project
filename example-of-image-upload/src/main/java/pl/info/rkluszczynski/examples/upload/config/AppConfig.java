package pl.info.rkluszczynski.examples.upload.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
//import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import pl.info.rkluszczynski.examples.upload.validator.FileValidator;

//@Configuration
//@ComponentScan(basePackages = { "pl.info.rkluszczynski.examples.upload" })
//@EnableWebMvc
public class AppConfig { //extends WebMvcConfigurerAdapter {

//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
//
//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/pages/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
//
//    @Bean
//    public FileValidator fileValidator() {
//        return new FileValidator();
//    }
//
//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver commonsMultipartResolver() {
//        return new CommonsMultipartResolver();
//    }
}
