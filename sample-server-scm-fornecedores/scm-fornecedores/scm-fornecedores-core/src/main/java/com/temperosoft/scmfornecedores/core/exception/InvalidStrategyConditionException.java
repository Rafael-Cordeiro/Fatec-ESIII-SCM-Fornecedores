package com.temperosoft.scmfornecedores.core.exception;

public class InvalidStrategyConditionException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

    public InvalidStrategyConditionException () {
        super();
    }

    public InvalidStrategyConditionException ( String message , Throwable cause ) {
        super( message , cause );
    }

    public InvalidStrategyConditionException ( String message ) {
        super( message );
    }

    public InvalidStrategyConditionException ( Throwable cause ) {
        super( cause );
    }
    
}
