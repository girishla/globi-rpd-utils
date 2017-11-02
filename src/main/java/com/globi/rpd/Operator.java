
package com.globi.rpd;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;

public interface Operator<R> {

	R operate(PresentationCatalog presCatalog);

	R operate(PresentationTable presTable);

	R operate(PresentationColumn presColumn);
	
	R operate(BusinessModel model);
	
	R operate(LogicalTable model);
		
	R operate(LogicalColumn model);

}
