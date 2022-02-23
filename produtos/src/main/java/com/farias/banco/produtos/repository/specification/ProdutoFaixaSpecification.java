package com.farias.banco.produtos.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.farias.banco.produtos.model.ProdutoFaixa;

import lombok.Builder;

@Builder
public class ProdutoFaixaSpecification implements Specification<ProdutoFaixa> {

	private static final long serialVersionUID = 1L;
    @Builder.Default
    private final transient Optional<Long> id = Optional.empty();
    @Builder.Default
    private final transient Optional<Long> produtoTipo = Optional.empty();
    @Builder.Default
    private final transient Optional<Long> scoreMin = Optional.empty();
    @Builder.Default
    private final transient Optional<Long> scoreMax = Optional.empty();
    @Builder.Default
    private final transient Optional<Long> valor = Optional.empty();

    @Override
    public Predicate toPredicate(Root<ProdutoFaixa> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();
        id.ifPresent(v -> predicates.add(builder.equal(root.get("id"), v)));
        produtoTipo.ifPresent(v -> predicates.add(builder.equal(root.get("produtoTipo"), v)));
        scoreMin.ifPresent(v -> predicates.add(builder.equal(root.get("scoreMin"), v)));
        scoreMax.ifPresent(v -> predicates.add(builder.equal(root.get("scoreMax"), v)));
        valor.ifPresent(v -> predicates.add(builder.equal(root.get("valor"), v)));

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
