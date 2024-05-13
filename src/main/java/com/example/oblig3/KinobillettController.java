package com.example.oblig3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/tickets")
public class KinobillettController {

    @Autowired
    private KinobillettRepository billettRepository;

    @GetMapping
    public ResponseEntity<List<Kinobillett>> getAllTickets() {
        List<Kinobillett> tickets = billettRepository.findAll();
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody Kinobillett billett) {
        if (billett.getFilmnavn() == null || billett.getFilmnavn().isEmpty()) {
            return ResponseEntity.badRequest().body("Må velge film");
        }
        if (ValidationUtils.containsNumbers(billett.getFornavn()) || ValidationUtils.containsNumbers(billett.getEtternavn())) {
            return ResponseEntity.badRequest().body("Navn må ha bokstaver");
        }
        if (billett.getAntall() == 0) {
            return ResponseEntity.badRequest().body("Antall Må være høyere enn null");
        }

        Kinobillett savedBillett = billettRepository.save(billett);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBillett);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Kinobillett> updateTicket(@PathVariable Long id, @RequestBody Kinobillett updatedBillett) {
        Optional<Kinobillett> existingBillettOptional = billettRepository.findById(id);
        if (existingBillettOptional.isPresent()) {
            Kinobillett existingBillett = existingBillettOptional.get();
            existingBillett.setFilmnavn(updatedBillett.getFilmnavn());
            existingBillett.setAntall(updatedBillett.getAntall());
            existingBillett.setFornavn(updatedBillett.getFornavn());
            existingBillett.setEtternavn(updatedBillett.getEtternavn());
            existingBillett.setTelefon(updatedBillett.getTelefon());
            existingBillett.setEpost(updatedBillett.getEpost());
            Kinobillett savedBillett = billettRepository.save(existingBillett);
            return ResponseEntity.ok(savedBillett);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        Optional<Kinobillett> billettOptional = billettRepository.findById(id);
        if (billettOptional.isPresent()) {
            billettRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    static class ValidationUtils {
        public static boolean containsNumbers(String input) {
            return input != null && Pattern.compile("[0-9]").matcher(input).find();
        }
    }
}
