package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@PropertySource("classpath:/timeout.properties")
public class AppConfig {

	@Value(value = "${serviceFacade2.timeout}")
	int timuout2;
	@Value(value = "${serviceFacade3.timeout}")
	int timuout3;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public TransactionTemplate setTransactionTemplateToFacade2(PlatformTransactionManager transactionManager) {
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		transactionTemplate.setReadOnly(true);
		transactionTemplate.setTimeout(timuout2);
		return transactionTemplate;
	}

	@Bean
	public TransactionTemplate setTransactionTemplateToFacade3(PlatformTransactionManager transactionManager) {
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		transactionTemplate.setReadOnly(true);
		transactionTemplate.setTimeout(timuout3);
		return transactionTemplate;
	}
}
