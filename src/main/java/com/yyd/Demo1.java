package com.yyd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Demo1 {

    List<String> adbCommands = new ArrayList<String>();

    private void initialize(){
        adbCommands.add("adb shell am start -n com.jzzs.ParentsHelper/com.jzzs.ParentsHelper.MainActivity");
        //点击用户名输入框
        adbCommands.add("adb shell input tap 589.5 798.5");
        //点击清除输入框
        adbCommands.add("adb shell input tap 1007 788.5");
        adbCommands.add("adb shell input keyboard text wangyue1253213");
        //点击密码框
        adbCommands.add("adb shell input tap 235.8 1009.5");
        adbCommands.add("adb shell input keyboard text 123456");
        //收回键盘
        adbCommands.add("adb shell input tap 994 1188.4");
        //点击登录
        adbCommands.add("adb shell input tap 578.5 1189.4");

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Demo1 demo1 = new Demo1();
        demo1.initialize();
        ListIterator<String> it =  demo1.adbCommands.listIterator();
        while(it.hasNext()){
           String command = it.next();
           Process process = Runtime.getRuntime().exec(command);
           Thread.sleep(1000);
           process.destroy();
        }
    }


    public void 安全教育平台() {
        try {
            Process process = Runtime.getRuntime().exec("adb shell am start -n  com.jzzs.ParentsHelper/com.jzzs.ParentsHelper.GuideActivity");
            Thread.sleep(3000);
            process.destroy();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
