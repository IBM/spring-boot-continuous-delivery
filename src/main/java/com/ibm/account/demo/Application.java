package com.ibm.account.demo;

import com.cloudant.client.api.ClientBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	private final static String KUBE_CLOUDANT_SECRETS_FILE = "/etc/cloudant-secrets/binding";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}


	@Profile("kubernetes")
	@Bean
	/*
	 * Load the CloudantClient from the Kubernetes Secrets file.
	 * This method is only loaded when the kubernetes profile is activated
	 */
	public CloudantClient client() throws IOException {

		String secrets = readKubeSecretsFiles();
		String secretsJson = StringUtils.newStringUtf8(Base64.decodeBase64(secrets));
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();

		// convert JSON string to Map
		map = mapper.readValue(secretsJson, new TypeReference<Map<String, String>>(){});

		String username = (String) map.get("username");
		String password = (String) map.get("password");
		String url = "http://" + map.get("username") + ".cloudant.com";

		return ClientBuilder.url(new URL(url))
				.username(username)
				.password(password)
				.build();
	}

	@Bean
	public Database account(CloudantClient cloudant) throws MalformedURLException {
		return cloudant.database("account", true);
	}

	private String readKubeSecretsFiles() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(KUBE_CLOUDANT_SECRETS_FILE));

		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		String everything = sb.toString();
		br.close();

		return everything;
	}

}
