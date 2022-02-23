package com.farias.banco.contacorrente.modules.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.farias.banco.contacorrente.modules.model.ContaCorrente;

import lombok.Builder;

@Builder
public class ContaCorrenteSpecification implements Specification<ContaCorrente> {

    @Builder.Default
    private final transient Optional<String> agencia = Optional.empty();
    @Builder.Default
    private final transient Optional<String> numero = Optional.empty();
    @Builder.Default
    private final transient Optional<String> tipo = Optional.empty();
    @Builder.Default
    private final transient Optional<Long> pessoaId = Optional.empty();

    @Override
    public Predicate toPredicate(Root<ContaCorrente> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();
        agencia.ifPresent(v -> predicates.add(builder.equal(root.get("agencia"), v)));
        tipo.ifPresent(v -> predicates.add(builder.equal(root.get("tipo"), v)));
        numero.ifPresent(v -> predicates.add(builder.equal(root.get("numero"), v)));
        pessoaId.ifPresent(v -> predicates.add(builder.equal(root.get("pessoa"), v)));

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
