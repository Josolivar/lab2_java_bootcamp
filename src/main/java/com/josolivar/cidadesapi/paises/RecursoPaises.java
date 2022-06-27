package com.josolivar.cidadesapi.paises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paises")
public class RecursoPaises {

    @Autowired
    private RepositorioDePaises repositorioDePaises;

    @GetMapping
    public List<Pais> paises() {

        return repositorioDePaises.findAll();
    }

    @GetMapping("/paginas")
    public Page<Pais> paises(Pageable pagina) {

        return repositorioDePaises.findAll(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPais(@PathVariable long id) {
        Optional optional = repositorioDePaises.findById(id);
        if(optional.isPresent())
            return ResponseEntity.ok().body(optional.get());
        else
            return ResponseEntity.notFound().build();
    }
}
