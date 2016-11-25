package org.glycoinfo.rdf.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glycoinfo.rdf.SparqlException;
import org.glycoinfo.rdf.dao.SparqlDAO;
import org.glycoinfo.rdf.dao.SparqlEntity;
import org.glycoinfo.rdf.literature.DeleteLiterature;
import org.glycoinfo.rdf.literature.InsertLiterature;
import org.glycoinfo.rdf.literature.Literature;
import org.glycoinfo.rdf.literature.SelectLiterature;
import org.glycoinfo.rdf.service.exception.LiteratureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import jp.bluetree.gov.ncbi.model.Publication;
import jp.bluetree.gov.ncbi.service.NCBIService;

public class LiteratureProcedure {
	private static final Log logger = LogFactory.getLog(LiteratureProcedure.class);
	
	@Autowired
	SparqlDAO sparqlDAO;
	
	@Autowired
	InsertLiterature insertLiterature;

	@Autowired
	DeleteLiterature deleteLiterature;
	
	@Autowired
	NCBIService ncbiService;
	
	/**
	 * adds a Literature (bibo:Article).
	 * This RDF construct by Accession number and PubMed ID
	 
		@prefix rdfs:   <http://www.w3.org/2000/01/rdf-schema#> .
		@prefix dcterms: <http://purl.org/dc/terms/> .
		@prefix bibo: <http://purl.org/ontology/bibo/>.

		# Literature
		<http://rdf.glycoinfo.org/glycan/{accession_number}> #Saccharide 
		 dcterms:references <http://rdf.glycoinfo.org/references/{pubmed_id}> ; #Article

		<http://rdf.glycoinfo.org/references/{pubmed_id}>
		 a bibo:Article ;
		 dcterms:identifier "{pubmed_id}" ;
		 rdfs:seeAlso <http://identifiers.org/pubmed/{pubmed_id}> . #PubMed URL

		 @throws SparqlException
	 * 
	 */

	// Add
	@Transactional
	public String addLiterature(String accessionNumber, String pubmedId) throws LiteratureException {
		if (pubmedId != null) {
		  Publication pub = ncbiService.getSummary(pubmedId);
      if (null==pub || StringUtils.isBlank(pub.getTitle())) {
        throw new LiteratureException("could not retrieve publication title");
      }
		  
			SparqlEntity sparqlEntity = new SparqlEntity();
			sparqlEntity.setValue(Literature.AccessionNumber, accessionNumber);
			sparqlEntity.setValue(Literature.PubemdId, pubmedId);
			insertLiterature.setSparqlEntity(sparqlEntity);
			try {
				sparqlDAO.insert(insertLiterature);
			} catch (SparqlException e) {
				throw new LiteratureException(e);
			}
		} else {
			logger.info("PubMed ID is null");
			throw new LiteratureException("literature id cannot be null.");
		}
		return pubmedId;
	}

	// Delete
	@Transactional
	public String deleteLiterature(String accessionNumber, String pubmedId) throws LiteratureException {
		if (pubmedId != null) {
			SparqlEntity sparqlEntity = new SparqlEntity();
			sparqlEntity.setValue(Literature.AccessionNumber, accessionNumber);
			sparqlEntity.setValue(Literature.PubemdId, pubmedId);
			deleteLiterature.setSparqlEntity(sparqlEntity);
			try {
				sparqlDAO.delete(deleteLiterature);
			} catch (SparqlException e) {
				throw new LiteratureException(e);
			}
		} else {
			logger.info("PubMed ID is null");
      throw new LiteratureException("literature id cannot be null.");
		}
		return pubmedId;
	}
}
