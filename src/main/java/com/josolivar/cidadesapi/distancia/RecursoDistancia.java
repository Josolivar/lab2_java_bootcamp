package com.josolivar.cidadesapi.distancia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/distancias")
public class RecursoDistancia {

    Logger log = LoggerFactory.getLogger(RecursoDistancia.class);

    private final ServicoDistancia servicoDistancia;

    public RecursoDistancia(ServicoDistancia servicoDistancia) {
        this.servicoDistancia = servicoDistancia;
    }

    @GetMapping("/milhas")
    public ResponseEntity byPoints(@RequestParam(name = "from") final long cidadeId1, @RequestParam(name = "to") final long cidadeId2) {
        log.info("byPoints");
        return ResponseEntity.ok().body(servicoDistancia.calculaDistanciaEmMilhas(cidadeId1, cidadeId2));
    }

    @GetMapping("/metros")
    public ResponseEntity byCube(@RequestParam(name = "from") final long cidadeId1, @RequestParam(name = "to") final long cidadeId2) {
        log.info("byCube");
        return ResponseEntity.ok().body(servicoDistancia.calculaDistanciaEmMetros(cidadeId1, cidadeId2));
    }

    @GetMapping("/by-math")
    public Double byMath(@RequestParam(name = "from") final long cidadeId1, @RequestParam(name = "to") final long cidadeId2, @RequestParam final RaioDaTerra unit) {
        log.info("byMath");
        return servicoDistancia.distanciaUsandoMath(cidadeId1, cidadeId2, unit);
    }

}
