package com.yyd.autocheckin.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class AutoCheckInMorningTask {

    @Scheduled(cron = "0 45 8 * * ? ")
    public void call() throws IOException, InterruptedException {

        Random random = new Random();
        Thread.sleep(random.nextInt(60000));

        List<String> adbCommands = new ArrayList<>();

        adbCommands.add("adb reboot");
        execFromList(adbCommands, 45000);

        // 往上滑出密码区域
        adbCommands.clear();
        adbCommands.add("adb shell input swipe 577 1809 577 200");
        execFromList(adbCommands, 1000);


        // 解锁
        adbCommands.clear();
        adbCommands.add("adb shell input keyevent 11");
        adbCommands.add("adb shell input keyevent 12");
        adbCommands.add("adb shell input keyevent 10");
        adbCommands.add("adb shell input keyevent 11");
        adbCommands.add("adb shell input keyevent 13");
        adbCommands.add("adb shell input keyevent 16");
        execFromList(adbCommands, 0);


        // 滑动屏幕
        adbCommands.clear();
        adbCommands.add("adb shell input swipe 500 300 300 300");
        adbCommands.add("adb shell input swipe 500 300 300 300");
        execFromList(adbCommands,1000);

        // 打开企业微信
        adbCommands.clear();
        adbCommands.add("adb shell input tap 670 1370");
        //点击工作台
        adbCommands.add("adb shell input tap 669 1832");
        // 点开打开页面
        adbCommands.add("adb shell input tap 225 723");

        execFromList(adbCommands,6000);

        // 打卡
        adbCommands.clear();
        adbCommands.add("adb shell input tap 556 1400");

    }

    private Process exec(String command, long mills) throws IOException {
        Process process = Runtime.getRuntime().exec(command);;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String line = null;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            System.out.println("exec " + command + "end");
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return process;
    }

    private void execFromList(List<String> commands, long mills) throws IOException {
        for (String command : commands){
            Process process;
            process = exec(command, mills);
            process.destroy();

        }
    }
    private void execFromFile(String path){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));) {
            String line = null;
            while((line = br.readLine()) != null){
                exec(line, 0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
