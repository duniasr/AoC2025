import java.nio.file.*;
import java.util.regex.*;
import java.io.IOException;

public class FixLinks {
    public static void main(String[] args) throws IOException {
        String base = "c:/Users/dunis/Desktop/4CURSO_EII/IS2/AdventOfCode2025/src/main/java/software/ulpgc/aoc/";
        Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]\\(([^)]+\\.java)\\)");
        
        for (int i = 1; i <= 12; i++) {
            if (i == 11) continue; // ya está arreglado
            String day = String.format("day%02d", i);
            Path p = Paths.get(base, day, "README.md");
            if (Files.exists(p)) {
                String content = new String(Files.readAllBytes(p), "UTF-8");
                StringBuffer sb = new StringBuffer();
                Matcher m = pattern.matcher(content);
                boolean changed = false;
                while (m.find()) {
                    String name = m.group(1);
                    String link = m.group(2);
                    
                    // Eliminar .java del nombre si lo tiene
                    if (name.endsWith(".java")) {
                        name = name.substring(0, name.length() - 5);
                    }
                    
                    // Añadir backticks si no los tiene
                    if (!name.startsWith("`")) {
                        name = "`" + name + "`";
                    }
                    
                    m.appendReplacement(sb, Matcher.quoteReplacement("[" + name + "](" + link + ")"));
                    changed = true;
                }
                m.appendTail(sb);
                
                if (changed) {
                    Files.write(p, sb.toString().getBytes("UTF-8"));
                    System.out.println("Fixed links in " + day);
                }
            }
        }
    }
}
