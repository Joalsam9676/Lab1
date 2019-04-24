package gt.edu.umg.ingenieria.sistemas.laboratorio1.service;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.dao.ClientRepository;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
        
    @Autowired
    private ClientRepository clienteRepo;
    
    public Client buscarNit(String Nit) {
        return this.clienteRepo.findclientBynit(Nit);
    }
    
    public List<Client> getAllclients() {
        return (List<Client>) this.clienteRepo.findAll();
    }
    
    public Client createStudent(Client cliente) {
        return this.clienteRepo.save(cliente);
    }
    
}
