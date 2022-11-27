package com.example.brickx.appInit;

import com.example.brickx.entities.*;
import com.example.brickx.entities.enums.ApplicationStatus;
import com.example.brickx.entities.enums.Gender;
import com.example.brickx.entities.enums.ProjectStatus;
import com.example.brickx.entities.enums.Role;
import com.example.brickx.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Configuration
public class AppInit implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(CommandLineRunner.class);

//    private final ContractorRepository contractorRepository;

//    private final WorkerRepository workerRepository;

    private final UserRepository userRepository;

    private final JobRepository jobRepository;

    private final ProjectRepository projectRepository;

    private final ApplicationRepository applicationRepository;

    private final PasswordEncoder passwordEncoder;

    public AppInit(UserRepository userRepository, JobRepository jobRepository, ProjectRepository projectRepository, ApplicationRepository applicationRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.jobRepository = jobRepository;
        this.projectRepository = projectRepository;
        this.applicationRepository = applicationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        User contractor = new Contractor("John","Doe","johndoe@gmail.com", passwordEncoder.encode("testuser"), Gender.male, Role.Contractor,"a great contractor","08023456798");
        User contractor1 = new Contractor("Zainab","Okonkwo","Zainabokonkwo@gmail.com",passwordEncoder.encode("testuser"), Gender.female, Role.Contractor,"Good with land based contracts","090468536429");
        User contractor2 = new Contractor("Lorenzo","Bricks","lorenzobricks@gmail.com",passwordEncoder.encode("testuser"), Gender.male, Role.Contractor,"With over 73 complete building contracts under my belt, I can assure you of delivering worthy buildings","07057456263");
        User contractor3 = new Contractor("Neha","Weaver","Nehdeweaver@gmail.com",passwordEncoder.encode("testuser"), Gender.female, Role.Contractor,"Great at turning bricks to structures","08034812586");
        User contractor4 = new Contractor("Jane","Aiko","janethebeaver@gmail.com",passwordEncoder.encode("testuser"), Gender.female, Role.Contractor,"I will turn your dream house to reality","08073758792");
        User contractor5 = new Contractor("William","Ogundegbe","Williamogundegbe@gmail.com",passwordEncoder.encode("testuser"), Gender.male, Role.Contractor,"I am good at churning out amazing buildings","09054392140");
        User contractor6 = new Contractor("Ife","Jeremiah","0001@gmail.com", passwordEncoder.encode("123456789"), Gender.male, Role.Contractor,"a great contractor","08023456798");

        userRepository.saveAll(List.of(contractor,contractor2,contractor3,contractor4,contractor5,contractor1, contractor6));

        User worker = new Worker("Tosin","martin","tosinmartin@gmail.com",passwordEncoder.encode("testuser"),Gender.male,Role.Worker,"I am good with my hands","0804263325","INSPECTOR");

        User worker1 = new Worker("Klondike","pepple","klondikepepple@gmail.com",passwordEncoder.encode("testuser"),Gender.male,Role.Worker,"Give me bricks and mortar and watch me turn blueprints to glorious landscapes","0924769422","BRICKMASON");

        User worker2 = new Worker("Kayode","Etoha","kayodetoha@gmail.com",passwordEncoder.encode("testuser"),Gender.male,Role.Worker,"my nicked hands are a testament to my many years of experience in turning the strongest irons to backbones for beautiful structures","0902579021","IRONWORKER");

        User worker3 = new Worker("Ian","wicker","ianwicker@gmail.com",passwordEncoder.encode("testuser"),Gender.male,Role.Worker,"I make crane operating jobs look easy","0903429421","CRANEOPERATOR");

        User worker4 = new Worker("Esther","Eke","Esthereke@gmail.com",passwordEncoder.encode("testuser"),Gender.female,Role.Worker,"I keep people from experiencing accidents incurred from work hazards","0700689913","SAFETYMANAGER");

        User worker5 = new Worker("Akomaye","Udeme","akomayeudeme@gmail.com",passwordEncoder.encode("testuser"),Gender.male,Role.Worker,"I am a good worker","0913422045", "COSTESTIMATOR");

        userRepository.saveAll(List.of(worker,worker1,worker2,worker3,worker4,worker5));


        Job job1 = new Job("MANAGER");
        Job job2 = new Job("COSTMANAGER");
        Job job3 = new Job("INSPECTOR");
        Job job4 = new Job("FLOORINGINSTALLER");
        Job job5 = new Job("SURVEYOR");
        Job job6 = new Job("BRICKMASON");
        Job job7 = new Job("IRONWORKER");
        Job job8 = new Job("CRANEOPERATOR");
        Job job9 = new Job("SAFETYMANAGER");
        Job job10 = new Job("COSTESTIMATOR");

        jobRepository.saveAll(List.of(job1,job2,job3,job4,job5,job6,job7,job8,job9,job10));

        Project project = new Project("Zodiac Suits","6 months",new Date(),5000, ProjectStatus.Open, Date.from(Instant.now()), (Contractor) contractor2, List.of(job5,job8,job2));
        Project project1 = new Project("Wema Domes","6 months",new Date(),5000, ProjectStatus.Open, Date.from(Instant.now()), (Contractor) contractor4,List.of(job7,job1,job2,job3));
        Project project2 = new Project("Tem Homes","6 months",new Date(),5000, ProjectStatus.Open, Date.from(Instant.now()), (Contractor) contractor5,List.of(job4,job2,job5));

        projectRepository.saveAll(List.of(project1,project2,project));


        Application application = new Application(ApplicationStatus.Applied,job2, (Worker) worker);
        Application application1 = new Application(ApplicationStatus.Applied,job7, (Worker) worker3);
        Application application2 = new Application(ApplicationStatus.Applied,job1, (Worker) worker4);
        Application application3 = new Application(ApplicationStatus.Applied,job5, (Worker) worker5);
        Application application4 = new Application(ApplicationStatus.Applied,job4, (Worker) worker2);
        Application application5 = new Application(ApplicationStatus.Applied,job5, (Worker) worker2);
        Application application6 = new Application(ApplicationStatus.Applied,job10, (Worker) worker3);
        Application application7 = new Application(ApplicationStatus.Applied,job9, (Worker) worker);
        Application application8 = new Application(ApplicationStatus.Applied,job6, (Worker) worker1);
        Application application9 = new Application(ApplicationStatus.Applied,job5, (Worker) worker2);
        Application application10 = new Application(ApplicationStatus.Applied,job6, (Worker) worker4);
        Application application11 = new Application(ApplicationStatus.Applied,job3, (Worker) worker4);
        Application application12 = new Application(ApplicationStatus.Applied,job1, (Worker) worker5);
        Application application13 = new Application(ApplicationStatus.Applied,job4, (Worker) worker2);

        applicationRepository.saveAll(List.of(application,application1,application2,application4,application5,application6,application7,application8,application9,application3,application10,application11,application12,application13));
//














    }
}
