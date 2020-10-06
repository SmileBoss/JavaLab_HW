package ru.itis;

import java.util.concurrent.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Loader {
    private int cnt = 0;
    private ExecutorService executor;

    public void loadOneThread(List<String> urls, String path) {

        executor = Executors.newFixedThreadPool(1);

        for (String url : urls) {
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName());
                downloadFiles(url, path);
            };
            executor.submit(task);
        }
    }

    public void loadMultiThread(Integer count, List<String> urls, String path) {

        executor = Executors.newFixedThreadPool(count);

        for (String url : urls) {
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName());
                downloadFiles(url, path);
            };
            executor.submit(task);
        }

    }

    private void downloadFiles(String strURL, String path) {
        try {
            URL url = new URL(strURL);
            URLConnection connect = url.openConnection();
            connect.setDoOutput(true);
            connect.setDoInput(true);

            BufferedInputStream bis = new BufferedInputStream(connect.getInputStream());
            FileOutputStream fos = new FileOutputStream(new File(path + "image" + (cnt++) + ".jpg"));

            int ch;
            while ((ch = bis.read()) != -1) {
                fos.write(ch);
            }
            bis.close();
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}