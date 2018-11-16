package amc.mb.rsassociations.configuration;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import amc.mb.rsassociations.enums.PredefinedDataSource;

@Configuration
@MapperScan(basePackages = { "amc.mb.rsassociations.persistence" })
public class DbConfig {

	private final static Logger LOGGER = LoggerFactory.getLogger(DbConfig.class);

	@Profile("dev")
	@Bean()
	public static DataSource devDataSource() {
		return PredefinedDataSource.POSTGRES_DATASOURCE.getDataSource();
	}

	@Profile("test")
	@Bean()
	public static DataSource testDataSource() {
		return PredefinedDataSource.POSTGRES_DATASOURCE.getDataSource();
	}

	@Profile({ "dev-local", "acceptatie", "productie" })
	@Bean()
	public static DataSource dataSource() {
		return PredefinedDataSource.POSTGRES_DATASOURCE.getDataSource();
	}

	@Bean()
	public static DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
