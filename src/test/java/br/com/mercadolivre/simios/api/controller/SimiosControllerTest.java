package br.com.mercadolivre.simios.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.mercadolivre.simios.domain.repository.ConsultaDNARepository;

@SpringBootTest
@AutoConfigureMockMvc
public class SimiosControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ConsultaDNARepository repository;
	
	@Test
    public void deveRetornarStatus200_QuandoAConsultaForUmSimio() throws Exception {
		
		mockMvc.perform(post("/simian")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                		+ "    \"dna\": [\"CTGCGA\", \"CTCAGC\", \"ACTTGT\", \"CGAGAG\", \"ACTCTA\", \"TCACTG\"]\n"
                		+ "}"))
                .andExpect(status().isOk());
    }
	
	@Test
    public void deveRetornarStatus403_QuandoAConsultaNaoForUmSimio() throws Exception {
		
		mockMvc.perform(post("/simian")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                		+ "    \"dna\": [\"CTFCGA\", \"TGAGGC\", \"TFTCGT\", \"GGFACG\", \"CCCGGA\", \"SCTCCG\"]\n"
                		+ "}"))
                .andExpect(status().isForbidden());
    }
    
	@Test
    public void deveRetornarStatus200_QuandoSolicitarEstatisticas() throws Exception {
		
		when(repository.countByEspecie('S')).thenReturn(20);
		when(repository.countByEspecie('H')).thenReturn(30);
		
		mockMvc.perform(get("/stats")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
 
}
