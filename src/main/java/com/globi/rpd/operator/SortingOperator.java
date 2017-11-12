package com.globi.rpd.operator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.xudml.XudmlConstants;

import xudml.RefPresentationCatalogTableT;


public class SortingOperator implements Operator<RpdComponent> {

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {

		Collections.sort(presCatalog.getPresentationTables(), new Comparator<PresentationTable>() {
			public int compare(PresentationTable t1, PresentationTable t2) {



				boolean table1IsMeasureTable = t1.getXudmlObject()
						.getName()
						.contains("Measures");
				boolean table2IsMeasureTable = t2.getXudmlObject()
						.getName()
						.contains("Measures");

				if (table1IsMeasureTable && !table2IsMeasureTable) {
					return 1;
				} else if (!table1IsMeasureTable && table2IsMeasureTable) {
					return -1;
				} 

				return t1.getXudmlObject()
						.getName()
						.compareTo(t2.getXudmlObject()
								.getName());
			}
		});

		List<RefPresentationCatalogTableT> xudmlTables = presCatalog.getXudmlObject()
				.getRefTables()
				.getRefPresentationTable();
		xudmlTables.clear();

		// Reorder Refs Using the Ordered List
		presCatalog.getPresentationTables()
				.stream()
				.forEach(presTable -> {
					RefPresentationCatalogTableT refTable = new RefPresentationCatalogTableT();
					refTable.setPresentationTableRef(
							XudmlConstants.XUDML_PRESTABLEURL + presTable.getId() + ".xml#m" + presTable.getId());
					refTable.setRefId(presTable.getRefId());
					xudmlTables.add(refTable);
				});

		return presCatalog;
	}

}
