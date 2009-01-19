package com.zzh.lang.types.castor;

import java.text.ParseException;

import com.zzh.lang.Lang;

public class String2Datetime extends DateTimeCastor<String,java.util.Date> {

	@Override
	protected java.util.Date cast(String src) {
		try {
			return dateTimeFormat.parse((String) src);
		} catch (ParseException e) {
			throw Lang.wrapThrow(e);
		}
	}

}
