package org.chen.cibrary.log;

import androidx.annotation.NonNull;

public class CLogManger {
    private CLogConfig config;

    private static CLogManger instance;

    private CLogManger (CLogConfig config){
        this.config = config;
    }

    public static CLogManger getInstance(){
        return instance;
    }

    public static void init(@NonNull CLogConfig config){
        instance = new CLogManger(config);
    }

    public CLogConfig getConfig(){
        return  config;
    }
}
