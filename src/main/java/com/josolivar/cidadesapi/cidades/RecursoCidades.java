package com.josolivar.cidadesapi.cidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cidades")
public class RecursoCidades {

    @Autowired
    private RepositorioDeCidades repositorioDeCidades;

    @GetMapping
    public List<Cidade> cities() {
        return repositorioDeCidades.findAll();
    }

    @GetMapping("/paginas")
    public Page<Cidade> cities(final Pageable page) {
        return repositorioDeCidades.findAll(page);
    }
}
