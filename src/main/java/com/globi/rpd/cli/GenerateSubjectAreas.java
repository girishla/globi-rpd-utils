package com.globi.rpd.cli;

import com.globi.rpd.DefaultLoggerProgressMonitor;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.operator.HydratingOperator;
import com.globi.rpd.operator.ModelTraversingOperator;
import com.globi.rpd.operator.XudmlMarshallingOperator;
import com.globi.rpd.operator.XudmlUnmarshallingOperator;
import com.globi.rpd.traverser.ModelDefaultTraverser;
import com.globi.rpd.xudml.XudmlConstants;

public class GenerateSubjectAreas implements RpdObjectCommand<Boolean, String> {

	@Override
	public String execute(String subjectAreaName) throws Exception {

		if (!subjectAreaName.toUpperCase()
				.equals("ALL"))
			throw new IllegalArgumentException("Invalid Argument " + subjectAreaName);

		try {

			BusinessModel model = BusinessModel.fromResource(
					"testrepo/oracle/bi/server/base/BusinessModel/00000000-28a1-1627-806e-0a3fce3c0000.xml");

			XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
			ModelTraversingOperator tv = new ModelTraversingOperator(new ModelDefaultTraverser(),
					unmarshalOperator);

			tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
			model.apply(tv);

			HydratingOperator hydratingOperator = new HydratingOperator();
			ModelTraversingOperator tv2 = new ModelTraversingOperator(new ModelDefaultTraverser(),
					hydratingOperator);
			tv2.setProgressMonitor(new DefaultLoggerProgressMonitor());
			model.apply(tv2);

			model.setResourceUri(XudmlConstants.XUDML_OUTPUT + "00000000-28a1-1627-806e-0a3fce3c0000.xml");

			XudmlMarshallingOperator marshallingOperator = new XudmlMarshallingOperator();
			ModelTraversingOperator tv3 = new ModelTraversingOperator(new ModelDefaultTraverser(),
					marshallingOperator);
			tv3.setProgressMonitor(new DefaultLoggerProgressMonitor());
			model.apply(tv3);

		} catch (Exception e) {
			e.printStackTrace();
			return "COMMAND FAILED.";
		}

		return "COMMAND PROCESSED.";
	}

}
