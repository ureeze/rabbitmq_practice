package com.example.receiver;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(bindings = @QueueBinding(exchange = @Exchange(name = "time", type = ExchangeTypes.TOPIC),
            value = @Queue(name = "time-second"),
            key = "time-first"))
    public void receiver(String msg) {
        System.out.println("<= receiver : " + msg);
    }

  /*  @RabbitListener(bindings = @QueueBinding(exchange = @Exchange(name = "dept", type = ExchangeTypes.TOPIC),
            value = @Queue(name = "dept-second"),
            key = "dept-first"))
    public void receiver2(Dept dept) {
        System.out.println("2 <= receiver : " + dept.toString());
    }*/

    @RabbitListener(queues = "dept-second")
    public void receiver2(Dept dept) {
        System.out.println("2 <= receiver : " + dept.toString());
    }
}
