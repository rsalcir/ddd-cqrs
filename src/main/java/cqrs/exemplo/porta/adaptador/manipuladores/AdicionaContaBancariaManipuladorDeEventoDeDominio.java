package cqrs.exemplo.porta.adaptador.manipuladores;

import cqrs.exemplo.aplicacao.consulta.modelo.ContaBancaria;
import cqrs.exemplo.dominio.ContaBancariaId;
import cqrs.exemplo.dominio.ContaCriada;
import cqrs.exemplo.porta.adaptador.manipuladores.base.ManipuladorDeEventoDeDominio;
import cqrs.exemplo.porta.adaptador.persistencia.base.RepositorioJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class AdicionaContaBancariaManipuladorDeEventoDeDominio extends RepositorioJpa<ContaBancaria, ContaBancariaId> implements ManipuladorDeEventoDeDominio<ContaCriada> {

    @Autowired
    protected AdicionaContaBancariaManipuladorDeEventoDeDominio(EntityManager entityManager) {
        super(entityManager);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void manipular(ContaCriada evento) {
        ContaBancaria contaBancaria = new ContaBancaria(evento.id(), evento.cliente(), evento.valor());
        super.adicionar(contaBancaria);
    }
}