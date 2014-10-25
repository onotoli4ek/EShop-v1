package aspect;


import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

public class SimpleLogger {
    public Object log(ProceedingJoinPoint call) throws Throwable {
        try {
            return call.proceed();
        } finally {
            System.out.println("ASPECT.LOGGER: " + call.toShortString() + " called. args = " + Arrays.toString(call.getArgs()));
        }
    }
}
