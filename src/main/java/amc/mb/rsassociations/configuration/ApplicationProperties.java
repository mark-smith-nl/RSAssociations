package amc.mb.rsassociations.configuration;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import amc.mb.rsassociations.enums.PredefinedDataSource;

@Configuration
@ConfigurationProperties("application-properties")
public class ApplicationProperties {

	private Map<String, Map<String, String>> dataSources;

	private String idsImportFile;

	public Map<String, Map<String, String>> getDataSources() {
		return dataSources;
	}

	public void setDataSources(Map<String, Map<String, String>> dataSources) {
		this.dataSources = dataSources;
	}

	public String getIdsImportFile() {
		return idsImportFile;
	}

	public void setIdsImportFile(String idsImportFile) {
		this.idsImportFile = idsImportFile;
	}

	@PostConstruct
	private void bindToPredefinedDataSourcEnums() {
		dataSources.forEach((key, properties) -> {
			PredefinedDataSource predefinedDataSource = PredefinedDataSource.getByKey(key);
			predefinedDataSource.setJdbcUrl(properties.get("jdbcUrl"));
			predefinedDataSource.setDriverClassName(properties.get("driverClassName"));
			predefinedDataSource.setDatabaseName(properties.get("databaseName"));
			predefinedDataSource.setSchema(properties.get("schema"));
			predefinedDataSource.setUserName(properties.get("userName"));
			predefinedDataSource.setPassword(properties.get("password"));
		});
	}
}
