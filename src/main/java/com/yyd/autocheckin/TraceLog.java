package com.yyd.autocheckin;

import java.io.*;
import java.math.BigInteger;

public class TraceLog {
    public static final String traceLogPath = "C:\\Users\\yyd\\IdeaProjects\\androidauto\\src\\main\\resources\\滑动解锁.log";
    public static final String convertedTraceLogPath = "C:\\Users\\yyd\\IdeaProjects\\androidauto\\src\\main\\resources\\convertedTraceLogPath.log";

    public static void main(String[] args) {
        convertHexTraceToDecinalTrace(traceLogPath, convertedTraceLogPath);
    }


    private static void convertHexTraceToDecinalTrace(String inputPath, String outputPath){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath)));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath)))) {
            String line = null;
            while((line = br.readLine()) != null){
                String[] hexes = line.split(" ");
                String decimals = "";
                for(int i=0; i<hexes.length; i++){
                    decimals = decimals + hexToDecimal(hexes[i]) + " ";
                }
                decimals = decimals + "\n";
                String swipeCommand = "adb shell sendevent /dev/input/event1 ";
                bw.write(swipeCommand + decimals);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int hexToDecimal(String hex){
        BigInteger amount = new BigInteger(hex, 16);
        System.out.println(amount);
        return amount.intValue();
    }
}
