package com.journaldev.dao;


import com.journaldev.service.TypesCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NodeDAOImpl implements NodeDAO {
	@Autowired
	private TypesCalculator typesCalculator;

	@Value("${requestProps.type1Requested}")
	private int type1Requested;
	@Value("${requestProps.type2Requested}")
	private int type2Requested;

	public NodeDAOImpl() {
	}

	@Override
	public void list() {
		typesCalculator.listNodes();
	}

	public void swap() {
		typesCalculator.swapTypes();
	}

	public void closeSession() {
		typesCalculator.closeSess();
	}
}
