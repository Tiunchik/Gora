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
public class LoadController {

    private final ImageRepository imgRep;

    public LoadController(@Autowired ImageRepository imgRep) {
        this.imgRep = imgRep;
    }

    @GetMapping(value = "/load/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImageBytes(@PathVariable Integer id) throws IOException {

        Optional<Image> img = imgRep.findById(id);
        if (img.isPresent()) {
            return IOUtils.toByteArray(new ByteArrayInputStream(img.get().getImage()));
        } else {
            return null;
        }
    }

    @GetMapping("/load")
    public ResponseEntity<List<Integer>> getImageIDs(){
        return new ResponseEntity<>(imgRep.getIdList(), HttpStatus.OK);
    }
}
