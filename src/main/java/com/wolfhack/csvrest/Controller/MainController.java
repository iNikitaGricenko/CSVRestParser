package com.wolfhack.csvrest.Controller;

import com.wolfhack.csvrest.MapConverter;
import com.wolfhack.csvrest.MultipartFileUtils.MultipartCSVUtils;
import com.wolfhack.csvrest.data.Model.CSVModel;
import com.wolfhack.csvrest.data.Repository.CSVRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final CSVRepository csvRepository;

    @RequestMapping(value = "/load", method = POST, consumes = "multipart/form-data", produces = "application/json")
    public void parseCSV(@RequestBody MultipartFile file, HttpServletResponse response) throws IOException{
        MultipartCSVUtils fileUploader = new MultipartCSVUtils();
        fileUploader.saveFile(file);

        Map<String, String[]> parsedFile = fileUploader.parseFile(file);

        Vector<CSVModel> models = MapConverter.convertToVector(parsedFile);
        System.out.println(models.size());
        csvRepository.saveAll(models);

        response.sendRedirect("/csv");
    }

    @RequestMapping(value = "/load", method = GET)
    public ModelAndView getLoadingPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @RequestMapping("/csv")
    public ModelAndView getCSVPage(@RequestParam(name="page", required = false, defaultValue = "0")Integer page,
                                   @RequestParam(name = "size", required = false, defaultValue = "100")Integer size) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("home.html");
        Pageable pageable = PageRequest.of(page, size);
        modelAndView.addObject("ELEMENTS", csvRepository.findAll(pageable));
        modelAndView.addObject("cP", pageable.getPageNumber());

        return modelAndView;
    }

    @RequestMapping("/json")
    public Iterable<CSVModel> getParsedData() {
        return csvRepository.findAll();
    }

}
