package org.glycoinfo.rdf.literature;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.SparqlException;
import org.glycoinfo.rdf.dao.SparqlDAO;
import org.glycoinfo.rdf.dao.SparqlEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

public class SelectLiteratureTest extends SelectLiterature {
	
  private static final Log logger = LogFactory.getLog(SelectLiteratureTest.class);
  
	@Autowired
	SparqlDAO sparqlDAO;
	
	// Select SPARQL
	@Bean
	SelectLiterature getSelectLiterature() {
		SelectLiterature sLiterature = new SelectLiterature();
		SparqlEntity sparqlEntity = new SparqlEntity();
		sparqlEntity.setValue(Literature.AccessionNumber, "G12345AB");
		sLiterature.setSparqlEntity(sparqlEntity);
		return sLiterature;
	}
	
	@Test
	public void testSelectSparql() throws SparqlException {
//		String test = getSelectLiterature().getSparql();
//		System.out.println(test);
		logger.debug(getSelectLiterature().getSparql());
	}
	
	// Exec SPARQL
//	@Test
//	@Transactional
//	public void selectSparql() throws SparqlException {
//		sparqlDAO.query(getSelectLiterature());
//	}
	
}
