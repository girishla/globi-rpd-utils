
package com.globi.rpd;

import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;

public interface Operator<R, E extends Throwable> {

	R operate(PresentationCatalog presCatalog) throws E;

	R operate(PresentationTable presTable) throws E;

	R operate(PresentationColumn presColumn) throws E;

}
