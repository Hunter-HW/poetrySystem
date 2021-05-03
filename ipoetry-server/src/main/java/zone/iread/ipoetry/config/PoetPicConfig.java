package zone.iread.ipoetry.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 定位各种图片或资源地址
 */
@Configuration
public class PoetPicConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //诗人头像定位
        registry.addResourceHandler("/img/poetPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"poetPic" +System.getProperty("file.separator")
        );
        //诗词图片定位
        registry.addResourceHandler("/img/poemPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"poemPic" +System.getProperty("file.separator")
        );

        //分类图片定位
        registry.addResourceHandler("/img/poemTypePic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"poemTypePic" +System.getProperty("file.separator")
        );

        //用户头像定位
        registry.addResourceHandler("/img/userPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"userPic" +System.getProperty("file.separator")
        );
    }
}
