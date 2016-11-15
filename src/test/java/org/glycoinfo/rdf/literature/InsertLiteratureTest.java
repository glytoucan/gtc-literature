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
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/*
 * Test the insert data for the partner metadata
 * @author shinmachi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { VirtSesameTransactionConfig.class, InsertLiteratureTest.class })
@Configuration
@EnableAutoConfiguration 
public class InsertLiteratureTest {

	
	@Autowired
	SparqlDAO sparqlDAO;
	
	@Bean
	InsertLiterature getInsertLiterature() {
		InsertLiterature insertLiterature = new InsertLiterature();
		SparqlEntity sparqlEntity = new SparqlEntity();
		sparqlEntity.setValue(Literature.AccessionNumber, "G12345AB");
		sparqlEntity.setValue(Literature.PubemdId, "12345");
		insertLiterature.setSparqlEntity(sparqlEntity);
		insertLiterature.setGraph("http://test/literature");
		return insertLiterature;	
	}

	// Select SPARQL
	@Bean
	SelectLiterature getSelectLiterature() {
		SelectLiterature selectLiterature = new SelectLiterature();
		SparqlEntity sparqlEntity = new SparqlEntity();
		sparqlEntity.setValue(Literature.AccessionNumber, "G12345AB");
		selectLiterature.setSparqlEntity(sparqlEntity);
		return selectLiterature;
	}
/*
	@Test
	public void testInsertSparql() throws SparqlException {
		String test = getInsertLiterature().getSparql();
		System.out.println(test);
	}
	@Test
	public void testSelectSparql() throws SparqlException {
		String test = getSelectLiterature().getSparql();
		System.out.println(test);
	}
*/

	// Exec SPARQL
	@Test
	@Transactional
	public void inseqtSparql() throws SparqlException {
//		sparqlDAO.query(getSelectLiterature());
		sparqlDAO.insert(getInsertLiterature());
		sparqlDAO.query(getSelectLiterature());
	}
	
}
