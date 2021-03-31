package io.motoo.www.log;

import android.content.Context;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExUncaughtExceptionHandler implements UncaughtExceptionHandler {
    private Context mContext;

    private UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;

    public ExUncaughtExceptionHandler(Context context) {

        mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

        mContext = context;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.
     * lang.Thread, java.lang.Throwable)
     */

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        final String logMsgParams = makeStackTrace(thread, ex);

        LogUtil.e(logMsgParams);

        callDefaultUncaughtExceptionHandler(thread, ex);
    }

    private String makeStackTrace(Thread thread, Throwable ex) {

        StringBuilder errLog = new StringBuilder();

        errLog.append("FATAL EXCEPTION: " + thread.getName());

        errLog.append("\n");

        errLog.append(ex.toString());

        errLog.append("\n");

        StackTraceElement[] stack = ex.getStackTrace();

        for (StackTraceElement element : stack) {

            errLog.append("    at " + element);

            errLog.append("\n");

        }

        StackTraceElement[] parentStack = stack;

        Throwable throwable = ex.getCause();

        while (throwable != null) {

            errLog.append("Caused by: ");

            errLog.append(throwable.toString());

            errLog.append("\n");

            StackTraceElement[] currentStack = throwable.getStackTrace();

            int duplicates = countDuplicates(currentStack, parentStack);

            for (int i = 0; i < currentStack.length - duplicates; i++) {

                errLog.append("    at " + currentStack[i].toString());

                errLog.append("\n");

            }

            if (duplicates > 0) {

                errLog.append("    ... " + duplicates + " more");

            }

            parentStack = currentStack;

            throwable = throwable.getCause();

        }

        return errLog.toString();

    }

    private int countDuplicates(StackTraceElement[] currentStack, StackTraceElement[] parentStack) {

        int duplicates = 0;

        int parentIndex = parentStack.length;

        for (int i = currentStack.length; --i >= 0 && --parentIndex >= 0;) {

            StackTraceElement parentFrame = parentStack[parentIndex];

            if (parentFrame.equals(currentStack[i])) {

                duplicates++;

            } else {

                break;
            }
        }

        return duplicates;
    }

    private void callDefaultUncaughtExceptionHandler(Thread thread, Throwable ex) {

        if (mDefaultUncaughtExceptionHandler != null) {
            mDefaultUncaughtExceptionHandler.uncaughtException(thread, ex);
        }
    }
}
