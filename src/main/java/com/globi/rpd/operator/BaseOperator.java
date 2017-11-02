
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
public class BaseOperator<R> implements Operator<R> {

	@Override
	public R operate(PresentationCatalog prescatalog) {
		return null;
	}

	@Override
	public R operate(PresentationTable presTable) {
		return null;
	}

	@Override
	public R operate(PresentationColumn presColumn) {
		return null;
	}

	@Override
	public R operate(BusinessModel model) {
		return null;
	}

	@Override
	public R operate(LogicalTable model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R operate(LogicalColumn model) {
		// TODO Auto-generated method stub
		return null;
	}
}
