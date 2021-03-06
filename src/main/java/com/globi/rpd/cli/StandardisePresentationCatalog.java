package com.globi.rpd.cli;

import com.globi.rpd.DefaultLoggerProgressMonitor;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.operator.BreadthFirstTraversingOperator;
import com.globi.rpd.operator.DisplayNameModificationOperator;
import com.globi.rpd.operator.HydratingOperator;
import com.globi.rpd.operator.SortingOperator;
import com.globi.rpd.operator.XudmlMarshallingOperator;
import com.globi.rpd.operator.XudmlUnmarshallingOperator;
import com.globi.rpd.traverser.DefaultTraverser;
import com.globi.rpd.xudml.XudmlConstants;

public class StandardisePresentationCatalog implements RpdObjectCommand<Boolean, String> {

	@Override
	public String execute(String subjectAreaName) throws Exception {

		if (!subjectAreaName.toUpperCase()
				.equals("ALL"))
			throw new IllegalArgumentException("Invalid Argument " + subjectAreaName);

		try {

			PresentationCatalog presCatalog = PresentationCatalog.fromResource("file:" + XudmlConstants.XUDML_BASEURL + 
					"/oracle/bi/server/base/PresentationCatalog/40000456-6dc5-167d-806e-c0a838100000.xml");

			XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
			BreadthFirstTraversingOperator tv = new BreadthFirstTraversingOperator(new DefaultTraverser(),
					unmarshalOperator);

			tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
			presCatalog.apply(tv);

			HydratingOperator hydratingOperator = new HydratingOperator();
			BreadthFirstTraversingOperator tv2 = new BreadthFirstTraversingOperator(new DefaultTraverser(),
					hydratingOperator);
			tv2.setProgressMonitor(new DefaultLoggerProgressMonitor());
			presCatalog.apply(tv2);

			DisplayNameModificationOperator renamingOperator = new DisplayNameModificationOperator(
					name -> name.replaceAll("Global Reporting - Measures - ", ""));
			BreadthFirstTraversingOperator traversingOperator = new BreadthFirstTraversingOperator(new DefaultTraverser(),
					renamingOperator);
			traversingOperator.setProgressMonitor(new DefaultLoggerProgressMonitor());
			presCatalog.apply(traversingOperator);

			SortingOperator sortingOperator = new SortingOperator();
			BreadthFirstTraversingOperator traversingSortingOperator = new BreadthFirstTraversingOperator(
					new DefaultTraverser(), sortingOperator);
			traversingSortingOperator.setProgressMonitor(new DefaultLoggerProgressMonitor());
			presCatalog.apply(traversingSortingOperator);

			presCatalog.setResourceUri(XudmlConstants.XUDML_COPYURL + "40000456-6dc5-167d-806e-c0a838100000.xml");

			XudmlMarshallingOperator marshallingOperator = new XudmlMarshallingOperator();
			BreadthFirstTraversingOperator tv3 = new BreadthFirstTraversingOperator(new DefaultTraverser(),
					marshallingOperator);
			tv3.setProgressMonitor(new DefaultLoggerProgressMonitor());
			presCatalog.apply(tv3);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "COMMAND PROCESSED.";
	}

}
