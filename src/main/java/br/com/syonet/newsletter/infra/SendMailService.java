package br.com.syonet.newsletter.infra;

import br.com.syonet.newsletter.domain.model.Cliente;
import br.com.syonet.newsletter.domain.model.Noticia;
import br.com.syonet.newsletter.domain.service.ClienteService;
import br.com.syonet.newsletter.domain.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SendMailService {

    private final int NUMBER_CLIENTES_PER_SEND = 50;

    @Autowired
    private NoticiaService noticiaService;

    @Autowired
    private ClienteService clienteService;


    public void sendMail() {

        List<Cliente> clientes = new ArrayList<>();
        int totalPages = 0;

        List<Noticia> noticiasNotSend = getNoticiasNotSend();

        System.out.println(noticiasNotSend);

        for (int i = 0; i <= totalPages; i++) {
            Page<Cliente> clientesPage = clienteService
                    .getListPageable(PageRequest.of(i, NUMBER_CLIENTES_PER_SEND));

            totalPages = clientesPage.getTotalPages();

            clientesPage.getContent().forEach(cliente -> {});
        }

    }

    private List<Noticia> getNoticiasNotSend() {
        return noticiaService.noticiaNotSend();
    }

}
