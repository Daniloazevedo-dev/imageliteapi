package io.github.daniloazevedodev.imageliteapi.application.images;

import io.github.daniloazevedodev.imageliteapi.domain.enums.ImageExtension;
import io.github.daniloazevedodev.imageliteapi.domain.service.ImageService;
import io.github.daniloazevedodev.imageliteapi.domain.entity.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/images")
public class ImagesController {

    private final ImageService imageService;
    private final ImageMapper imageMapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestParam("file") MultipartFile file,
                               @RequestParam("name") String name,
                               @RequestParam("tags") List<String> tags) throws IOException {

        Image image = imageMapper.mapToImage(file, name, tags);
        Image saveImage = imageService.save(image);
        URI imageUri = buildImageURL(saveImage);

        return ResponseEntity.created(imageUri).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {

        var possibleImage = imageService.getById(id);

        if (possibleImage.isEmpty())
            return ResponseEntity.notFound().build();

        var image = possibleImage.get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(image.getExtension().getMediaType());
        headers.setContentLength(image.getSize());
        headers.setContentDispositionFormData("inline; filename=\"" + image.getFileName() +  "\"", image.getFileName());

        return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
    }

    private URI buildImageURL(Image image) {
        String imagePath = String.format("/%s", image.getId());
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(imagePath)
                .build()
                .toUri();
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> search(@RequestParam(value = "extension", required = false, defaultValue = "") String extension,
                              @RequestParam(value = "query", required = false) String query) {
        var result =  imageService.search(ImageExtension.ofName(extension), query);
        var images = result.stream().map(image -> {
            var url = buildImageURL(image);
            return imageMapper.imageToDTO(image, url.toString());
        }).collect(Collectors.toList());

        return ResponseEntity.ok(images);
    }



}
