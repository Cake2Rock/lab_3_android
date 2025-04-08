package com.example.lab3;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class FileHelper {

    public static boolean appendToFile(Context context, String fileName, String data) {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(fileName, Context.MODE_APPEND);
            fos.write((data + "\n").getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fos != null) {
                try { fos.close(); } catch (IOException ignored) {}
            }
        }
    }

    public static String readFromFile(Context context, String fileName) {
        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();
        try {
            fis = context.openFileInput(fileName);
            int c;
            while ((c = fis.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try { fis.close(); } catch (IOException ignored) {}
            }
        }
        return sb.toString();
    }
}
