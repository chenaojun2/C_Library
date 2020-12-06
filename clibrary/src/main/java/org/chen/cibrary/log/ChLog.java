package org.chen.cibrary.log;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public class ChLog {

    private static final String CH_LOG_PACKAGE;

    static {
        String className = ChLog.class.getName();
        CH_LOG_PACKAGE = className.substring(0,className.lastIndexOf('.')+1);
    }

    public static void v(Object... contents) {
        log(ChLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(ChLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(ChLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(ChLogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(ChLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(ChLogType.I, tag, contents);
    }

    public static void e(Object... contents) {
        log(ChLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(ChLogType.E, tag, contents);
    }

    public static void w(Object... contents) {
        log(ChLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(ChLogType.W, tag, contents);
    }

    public static void a(Object... contents) {
        log(ChLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(ChLogType.A, tag, contents);
    }

    public static void log(@ChLogType.TYPE int type, Object... contents) {
        log(type, ChLogManger.getInstance().getConfig().getGlobalTag(),contents);
    }

    public static void log(@ChLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(ChLogManger.getInstance().getConfig(),type,tag,contents);
    }

    public static void log(@NonNull ChLogConfig config, @ChLogType.TYPE int type, @NonNull String tag, Object... contents) {
        if(!config.enable()){
            return;
        }
        StringBuilder sb = new StringBuilder();
        if(config.includeThread()){
            String threadInfo = ChLogConfig.CH_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }

        if(config.stackTraceDepth() > 0){
            String stackTrace = ChLogConfig.CH_STACK_TRACE_FORMATTER.format(ChStackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(),CH_LOG_PACKAGE,config.stackTraceDepth() ));
            sb.append(stackTrace).append("\n");
        }
        String body = pareBody(contents,config);
        sb.append(body);
        List<ChLogPrinter> printers = config.printers()!=null? Arrays.asList(config.printers()):ChLogManger.getInstance().getPrinters();
        for(ChLogPrinter printer :printers){
            printer.print(config,type,tag,sb.toString());
        }
    }

    private static String pareBody(@NonNull Object[] contents,@NonNull ChLogConfig config){
        if (config.injectJsonParser() != null){
            return config.injectJsonParser().toJson(contents);
        }

        StringBuilder sb = new StringBuilder();
        for(Object o : contents){
            sb.append(o.toString()).append(";");
        }
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

}
