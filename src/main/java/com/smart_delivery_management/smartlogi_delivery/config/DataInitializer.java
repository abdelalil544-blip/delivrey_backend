package com.smart_delivery_management.smartlogi_delivery.config;

import com.smart_delivery_management.smartlogi_delivery.entity.*;
import com.smart_delivery_management.smartlogi_delivery.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final ProduitRepository produitRepository;
    private final ZoneRepository zoneRepository;
    private final DestinataireRepository destinataireRepository;
    private final ClientExpediteurRepository clientExpediteurRepository;
    private final LivreurRepository livreurRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (produitRepository.count() == 0) {
            log.info("Seeding products...");
            Produit p1 = new Produit();
            p1.setNom("Ordinateur Portable Dell");
            p1.setCategorie("Électronique");
            p1.setPoids(new BigDecimal("2.5"));
            p1.setPrix(new BigDecimal("1200.00"));

            Produit p2 = new Produit();
            p2.setNom("Smartphone Samsung S21");
            p2.setCategorie("Électronique");
            p2.setPoids(new BigDecimal("0.5"));
            p2.setPrix(new BigDecimal("800.00"));

            Produit p3 = new Produit();
            p3.setNom("Cafetière Nespresso");
            p3.setCategorie("Maison");
            p3.setPoids(new BigDecimal("3.0"));
            p3.setPrix(new BigDecimal("150.00"));

            produitRepository.saveAll(List.of(p1, p2, p3));
        }

        if (zoneRepository.count() == 0) {
            log.info("Seeding zones...");
            Zone z1 = new Zone();
            z1.setNom("Casablanca Centre");
            z1.setCodePostal("20000");

            Zone z2 = new Zone();
            z2.setNom("Rabat Agdal");
            z2.setCodePostal("10000");

            zoneRepository.saveAll(List.of(z1, z2));
        }

        if (destinataireRepository.count() == 0) {
            log.info("Seeding destinataires...");
            Destinataire d1 = new Destinataire();
            d1.setNom("Alami");
            d1.setPrenom("Yassine");
            d1.setEmail("yassine.alami@email.com");
            d1.setTelephone("0612345678");
            d1.setAdresse("123 Rue de la Liberté, Casablanca");

            destinataireRepository.save(d1);
        }

        clientExpediteurRepository.findByEmail("client@example.com").ifPresentOrElse(
                c -> {
                    c.setPassword(passwordEncoder.encode("password123"));
                    c.setEntreprise("LogiTech S.A.");
                    clientExpediteurRepository.save(c);
                },
                () -> {
                    log.info("Seeding default client...");
                    ClientExpediteur c1 = new ClientExpediteur();
                    c1.setNom("Client");
                    c1.setPrenom("Demo");
                    c1.setEmail("client@example.com");
                    c1.setTelephone("0600000000");
                    c1.setAdresse("Casablanca, Maroc");
                    c1.setEntreprise("LogiTech S.A.");
                    c1.setRole(com.smart_delivery_management.smartlogi_delivery.entity.enums.Role.CLIENT);
                    c1.setPassword(passwordEncoder.encode("password123"));
                    clientExpediteurRepository.save(c1);
                });

        livreurRepository.findByEmail("livreur@example.com").ifPresentOrElse(
                l -> {
                    l.setPassword(passwordEncoder.encode("password123"));
                    livreurRepository.save(l);
                },
                () -> {
                    log.info("Seeding default livreur...");
                    Livreur l1 = new Livreur();
                    l1.setNom("Benani");
                    l1.setPrenom("Karim");
                    l1.setEmail("livreur@example.com");
                    l1.setTelephone("0611223344");
                    l1.setAdresse("Rabat, Maroc");
                    l1.setRole(com.smart_delivery_management.smartlogi_delivery.entity.enums.Role.LIVREUR);
                    l1.setPassword(passwordEncoder.encode("password123"));
                    l1.setVehicule("Moteur Yamaha");
                    livreurRepository.save(l1);
                });

        userRepository.findByEmail("abdelailatifi10@gmail.com").ifPresentOrElse(
                u -> {
                    u.setPassword(passwordEncoder.encode("password123"));
                    userRepository.save(u);
                },
                () -> {
                    log.info("Seeding user abdelailatifi10@gmail.com...");
                    ClientExpediteur user = new ClientExpediteur();
                    user.setNom("Latifi");
                    user.setPrenom("Abdel");
                    user.setEmail("abdelailatifi10@gmail.com");
                    user.setTelephone("0600000000");
                    user.setAdresse("Maroc");
                    user.setEntreprise("Perso");
                    user.setRole(com.smart_delivery_management.smartlogi_delivery.entity.enums.Role.CLIENT);
                    user.setPassword(passwordEncoder.encode("password123"));
                    clientExpediteurRepository.save(user);
                });

        userRepository.findByEmail("abdelailatifi100@gmail.com").ifPresentOrElse(
                u -> {
                    u.setPassword(passwordEncoder.encode("password123"));
                    userRepository.save(u);
                },
                () -> {
                    log.info("Seeding user abdelailatifi100@gmail.com...");
                    ClientExpediteur user = new ClientExpediteur();
                    user.setNom("Latifi");
                    user.setPrenom("Abdel");
                    user.setEmail("abdelailatifi100@gmail.com");
                    user.setTelephone("0600000000");
                    user.setAdresse("Maroc");
                    user.setEntreprise("Perso");
                    user.setRole(com.smart_delivery_management.smartlogi_delivery.entity.enums.Role.CLIENT);
                    user.setPassword(passwordEncoder.encode("password123"));
                    clientExpediteurRepository.save(user);
                });

        userRepository.findByEmail("destinataire@example.com").ifPresentOrElse(
                u -> {
                    u.setPassword(passwordEncoder.encode("password123"));
                    userRepository.save(u);
                },
                () -> {
                    log.info("Seeding user destinataire@example.com...");
                    Destinataire d = new Destinataire();
                    d.setNom("Benani");
                    d.setPrenom("Sami");
                    d.setEmail("destinataire@example.com");
                    d.setTelephone("0655443322");
                    d.setAdresse("Marrakech, Maroc");
                    d.setRole(com.smart_delivery_management.smartlogi_delivery.entity.enums.Role.DESTINATAIRE);
                    d.setPassword(passwordEncoder.encode("password123"));
                    destinataireRepository.save(d);
                });
    }
}
