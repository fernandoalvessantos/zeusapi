package com.fernando.zeus.zeusapi.repository;

import com.fernando.zeus.zeusapi.domain.Demanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DemandaRepository extends JpaRepository<Demanda, Long> {

    @Query("SELECT d FROM Demanda d JOIN d.cliente c WHERE c.id = :idCliente")
    List<Demanda> listarPorIdCliente(@Param("idCliente") Long idCliente);

    @Query("SELECT d FROM Demanda d JOIN d.cliente c WHERE c.id = :idCliente " +
            "AND (d.id = :id OR :id is null) " +
            "AND (d.nome like CONCAT('%', :nome, '%') OR :nome is null )" +
            "AND (d.descricao like CONCAT('%', :descricao, '%') OR :descricao is null )")
    List<Demanda> listaPesquisaPorCliente(@Param("idCliente") Long idCliente,
                                          @Param("id") Long id,
                                          @Param("nome") String nome,
                                          @Param("descricao") String descricao);

    @Query("SELECT d FROM Demanda d WHERE d.gerente is null")
    List<Demanda> listaNaoRelacionadas();

    @Query("SELECT d FROM Demanda d JOIN d.cliente c WHERE d.gerente is null " +
            "AND (d.id = :id OR :id is null) " +
            "AND (d.nome like CONCAT('%', :nome, '%') OR :nome is null )" +
            "AND (d.descricao like CONCAT('%', :descricao, '%') OR :descricao is null )" +
            "AND (c.nome like CONCAT('%', :nomeCliente, '%') OR :nomeCliente is null )")
    List<Demanda> listaPesquisaNaoRelacionada(@Param("id") Long id,
                                              @Param("nome") String nome,
                                              @Param("descricao") String descricao,
                                              @Param("nomeCliente") String nomeCliente);


    @Query("SELECT d FROM Demanda d JOIN d.cliente c WHERE c.id = :idCliente " +
            "AND d.id = :id")
    Optional<Demanda> buscaPorIdeIdCliente(@Param("id") Long id, @Param("idCliente") Long idCliente);


    @Query("SELECT d FROM Demanda d JOIN d.gerente g WHERE g.id = :id")
    List<Demanda> listaPorGerente(@Param("id") Long idGerente);

    @Query("SELECT d FROM Demanda d JOIN d.cliente c JOIN d.gerente g " +
            "WHERE g.id = :idGerente " +
            "AND (d.id = :idDemanda OR :idDemanda is null) " +
            "AND (d.nome like CONCAT('%', :nome, '%') OR :nome is null )" +
            "AND (d.descricao like CONCAT('%', :descricao, '%') OR :descricao is null )" +
            "AND (c.nome like CONCAT('%', :nomeCliente, '%') OR :nomeCliente is null )")
    List<Demanda> listaPesquisaPorGerente(@Param("idDemanda") Long idDemanda,
                                          @Param("nome") String nome,
                                          @Param("descricao") String descricao,
                                          @Param("nomeCliente") String nomeCliente,
                                          @Param("idGerente") Long idGerente);

}
