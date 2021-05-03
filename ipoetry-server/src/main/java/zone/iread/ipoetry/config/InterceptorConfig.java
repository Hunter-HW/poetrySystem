package zone.iread.ipoetry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zone.iread.ipoetry.interceptors.JWTInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/userLogin");//用户的请求都放行，其他接口token验证
    }
}
