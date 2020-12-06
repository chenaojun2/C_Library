package org.chen.cibrary.log;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ChLogMo {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
    public long timeMills;
    public int level;
    public String tag;
    public String log;

    public ChLogMo(long timeMills, int level, String tag, String log) {
        this.timeMills = timeMills;
        this.level = level;
        this.tag = tag;
        this.log = log;
    }

    public String flattenedLog() {
        return getFlattened() + "\n" + log;
    }

    public String getFlattened() {
        return format(timeMills) + '|' + level + '|' + tag + "|:";
    }

    public String format(long timeMills) {
        return sdf.format(timeMills);
    }
}
