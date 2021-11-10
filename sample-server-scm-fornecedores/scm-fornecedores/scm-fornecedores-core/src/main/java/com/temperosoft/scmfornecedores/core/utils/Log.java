package com.temperosoft.scmfornecedores.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dvsmedeiros.bce.core.controller.INavigationCase;

public final class Log {

	private Log() {}
	
    @SuppressWarnings("rawtypes")
    public static void suspendExecutionThenLogError (String msg, Class clazz, INavigationCase aCase) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.error(msg);
        aCase.suspendExecution(msg);
    }

    @SuppressWarnings("rawtypes")
    public static void suspendExecutionThenLogException(String msg, Class clazz, INavigationCase aCase, Exception e) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.error(e.getMessage(), e);
        aCase.suspendExecution(msg);
    }
}
