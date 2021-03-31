package io.motoo.www.log;

import android.util.Log;

import java.io.IOException;

public class LogUtil {
    /**
     * 로그상에 출력될 어플리케이션 이름 직접 입력 할 것
     */
    public static final String APP_NAME = "모투";

    /**
     * 디버그 없음
     */
    public static final int DEBUG_NONE = 0;

    /**
     * LogCat으로만 출력
     */
    public static final int DEBUG_TRACE = 1;

    /**
     * 파일에 로그 출력
     */
    public static final int DEBUG_FILE = 2;

    /**
     * 정적 로그 모드 멤버 변수
     */
    private static int sDebugMode = 1;

    
    private static void writeToFile(String level, String msg) {
        LogToFile logToFile = null;

        try {
            logToFile = new LogToFile();
            logToFile.println("[" + level + "]" + msg);

        } catch (IOException e) {
            Log.e(APP_NAME, e.getLocalizedMessage());
        } finally {
            if (logToFile != null)
                try {
                    logToFile.close();
                } catch (IOException e) {
                    Log.e(APP_NAME, e.getLocalizedMessage());
                }
        }
    }

    public static void v(String message) {
        if (sDebugMode == DEBUG_TRACE || sDebugMode == DEBUG_FILE) {
            String tag = "";
            String temp = new Throwable().getStackTrace()[1].getClassName();
            if (temp != null) {
                int lastDotPos = temp.lastIndexOf(".");
                tag = temp.substring(lastDotPos + 1);
            }
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            String logText = "[" + tag + "] " + methodName + "()" + "[" + lineNumber + "]" + " >> "
                    + message;

            if (sDebugMode == DEBUG_FILE) {
                writeToFile("verbose", logText);
            } else {
                Log.v(APP_NAME, logText);
            }
        }
    }

    public static void i(String message) {
        if (sDebugMode == DEBUG_TRACE || sDebugMode == DEBUG_FILE) {
            String tag = "";
            String temp = new Throwable().getStackTrace()[1].getClassName();
            if (temp != null) {
                int lastDotPos = temp.lastIndexOf(".");
                tag = temp.substring(lastDotPos + 1);
            }
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            String logText = "[" + tag + "] " + methodName + "()" + "[" + lineNumber + "]" + " >> "
                    + message;

            if (sDebugMode == DEBUG_FILE) {
                writeToFile("info", logText);
            } else {
                Log.i(APP_NAME, logText);
            }
        }
    }

    public static void d(String message) {
        if (sDebugMode == DEBUG_TRACE || sDebugMode == DEBUG_FILE) {
            String tag = "";
            String temp = new Throwable().getStackTrace()[1].getClassName();
            if (temp != null) {
                int lastDotPos = temp.lastIndexOf(".");
                tag = temp.substring(lastDotPos + 1);
            }
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            String logText = "[" + tag + "] " + methodName + "()" + "[" + lineNumber + "]" + " >> "
                    + message;
            if (sDebugMode == DEBUG_FILE) {
                writeToFile("debug", logText);
            } else {
                Log.d(APP_NAME, logText);
            }
        }
    }

    public static void w(String message) {
        if (sDebugMode == DEBUG_TRACE || sDebugMode == DEBUG_FILE) {
            String tag = "";
            String temp = new Throwable().getStackTrace()[1].getClassName();
            if (temp != null) {
                int lastDotPos = temp.lastIndexOf(".");
                tag = temp.substring(lastDotPos + 1);
            }
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            String logText = "[" + tag + "] " + methodName + "()" + "[" + lineNumber + "]" + " >> "
                    + message;

            if (sDebugMode == DEBUG_FILE) {
                writeToFile("warn", logText);
            } else {
                Log.w(APP_NAME, logText);
            }
        }
    }

    public static void e(String message) {
        if (sDebugMode == DEBUG_TRACE || sDebugMode == DEBUG_FILE) {
            String tag = "";
            String temp = new Throwable().getStackTrace()[1].getClassName();
            if (temp != null) {
                int lastDotPos = temp.lastIndexOf(".");
                tag = temp.substring(lastDotPos + 1);
            }
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            String logText = "[" + tag + "] " + methodName + "()" + "[" + lineNumber + "]" + " >> "
                    + message;

            if (sDebugMode == DEBUG_FILE) {
                writeToFile("error", logText);

                Log.e(APP_NAME, logText);
            } else {
                Log.e(APP_NAME, logText);
            }
        }
    }

    public static void wtf(String message) {
        if (sDebugMode == DEBUG_TRACE || sDebugMode == DEBUG_FILE) {
            String tag = "";
            String temp = new Throwable().getStackTrace()[1].getClassName();
            if (temp != null) {
                int lastDotPos = temp.lastIndexOf(".");
                tag = temp.substring(lastDotPos + 1);
            }
            String methodName = new Throwable().getStackTrace()[1].getMethodName();
            int lineNumber = new Throwable().getStackTrace()[1].getLineNumber();

            String logText = "[" + tag + "] " + methodName + "()" + "[" + lineNumber + "]" + " >> "
                    + message;

            if (sDebugMode == DEBUG_FILE) {
                writeToFile("wtf", logText);
            } else {
                Log.wtf(APP_NAME, logText);
            }
        }
    }

    public static int getDebugMode() {
        return sDebugMode;
    }

    public static void setDebugMode(int debugMode) {
        if (debugMode < DEBUG_NONE || debugMode > DEBUG_FILE)
            sDebugMode = DEBUG_NONE;
        else
            sDebugMode = debugMode;
    }
}
