
package com.globi;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;

public interface Operator<R, E extends Throwable> {

	R operate(PresentationCatalog aBean) throws E;

	R operate(PresentationTable aBean) throws E;

	R operate(PresentationColumn aBean) throws E;

}
