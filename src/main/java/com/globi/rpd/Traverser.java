
package com.globi.rpd;

import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;

public interface Traverser {

	void traverse(PresentationCatalog aBean, Operator<?> presCatalog);

	void traverse(PresentationTable aBean, Operator<?> presTable);

	void traverse(PresentationColumn aBean, Operator<?> presColumn);

}
