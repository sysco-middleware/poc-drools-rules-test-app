package no.sysco.middleware;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 *
 * @author 100tsa
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        KieRepository kr = ks.getRepository();
        //loading rules
        try (Stream<Path> paths = Files.walk(Paths.get("rules"))) {
            paths.filter(Files::isRegularFile)
                    .forEach((path) -> {
                        Resource resource = ks.getResources().newFileSystemResource(path.toFile())
                                .setResourceType(ResourceType.DRL);
                        kfs.write(resource);
                    });
        }

        KieBuilder kb = ks.newKieBuilder(kfs);
        kb.buildAll();
        KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
        List<Component> samples = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get("samples"))) {
            paths.filter(Files::isRegularFile)
                    .forEach((path) -> {
                        System.out.println("Found file "+ path.getFileName().toString());
                        try {
                            byte[] bytesReaded = Files.readAllBytes(path);
                            String fileString = new String(bytesReaded, StandardCharsets.UTF_8);
                            List<Component> components = mapper.readValue(
                                    fileString, new TypeReference<List<Component>>() {
                            });
                            samples.addAll(components);
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
        }
        //creating session
        KieSession kSession = kContainer.newKieSession();
        samples.forEach((sample) -> {
            kSession.insert(sample);
        });

        kSession.fireAllRules();
        
        kSession.getObjects().forEach((object) -> {
            System.out.println(object);
        });
        
        kSession.dispose();
        kContainer.dispose();
    }
}
