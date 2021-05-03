package zone.iread.ipoetry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@MapperScan("zone.iread.ipoetry.mapper")
@EnableOpenApi
public class IpoetryApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpoetryApplication.class, args);
    }

}
