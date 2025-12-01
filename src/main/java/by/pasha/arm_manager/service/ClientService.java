package by.pasha.arm_manager.service;


import by.pasha.arm_manager.entity.Client;
import by.pasha.arm_manager.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> searchClients(String searchTerm) {
        return clientRepository.searchClients(searchTerm);
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Client getClientById(Long id) {
        return clientRepository.findById(id);
    }


    @Transactional(readOnly = true)
    public Client getClientByPhone(String phone) {
        return clientRepository.findByPhone(phone);
    }
}