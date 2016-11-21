package org.glycoinfo.rdf.service.impl;

import org.glycoinfo.rdf.SparqlException;
import org.glycoinfo.rdf.literature.InsertLiterature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiteratureProcedureConfig implements GraphConfig {
	
	@Bean(name = "literatureProcedure")
	LiteratureProcedure getLiteratureProcedure() throws SparqlException {
		LiteratureProcedure literatureProcedure = new LiteratureProcedure();
		return literatureProcedure;
	}
	
	@Bean
	InsertLiterature getInsertLiterature() {
		InsertLiterature insertLiterature = new InsertLiterature();
		insertLiterature.setGraph(graph + "/contributor/literature");
		return insertLiterature;
	}
}
