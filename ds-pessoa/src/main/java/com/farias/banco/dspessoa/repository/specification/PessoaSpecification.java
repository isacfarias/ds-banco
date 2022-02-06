package com.farias.banco.dspessoa.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.farias.banco.dspessoa.model.Pessoa;

import lombok.Builder;

@Builder
public class PessoaSpecification implements Specification<Pessoa> {

    @Builder.Default
    private final transient Optional<String> nome = Optional.empty();
    @Builder.Default
    private final transient Optional<String> tipo = Optional.empty();
    @Builder.Default
    private final transient Optional<Integer> score = Optional.empty();

    @Override
    public Predicate toPredicate(Root<Pessoa> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();
        nome.ifPresent(v -> predicates.add(builder.like(builder.lower(root.get("nome")), "%".concat(v.toLowerCase()).concat("%"))));
        tipo.ifPresent(v -> predicates.add(builder.equal(root.get("tipo"), v)));
        score.ifPresent(v -> predicates.add(builder.equal(root.get("score"), v)));

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
