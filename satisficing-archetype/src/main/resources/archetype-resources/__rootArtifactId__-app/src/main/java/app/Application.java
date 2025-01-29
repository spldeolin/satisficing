#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ${author} ${today}
 */
@ComponentScan("${package}.app")
@MapperScan("${package}.app.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("curl --request POST --url 'http://localhost:2333/sampleMethod' --header 'Content-Type: application/json' --data '{ \"name\": \"${author}\" }'");
    }

}
