package com.globi.rpd.operator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;

import xudml.RefPresentationCatalogTableT;


public class SortingOperator implements Operator<RpdComponent> {

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {

		Collections.sort(presCatalog.getPresentationTables(), new Comparator<PresentationTable>() {
			public int compare(PresentationTable t1, PresentationTable t2) {

				if (t1.getXudmlObject().getName().contains("Measures"))
					return 1;
				if (t2.getXudmlObject().getName().contains("Measures"))
					return -1;

				return t1.getXudmlObject().getName().compareTo(t2.getXudmlObject().getName());
			}
		});

		
		List<RefPresentationCatalogTableT> xudmlTables=presCatalog.getXudmlObject().getRefTables().getRefPresentationTable();
		xudmlTables.clear();
		
		//Reorder Refs Using the Ordered List
		presCatalog.getPresentationTables().stream().forEach(presTable->{
			RefPresentationCatalogTableT refTable=new RefPresentationCatalogTableT();
			refTable.setPresentationTableRef("/oracle/bi/server/base/PresentationTable/" + presTable.getId() + ".xml#m" +  presTable.getId());
			refTable.setRefId(presTable.getRefId());
			xudmlTables.add(refTable);
		});
		
		
		
		return presCatalog;
	}
	
	
	@Override
	public PresentationTable operate(PresentationTable presTable) {

		if (presTable.getXudmlObject() == null)
			throw new IllegalStateException("Cannot process without a XUDML instance set");

		// nothing to Sort yet

		return presTable;

	}


}
