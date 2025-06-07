package com.joshuapok.buynowdotcom.service.image;

import com.joshuapok.buynowdotcom.Dto.ImageDto;
import com.joshuapok.buynowdotcom.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image getImageById(Long imageId);
    void deleteImageById(Long imageId);
    void updateImage(MultipartFile file, Long imageId);
    List<ImageDto> saveImages(Long productId, List<MultipartFile> files);
}
