
package com.globi.rpd;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;

public interface Operator<R, E extends Throwable> {

	R operate(PresentationCatalog presCatalog) throws E;

	R operate(PresentationTable presTable) throws E;

	R operate(PresentationColumn presColumn) throws E;

}
