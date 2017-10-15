
package com.globi;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;


public class BaseVisitor<R, E extends Throwable >
    implements Visitor<R, E>
{

    @Override
    public R visit(PresentationCatalog aBean)
        throws E
    {
        return null;
    }

    @Override
    public R visit(PresentationTable aBean)
        throws E
    {
        return null;
    }

	@Override
	public R visit(PresentationColumn aBean) throws E {
		// TODO Auto-generated method stub
		return null;
	}}
