
package com.globi.rpd.operator;

import com.globi.rpd.Operator;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;



/**
 * Provides no-op implementations for all operations on all components to reduce burden on individual Operators.
 * This allows Operators to implement inly the Components they are interested in
 * @author Girish Lakshmanan
 *
 * @param <R>
 */
public class BaseOperator implements Operator {

	@Override
	public PresentationCatalog operate(PresentationCatalog prescatalog) {
		return null;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {
		return null;
	}

	@Override
	public PresentationColumn operate(PresentationColumn presColumn) {
		return null;
	}

	@Override
	public BusinessModel operate(BusinessModel model) {
		return null;
	}

	@Override
	public LogicalTable operate(LogicalTable model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogicalColumn operate(LogicalColumn model) {
		// TODO Auto-generated method stub
		return null;
	}
}
