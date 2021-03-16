package br.com.zup.proposta.proposta;

import br.com.zup.proposta.builders.MockBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class PropostaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PropostaRepository propostaRepository;

    private MockBuilder mockBuilder = new MockBuilder();
    private String url = "/proposta";


    @Test
    @DisplayName("Deveria salvar no banco uma proposta e deve retornar 201")
    public void deveriaCadastrarUmaProposta() throws Exception{
        EnderecoRequest endereco = new EnderecoRequest("fddg", "58", "48753");

        PropostaRequest request = new PropostaRequest("94765588000118", "teste@mail.com",
                "teste", BigDecimal.valueOf(2500), endereco);

        mockBuilder.perform(url, 201, mockMvc, objectMapper, request);

        Proposta salva = propostaRepository.findByDocumento(request.getDocumento());

        assertAll(
                () -> assertTrue(salva != null),
                () -> assertEquals(salva.getDocumento(), request.getDocumento()),
                () -> assertEquals(salva.getEmail(), request.getEmail()),
                () -> assertEquals(salva.getSalario(), request.getSalario()),
                () -> assertEquals(salva.getEndereco().toString(), request.getEndereco().toString())
        );
    }

    @Test
    @DisplayName("Não deveria salvar no banco uma proposta com documento repetido e deve retornar 422")
    public void naoDeveriaCadastrarUmaPropostaComDocumentoRepetido() throws Exception{
        EnderecoRequest endereco = new EnderecoRequest("fddg", "58", "48753");

        PropostaRequest proposta1 = new PropostaRequest("94765588000118", "teste@mail.com",
                "teste", BigDecimal.valueOf(2500), endereco);

        propostaRepository.save(proposta1.toModel());

        PropostaRequest proposta2 = new PropostaRequest("94765588000118", "teste2@mail.com",
                "teste2", BigDecimal.valueOf(3500), endereco);

        mockBuilder.perform(url, 422, mockMvc, objectMapper, proposta2);

        List<Proposta> resultado = propostaRepository.findAll();

        assertAll(
                () -> assertTrue(resultado.size() == 1),
                () -> assertEquals(resultado.get(0).getDocumento(), proposta1.getDocumento())
        );
    }

    @Test
    @DisplayName("Não deveria salvar no banco uma proposta com documento invalido e deve retornar 400")
    public void naodeveriaCadastrarUmaPropostaComDocumentoInvalido() throws Exception{
        EnderecoRequest endereco = new EnderecoRequest("fddg", "58", "48753");

        PropostaRequest request = new PropostaRequest("9476", "teste@mail.com",
                "teste", BigDecimal.valueOf(2500), endereco);

        mockBuilder.perform(url, 400, mockMvc, objectMapper, request);

        Proposta salva = propostaRepository.findByDocumento(request.getDocumento());

        assertTrue(salva == null);

    }

    @Test
    @DisplayName("Não deveria salvar no banco uma proposta com endereco invalido e deve retornar 400")
    public void naodeveriaCadastrarUmaPropostaComEnderecoInvalido() throws Exception{
        EnderecoRequest endereco = new EnderecoRequest("", "", "48753");

        PropostaRequest request = new PropostaRequest("94765588000118", "teste@mail.com",
                "teste", BigDecimal.valueOf(2500), endereco);

        mockBuilder.perform(url, 400, mockMvc, objectMapper, request);

        Proposta salva = propostaRepository.findByDocumento(request.getDocumento());

        assertTrue(salva == null);

    }

    @Test
    @DisplayName("Não deveria salvar no banco uma proposta sem email ou email invalido invalido e deve retornar 400")
    public void naodeveriaCadastrarUmaPropostaSemEmailValido() throws Exception{
        EnderecoRequest endereco = new EnderecoRequest("fdbf", "fbd", "48753");

        PropostaRequest request = new PropostaRequest("94765588000118", "",
                "teste", BigDecimal.valueOf(2500), endereco);

        mockBuilder.perform(url, 400, mockMvc, objectMapper, request);

        PropostaRequest request2 = new PropostaRequest("94765588000118", "vitorgmail",
                "teste", BigDecimal.valueOf(2500), endereco);

        mockBuilder.perform(url, 400, mockMvc, objectMapper, request2);

        Proposta salva = propostaRepository.findByDocumento(request.getDocumento());

        assertTrue(salva == null);

    }
}