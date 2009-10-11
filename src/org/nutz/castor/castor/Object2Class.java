package org.nutz.castor.castor;

import org.nutz.castor.Castor;
import org.nutz.castor.FailToCastObjectException;

@SuppressWarnings("unchecked")
public class Object2Class extends Castor<Object, Class> {

	@Override
	protected Class cast(Object src, Class<?> toType, String... args) throws FailToCastObjectException {
		return src.getClass();
	}

}
