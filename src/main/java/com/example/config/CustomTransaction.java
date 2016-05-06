package com.example.config;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.TransactionAnnotationParser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.domain.service.TodoServiceFacade2;

@Configuration
@PropertySource("classpath:/timeout.properties")
public class CustomTransaction implements InitializingBean {

	private int[] timeout;

	@Autowired
	private Environment env;
	
	@SuppressWarnings("unchecked")
	public void changeAnnotationValue(Annotation annotation, String key, int index) {

		Object handler = Proxy.getInvocationHandler(annotation);
		Field f;
		try {
			f = handler.getClass().getDeclaredField("memberValues");
		} catch (NoSuchFieldException | SecurityException e) {
			throw new IllegalStateException(e);
		}
		f.setAccessible(true);
		Map<String, LinkedHashMap<String, Object>> memberValues;
		try {
			memberValues = (Map<String, LinkedHashMap<String, Object>>) f.get(handler);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
		Transactional values = (Transactional) memberValues.get("transactional");
		
		/**
		 * 値を渡したいが左辺が変数でないのでエラーになる
		 */
		values.timeout() = timeout[index];

	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface TransacationalA {
		Transactional transactional();
		int source();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		timeout = env.getProperty("serviceFacade.timeout", int[].class);
		final TransacationalA classAnnotation = TodoServiceFacade2.class.getAnnotation(TransacationalA.class);
		changeAnnotationValue(classAnnotation, "timeout", classAnnotation.source());
	}
}