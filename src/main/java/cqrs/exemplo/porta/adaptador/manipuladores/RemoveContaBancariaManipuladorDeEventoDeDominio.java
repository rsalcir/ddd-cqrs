package cqrs.exemplo.porta.adaptador.manipuladores;

import cqrs.exemplo.porta.adaptador.repositoriodeconsulta.ContaBancariaConsulta;
import cqrs.exemplo.dominio.ContaBancariaId;
import cqrs.exemplo.dominio.ContaRemovida;
import cqrs.exemplo.porta.adaptador.manipuladores.base.ManipuladorDeEventoDeDominio;
import cqrs.exemplo.porta.adaptador.persistencia.base.RepositorioJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class RemoveContaBancariaManipuladorDeEventoDeDominio extends RepositorioJpa<ContaBancariaConsulta, ContaBancariaId> implements ManipuladorDeEventoDeDominio<ContaRemovida> {

    @Autowired
    protected RemoveContaBancariaManipuladorDeEventoDeDominio(EntityManager entityManager) {
        super(entityManager);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void manipular(ContaRemovida evento) {
        ContaBancariaConsulta contaBancaria = super.obter(evento.id());
        super.remover(contaBancaria);
    }
}
