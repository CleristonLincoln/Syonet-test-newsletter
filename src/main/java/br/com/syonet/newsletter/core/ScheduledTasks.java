package br.com.syonet.newsletter.core;

import br.com.syonet.newsletter.infra.SendMailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Log4j2
@EnableScheduling
@Component
public class ScheduledTasks {

    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    private SendMailService sendMailService;


    @Scheduled(cron = "0 45 13 * * ?", zone = TIME_ZONE)
    public void performTask() {
        log.info("Iniciando tarefa agendada: {}", LocalDateTime.now());

        sendMailService.enviarEmail();

        log.info("Finalizando tarefa agendada: {}", LocalDateTime.now());

    }

}