package br.com.syonet.newsletter.infra;

import br.com.syonet.newsletter.domain.model.Cliente;
import br.com.syonet.newsletter.domain.model.ControleEnvio;
import br.com.syonet.newsletter.domain.model.Noticia;
import br.com.syonet.newsletter.domain.service.ClienteService;
import br.com.syonet.newsletter.domain.service.ControleEnvioService;
import br.com.syonet.newsletter.domain.service.NoticiaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class SendMailService {

    private final NoticiaService noticiaService;
    private final ClienteService clienteService;
    private final EmailService emailService;
    private final ControleEnvioService controleEnvioService;



    public void enviarEmail() {

        log.info("Iniciando metodo de envio de emails");

        List<Noticia> noticiasNotSend = new ArrayList<>(getNoticiasNaoEnviadas());

        List<Cliente> clientes = clienteService.findAll();

        if (!noticiasNotSend.isEmpty()) {
            noticiasNotSend.forEach(noticia -> {
                clientes.forEach(cliente -> {
                    criarEmailEEnviar(noticia, cliente);
                });
            });
        } else {
            log.info("Nenhum notícia a ser enviada.");
        }

    }

    public void criarEmailEEnviar(Noticia noticia, Cliente cliente) {

        StringBuilder body = new StringBuilder();

        body.append("Bom dia ").append(cliente.getNome()).append("!\n");

        if (cliente.getDataNascimento() != null && cliente.getDataNascimento().equals(LocalDate.now())) {
            body.append("Feliz aniversário!\n\n");
        }

        if (noticia.getLink() != null) {
            body.append(noticia.getLink()).append("\n\n");
        }

        body.append(noticia.getDescricao());

        if (cliente.getEmail() != null && noticia.getTitulo()!= null )
            emailService.sendEmail(cliente.getEmail(), noticia.getTitulo(), body.toString());

        controleEnvioService.save(new ControleEnvio(noticia, cliente));
    }


    List<Noticia> getNoticiasNaoEnviadas() {
        return noticiaService.noticiasNaoEnviadas();
    }

}
