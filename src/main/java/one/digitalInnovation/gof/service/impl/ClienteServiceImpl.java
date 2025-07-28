ackage one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author falvojr
 */
@Service
public class ClienteServiceImpl implements ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	

	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();

	}

	@Override
	public Cliente buscarPorId(Long id) {
	Optional<Cliente> cliente = clienteRepository.finfById(id);
	return cliente.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		salvarClienteComCep(cliente);		
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteBd = clienteRepository.finfById(id);
		if(clienteBd.isPresent()){
			salvarClienteComCep(cliente);
		}
		
	}

	private void salvarClienteComCep(Cliente cliente){
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.finfById(cep).orElseGet(() -> {
		Endereco novoEndereco = viaCepService.consultarCep(cep);
		enderecoRepository.save(novoEndereco);	
		return novoEndereco;
		});
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deletarById(id);
	}

	
}