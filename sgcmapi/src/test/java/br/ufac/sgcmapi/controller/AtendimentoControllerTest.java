package br.ufac.sgcmapi.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.Convenio;
import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.model.Paciente;
import br.ufac.sgcmapi.model.Profissional;
import br.ufac.sgcmapi.service.AtendimentoService;
import br.ufac.sgcmapi.service.UsuarioService;

@WebMvcTest(AtendimentoController.class)
public class AtendimentoControllerTest {

    @MockBean
    private AtendimentoService servico;

    @MockBean
    private UsuarioService usuarioService;

    private MockMvc mock;
    
    @Autowired
    public AtendimentoControllerTest(MockMvc mock) {
        this.mock = mock;
    }

    @Test
    @WithMockUser
    public void testGetAll() throws Exception {

        Profissional profissional = new Profissional();
        profissional.setId(1L);

        Convenio convenio = new Convenio();
        convenio.setId(1L);

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        
        Atendimento atendimento = new Atendimento();
        atendimento.setId(1L);
        atendimento.setData(LocalDate.of(2022, 12, 1));
        atendimento.setHora(LocalTime.of(14, 0));
        atendimento.setProfissional(profissional);
        atendimento.setConvenio(convenio);
        atendimento.setPaciente(paciente);
        atendimento.setStatus(EStatus.AGENDADO);
        
        List<Atendimento> registros = new ArrayList<>();
        registros.add(atendimento);

        Mockito.when(servico.get()).thenReturn(registros);
        mock.perform(MockMvcRequestBuilders.get("/atendimento/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].status", Matchers.is(EStatus.AGENDADO.toString())));
    }
    
}
