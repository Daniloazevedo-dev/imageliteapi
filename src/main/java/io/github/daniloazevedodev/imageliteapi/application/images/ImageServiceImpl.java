package io.github.daniloazevedodev.imageliteapi.application.images;

import io.github.daniloazevedodev.imageliteapi.domain.ImageService;
import io.github.daniloazevedodev.imageliteapi.domain.entity.Image;
import io.github.daniloazevedodev.imageliteapi.infra.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Image> getById(String id) {
        return imageRepository.findById(id);
    }
}
