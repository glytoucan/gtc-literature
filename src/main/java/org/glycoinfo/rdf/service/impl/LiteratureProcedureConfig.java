package org.glycoinfo.rdf.service.impl;

import org.glycoinfo.rdf.SparqlException;
import org.glycoinfo.rdf.literature.DeleteLiterature;
import org.glycoinfo.rdf.literature.InsertLiterature;
import org.glycoinfo.rdf.literature.SelectLiterature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.bluetree.gov.ncbi.service.NCBIService;

@Configuration
public class LiteratureProcedureConfig {
	public static final String graph = "http://rdf.glytoucan.org";
	
	@Bean(name = "literatureProcedure")
	LiteratureProcedure getLiteratureProcedure() throws SparqlException {
		LiteratureProcedure literatureProcedure = new LiteratureProcedure();
		return literatureProcedure;
	}

	// Insert
	@Bean
	InsertLiterature getInsertLiterature() {
		InsertLiterature insertLiterature = new InsertLiterature();
		insertLiterature.setGraph(graph + "/contributor/literature");
		return insertLiterature;
	}

	// Delete
	@Bean
	DeleteLiterature getDeleteLiterature() {
		DeleteLiterature deleteLiterature = new DeleteLiterature();
		deleteLiterature.setGraph(graph + "/contributor/literature");
		return deleteLiterature;
	}
	
	// Select
	@Bean
	SelectLiterature getSelectLiterature() {
		SelectLiterature selectLiterature = new SelectLiterature();
		selectLiterature.setFrom("FROM <" + graph + "/contributor/literature" + ">\n");
		return selectLiterature;
	}
	
	 // Select
  @Bean
  NCBIService ncbiService() {
    NCBIService ncbiService = new NCBIService();
    return ncbiService;
  }
}
