package at.styleflasher.htmltopdf;

import at.styleflasher.htmltopdf.cli.ConvertPdfToHtml;
import picocli.CommandLine;

class ConvertApplication {
    public static void main(String... args) {
        CommandLine cli = new CommandLine(new ConvertPdfToHtml());
        int exitCode = cli.execute(args);

        System.exit(exitCode);
    }
}