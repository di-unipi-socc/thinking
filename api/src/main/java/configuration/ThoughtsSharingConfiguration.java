package configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class ThoughtsSharingConfiguration extends Configuration {
	@Valid
	@NotEmpty
	@JsonProperty("dbURL")
	private String dbURL;
	@Valid
	@NotNull
	@JsonProperty("dbPort")
	private Integer dbPort;
	@Valid
	@NotEmpty
	@JsonProperty("dbName")
	private String dbName;
	@Valid
	@NotEmpty
	@JsonProperty("collectionName")
	private String collectionName;
	
	@JsonProperty
	public String getDbURL() {
		return dbURL;
	}
	@JsonProperty
	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}
	@JsonProperty
	public Integer getDbPort() {
		return dbPort;
	}
	@JsonProperty
	public void setDbPort(Integer dbPort) {
		this.dbPort = dbPort;
	}
	@JsonProperty
	public String getDbName() {
		return dbName;
	}
	@JsonProperty
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	@JsonProperty
	public String getCollectionName() {
		return collectionName;
	}
	@JsonProperty
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
}
