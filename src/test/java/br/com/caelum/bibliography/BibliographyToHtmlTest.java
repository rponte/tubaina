package br.com.caelum.bibliography;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Scanner;

import org.junit.Test;

import br.com.caelum.tubaina.TubainaBuilder;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;

public class BibliographyToHtmlTest {

    @Test
    public void shouldGenerateHtmlBibContent() throws Exception {
        Bibliography bibliography = new BibliographyFactory().build(new File(
                "src/test/resources/bibliography/bibsimple.xml"));
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(TubainaBuilder.DEFAULT_TEMPLATE_DIR, "kindle"));
        cfg.setObjectWrapper(new BeansWrapper());
        BibliographyToHtml htmlBibGenerator = new BibliographyToHtml(bibliography, cfg);

        String expectedBib = new Scanner(new File("src/test/resources/bibliography/bib.html"))
                .useDelimiter("$$").next();

        assertEquals(expectedBib, htmlBibGenerator.generate());
    }
    
}