package amc.mb.rsassociations.enums;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;

import com.zaxxer.hikari.HikariDataSource;

public enum PredefinedDataSource {

	POSTGRES_DATASOURCE("postgres"),
	IDS("ids");

	private final String key;

	private String driverClassName;

	private String jdbcUrl;

	private String databaseName;

	private String schema;

	private String userName;

	private String password;

	private PredefinedDataSource(String key) {
		this.key = key;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DataSource getDataSource() {
		HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();

		dataSource.setDriverClassName(driverClassName);
		dataSource.setJdbcUrl(jdbcUrl + databaseName);
		dataSource.setSchema(schema);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);

		return dataSource;
	}

	public DataSource getDataSource(String databaseName, String userName) {
		setDatabaseName(databaseName);
		setUserName(userName);

		return getDataSource();
	}

	public static PredefinedDataSource getByKey(String key) {
		for (PredefinedDataSource value : PredefinedDataSource.values()) {
			if (value.key.equals(key)) {
				return value;
			}
		}
		throw new IllegalStateException();
	}

}
