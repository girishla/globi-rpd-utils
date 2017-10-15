
package com.globi;

import javax.xml.namespace.QName;

public interface Named {


    public void setJAXBElementName(QName name);

    public QName getJAXBElementName();

}
