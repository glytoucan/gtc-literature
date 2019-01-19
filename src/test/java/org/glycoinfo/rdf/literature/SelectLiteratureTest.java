package org.glycoinfo.rdf.literature;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.SparqlException;
import org.glycoinfo.rdf.dao.SparqlDAO;
import org.glycoinfo.rdf.dao.SparqlEntity;
import org.glycoinfo.rdf.dao.virt.VirtSesameTransactionConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/*
 * test Select SPARQL for literature
 * @author shinmachi
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = { VirtSesameTransactionConfig.class, SelectLiteratureTest.class })
@Configuration
@EnableAutoConfiguration 
public class SelectLiteratureTest extends SelectLiterature {
	
  private static final Log logger = LogFactory.getLog(SelectLiteratureTest.class);
  
	@Autowired
	SparqlDAO sparqlDAO;
	
	// Select SPARQL
	@Bean
	SelectLiterature getSelectLiterature() {
		SelectLiterature selectLiterature = new SelectLiterature();
		SparqlEntity sparqlEntity = new SparqlEntity();
		sparqlEntity.setValue(Literature.AccessionNumber, "G82109MW");
//		sparqlEntity.setValue(Literature.AccessionNumber, "G12345AB");
		selectLiterature.setSparqlEntity(sparqlEntity);
		return selectLiterature;
	}
	
/*
	@Test
	public void testSelectSparql() throws SparqlException {
		String test = getSelectLiterature().getSparql();
		System.out.println(test);
		logger.debug(getSelectLiterature().getSparql());
	}
*/	
	
	// Exec SPARQL
	@Test
	@Transactional
	public void selectSparql() throws SparqlException {
		sparqlDAO.query(getSelectLiterature());
	}
}
