package com.globi.rpd.component;

import com.globi.rpd.RpdComponent;
import com.globi.rpd.RpdMarshalledObject;

import lombok.Data;
import xudml.ObjectW;


/**
 * Represents an RPD component that is marshalled as its own XML file
 * @author Girish Lakshmanan
 *
 * @param <T>
 */

@Data
public abstract class MarshalledRpdComponent<T extends ObjectW> implements RpdComponent,RpdMarshalledObject<T> {

	/**
	 * MDS id of the component
	 */
	private String id;
	
	/**
	 * Path to the xml file
	 */
	private String resourceUri;
	
	/**
	 * Raw JAXB object
	 */
	private T xudmlObject;
	
	public MarshalledRpdComponent(String id) {
		this.id = id;
	}



	@Override
	public String getName() {
		return this.xudmlObject.getName();
	}


}
