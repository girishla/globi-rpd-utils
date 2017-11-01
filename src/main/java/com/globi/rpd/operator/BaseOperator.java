
package com.globi.rpd.operator;

import com.globi.rpd.Operator;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;


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
