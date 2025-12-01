package by.pasha.arm_manager.controller;

import by.pasha.arm_manager.entity.Client;
import by.pasha.arm_manager.entity.CreditContract;
import by.pasha.arm_manager.service.ClientService;
import by.pasha.arm_manager.service.CreditApplicationService;
import by.pasha.arm_manager.service.CreditContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/credit-manager")
public class CreditManagerController {
    private final ClientService clientService;
    private final CreditApplicationService applicationService;
    private final CreditContractService contractService;

    public CreditManagerController(ClientService clientService,
                                   CreditApplicationService applicationService,
                                   CreditContractService contractService) {
        this.clientService = clientService;
        this.applicationService = applicationService;
        this.contractService = contractService;
    }

    // Главная страница
    @GetMapping
    public String index() {
        return "index";
    }
    @GetMapping("/clients/{id}")
    public String viewClient(@PathVariable Long id, Model model) {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "view-client";
    }
    // Клиенты
    @GetMapping("/clients")
    public String clientsPage(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients";
    }

    @GetMapping("/clients/search")
    public String searchClients(@RequestParam(required = false) String searchTerm, Model model) {
        List<Client> clients;
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            clients = clientService.searchClients(searchTerm);
        } else {
            clients = clientService.getAllClients();
        }
        model.addAttribute("clients", clients);
        model.addAttribute("searchTerm", searchTerm);
        return "clients";
    }
    // Создание клиента и заявки
    @GetMapping("/application/new")
    public String newApplicationForm(Model model) {
        model.addAttribute("client", new Client());
        return "new-application";
    }

    @PostMapping("/application/submit")
    public String submitApplication(@ModelAttribute Client client,
                                    @RequestParam BigDecimal desiredAmount) {
        clientService.saveClient(client);
        applicationService.createApplication(client, desiredAmount);
        return "redirect:/credit-manager/applications";
    }

    // Заявки
    @GetMapping("/applications")
    public String applicationsPage(Model model) {
        model.addAttribute("applications", applicationService.getAllApplications());
        return "applications";
    }

    @GetMapping("/applications/approved")
    public String approvedApplicationsPage(Model model) {
        model.addAttribute("applications", applicationService.getApprovedApplications());
        return "approved-applications";
    }

    @PostMapping("/applications/{id}/decision")
    public String makeDecision(@PathVariable Long id) {
        applicationService.makeDecision(id);
        return "redirect:/credit-manager/applications";
    }

    // Договоры
    @GetMapping("/contracts")
    public String contractsPage(Model model) {
        model.addAttribute("contracts", contractService.getAllContracts());
        return "contracts";
    }

    @GetMapping("/contracts/signed")
    public String signedContractsPage(Model model) {
        model.addAttribute("contracts", contractService.getSignedContracts());
        return "signed-contracts";
    }

    @PostMapping("/contracts/{id}/sign")
    public String signContract(@PathVariable Long id) {
        contractService.signContract(id);
        return "redirect:/credit-manager/contracts";
    }

    // Просмотр договора
    @GetMapping("/contracts/{id}/view")
    public String viewContract(@PathVariable Long id, Model model) {CreditContract contract = contractService.getContractById(id);
        model.addAttribute("contract", contract);
        return "view-contract";
    }
}