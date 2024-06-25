package lk.rupavahini.PPUManagement.asset.clibrary.controller;

import lk.rupavahini.PPUManagement.asset.clibrary.entity.Clibrary;
import lk.rupavahini.PPUManagement.asset.clibrary.service.ClibraryService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lk.rupavahini.PPUManagement.asset.commonAsset.model.ClibraryModel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReportAController {
    private final ClibraryService clibraryService;

    public ReportAController(ClibraryService clibraryService) {
        this.clibraryService = clibraryService;
    }

    //JasperReport Code
    @GetMapping(value = "/ab")
    public String exportPDF() throws JRException {

        List<ClibraryModel> clibraryModels=new ArrayList<>();
        List<Clibrary> all = clibraryService.findAll();
        for (Clibrary clibrary:all) {
            clibraryModels.add(new ClibraryModel(clibrary.getId(),clibrary.getProgrammeName(),clibrary.getEpisodeNumber(),clibrary.getCode()));
        }

        // Fetching the .jrxml file from the resources folder.
        final InputStream stream = this.getClass().getResourceAsStream("/Cherry_3.jrxml");

        // Compile the Jasper report from .jrxml to .japser
        final JasperReport report = JasperCompileManager.compileReport(stream);

        // Fetching the employees from the data source.
        final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(clibraryModels);

        // Adding the additional parameters to the pdf.
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "javacodegeek.com");

        // Filling the report with the employee data and additional parameters information.
        final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);

        // Users can change as per their project requrirements or can take it as request input requirement.
        // For simplicity, this tutorial will automatically place the file under the "c:" drive.
        // If users want to download the pdf file on the browser, then they need to use the "Content-Disposition" technique.
        final String filePath = "D:\\jarfile\\";
        // Export the report to a PDF file.
        try {
            JasperExportManager.exportReportToPdfFile(print, filePath + "Employee_report.pdf");
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "redirect:/clibrary";

    }


}
