package com.joshuapok.buynowdotcom.Controller;

import com.joshuapok.buynowdotcom.Dto.ImageDto;
import com.joshuapok.buynowdotcom.Response.ApiResponse;
import com.joshuapok.buynowdotcom.model.Image;
import com.joshuapok.buynowdotcom.service.image.ImageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadImages(@RequestParam List<MultipartFile> files,@RequestParam Long productId) {
            List<ImageDto> imageDto = imageService.saveImages(productId, files);
            return ResponseEntity.ok(new ApiResponse("Images loaded successfully!", imageDto));
    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int)image.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFilename() + "\"").body(resource);

    }

    @PutMapping("/image/update/{imageid}")
    public ResponseEntity<ApiResponse> updateImage(@RequestParam MultipartFile file, @RequestParam Long imageId){
        Image image = imageService.getImageById(imageId);
            imageService.updateImage(file, imageId);
            return ResponseEntity.ok(new ApiResponse("Image updated successfully", null));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteImage(@RequestParam Long imageId){
            imageService.deleteImageById(imageId);
            return ResponseEntity.ok(new ApiResponse("Image deleted successfully", null));

    }


}
