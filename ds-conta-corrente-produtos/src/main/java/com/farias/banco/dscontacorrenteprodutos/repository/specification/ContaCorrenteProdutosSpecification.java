package com.farias.banco.dscontacorrenteprodutos.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.farias.banco.dscontacorrenteprodutos.model.ContaCorrenteProdutos;

import lombok.Builder;

@Builder
public class ContaCorrenteProdutosSpecification implements Specification<ContaCorrenteProdutos> {

	private static final long serialVersionUID = 1L;
	
	@Builder.Default
    private final transient Optional<Long> id = Optional.empty();
	@Builder.Default
    private final transient Optional<Long> contaCorrente = Optional.empty();
    @Builder.Default
    private final transient Optional<Integer> ativo = Optional.empty();
    @Builder.Default
    private final transient Optional<Long> produtoTipo = Optional.empty();

    @Override
    public Predicate toPredicate(Root<ContaCorrenteProdutos> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();
        id.ifPresent(v -> predicates.add(builder.equal(root.get("id"), v)));
        contaCorrente.ifPresent(v -> predicates.add(builder.equal(root.get("contaCorrente"), v)));
        produtoTipo.ifPresent(v -> predicates.add(builder.equal(root.get("produtoTipo"), v)));
        ativo.ifPresent(v -> predicates.add(builder.equal(root.get("ativo"), v)));

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
