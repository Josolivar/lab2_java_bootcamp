package com.josolivar.cidadesapi.estados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class RecursoEstados {

    @Autowired
    RepositorioDeEstados repositorioDeEstados;

    @GetMapping
    public List<Estado> estados() {
        return repositorioDeEstados.findAll();
    }

}
