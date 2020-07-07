package org.glycoinfo.rdf.service.impl;

import java.io.IOException;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.service.exception.LiteratureException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author shinmachi
 *
 * This work is licensed under the Creative Commons Attribution 4.0 International License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {LiteratureProcedureTest.class, VirtSesameTransactionConfig.class, LiteratureProcedureConfig.class})
@Configuration
@EnableAutoConfiguration
public class LiteratureProcedureTest  {

  private static final Log logger = LogFactory.getLog(LiteratureProcedureTest.class);

	@Autowired
	LiteratureProcedure literatureProcedure;
	
	
	@Test
	public void testAddLiterature() throws LiteratureException, IOException {
		String id = literatureProcedure.searchLiterature("G12345MO", "http://rdf.glytoucan.org/contributor/literature/demo");
		Assert.assertNotNull(id);
//		Assert.assertNull(id);
		literatureProcedure.addLiterature("G12345MO", "12345", "543");
		String id2 = literatureProcedure.searchLiterature("G12345MO", "http://rdf.glytoucan.org/contributor/literature/demo");
		Assert.assertNotNull(id2);
		literatureProcedure.deleteLiterature("G12345MO", "12345", "543");
		String id3 = literatureProcedure.searchLiterature("G12345MO", "http://rdf.glytoucan.org/contributor/literature/demo");
		Assert.assertNotNull(id3);
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
