package br.com.techhub.techstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techhub.techstock.controller.espelhos.Response;
import br.com.techhub.techstock.controller.espelhos.TicketEspelho;
import br.com.techhub.techstock.controller.filters.IFilter;
import br.com.techhub.techstock.controller.requests.TicketRequest;
import br.com.techhub.techstock.service.TicketService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ticket")
public class TicketController implements IController<TicketEspelho, TicketRequest> {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Response<Boolean>> create(@Valid
    TicketRequest entity, BindingResult result) { // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'create'"
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<TicketEspelho>> read(Long id) { // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @GetMapping
    public ResponseEntity<Response<List<TicketEspelho>>> readAll(
        IFilter filterObj
    ) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'readAll'"
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Boolean>> update(
        Long id,
        TicketRequest request,
        BindingResult result
    ) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'update'"
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(Long id) { // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'delete'"
        );
    }
}
