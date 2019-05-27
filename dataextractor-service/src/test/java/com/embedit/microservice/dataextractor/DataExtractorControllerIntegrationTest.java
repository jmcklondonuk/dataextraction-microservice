/*
 * Developed by Jack McKenzie, MSc(Dist) on 5/27/19 2:14 AM.
 * Last modified 5/27/19 2:12 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.embedit.microservice.dataextractor;

import com.embedit.dataextraction.dataextractor.model.DataExtractionResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataExtractorControllerIntegrationTest {
    @Autowired
    private DataExtractorController dataExtractorController;

    @Test
    public void testExtractData_HappyPath() {
        ResponseEntity<DataExtractionResult> dataExtractionResult = dataExtractorController.extractData(1);
        assertNotNull("Data extraction result cannot be null!", dataExtractionResult.getBody());
        assertEquals("Data extraction result must be ExtractedData!", "ExtractedData", dataExtractionResult.getBody().getClass().getSimpleName());
    }

    @Test
    public void testExtractData_NonExistentUser() {
        ResponseEntity<DataExtractionResult> dataExtractionResult = dataExtractorController.extractData(0);
        assertNotNull("Data extraction result cannot be null!", dataExtractionResult.getBody());
        assertEquals("Data extraction result must be Error!", "Error", dataExtractionResult.getBody().getClass().getSimpleName());
    }
}
