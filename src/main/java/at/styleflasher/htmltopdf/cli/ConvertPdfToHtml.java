package at.styleflasher.htmltopdf.cli;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfUAConformance;
import com.itextpdf.kernel.pdf.PdfVersion;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;
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

    @Option(names = {"-b", "--base-uri"}, description = "/var/www/html (default: output directory)", defaultValue = "")
    private String baseUri;

    @Option(names = {"-m", "--media-type"}, description = "Media type to use for conversion (print, screen, etc.)", defaultValue = "")
    private String mediaType;

    @Option(names = {"-o", "--output"}, description = "example.pdf", required = true)
    private String outputPath;

    @Parameters(index = "0", description = "File to convert")
    private File inputFile;

    @Override
    public Integer call() throws Exception {
        ConverterProperties converterProperties = new ConverterProperties()
                .setPdfUAConformance(PdfUAConformance.PDF_UA_2);

        if (!baseUri.isEmpty()) {
            converterProperties.setBaseUri(baseUri);
        }
        if (!mediaType.isEmpty()) {
            if (!MediaType.isValidMediaType(mediaType)) {
                System.err.println("Invalid media type: " + mediaType);
                return 1;
            }
            converterProperties.setMediaDeviceDescription(new MediaDeviceDescription(mediaType));
        }

        FileInputStream inputFileStream = new FileInputStream(inputFile.getAbsolutePath());

        WriterProperties writerProperties = new WriterProperties()
            .setPdfVersion(PdfVersion.PDF_2_0);
        PdfWriter writer = new PdfWriter(outputPath, writerProperties);

        HtmlConverter.convertToPdf(inputFileStream, writer, converterProperties);

        return 0;
    }
}