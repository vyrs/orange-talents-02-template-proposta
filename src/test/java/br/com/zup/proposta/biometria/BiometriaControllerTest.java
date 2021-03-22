package br.com.zup.proposta.biometria;

import br.com.zup.proposta.builders.MockBuilder;
import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.proposta.EnderecoRequest;
import br.com.zup.proposta.proposta.Proposta;
import br.com.zup.proposta.proposta.PropostaRepository;
import br.com.zup.proposta.proposta.PropostaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class BiometriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EntityManager entityManager;

    private Cartao cartao;

    private MockBuilder mockBuilder = new MockBuilder();
    private String url = "/biometria/1";

//    @BeforeEach
//    public void before() {
//    }
//
//
//    @Test
//    @DisplayName("Deveria salvar no banco uma Biometria e deve retornar 201 e um location")
//    public void deveriaCadastrarUmaBiometria() throws Exception{
//        BiometriaRequest request = new BiometriaRequest();
//        request.setFingerprint("YW55IGNhcm5hbCBwbGVhcw==");
//
//        mockBuilder.perform(url, 201, mockMvc, objectMapper, request);
//    }
}