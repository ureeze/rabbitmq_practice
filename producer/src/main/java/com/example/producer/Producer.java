package com.example.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@RequiredArgsConstructor
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    //@Scheduled(fixedRate = 1000)
    public void sender1() {
        LocalDateTime now = LocalDateTime.now();
        String nowTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("send => " + nowTime);
        rabbitTemplate.convertAndSend("time", "time-first", nowTime);

    }

    @Scheduled(fixedRate = 1000)
    public void sender2() {
        LocalDateTime now = LocalDateTime.now();
        String nowTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Dept dept = new Dept(10, "test", "test", nowTime);
        System.out.println("1 <= " + dept.toString());
        rabbitTemplate.convertAndSend("dept", "dept-first", dept);

    }
}
