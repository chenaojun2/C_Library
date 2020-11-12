package org.chen.cibrary.log;

public abstract class CLogConfig {

    public String getGlobalTag(){
        return "CLog";
    }

    public boolean enable(){
        return true;
    }



}
