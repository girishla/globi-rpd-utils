
package com.globi.rpd.operator;

import com.globi.rpd.Operator;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;

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
}
