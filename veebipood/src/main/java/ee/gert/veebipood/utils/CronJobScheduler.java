package ee.gert.veebipood.utils;

import ee.gert.veebipood.entity.Order;
import ee.gert.veebipood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

// e-mail // SMS-i valjasaatmised.
// maksed, mis on maksmata seisundis.
//andmebaasi puhastus, naiteks sis kui on "holjuma" jaanud order.

@Component
public class CronJobScheduler {

    @Autowired
    OrderRepository orderRepository;

    // ? voi * - lopus (6th place) tahendab et programm jookseb vastavalt kellajale. - doesnt matter, sama tulemus
    // selle jaoks et jooksutada peale teatud aega on vaja kasutada teist lahendust mitte CRON-i
//                       S M H date
//    @Scheduled(cron = "* * * *    * *")
//    @Scheduled(cron = "0 * * * * ?") //runs every minute
//    public void runEveryMinute(){
//        Date date = new Date();
//        System.out.println(date.getMinutes() +":" + date.getSeconds());
//    }

//    @Scheduled(cron = "0/15 * * * * *") //runs every day at 10
//    public void sendBookingReminders(){
//        Date date = new Date();
//        System.out.println(date.getMinutes() +":" + date.getSeconds());
////        List<Order> orders = orderRepository.findAllbyCreationBetween();
////        for (Order order : orders){
////            order.getPerson().getEmail();
////            // saada koigidele tellmiuse omanikele meedetuletuse e-mail.
////        }
//        System.out.println("tuli 0:49");
//
//    }

    @Scheduled(cron = "0/10 * * 6 * SAT") //runs every minute
    public void asdfasdf(){
        Date date = new Date();
        System.out.println(date.getMinutes() +":" + date.getSeconds());
    }
//
//    @Scheduled(cron = "0/15 * * * * ?") //runs every minute
//    public void fddf(){
//        Date date = new Date();
//        System.out.println(date.getMinutes() +":" + date.getSeconds());
//    }
//
//    @Scheduled(cron = "0/15 * * * * ?") //runs every minute
//    public void fddffdfdff(){
//        Date date = new Date();
//        System.out.println(date.getMinutes() +":" + date.getSeconds());
//    }



}
