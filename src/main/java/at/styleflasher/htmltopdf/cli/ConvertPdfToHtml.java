package at.styleflasher.htmltopdf.cli;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfUAConformance;
import com.itextpdf.kernel.pdf.PdfVersion;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.Callable;

@Command(name = "htmlToPdf",
        description = "Convert HTML to PDF",
        mixinStandardHelpOptions = true,
        version = "htmlToPdf 0.1")
public class ConvertPdfToHtml implements Callable<Integer> {

    @Parameters(index = "0", description = "The file whose checksum to calculate.")
    private File file;

    @Option(names = {"-o", "--output"}, description = "/var/www/example.html", required = true)
    private String outputPath;

    @Override
    public Integer call() throws Exception {
        ConverterProperties converterProperties = new ConverterProperties()
                .setPdfUAConformance(PdfUAConformance.PDF_UA_2);

        FileInputStream inputFile = new FileInputStream(file.getAbsolutePath());

        WriterProperties writerProperties = new WriterProperties()
            .setPdfVersion(PdfVersion.PDF_2_0);
        PdfWriter writer = new PdfWriter(outputPath, writerProperties);

        HtmlConverter.convertToPdf(inputFile, writer, converterProperties);

        return 0;
    }
}