package io.github.daniloazevedodev.imageliteapi.infra.repository.specs;

import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
public class GenericsSpecs {

    public static <T> Specification<T> conjuction() {
        return (root, q, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public static <T> Specification<T>  like(String field, String value) {
        return (root, q, cb) -> cb.like(cb.upper(root.get(field)), "%" + value.toUpperCase() + "%");
    }

}
