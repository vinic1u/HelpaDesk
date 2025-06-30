package com.pedroribeiro.helpaai.services;

import com.pedroribeiro.helpaai.dtos.sector.SectorAdminResponseDTO;
import com.pedroribeiro.helpaai.dtos.sector.SectorRequestDTO;
import com.pedroribeiro.helpaai.dtos.sector.SectorResponseDTO;
import com.pedroribeiro.helpaai.entities.Sector;
import com.pedroribeiro.helpaai.exceptions.ResourceNotFoundException;
import com.pedroribeiro.helpaai.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService {


    @Autowired
    private SectorRepository sectorRepository;

    public List<SectorAdminResponseDTO> findAllSectors(){
        List<Sector> sectors = sectorRepository.findAll();
        return sectors.stream().map(SectorAdminResponseDTO::new).toList();
    }

    public List<SectorResponseDTO> findAllSectorsEnabled(){
        List<Sector> sectors = sectorRepository.findByDeletedFalse();
        return sectors.stream().map(SectorResponseDTO::new).toList();
    }

    public SectorResponseDTO findSectorById(Integer id){
        Sector sector = sectorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Setor com ID: " + id + " não encontrado!"));
        return new SectorResponseDTO(sector);
    }

    public SectorResponseDTO saveSector(SectorRequestDTO dto){
        Sector sector = new Sector();
        sector.setName(dto.getName());
        sector.setDeleted(false);
        sectorRepository.save(sector);
        return new SectorResponseDTO(sector);
    }

    public SectorResponseDTO updateSector(Integer id,SectorRequestDTO dto){
        Sector sector = sectorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Setor com ID: " + id + " não encontrado!"));
        sector.setName(dto.getName());
        sectorRepository.save(sector);
        return new SectorResponseDTO(sector);
    }

    public void deleteSector(Integer id){
        Sector sector = sectorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Setor com ID: " + id + " não encontrado!"));
        if((sector.getDeleted() != null )&& (sector.getDeleted())) {
            sector.setDeleted(false);
            sectorRepository.save(sector);
            return;
        }

        sector.setDeleted(true);
        sectorRepository.save(sector);
    }


}
