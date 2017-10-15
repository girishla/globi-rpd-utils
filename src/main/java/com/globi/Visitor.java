
package com.globi;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;

public interface Visitor<R, E extends Throwable> {

	R visit(PresentationCatalog aBean) throws E;

	R visit(PresentationTable aBean) throws E;

	R visit(PresentationColumn aBean) throws E;

}
