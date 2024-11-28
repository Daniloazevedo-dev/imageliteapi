package io.github.daniloazevedodev.imageliteapi.infra.repository.specs;

import io.github.daniloazevedodev.imageliteapi.domain.entity.Image;
import io.github.daniloazevedodev.imageliteapi.domain.enums.ImageExtension;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
public class ImageSpecs {

    public static Specification<Image>  extensionEqual(ImageExtension extension) {
        return (root, q, cb) -> cb.equal(root.get("extension"), extension);
    }

    public static Specification<Image>  nameLike(String name) {
        return (root, q, cb) -> cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%");
    }

    public static Specification<Image>  tagsLike(String tags) {
        return (root, q, cb) -> cb.like(cb.upper(root.get("tags")), "%" + tags.toUpperCase() + "%");
    }

}
