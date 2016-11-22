package org.glycoinfo.rdf.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.dao.SparqlDAO;
import org.glycoinfo.rdf.dao.virt.VirtSesameTransactionConfig;
import org.glycoinfo.rdf.literature.InsertLiterature;
import org.glycoinfo.rdf.literature.SelectLiterature;
import org.glycoinfo.rdf.service.exception.LiteratureException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shinmachi
 *
 * This work is licensed under the Creative Commons Attribution 4.0 International License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {LiteratureProcedureTest.class, VirtSesameTransactionConfig.class, LiteratureProcedureConfig.class})
@Configuration
@EnableAutoConfiguration
public class LiteratureProcedureTest  {

  private static final Log logger = LogFactory.getLog(LiteratureProcedureTest.class);

	@Autowired
	SparqlDAO sparqlDAO;
	
	@Autowired
	LiteratureProcedure literatureProcedure;
	
	
	@Test
	@Transactional
	public void testAddLiterature() throws LiteratureException {
		literatureProcedure.searchLiterature("G12345MO");
		literatureProcedure.addLiterature("G12345MO", "12345");
		literatureProcedure.searchLiterature("G12345MO");
		literatureProcedure.deleteLiterature("G12345MO", "12345");
		literatureProcedure.searchLiterature("G12345MO");
//		String id = literatureProcedure.addLiterature("G12345MO", "123456");
//		Assert.assertNotNull(id);
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
