package NetDevops.BuenSabor.service.funcionalidades;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class Funcionalidades {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public String guardarImagen(String base64Image, String fileName) throws IOException {
        // Decodificar el string base64 a un array de bytes
        byte[] decodedBytes = Base64.decodeBase64(base64Image);

        // Crear un archivo en la carpeta designada con el nombre de archivo proporcionado
        File file = new File(uploadDir + File.separator + fileName);
        FileOutputStream fop = new FileOutputStream(file);

        // Escribir el array de bytes en el archivo
        fop.write(decodedBytes);
        fop.flush();
        fop.close();

        // Aquí puedes guardar el nombre del archivo en la base de datos y setearlo en la clase correspondiente

        return fileName;
    }

    public String convertirImagenABase64(String rutaImagen) throws IOException {
        // Leer el archivo de la imagen en un array de bytes
        byte[] bytesImagen = Files.readAllBytes(Paths.get(rutaImagen));

        // Codificar el array de bytes a un string en formato base64
        String imagenBase64 = Base64.encodeBase64String(bytesImagen);

        return imagenBase64;
    }

}
