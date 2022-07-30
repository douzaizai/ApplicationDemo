package com.example.demo.aspect;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志切面类
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

  @Pointcut("execution(* com..*.*Controller.*(..)) || @annotation(com.example.demo.annotation.Logging)")
  public void pointcut(){
  }

  @Around("pointcut()")
  public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
    Object[] args = pjp.getArgs();
    String[] argNames = ((MethodSignature) pjp.getSignature()).getParameterNames();
    Map<String, Object> paramsMap = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    if (args != null && args.length > 0) {
      for (int i = 0; i < args.length; i++) {
        paramsMap.put(argNames[i], args[i] != null ? args[i].toString() : "");
      }

      for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
        sb.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
      }
    }
    log.info("method:{},request params:{}", pjp.getSignature().toLongString(), sb);
    Object result = pjp.proceed();
    Gson gson = new Gson();
    log.info("response:{}", gson.toJson(result));
    return result;
  }
}