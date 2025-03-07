package com.pedroribeiro.helpaai.controller;

import com.pedroribeiro.helpaai.dtos.category.CategoryRequestDTO;
import com.pedroribeiro.helpaai.dtos.category.CategoryResponseDTO;
import com.pedroribeiro.helpaai.dtos.sector.SectorRequestDTO;
import com.pedroribeiro.helpaai.dtos.sector.SectorResponseDTO;
import com.pedroribeiro.helpaai.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {

    @Autowired
    private SectorService sectorService;


    @GetMapping
    public ResponseEntity<List<SectorResponseDTO>> findAllSectors(){
        return ResponseEntity.ok(sectorService.findAllSectors());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SectorResponseDTO> findSectorById(
            @PathVariable("id") Integer id
    ){
        return ResponseEntity.ok(sectorService.findSectorById(id));
    }

    @PostMapping
    public ResponseEntity<SectorResponseDTO> saveSector(
            @RequestBody SectorRequestDTO dto
    ){
        SectorResponseDTO response = sectorService.saveSector(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SectorResponseDTO> updateSector(
            @PathVariable("id") Integer id,
            @RequestBody SectorRequestDTO dto
    ){
        SectorResponseDTO response = sectorService.updateSector(id,dto);
        return ResponseEntity.ok(response);
    }
}
