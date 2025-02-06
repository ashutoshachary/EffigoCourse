package com.example.caching_and_streaming.service;



import com.example.caching_and_streaming.model.TrafficAccident;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TrafficDataService {
    
    @Value("${csv.file.path}")
    private String csvPath;


    @Cacheable("trafficData")
    public Stream<TrafficAccident> streamData() throws IOException, CsvException {
        // Load file from resources folder
        ClassPathResource resource = new ClassPathResource(csvPath);
        Reader reader = new InputStreamReader(resource.getInputStream());
        CSVReader csvReader = new CSVReader(reader);
        
        // Skip header
        csvReader.readNext();
        
        return csvReader.readAll().stream()
            .map(this::mapToTrafficAccident)
            .onClose(() -> {
                try {
                    csvReader.close();
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
    }
    @Cacheable("trafficData")
    public List<TrafficAccident> getAccidentsByCountryPaginated(
            String country, 
            int page, 
            int size
    ) throws IOException, CsvException {
        return streamData()
            .filter(accident -> accident.getCountry().equals(country))
            .skip((long) (page - 1) * size)
            .limit(size)
            .collect(Collectors.toList());
    }


    private TrafficAccident mapToTrafficAccident(String[] row) {
        TrafficAccident accident = new TrafficAccident();
        accident.setCountry(row[0]);
        accident.setYear(Integer.parseInt(row[1]));
        accident.setMonth(row[2]);
        accident.setDayOfWeek(row[3]);
        accident.setTimeOfDay(row[4]);
        accident.setUrbanRural(row[5]);
        accident.setRoadType(row[6]);
        accident.setWeatherConditions(row[7]);
        accident.setVisibilityLevel(Double.parseDouble(row[8]));
        accident.setNumberOfVehicles(Integer.parseInt(row[9]));
        accident.setSpeedLimit(Integer.parseInt(row[10]));
        accident.setDriverAgeGroup(row[11]);
        accident.setDriverGender(row[12]);
        accident.setDriverAlcoholLevel(Double.parseDouble(row[13]));
        accident.setDriverFatigue(Boolean.parseBoolean(row[14]));
        accident.setVehicleCondition(row[15]);
        accident.setPedestriansInvolved(Integer.parseInt(row[16]));
        accident.setCyclistsInvolved(Integer.parseInt(row[17]));
        accident.setAccidentSeverity(row[18]);
        accident.setNumberOfInjuries(Integer.parseInt(row[19]));
        accident.setNumberOfFatalities(Integer.parseInt(row[20]));
        accident.setEmergencyResponseTime(Double.parseDouble(row[21]));
        accident.setTrafficVolume(Double.parseDouble(row[22]));
        accident.setRoadCondition(row[23]);
        accident.setAccidentCause(row[24]);
        accident.setInsuranceClaims(Integer.parseInt(row[25]));
        accident.setMedicalCost(Double.parseDouble(row[26]));
        accident.setEconomicLoss(Double.parseDouble(row[27]));
        accident.setRegion(row[28]);
        accident.setPopulationDensity(Double.parseDouble(row[29]));
        return accident;
    }

    // Additional methods for filtering and analysis
    public List<TrafficAccident> getAccidentsByCountry(String country) throws IOException, CsvException {
        return streamData()
            .filter(accident -> accident.getCountry().equals(country))
            .toList();
    }

    public List<TrafficAccident> getAccidentsByYear(int year) throws IOException, CsvException {
        return streamData()
            .filter(accident -> accident.getYear() == year)
            .toList();
    }
}
