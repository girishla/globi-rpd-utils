
package com.globi.rpd;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;


public class BaseOperator<R, E extends Throwable >
    implements Operator<R, E>
{

    @Override
    public R operate(PresentationCatalog prescatalog)
        throws E
    {
        return null;
    }

    @Override
    public R operate(PresentationTable presTable)
        throws E
    {
        return null;
    }

	@Override
	public R operate(PresentationColumn presColumn) throws E {
		// TODO Auto-generated method stub
		return null;
	}}
