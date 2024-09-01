package br.edu.iftm.rastreamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.service.PacoteService;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/pacotes")
public class PacoteController {

	@Autowired
	private PacoteService pacoteService;

	@Operation(summary = "Buscar todos os pacotes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pacotes encontrados",
        content = {@Content(mediaType = "application/json", 
        schema = @Schema(implementation = Pacote.class))}),
        @ApiResponse(responseCode = "204", description = "Nenhum registro de pacote encontrado!")
    })
	@GetMapping
	public List<Pacote> getAllPacotes() {
		return pacoteService.getAllPacotes();
	}

	@Operation(summary = "Salva o pacote")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pacote salvo",
        content = {@Content(mediaType = "application/json", 
        schema = @Schema(implementation = Pacote.class))}),
        @ApiResponse(responseCode = "400", description = "Algo de errado ocorreu ao salvar o pacote!")
    })
	@PostMapping
	public Pacote createPacote(@RequestBody Pacote pacote) {
		return pacoteService.createPacote(pacote);
	}

	@Operation(summary = "Buscar pacote por id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pacote encontrado",
        content = {@Content(mediaType = "application/json", 
        schema = @Schema(implementation = Pacote.class))}),
        @ApiResponse(responseCode = "204", description = "Nenhum pacote encontrado para o id informado!")
    })
	@GetMapping("/{id}")
	public Pacote getPacoteById(@PathVariable Long id) {

		try {

			return pacoteService.getPacoteById(id);
			
		} catch (Exception e) {

			throw new NaoAcheiException("Nenhum pacote encontrado para o id: " + id);
		}
	}

	@Operation(summary = "Atualiza pacote")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pacote atualizado",
        content = {@Content(mediaType = "application/json", 
        schema = @Schema(implementation = Pacote.class))}),
        @ApiResponse(responseCode = "204", description = "Nenhum pacote encontrado pelo id informado!")
    })
	@PutMapping("/{id}")
	public Pacote updatePacote(@PathVariable Long id, @RequestBody Pacote pacoteDetails) {

		try{

			return pacoteService.updatePacote(id, pacoteDetails);

		}catch(Exception e){

			throw new NaoAcheiException("Nenhum pacote encontrado para o id: " + id);
		}
	}

	@Operation(summary = "Deleta pacote")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pacote deletado",
        content = {@Content(mediaType = "application/json", 
        schema = @Schema(implementation = Pacote.class))}),
        @ApiResponse(responseCode = "204", description = "Nenhum pacote encontrado pelo id informado!")
    })
	@DeleteMapping("/{id}")
	public void deletePacote(@PathVariable Long id) {
		pacoteService.deletePacote(id);
	}

	@Operation(summary = "Busca pacote pelo status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pacotes encontrados",
        content = {@Content(mediaType = "application/json", 
        schema = @Schema(implementation = Pacote.class))}),
        @ApiResponse(responseCode = "204", description = "Nenhum pacote encontrado pelo status informado!")
    })
	@GetMapping("/status")	
	public List<Pacote> getPacotePorStatus(@RequestParam String status) {
		
		return pacoteService.getPacotePorStatus(status);
		
	}

	@Operation(summary = "Busca pacote pelo destinatario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pacotes encontrados",
        content = {@Content(mediaType = "application/json", 
        schema = @Schema(implementation = Pacote.class))}),
        @ApiResponse(responseCode = "204", description = "Nenhum pacote encontrado pelo destinatario informado!")
    })
	@GetMapping("/destinatario")	
	public List<Pacote> getPacotePorDestinatario(@RequestParam String destinatario) {
		
		return pacoteService.getPacotePorDestinatario(destinatario);
		
	}
	
}