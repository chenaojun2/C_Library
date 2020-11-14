package org.chen.cibrary.log;

public interface ChLogFormatter<T> {
    String format(T data);
}
