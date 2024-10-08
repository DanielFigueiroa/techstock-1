package br.com.techhub.techstock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techhub.techstock.controller.espelhos.MovimentacaoEspelho;
import br.com.techhub.techstock.controller.espelhos.Response;
import br.com.techhub.techstock.controller.filters.IFilter;
import br.com.techhub.techstock.controller.requests.MovimentacaoRequest;
import br.com.techhub.techstock.model.Movimentacao;
import br.com.techhub.techstock.service.MovimentacaoService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/movimentacao")
public class MovimentacaoController implements IController<MovimentacaoEspelho, MovimentacaoRequest, IFilter> {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<Response<Long>> create(@Valid @RequestBody
    MovimentacaoRequest entity, BindingResult result) {
        Response<Long> response = new Response<>();

        var obj = movimentacaoService.save(new Movimentacao(entity));
        response.setData(obj.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MovimentacaoEspelho>> read(@PathVariable
    Long id) {
        Response<MovimentacaoEspelho> response = new Response<MovimentacaoEspelho>();

        var obj = movimentacaoService.findById(id);
        if (!obj.isPresent()) {
            response.getErrors()
                .add(
                    String.format(
                        "Movimentacao com o id %s não foi encontrada",
                        id
                    )
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.setData(new MovimentacaoEspelho(obj.get()));
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<MovimentacaoEspelho>>> readAll() {
        Response<List<MovimentacaoEspelho>> response = new Response<List<MovimentacaoEspelho>>();

        var list = movimentacaoService.findAll();
        List<MovimentacaoEspelho> listEspelho = new ArrayList<MovimentacaoEspelho>();
        for (Movimentacao movimentacao : list) {
            listEspelho.add(new MovimentacaoEspelho(movimentacao));
        }
        response.setData(listEspelho);

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Long>> update(@PathVariable
    Long id, @Valid @RequestBody
    MovimentacaoRequest request, BindingResult result) {
        Response<Long> response = new Response<>();
        if (!movimentacaoService.findById(id).isPresent()) {
            response.getErrors()
                .add(
                    String.format(
                        "Movimentacao com o id %s não foi encontrada",
                        id
                    )
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        request.setId(id);
        movimentacaoService.save(new Movimentacao(request));
        response.setData(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> delete(@PathVariable
    Long id) {
        Response<Boolean> response = new Response<Boolean>();
        response.setData(false);

        if (!movimentacaoService.findById(id).isPresent()) {
            response.getErrors()
                .add(
                    String.format(
                        "Movimentacao com o id %s não foi encontrada",
                        id
                    )
                );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        movimentacaoService.delete(new Movimentacao(id));
        response.setData(true);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
