/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 2:14 AM.
 * Last modified 5/27/19 2:14 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.microservice.dataextractor;

import com.embedit.dataextraction.dataextractor.DataExtractorService;
import com.embedit.dataextraction.dataextractor.model.DataExtractionResult;
import com.embedit.dataextraction.dataextractor.model.ExtractedData;
import com.embedit.dataextraction.dataextractor.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data-extractor")
public class DataExtractorController {
    private final DataExtractorService dataExtractionService;

    @Autowired
    public DataExtractorController(DataExtractorService dataExtractorService) {
        this.dataExtractionService = dataExtractorService;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<DataExtractionResult> extractData(@PathVariable("id") long userId) {
        DataExtractionResult extractionResult = dataExtractionService.extractUserAndPosts(userId);
        if (extractionResult instanceof Error) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(extractionResult);
        } else if (extractionResult instanceof ExtractedData) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(extractionResult);
        } else
            throw new IllegalStateException("Unknown data extraction result: " + extractionResult.getClass().getSimpleName());
    }
}
