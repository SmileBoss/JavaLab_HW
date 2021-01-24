package ru.itis;

import com.google.auto.service.AutoService;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"ru.itis.HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // получить типы с аннотаций ru.itis.HtmlForm
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        for (Element element : annotatedElements) {
            // получаем полный путь для генерации html
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            // ru.itis.User.class -> ru.itis.User.html
            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);

            Version incompatibleImprovements;
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
            configuration.setDefaultEncoding("UTF-8");
            try {
                configuration.setTemplateLoader(new FileTemplateLoader(new File("/src/main/resources/")));
                Template template = configuration.getTemplate("fr_form.htlh");

                Map<String, Object> attributes = new HashMap<>();
                List<Input> inputs = new ArrayList<>();
                HtmlForm htmlForm = element.getAnnotation(HtmlForm.class);
                Form form = new Form(htmlForm.action(), htmlForm.method());
                attributes.put("form", form);

                List<? extends Element> enclosedElements = element.getEnclosedElements();
                for (Element elem : enclosedElements) {
                    if (elem.getKind().isField()){
                        List<? extends AnnotationMirror> annotationMirrors = elem.getAnnotationMirrors();
                        for (AnnotationMirror annotationMirror : annotationMirrors) {
                            Element annotationElement = annotationMirror.getAnnotationType().asElement();
                            if (annotationElement.getSimpleName().contentEquals("HtmlInput")) {
                                HtmlInput htmlInput = elem.getAnnotation(HtmlInput.class);
                                Input input = new Input(htmlInput.type(), htmlInput.name(), htmlInput.placeholder());
                                inputs.add(input);
                            }
                        }
                    }
                }
                attributes.put("input", inputs);

                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
                template.process(attributes, writer);
                writer.close();

//                BufferedWriter writer = new BufferedWriter(new FileWriter(new File(  element.getSimpleName().toString() + ".html")));
//                HtmlForm annotation = element.getAnnotation(HtmlForm.class);
//                writer.write("<form action='" + annotation.action() + "' method='" + annotation.method() + "'/>");
//                writer.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            } catch (TemplateException e) {
                e.printStackTrace();
            }

        }
        return true;
    }
}
