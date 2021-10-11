package br.com.ottimizza.regraslistener.controller.v1;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ottimizza.regraslistener.service.AtualizaRoteirosService;

@RestController
@RequestMapping("/api/v1/atualiza_roteiros")
public class AtualizaRoteirosController {
    
    @Autowired
    AtualizaRoteirosService service;

    @PostMapping("/exportados")
    public ResponseEntity<?> atualizaExportados(@RequestBody List<BigInteger> roteirosIds) throws Exception {
        String response = service.updateExportados(roteirosIds);
        return ResponseEntity.ok(response);
    }

}
