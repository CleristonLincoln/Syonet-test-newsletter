package br.com.syonet.newsletter.infra;

import br.com.syonet.newsletter.domain.model.Cliente;
import br.com.syonet.newsletter.domain.model.ControleEnvio;
import br.com.syonet.newsletter.domain.model.Noticia;
import br.com.syonet.newsletter.domain.repository.NoticiaRepository;
import br.com.syonet.newsletter.domain.service.ClienteService;
import br.com.syonet.newsletter.domain.service.ControleEnvioService;
import br.com.syonet.newsletter.domain.service.NoticiaService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class SendMailServiceTest {

    // @Autowired
    @InjectMocks
    private SendMailService sendMailService;

    @Mock
    private ClienteService clienteService;
    @Mock
    private NoticiaService noticiaService;
    @Mock
    private EmailService emailService;
    @Mock
    private ControleEnvioService controleEnvioService;

    @Test
    void sendEmail() {
        List<Noticia> noticias = Arrays.asList(
                new Noticia(1L, "Noticia 1", "", ""),
                new Noticia(2L, "", "", "http://localhost:9090")
        );

        List<Cliente> clientes = Arrays.asList(
                new Cliente(1L, "Cleriston", "cleristonlincoln@hotmail.com", LocalDate.now()),
                new Cliente(2L, "Lincoln", "cleristonlincoln@hotmail.com", null)
        );

        lenient().when(clienteService.findAll()).thenReturn(clientes);
        lenient().when(noticiaService.noticiasNaoEnviadas()).thenReturn(noticias);

        doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString());

        doNothing().when(controleEnvioService).save(any(ControleEnvio.class));

        sendMailService.enviarEmail();

        verify(emailService, times(noticias.size() * clientes.size())).sendEmail(anyString(), anyString(), anyString());
        verify(controleEnvioService, times(noticias.size() * clientes.size())).save(any(ControleEnvio.class));

    }


    @Test
    void sendEmailWithBirthday() {
        List<Noticia> noticias = List.of(
                new Noticia(1L, "Noticia 1", "", "")
        );

        List<Cliente> clientes = List.of(
                new Cliente(1L, "Cleriston", "cleristonlincoln@hotmail.com", LocalDate.now())
        );

        lenient().when(clienteService.findAll()).thenReturn(clientes);
        lenient().when(noticiaService.noticiasNaoEnviadas()).thenReturn(noticias);

        doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString());

        doNothing().when(controleEnvioService).save(any(ControleEnvio.class));

        sendMailService.enviarEmail();

        ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
        verify(emailService, times(clientes.size())).sendEmail(anyString(), anyString(), bodyCaptor.capture());

        List<String> capturedBodies = bodyCaptor.getAllValues();
        for (String body : capturedBodies) {
            assertTrue(body.contains("Feliz aniversário!"));
        }
    }

    @Test
    void sendEmailWithNotBirthday() {
        List<Noticia> noticias = List.of(
                new Noticia(1L, "Noticia 1", "", "")
        );

        List<Cliente> clientes = List.of(
                new Cliente(1L, "Cleriston", "cleristonlincoln@hotmail.com", null)
        );

        lenient().when(clienteService.findAll()).thenReturn(clientes);
        lenient().when(noticiaService.noticiasNaoEnviadas()).thenReturn(noticias);

        doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString());

        doNothing().when(controleEnvioService).save(any(ControleEnvio.class));

        sendMailService.enviarEmail();

        ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
        verify(emailService, times(clientes.size())).sendEmail(anyString(), anyString(), bodyCaptor.capture());

        List<String> capturedBodies = bodyCaptor.getAllValues();
        for (String body : capturedBodies) {
            assertFalse(body.contains("Feliz aniversário!"));
        }
    }

    @Test
    void sendEmailWithLink() {
        List<Noticia> noticias = List.of(
                new Noticia(1L, "Noticia 1", "", "http://localhost:99090")
        );

        List<Cliente> clientes = List.of(
                new Cliente(1L, "Cleriston", "cleristonlincoln@hotmail.com", null)
        );

        lenient().when(clienteService.findAll()).thenReturn(clientes);
        lenient().when(noticiaService.noticiasNaoEnviadas()).thenReturn(noticias);

        doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString());

        doNothing().when(controleEnvioService).save(any(ControleEnvio.class));

        sendMailService.enviarEmail();

        ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
        verify(emailService, times(clientes.size())).sendEmail(anyString(), anyString(), bodyCaptor.capture());

        List<String> capturedBodies = bodyCaptor.getAllValues();
        for (String body : capturedBodies) {
            assertTrue(body.contains("http://localhost:99090"));
        }
    }

    @Test
    void sendEmailWithNotLink() {
        List<Noticia> noticias = List.of(
                new Noticia(1L, "Noticia 1", "", null)
        );

        List<Cliente> clientes = List.of(
                new Cliente(1L, "Cleriston", "cleristonlincoln@hotmail.com", null)
        );

        lenient().when(clienteService.findAll()).thenReturn(clientes);
        lenient().when(noticiaService.noticiasNaoEnviadas()).thenReturn(noticias);

        doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString());

        doNothing().when(controleEnvioService).save(any(ControleEnvio.class));

        sendMailService.enviarEmail();

        ArgumentCaptor<String> bodyCaptor = ArgumentCaptor.forClass(String.class);
        verify(emailService, times(clientes.size())).sendEmail(anyString(), anyString(), bodyCaptor.capture());

        List<String> capturedBodies = bodyCaptor.getAllValues();
        for (String body : capturedBodies) {
            assertFalse(body.contains("http://localhost:99090"));
        }
    }

}