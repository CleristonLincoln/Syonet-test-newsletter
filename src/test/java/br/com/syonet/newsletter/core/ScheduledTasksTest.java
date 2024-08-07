package br.com.syonet.newsletter.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ScheduledTasksTest {

    @SpyBean
    private ScheduledTasks scheduledTasks;

    @Test
    public void testPerformTask() {
        await().atMost(10, SECONDS).untilAsserted(() ->
                verify(scheduledTasks, times(1)).sendNotificationsPerEmail()
        );
    }
}