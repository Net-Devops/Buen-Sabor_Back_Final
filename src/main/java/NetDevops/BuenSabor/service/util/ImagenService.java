package NetDevops.BuenSabor.service.util;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImagenService {

    private final Path root = Paths.get("src/main/resources/images");

    public String saveImage(MultipartFile imageFile) throws IOException {
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }

        Path destinationFile = root.resolve(Paths.get(imageFile.getOriginalFilename()))
                .normalize().toAbsolutePath();

        if (!destinationFile.getParent().equals(root.toAbsolutePath())) {
            throw new IllegalStateException("Cannot store file outside current directory.");
        }

        imageFile.transferTo(destinationFile);
        return imageFile.getOriginalFilename();
    }
}