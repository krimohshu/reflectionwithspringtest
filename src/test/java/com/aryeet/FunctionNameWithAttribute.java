package com.aryeet;

import org.springframework.stereotype.Component;

@Component
public class FunctionNameWithAttribute {

    private String functionName;
    private String functionAttribute;

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionAttribute() {
        return functionAttribute;
    }

    public void setFunctionAttribute(String functionAttribute) {
        this.functionAttribute = functionAttribute;
    }

    public void clean(){
        this.functionName=null;
        this.functionAttribute=null;
    }

}
