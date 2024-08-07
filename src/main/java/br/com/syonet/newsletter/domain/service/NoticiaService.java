package br.com.syonet.newsletter.domain.service;

import br.com.syonet.newsletter.domain.model.Noticia;

import java.util.List;

public interface NoticiaService extends BaseService<Noticia> {
    List<Noticia> noticiaNotSend();
}
