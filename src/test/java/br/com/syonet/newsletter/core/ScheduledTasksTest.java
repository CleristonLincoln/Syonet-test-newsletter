package br.com.syonet.newsletter.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ScheduledTasks.class})
class ScheduledTasksTest {
    @Autowired
    private ScheduledTasks yourScheduledClass;

    @Test
    public void testScheduledTask() {
        await().atMost(60, SECONDS).untilAsserted(() -> {
            System.out.println("dwedfw");
         //   assertThat(yourScheduledClass.performTask(), is(true));
        });
    }
}