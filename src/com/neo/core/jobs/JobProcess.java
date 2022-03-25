package com.neo.core.jobs;

import java.util.HashMap;

/**
 * Định nghĩa Interface xử lý 1 công việc lặp đi lặp lại theo thời gian.
 * @author ManhKM on 3/25/2022
 * @project readfilexml
 */
public interface JobProcess {
    /**
     * Hàm cài đặt các tham số cho job
     * @param param
     */
    void setParam(HashMap<String, Object> param);

    /**
     * Hàm khởi tạo tiến trình cho job
     */
    void startJob();

    /**
     * Hàm xử lý chính của tiến trình.
     */
    void doWork();

    /**
     * Hàm kiểm tra trạng thái đang hoạt động của tiến trình.
     *      true -> đang hoạt động
     *      false -> đang dừng, không hoạt động.
     * @return
     */
    boolean isWorking();

    /**
     * Hàm tiết lập trên cho job.
     *      Mỗi job sẽ cần một tên để lúc chạy còn biết.
     * @param name
     */
    void setNameJob(String name);

    /**
     * Hàm lấy ra tên của Job
     * @return
     */
    String getNameJob();

    /**
     * Hàm thực hiện stop tiến trình ngay lập tức
     */
    void setStop();

    /**
     * Hàm thiết lập thời gian delay cho job
     *      Theo đơn vị: mili second.
     * @param time
     */
    void setTimeDelay(int time);

    int getTimeDelay();
}
