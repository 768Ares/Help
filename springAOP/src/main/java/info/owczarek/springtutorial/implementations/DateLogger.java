package info.owczarek.springtutorial.implementations;

import info.owczarek.springtutorial.TestTargetAnnotation;
import info.owczarek.springtutorial.api.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@TestTargetAnnotation
public class DateLogger implements Logger {
	public void log(String message) {
		System.out.println(new Date() + ": " + message);
	}
}
