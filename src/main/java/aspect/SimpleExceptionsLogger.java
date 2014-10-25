package aspect;

import org.aspectj.lang.JoinPoint;

public class SimpleExceptionsLogger {

    public void logException(JoinPoint joinPoint, Throwable t){
        System.out.println("EXCEPTION.LOGGER: " + t.getMessage());
    }
}
