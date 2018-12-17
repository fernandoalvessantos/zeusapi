package com.fernando.zeus.zeusapi;

import com.fernando.zeus.zeusapi.domain.Demanda;
import com.fernando.zeus.zeusapi.domain.Usuario;
import com.fernando.zeus.zeusapi.repository.DemandaRepository;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DemandaTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private DemandaRepository demandaRepository;

    public void criandoReg() {

        Usuario usuario = new Usuario();
        usuario.setNome("USER TESTE");
        usuario.setPerfil("cliente");
        usuario.setEmail("email");
        usuario.setSenha("senha");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("USER TESTE 2");
        usuario2.setPerfil("cliente");
        usuario2.setEmail("email");
        usuario2.setSenha("senha");

        Usuario usuario3 = new Usuario();
        usuario3.setNome("USER TESTE 3");
        usuario3.setPerfil("gerente");
        usuario3.setEmail("email");
        usuario3.setSenha("senha");

        usuario = testEntityManager.persist(usuario);
        testEntityManager.flush();
        usuario2 = testEntityManager.persist(usuario2);
        testEntityManager.flush();
        usuario3 = testEntityManager.persist(usuario3);
        testEntityManager.flush();

        Demanda demanda = new Demanda();
        demanda.setNome("Fernando");
        demanda.setDescricao("Descrição Fernando");
        demanda.setCliente(usuario);

        Demanda demanda2 = new Demanda();
        demanda2.setNome("Aline");
        demanda2.setDescricao("Descrição Aline");
        demanda2.setCliente(usuario);

        Demanda demanda3 = new Demanda();
        demanda3.setNome("3");
        demanda3.setCliente(usuario2);

        demanda = testEntityManager.persist(demanda);
        testEntityManager.flush();
        demanda2 = testEntityManager.persist(demanda2);
        testEntityManager.flush();
        demanda3 = testEntityManager.persist(demanda3);
        testEntityManager.flush();

    }

    public void relacionando(){
        Demanda demanda = testEntityManager.find(Demanda.class, 1L);
        Usuario usuario =testEntityManager.find(Usuario.class, 3L);
        demanda.setGerente(usuario);
        testEntityManager.merge(demanda);
        testEntityManager.flush();
    }

    @Test
    public void testePesquisaQuantidade() {
        this.criandoReg();
        List<Demanda> lista = demandaRepository.listaPesquisaPorCliente(1L, null, null, null, null);
        if (lista.isEmpty())
            Assert.fail();

        List<Demanda> lista2 = demandaRepository.listaPesquisaPorCliente(1L, 1L, null, null, null);
        if (lista2.get(0).getId() != 1L)
            Assert.fail();

        List<Demanda> lista3 = demandaRepository.listaPesquisaPorCliente(1L, null, "Fernando", null, null);
        if (!lista3.get(0).getNome().equals("Fernando"))
            Assert.fail();

        List<Demanda> lista4 = demandaRepository.listaPesquisaPorCliente(1L, null, "Fern", null, null);
        if (!lista4.get(0).getNome().equals("Fernando"))
            Assert.fail();

        List<Demanda> lista5 = demandaRepository.listaPesquisaPorCliente(1L, null, null, "Fernando", null);
        if (!lista5.get(0).getDescricao().equals("Descrição Fernando"))
            Assert.fail();

        Assert.assertTrue(lista.size() == 2);
    }

    @Test
    public void testeNaoRelacionados(){
        this.criandoReg();
        List<Demanda> lista = demandaRepository.listaNaoRelacionadas();
        if(lista.size() != 3){
            Assert.fail();
        }
        this.relacionando();
        List<Demanda> lista2 = demandaRepository.listaNaoRelacionadas();
        for (Demanda demanda: lista2 ) {
            if(demanda.getId() == 1L){
                Assert.fail("Listou demanda já relacionada");
            }
        }
    }

}
