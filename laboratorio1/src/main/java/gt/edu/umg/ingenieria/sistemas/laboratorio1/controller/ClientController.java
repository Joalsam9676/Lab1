package gt.edu.umg.ingenieria.sistemas.laboratorio1.controller;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ClientService;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ReportService;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private ReportService reportService;
    private ClientService clienteService;
    
    @GetMapping("/buscarPorNit")
    public Client getByNit(@RequestParam(name = "Nit", required = true) String Nit) {
        return this.clienteService.buscarNit(Nit);
    }
    @GetMapping("/buscarTodos")
    public List<Client> getAll() {
        return this.clienteService.getAllclients();
    }
    
    @PostMapping("/crear")
    public Client create(@RequestBody(required = true) Client newClient) throws Exception {
        
        if(!Edad(newClient.getBirthdate()))
            throw new Exception("NO se puede guardar debido a que es menor de edad");
        
        if(!Nit(newClient.getNit()))
            throw new Exception("No cumple con lo necesario para guardar");
                    
        
        return this.clienteService.createStudent(newClient);
    }
    
    private boolean Edad(Date birthDate)
    {
        LocalDate date = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int edad = Period.between(date, LocalDate.now()).getYears();
        
        if(edad < 18)
            return false;
        else
            return true;
    }
    
    private Boolean Nit(String Nit){
        if(Nit.matches("[0-9]{1,10}"))
            return true;
        else
            return false;
    }
    
    
}
