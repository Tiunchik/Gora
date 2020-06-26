package org.album.controllers;

import org.album.domains.Image;
import org.album.repositories.ImageRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    public ResponseEntity<List<Long>>  getAllHolidays(){
        return new ResponseEntity<>(imgRep.getAllId(), HttpStatus.OK);
    }

    @GetMapping(value = "/image/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getHoliday(@PathVariable Long id,
                      HttpServletResponse response) throws IOException {

        Optional<Image> img = imgRep.findById(id);
        if (img.isPresent()) {
            return IOUtils.toByteArray(new ByteArrayInputStream(img.get().getImage()));
        } else {
            return null;
        }
    }

    @PostMapping("/image")
    public ResponseEntity<Image> createHoliday(@RequestBody Image hol) {
        if (hol.getId() != 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Image answer = imgRep.save(hol);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PutMapping("/image")
    public ResponseEntity<Image> updateHoliday(@RequestBody Image hol) {
        if (hol.getId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Image answer = imgRep.save(hol);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @DeleteMapping("/image/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Long id) {
        imgRep.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
