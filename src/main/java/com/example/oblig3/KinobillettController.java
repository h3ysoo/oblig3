package com.example.oblig3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class KinobillettController {

    @Autowired
    KinobillettRepository BillettRepository;


    @PostMapping("/save")
    public Kinobillett leggBillett(Kinobillett billet){
        return BillettRepository.save(billet);
    }

    @GetMapping("/vis")
    public List<Kinobillett> getBillett(){
        List<Kinobillett> tickets = BillettRepository.findAll();
        Collections.sort(tickets, new Comparator<Kinobillett>() {
            @Override
            public int compare(Kinobillett o1, Kinobillett o2) {
                return o1.getEtternavn().compareTo(o2.getEtternavn());
            }
        });
        return tickets;
    }
    @GetMapping("/slett")
    public void SlettBillettene(){
        BillettRepository.deleteAll();
    }
}