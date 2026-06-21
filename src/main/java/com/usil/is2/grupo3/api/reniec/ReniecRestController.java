package com.usil.is2.grupo3.api.reniec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reniec")
public class ReniecRestController {

    @Autowired
    private ReniecRepository reniecRepository;

    @GetMapping("/{dni}")
    public ResponseEntity<PadronReniec> consultarDni(@PathVariable String dni) {
        return reniecRepository.findById(dni)
                .map(persona -> ResponseEntity.ok(persona))
                .orElse(ResponseEntity.notFound().build());
    }
}