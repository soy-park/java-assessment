package example.com.assessment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import example.com.assessment.interceptor.MyInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Autowired
  private MyInterceptor myInterceptor;

  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(myInterceptor)
      .addPathPatterns("/")
      .excludePathPatterns("/login", "/register");
  }
}
