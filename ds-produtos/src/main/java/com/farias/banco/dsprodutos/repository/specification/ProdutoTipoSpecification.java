package com.farias.banco.dsprodutos.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.farias.banco.dsprodutos.model.ProdutoTipo;

import lombok.Builder;

@Builder
public class ProdutoTipoSpecification implements Specification<ProdutoTipo> {

	private static final long serialVersionUID = 1L;
	@Builder.Default
    private final transient Optional<String> descricao = Optional.empty();
    @Builder.Default
    private final transient Optional<Long> produtoTipoId = Optional.empty();

    @Override
    public Predicate toPredicate(Root<ProdutoTipo> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();
        produtoTipoId.ifPresent(v -> predicates.add(builder.equal(root.get("id"), v)));
        descricao.ifPresent(v -> predicates.add(builder.like(builder.lower(root.get("descricao")), "%".concat(v.toLowerCase()).concat("%"))));

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
