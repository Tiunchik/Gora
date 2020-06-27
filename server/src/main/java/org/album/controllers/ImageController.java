package org.album.controllers;

import org.album.domains.Image;
import org.album.repositories.ImageRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ImageController {

    private final ImageRepository imgRep;

    public ImageController(@Autowired ImageRepository holRep) {
        this.imgRep = holRep;
    }

    @GetMapping("/image")
    public ResponseEntity<List<Image>> getAllImages() {
        return new ResponseEntity<>(imgRep.findAll(), HttpStatus.OK);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Integer id) {
        Optional<Image> emp = imgRep.findById(id);
        return emp
                .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/image")
    public ResponseEntity<Image> createImage(@RequestParam("image") MultipartFile file) throws IOException {
        Image img = new Image();
        img.setImage(file.getBytes());
        img.setId(0);
        img = imgRep.save(img);
        return new ResponseEntity<>(img, HttpStatus.OK);
    }

    @PutMapping("/image")
    public ResponseEntity<Image> updateImage(@RequestParam MultipartFile image,
                                             @RequestParam int id) throws IOException {
        Image img = new Image();
        img.setImage(image.getBytes());
        img.setId(id);
        img = imgRep.update(img);
        return new ResponseEntity<>(img, HttpStatus.OK);
    }

    @DeleteMapping("/image/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer id) {
        imgRep.delete(id);
        return ResponseEntity.noContent().build();
    }
}
