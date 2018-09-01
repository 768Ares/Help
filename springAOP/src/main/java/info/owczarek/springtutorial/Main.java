package info.owczarek.springtutorial;

import info.owczarek.springtutorial.api.UsersRepository;

import info.owczarek.springtutorial.implementations.UsersRepositoryImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		context.registerShutdownHook();

		UsersRepository usersRepository = context.getBean("usersRepository", UsersRepository.class);
		usersRepository.createUser("Dawid");
		try {
			usersRepository.crashMethod(null);
		} catch (Exception e) {}
		context.close();
	}
}