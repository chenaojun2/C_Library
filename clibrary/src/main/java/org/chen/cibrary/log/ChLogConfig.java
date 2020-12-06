package org.chen.cibrary.log;

public abstract class ChLogConfig {

    static int MAX_LEN = 512;
    static ChStackTraceFormatter CH_STACK_TRACE_FORMATTER = new ChStackTraceFormatter();
    static ChThreadFormatter CH_THREAD_FORMATTER = new ChThreadFormatter();

    public JsonParser injectJsonParser(){
        return null;
    }

    public String getGlobalTag() {
        return "CLog";
    }

    public boolean enable() {
        return true;
    }

    public int stackTraceDepth(){
        return 5;
    }

    public boolean includeThread(){
        return false;
    }

    public ChLogPrinter[] printers(){
        return null;
    }

    public interface JsonParser{
        String toJson(Object src);
    }


}
