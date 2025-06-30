package Model;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class RandomDateGenerator {
    public static Date getRandomDate() {
        Random rand = new Random();

        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        
        // Giới hạn ngày tháng cần tạo ngẫu nhiên (ví dụ: trong vòng 1 năm qua)
        calendar.add(Calendar.YEAR, -1); // Lùi 1 năm

        // Lấy ngày ngẫu nhiên trong khoảng từ 1 năm trước đến nay
        long startMillis = calendar.getTimeInMillis();
        long endMillis = System.currentTimeMillis();

        long randomMillis = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));

        return new Date(randomMillis); // Trả về ngày ngẫu nhiên
    }

    public static void main(String[] args) {
        Date randomDate = getRandomDate();
        System.out.println("Ngày ngẫu nhiên: " + randomDate);
    }
}
