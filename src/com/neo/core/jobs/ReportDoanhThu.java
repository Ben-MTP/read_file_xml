package com.neo.core.jobs;

import com.neo.core.utils.FuncHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author ManhKM on 3/25/2022
 * @project readfilexml
 */
public class ReportDoanhThu extends Thread implements JobProcess{
    /**
     * Tập hợp các tham số cấu hình cho Module:
     * Theo kiểu cặp Key - Value.
     */
    HashMap<String, Object> p = new HashMap<>();
    private boolean isRun = true;
    private boolean isSendToday = false;
    private int delay = 1;    // 20s -> mili-second | 20*1000

    private String name = "module_01_alarm@vasgate";
    private int from_minute = 0;
    private int to_minute = 0;
    private int max_delay = 2;    //20*1000
    private int min_delay = 1;    //10*1000
    private int hour;
    private int minute;

    SimpleDateFormat fDate = new SimpleDateFormat("yyyyMMdd");

    /**
     * Method thực hiện chính của tiến trình Thread.
     *      Thread thực hiện run() -> bắt buộc.
     *      run() -> sau khi thực hiện thì sẽ thực hiện chính vào method doWork của tiến trình.
     *
     */
    @Override
    public void run() {
        System.out.println("-----> Start JOB send Email Report VAS-Online: " + isRun);
        while(isRun){
            try {
                doWork();
                sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setParam(HashMap<String, Object> param) {
        try{
            this.p = param;
            this.name = p.get("name").toString();
            min_delay = FuncHelper.getInt(p.get("min_delay").toString());
            max_delay = FuncHelper.getInt(p.get("max_delay").toString());
            from_minute = FuncHelper.getInt(p.get("from_minute").toString());
            to_minute = FuncHelper.getInt(p.get("to_minute").toString());
            System.out.println("-----> from minute: " + from_minute + " , to: " + to_minute);
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void startJob() {

    }

    /**
     * Hàm sử lý chính của tiến trình.
     */
    @Override
    public void doWork() {
        System.out.println("-----> Running method doWork...................");
        Date d = new Date();
        int minute = d.getMinutes() + d.getHours() * 60;
        if(minute >= from_minute && minute <= to_minute){
            delay = min_delay;
            if(isSendToday){
                delay = max_delay;
            } else {
                isSendToday = doSendReportDoanhThu(d);
            }
        } else {
            isSendToday = false;
            delay = max_delay;
            System.out.println("-----> OUT time for send Report: " + from_minute + " to: " + to_minute);
        }
    }

    @Override
    public boolean isWorking() {
        return false;
    }

    @Override
    public void setNameJob(String name) {

    }

    @Override
    public String getNameJob() {
        return null;
    }

    @Override
    public void setStop() {

    }

    @Override
    public void setTimeDelay(int time) {

    }

    @Override
    public int getTimeDelay() {
        return 0;
    }

    /**
     * Thực hiện gửi file Report Doanh Thu
     * @param date
     * @return
     */
    public boolean doSendReportDoanhThu(Date date){
        boolean isCheck = false;
        System.out.println("-----> Thực hiện send report doanh thu tổng hợp!!!");
        /**
         * Coding login something
         * Default method return false.
         */
        return isCheck;
    }

    public ArrayList<String> getListDay(int pre_day){
        ArrayList<String> list = new ArrayList<>();
        try {
            for(int i = 2; i < pre_day; i++){
                Date d = new Date();
                d.setDate(d.getDate() - i);
                String day = fDate.format(d);
                list.add(day);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }


}
