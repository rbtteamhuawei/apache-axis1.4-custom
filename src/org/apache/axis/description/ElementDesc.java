/*
 * Copyright 2002-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.axis.description;

import javax.xml.namespace.QName;
import java.io.Serializable;

/**
 * An AttributeDesc is a FieldDesc for an Java field mapping to an
 * XML element
 *
 * @author Glen Daniels (gdaniels@apache.org)
 * @author Dominik Kacprzak (dominik@opentoolbox.com)
 */
public class ElementDesc extends FieldDesc implements Serializable {
    /** The minOccurs value from the schema */
    private int minOccurs = 1;
    /** The maxOccurs value from the schema */
    private int maxOccurs = 1;
    
    /** The nillable value from the schema.
     * By default, element cannot be nillable. */
	// After upgrade to Axis1.4, external calls are failing with
	// java.io.IOException: Non nillable element is null
	// since USDP not populating values for all the parameters in the SOAP request
	// Its populating values for only required parameters by the external system
	// To make it compatible with Axis1.1 changing the below property to true
	// If required the value can be changed to false using the Java VM parameter
	// axis.ElementDesc.nillable in $USDP_HOME/bin/setvmargs.sh file
	private boolean nillable = Boolean.parseBoolean(System.getProperty(
			"axis.ElementDesc.nillable", "true"));

    /** maxOccurs="unbounded" */
    private boolean unbounded = false;

    /** If this is an array, this holds the array type */
    private QName arrayType;
    /** If this is a "wrapped" array, this tells us the inner QName */
    private QName itemQName;

    public ElementDesc() {
        super(true);
    }

    public boolean isMinOccursZero() {
        return minOccurs == 0;
    }

    public int getMinOccurs() {
        return minOccurs;
    }

    public void setMinOccurs(int minOccurs) {
        this.minOccurs = minOccurs;
    }

    public int getMaxOccurs() {
        return maxOccurs;
    }

    public void setMaxOccurs(int maxOccurs) {
        this.maxOccurs = maxOccurs;
    }

    public void setMaxOccursUnbounded(boolean ubnd) {
        this.unbounded = ubnd;
    }

    public boolean isMaxOccursUnbounded() {
        return unbounded;
    }

    /**
     * Returns value of nillable property.
     *
     * @return
     */
    public boolean isNillable() {
        return nillable;
    }

    /**
     * Sets value of nillable property.  Default: <code>false</code>.
     *
     * @param nillable
     */
    public void setNillable(boolean nillable) {
        this.nillable = nillable;
    }

    public QName getArrayType() {
        return arrayType;
    }

    public void setArrayType(QName arrayType) {
        this.arrayType = arrayType;
    }

    public QName getItemQName() {
        return itemQName;
    }

    public void setItemQName(QName itemQName) {
        this.itemQName = itemQName;
    }
}
