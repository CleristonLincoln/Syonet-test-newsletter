package br.com.syonet.newsletter.core;

import br.com.syonet.newsletter.infra.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@EnableScheduling
@Component
public class ScheduledTasks {

    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    private SendMailService sendMailService;


 //   @Scheduled(cron = "0 12 9 * * ?", zone = TIME_ZONE)
  //  @Scheduled(fixedRate = 5000)
    public void performTask() {
        System.out.println("A tarefa agendada está sendo executada: " + LocalDateTime.now());
        sendMailService.sendMail();
    }

}