package br.com.syonet.newsletter.core;

import br.com.syonet.newsletter.infra.SendMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Log4j2

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private static final String TIME_ZONE = "America/Sao_Paulo";

    private final SendMailService sendMailService;

    @Scheduled(cron = "${syonet.datetime.sendNotification}", zone = TIME_ZONE)
    public void sendNotificationsPerEmail() {
        log.info("Iniciando tarefa agendada: sendNotificationsPerEmail {}", LocalDateTime.now());

        sendMailService.processarEmails();

        log.info("Finalizando tarefa agendada: {}", LocalDateTime.now());

    }

}