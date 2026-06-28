import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadmeReverter {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 12; i++) {
            String dir = String.format("day%02d", i);
            Path path = Paths.get("src/main/java/software/ulpgc/aoc/" + dir + "/README.md");
            if (!Files.exists(path)) continue;
            
            String content = Files.readString(path);
            
            content = content.replaceAll("(?m)^## Evaluación de Principios, Técnicas y Patrones de Diseño\\s*^### 1\\. Fundamentos", "## Fundamentos");
            content = content.replaceAll("(?m)^### 2\\. Principios de Diseño", "## Principios de Diseño");
            content = content.replaceAll("(?m)^#### SOLID", "* **SOLID**:");
            content = content.replaceAll("(?m)^### 3\\. Técnicas", "## Técnicas");
            content = content.replaceAll("(?m)^### 4\\. Patrones de Diseño", "## Patrones de Diseño");
            content = content.replaceAll("(?m)^### 5\\. Paradigmas", "## Paradigmas");
            
            Files.writeString(path, content);
            System.out.println("Reverted " + dir);
        }
    }
}
