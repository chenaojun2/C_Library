package org.chen.cibrary.log;

import androidx.annotation.NonNull;

public interface ChLogPrinter {

    void print(@NonNull ChLogConfig config, int level, String tag, @NonNull String printString);

}
