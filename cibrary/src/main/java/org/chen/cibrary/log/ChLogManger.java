package org.chen.cibrary.log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChLogManger {

    private ChLogConfig config;
    private static ChLogManger instance;
    private List<ChLogPrinter> printers = new ArrayList<>();

    private ChLogManger(ChLogConfig config, ChLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static ChLogManger getInstance() {
        return instance;
    }

    public static void init(@NonNull ChLogConfig config, ChLogPrinter... printers) {
        instance = new ChLogManger(config, printers);
    }

    public ChLogConfig getConfig() {
        return config;
    }


    public void addPrinter(ChLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(ChLogPrinter printer) {
        if (printers != null) {
            printers.remove(printer);
        }

    }

    public List<ChLogPrinter> getPrinters() {
        return printers;
    }
}
