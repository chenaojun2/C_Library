package org.chen.cibrary.log;

public class ChThreadFormatter implements ChLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread"+data.getName();
    }
}
