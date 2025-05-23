# TODO

- use dynamic linking for Participants

1. Gerando o Conteúdo do Relatório (String):
   Primeiro, você precisa de um método que gere o conteúdo do seu relatório como uma String.
   // Pacote: br.edu.ifba.inf008.uniEvents.service
   package br.edu.ifba.inf008.uniEvents.service;

import br.edu.ifba.inf008.uniEvents.model.evento.Evento;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioService {

    private static final DateTimeFormatter FORMATADOR_DATA_RELATORIO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String gerarRelatorioEventos(List<Evento> eventos, String tituloRelatorio) {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("============================================================\n");
        relatorio.append(String.format("Relatório: %s\n", tituloRelatorio));
        relatorio.append(String.format("Gerado em: %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
        relatorio.append("============================================================\n\n");

        if (eventos.isEmpty()) {
            relatorio.append("Nenhum evento encontrado para este relatório.\n");
        } else {
            for (Evento evento : eventos) {
                relatorio.append(String.format("Título: %s\n", evento.getTitulo()));
                if (evento.getData() != null) { // Supondo que evento.getData() retorne LocalDate ou LocalDateTime
                    // Adapte conforme o tipo de 'data' no seu Evento
                    String dataFormatada = "";
                    if (evento.getData() instanceof LocalDate) {
                        dataFormatada = ((LocalDate)evento.getData()).format(FORMATADOR_DATA_RELATORIO);
                    } else if (evento.getData() instanceof LocalDateTime) {
                        dataFormatada = ((LocalDateTime)evento.getData()).format(FORMATADOR_DATA_RELATORIO);
                    } else {
                        dataFormatada = evento.getData().toString(); // Fallback
                    }
                    relatorio.append(String.format("Data: %s\n", dataFormatada));
                }
                relatorio.append(String.format("Local: %s\n", evento.getLocal()));
                relatorio.append(String.format("Capacidade: %d\n", evento.getCapacidade()));
                relatorio.append(String.format("Inscritos: %d\n", evento.getParticipantes().size())); // Supondo getParticipantes()
                relatorio.append("------------------------------------------------------------\n");
            }
        }
        relatorio.append("\nFim do Relatório.\n");
        relatorio.append("============================================================\n");
        return relatorio.toString();
    }

}
Use code with caution.
Java 2. Salvando a String em um Arquivo TXT:
Você pode usar FileWriter ou Files.writeString (Java 11+).
// Pacote: br.edu.ifba.inf008.uniEvents.service (ou uma classe utilitária)
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeradorArquivoService { // Ou adicione a um service existente

    /**
     * Salva o conteúdo de texto em um arquivo no diretório especificado.
     *
     * @param conteudo Conteúdo a ser salvo.
     * @param nomeDiretorio Diretório onde o arquivo será salvo (ex: "relatorios").
     * @param nomeBaseArquivo Nome base do arquivo (ex: "relatorio_eventos"). A data e hora serão adicionadas.
     * @return O caminho completo do arquivo salvo, ou null em caso de erro.
     */
    public String salvarRelatorioTxt(String conteudo, String nomeDiretorio, String nomeBaseArquivo) {
        try {
            // Criar o diretório se não existir
            Path diretorioPath = Paths.get(nomeDiretorio);
            if (!Files.exists(diretorioPath)) {
                Files.createDirectories(diretorioPath);
                System.out.println("Diretório '" + nomeDiretorio + "' criado.");
            }

            // Criar nome de arquivo único com data e hora
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String nomeArquivo = nomeBaseArquivo + "_" + timestamp + ".txt";
            Path caminhoArquivo = diretorioPath.resolve(nomeArquivo);

            // Usando Files.writeString (Java 11+) - mais conciso
            Files.writeString(caminhoArquivo, conteudo, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            // Alternativa com FileWriter (compatível com Java mais antigo):
            /*
            try (FileWriter writer = new FileWriter(caminhoArquivo.toFile())) {
                writer.write(conteudo);
            }
            */

            System.out.println("Relatório salvo em: " + caminhoArquivo.toAbsolutePath());
            return caminhoArquivo.toAbsolutePath().toString();

        } catch (IOException e) {
            System.err.println("Erro ao salvar relatório TXT: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
Use code with caution.
Java 3. Exemplo de Uso (no seu menu ou service):
// Em alguma classe do seu menu ou um service que chama o RelatorioService
// ...
// Supondo que você tenha uma lista de eventos
List<Evento> eventosParaRelatorio = gerenciadorEventos.listarEventosPorTipo("Palestra"); // Exemplo de filtro

RelatorioService relatorioService = new RelatorioService();
String conteudoRelatorio = relatorioService.gerarRelatorioEventos(eventosParaRelatorio, "Eventos do Tipo Palestra");

GeradorArquivoService geradorArquivoService = new GeradorArquivoService();
String caminhoSalvo = geradorArquivoService.salvarRelatorioTxt(conteudoRelatorio, "meus_relatorios", "relatorio_palestras");

if (caminhoSalvo != null) {
System.out.println("Relatório gerado com sucesso!");
} else {
System.out.println("Falha ao gerar o relatório.");
}
// ...
Use code with caution.
Java
Onde salvar o arquivo?
Diretório relativo ao projeto: Como no exemplo, meus_relatorios criará uma pasta na raiz do seu projeto (onde o JAR é executado). Isso é bom para portabilidade e desenvolvimento.
Diretório do Usuário: Você pode salvar na pasta "Documentos" ou "Downloads" do usuário:
String userHome = System.getProperty("user.home"); // Pega o diretório home do usuário
Path diretorioDocumentos = Paths.get(userHome, "Documents", "RelatoriosUniEvents"); // Exemplo
// Path diretorioDownloads = Paths.get(userHome, "Downloads", "RelatoriosUniEvents");
// ... usar diretorioDocumentos como base para salvar
Use code with caution.
Java
Deixar o usuário escolher: Para uma aplicação mais robusta, você poderia usar JFileChooser (Swing) se estivesse em uma aplicação gráfica, mas para CLI, você teria que pedir ao usuário para digitar o caminho completo, o que pode ser propenso a erros. Para este trabalho, um diretório fixo ou relativo ao projeto é suficiente.
Não há uma "lib" específica apenas para salvar TXT, pois o Java já oferece recursos nativos excelentes para isso (java.io e java.nio.file). O trabalho está mais em formatar sua string de relatório e depois usar essas classes para gravá-la em um arquivo.
