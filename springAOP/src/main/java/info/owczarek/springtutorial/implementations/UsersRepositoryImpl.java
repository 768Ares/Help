package info.owczarek.springtutorial.implementations;

import info.owczarek.springtutorial.TestAnnotation;
import info.owczarek.springtutorial.api.Logger;
import info.owczarek.springtutorial.api.UsersRepository;
import info.owczarek.springtutorial.domain.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service(value = "usersRepository")
public class UsersRepositoryImpl implements UsersRepository{
	@Autowired
    private Logger logger;

    @TestAnnotation
	public User createUser(String name) {
		logger.log("Tworzenie użytkownika " + name);
		return new User(name);
	}

    public String crashMethod(RuntimeException ex) {

            throw new RuntimeException("exception !!!!!!!!!!!!!!");
    }

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

    @PostConstruct
    public void initIt() {
        System.out.println("Init method after properties are set : ");
    }
    @PreDestroy
    public void cleanUp() {
        System.out.println("Spring Container is destroy! Customer clean up");
    }

}
