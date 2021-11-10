package com.temperosoft.scmfornecedores.core.utils;

import java.util.List;

import com.temperosoft.scmfornecedores.domain.AbstractDomain;

public class ValidationUtils {
	
    private ValidationUtils() {}

    public static <T extends AbstractDomain> Long returnIdOrNull(T t) {
        if(t != null)
            return t.getId();
        else
            return null;
    }
    
    public static boolean stringIsNullOrEmpty(String str) {
        return str != null && ! str.isEmpty();
    }

    public static boolean listIsNullOrEmpty(List<?> list) {
        return list != null && ! list.isEmpty();
    }
}
