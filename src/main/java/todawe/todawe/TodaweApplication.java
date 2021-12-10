package todawe.todawe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.querydsl.jpa.impl.JPAQueryFactory"})
public class TodaweApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodaweApplication.class, args);
	}

}
