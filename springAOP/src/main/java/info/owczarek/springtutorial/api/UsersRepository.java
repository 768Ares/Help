package info.owczarek.springtutorial.api;

import info.owczarek.springtutorial.domain.User;

import javax.annotation.PreDestroy;

public interface UsersRepository {
	User createUser(String name);
	String crashMethod(RuntimeException ex);
	void setLogger(Logger logger);

    void initIt();

	void cleanUp();
}
