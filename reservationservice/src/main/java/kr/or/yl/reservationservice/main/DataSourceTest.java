package kr.or.yl.reservationservice.main;

import java.sql.Connection;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.yl.reservationservice.config.ApplicationConfig;

public class DataSourceTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			if (connection != null) {
				System.out.println("접속 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
