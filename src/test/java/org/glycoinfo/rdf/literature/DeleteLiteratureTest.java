package org.glycoinfo.rdf.literature;

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
Test the delete data that is inserted pubmed info
 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { VirtSesameTransactionConfig.class, DeleteLiteratureTest.class })
@Configuration
@EnableAutoConfiguration 
public class DeleteLiteratureTest {

  
	@Autowired
	SparqlDAO sparqlDAO;
	
	// Delete SPARQL
	@Bean
	DeleteLiterature getDeleteLiterature() {
		DeleteLiterature deleteLiterature = new DeleteLiterature();
		SparqlEntity sparqlEntity = new SparqlEntity();
		sparqlEntity.setValue(Literature.AccessionNumber, "G82109MW");
		sparqlEntity.setValue(Literature.PubemdId, "12345");
		deleteLiterature.setGraph("http://test/literature");
		deleteLiterature.setSparqlEntity(sparqlEntity);
		return deleteLiterature;
	}
/*
	@Test
	public void testDeleteLiterature() throws SparqlException {
		String test = getDeleteLiterature().getSparql();
		System.out.println(test);
	}
 */
	// Insert SPARQL
	@Bean
	InsertLiterature getInsertLiterature() {
		InsertLiterature insertLiterature = new InsertLiterature();
		SparqlEntity sparqlEntity = new SparqlEntity();
		sparqlEntity.setValue(Literature.AccessionNumber, "G82109MW");
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
		sparqlEntity.setValue(Literature.AccessionNumber, "G82109MW");
		selectLiterature.setSparqlEntity(sparqlEntity);
		selectLiterature.setLimit("100");
		return selectLiterature;
	}
	
	// Eexec SPARQL
	@Test
	@Transactional
	public void deleteSparql() throws SparqlException {
		sparqlDAO.insert(getInsertLiterature());
		sparqlDAO.query(getSelectLiterature());
		sparqlDAO.delete(getDeleteLiterature());
		sparqlDAO.query(getSelectLiterature());
	}
}
