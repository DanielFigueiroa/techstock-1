package br.com.techhub.techstock;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.techhub.techstock.model.Categoria;
import br.com.techhub.techstock.model.Equipamento;
import br.com.techhub.techstock.model.Movimentacao;
import br.com.techhub.techstock.model.Setor;
import br.com.techhub.techstock.model.Ticket;
import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.model.enums.EquipamentoStatus;
import br.com.techhub.techstock.model.enums.MovimentacaoTipo;
import br.com.techhub.techstock.model.enums.TicketStatus;
import br.com.techhub.techstock.service.CategoriaService;
import br.com.techhub.techstock.service.EquipamentoService;
import br.com.techhub.techstock.service.MovimentacaoService;
import br.com.techhub.techstock.service.SetorService;
import br.com.techhub.techstock.service.TicketService;
import br.com.techhub.techstock.service.UsuarioService;

@Configuration
public class LoadDatabase {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    EquipamentoService equipamentoService;

    @Autowired
    MovimentacaoService movimentacaoService;

    @Autowired
    SetorService setorService;

    @Autowired
    TicketService ticketService;

    @Autowired
    UsuarioService usuarioService;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            var categoria = new Categoria();
            categoria.setNome("Audio e Video");
            categoria.setDescricao("Tudo que envolve audio e vídeo");
            categoriaService.save(categoria);

            var equipamento = new Equipamento();
            equipamento.setAno_fabricacao(new Date());
            equipamento.setCategoria(categoria);
            equipamento.setDescricao("Caixa de som legal");
            equipamento.setDt_entrada(new Date());
            equipamento.setDt_saida(null);
            equipamento.setFabricante("Wolksvagem");
            equipamento.setModelo("Gol");
            equipamento.setNome("Caixa de som");
            equipamento.setStatus(EquipamentoStatus.EM_MANUTENCAO);

            categoria = new Categoria();
            categoria.setNome("Slideshow");
            categoria.setDescricao("Slideshow legal");
            categoriaService.save(categoria);

            equipamento = new Equipamento();
            equipamento.setAno_fabricacao(new Date());
            equipamento.setCategoria(categoria);
            equipamento.setDescricao("Um slideshow que é show");
            equipamento.setDt_entrada(new Date());
            equipamento.setDt_saida(null);
            equipamento.setFabricante("Yamaha");
            equipamento.setModelo("Fiat Uno");
            equipamento.setNome("SlideShow");
            equipamento.setStatus(EquipamentoStatus.DISPONIVEL);

            equipamento = new Equipamento();
            equipamento.setAno_fabricacao(new Date());
            equipamento.setCategoria(categoria);
            equipamento.setDescricao("Cadeira azul");
            equipamento.setDt_entrada(
                Date.from(Instant.now().minus(1, ChronoUnit.DAYS))
            );
            equipamento.setDt_saida(new Date());
            equipamento.setFabricante("Ford");
            equipamento.setModelo("Ford ka");
            equipamento.setNome("Cadeira");
            equipamento.setStatus(EquipamentoStatus.INDISPONIVEL);

            var setor = new Setor();
            setor.setNome("TI");

            var usuario = new Usuario();
            usuario.setAdmin(false);
            usuario.setEmail("daniel@gmail.com");
            usuario.setNome("Daniel");
            usuario.setSenha("123456");
            usuario.setSetor(setor);

            var ticket = new Ticket();
            ticket.setDt_devolucao(
                Date.from(Instant.now().plus(2, ChronoUnit.DAYS))
            );
            ticket.setEquipamento(equipamento);
            ticket.setStatus(TicketStatus.PROCESSANDO);
            ticket.setUsuario(usuario);


            var movimentacao = new Movimentacao();
            movimentacao.setData(new Date());
            movimentacao.setTicket(ticket);
            movimentacao.setTipo(MovimentacaoTipo.SAIDA);
            movimentacao.setUsuario(usuario);


        };
    }
}
