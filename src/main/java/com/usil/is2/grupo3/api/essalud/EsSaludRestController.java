package com.usil.is2.grupo3.api.essalud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/essalud")
public class EsSaludRestController {

    @Autowired
    private EsSaludRepository esSaludRepository;

    @GetMapping("/{dni}")
    public ResponseEntity<SeguroEsSalud> consultarSeguro(@PathVariable String dni) {
        return esSaludRepository.findById(dni)
                .map(seguro -> ResponseEntity.ok(seguro))
                .orElse(ResponseEntity.notFound().build());
    }
}