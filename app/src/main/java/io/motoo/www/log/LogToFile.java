package io.motoo.www.log;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.MEDIA_MOUNTED;

public class LogToFile {
    /**
     * 어플 기본 이름
     */
    public static final String APP_NAME = "모투";

    /**
     * 로그를 남길 filePath(외장에) 멤버 문자열 변수
     */
    private String mPath;

    /**
     * 파일 write를 쉽게 도와주는 멤버 Writer 변수
     */
    private Writer mWriter;

    private static final SimpleDateFormat TIMESTAMP_FMT = new SimpleDateFormat("[HH:mm:ss] ");

    public LogToFile() throws IOException {
        openLogFile();
    }

    protected void openLogFile() throws IOException {
        File sdcard = Environment.getExternalStorageDirectory();
        String sdcardPath = sdcard.getAbsolutePath();
        String extStorageState = Environment.getExternalStorageState();

        if (MEDIA_MOUNTED.equals(extStorageState)) {
            String dir = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/" + APP_NAME + "/";

            File fDir = new File(dir);
            if (!fDir.exists()) {
                fDir.mkdirs();
            }

            File f = new File(sdcardPath + "/" + APP_NAME + "/" + "LogTrace.txt");

            mPath = f.getAbsolutePath();
            mWriter = new BufferedWriter(new FileWriter(mPath, true), 2048);
        }
    }

    public String getPath() {
        return mPath;
    }

    public void println(String message) throws IOException {
        mWriter.write(TIMESTAMP_FMT.format(new Date()));
        mWriter.write(message);
        mWriter.write('\n');
        mWriter.flush();
    }

    public void close() throws IOException {
        mWriter.close();
    }

}
