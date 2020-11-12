package org.chen.cibrary.log;

import android.util.Log;

import androidx.annotation.NonNull;

public class CLog {

    public static void v(Object... contents) {
        log(CLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(CLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(CLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(CLogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(CLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(CLogType.I, tag, contents);
    }

    public static void e(Object... contents) {
        log(CLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(CLogType.E, tag, contents);
    }

    public static void w(Object... contents) {
        log(CLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(CLogType.W, tag, contents);
    }

    public static void a(Object... contents) {
        log(CLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(CLogType.A, tag, contents);
    }

    public static void log(@CLogType.TYPE int type, Object... contents) {
        log(type,CLogManger.getInstance().getConfig().getGlobalTag(),contents);
    }

    public static void log(@CLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(CLogManger.getInstance().getConfig(),type,tag,contents);
    }

    public static void log(@NonNull CLogConfig config,@CLogType.TYPE int type, @NonNull String tag, Object... contents) {
        if(!config.enable()){
            return;
        }
        StringBuilder sb = new StringBuilder();
        String body = pareBody(contents);
        sb.append(body);
        Log.println(type,tag,body);
    }

    private static String pareBody(@NonNull Object[] contents){
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
