package org.chen.cibrary.log;

public class ChStackTraceUtil {


    public static StackTraceElement[] getCroppedRealStackTrack(StackTraceElement[] stackTrace, String ignorePackage, int maxDepth) {
        return cropStackTrace(getRealStackTrace(stackTrace,ignorePackage),maxDepth);
    }


    /**
     * 获取除忽略包外的堆栈信息
     */
    private static StackTraceElement[] getRealStackTrace(StackTraceElement[] stackTrace, String ignorePackage) {

        int ignoreDepth = 0;
        int allDepth = stackTrace.length;
        String className;

        for (int i = allDepth - 1; i >= 0; i--) {
            className = stackTrace[i].getClassName();
            if (ignorePackage != null && className.startsWith(ignorePackage)) {
                ignoreDepth = i + 1;
                break;
            }
        }

        int realDepth = allDepth - ignoreDepth;
        StackTraceElement[] realStack = new StackTraceElement[realDepth];

        System.arraycopy(stackTrace, ignoreDepth, realStack, 0, realDepth);
        return realStack;

    }


    /**
     * 裁剪堆栈信息
     */
    private static StackTraceElement[] cropStackTrace(StackTraceElement[] callstack, int maxDepth) {
        int realDepth = callstack.length;
        if (maxDepth > 0) {
            realDepth = Math.min(maxDepth, realDepth);
        }
        StackTraceElement[] realstack = new StackTraceElement[realDepth];
        System.arraycopy(callstack, 0, realstack, 0, realDepth);
        return realstack;
    }

}
