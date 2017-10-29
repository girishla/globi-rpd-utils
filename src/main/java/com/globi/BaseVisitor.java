
package com.globi;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;


public class BaseVisitor<R, E extends Throwable >
    implements Operator<R, E>
{

    @Override
    public R operate(PresentationCatalog aBean)
        throws E
    {
        return null;
    }

    @Override
    public R operate(PresentationTable aBean)
        throws E
    {
        return null;
    }

	@Override
	public R operate(PresentationColumn aBean) throws E {
		// TODO Auto-generated method stub
		return null;
	}}
