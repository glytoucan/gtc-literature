package org.glycoinfo.rdf.service.impl;

import java.io.IOException;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.service.exception.LiteratureException;
import org.glycosmos.client.GlycosmosClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparqlite.api.rest.SparqlistRestService;
import com.sparqlite.api.rest.SparqlistService;

import jp.bluetree.gov.ncbi.service.NCBIService;

/**
 * @author shinmachi
 *
 * This work is licensed under the Creative Commons Attribution 4.0 International License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {LiteratureProcedureTest.class, VirtSesameTransactionConfig.class, LiteratureProcedureConfig.class})
//@Configuration
//@EnableAutoConfiguration
@SpringBootTest
public class LiteratureProcedureTest  {

  private static final Log logger = LogFactory.getLog(LiteratureProcedureTest.class);

	@Autowired
	LiteratureProcedure literatureProcedure;

	private ConfigurableApplicationContext context;

	@Configuration
	static class EmptyConfiguration {
	}
	
	@Configuration
	@Import(EmptyConfiguration.class)
	static class TestConfiguration {

		@Bean
		public GlycosmosClient glycosmosClient(SparqlistRestService sparqlistRestService) {
			return new GlycosmosClient(sparqlistRestService);
		}

		@Bean
		public NCBIService ncbiService() {
			return new NCBIService();
		}

		@Bean
		public LiteratureProcedure literatureProcedure(NCBIService ncbiService, GlycosmosClient glycosmosClient) {
			return new LiteratureProcedure(ncbiService, glycosmosClient);
		}

		@Bean
		public SparqlistRestService SparqlistRestService() {
			return new SparqlistService("https://test.batch.sparqlist.glycosmos.org/sparqlist/api/");
		}
		
		@Bean
		public ObjectMapper objectMapper() {
		    com.fasterxml.jackson.databind.ObjectMapper responseMapper = new com.fasterxml.jackson.databind.ObjectMapper();
		    return responseMapper;
		}
	}

	private void load(Class<?> config, String... environment) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(config);
//		EnvironmentTestUtils.addEnvironment(ctx, environment);
		TestPropertyValues.of(environment);
		ctx.refresh();
		this.context = ctx;
	}

	
	@Test
	public void testAddLiterature() throws LiteratureException, IOException {
		load(TestConfiguration.class, "sparqlist.server=https://test.batch.sparqlist.glycosmos.org/sparqlist/api/oops");
		LiteratureProcedure literatureProcedure = this.context.getBean(LiteratureProcedure.class);
		
		JsonNode id = literatureProcedure.searchLiterature("G12345MO", "http://rdf.glytoucan.org/contributor/literature/demo");
		Assert.assertNotNull(id);
		Assert.assertEquals(0, id.size());
//		Assert.assertNull(id);
		literatureProcedure.addLiterature("G12345MO", "12345", "543");
		JsonNode id2 = literatureProcedure.searchLiterature("G12345MO", "http://rdf.glytoucan.org/contributor/literature/demo");
		Assert.assertNotNull(id2);
		Assert.assertEquals("http://rdf.glycoinfo.org/glytoucan/contributor/userId/543", id2.findValue("contributor_id").asText());
		JsonNode iddelete = literatureProcedure.deleteLiterature("G12345MO", "12345", "543");
		Assert.assertNotNull(iddelete);
		JsonNode id3 = literatureProcedure.searchLiterature("G12345MO", "http://rdf.glytoucan.org/contributor/literature/demo");
		Assert.assertNotNull(id3);
		Assert.assertEquals(0, id3.size());
//		String id = literatureProcedure.addLiterature("G12345MO", "123456");
	}

/*
	@Test
	@Transactional
	public void testDeleteLiterature() throws LiteratureException {
		String accNum = literatureProcedure.deleteLiterature("G12345MO", "12345");
		Assert.assertNotNull(accNum);
	}
*/

}
