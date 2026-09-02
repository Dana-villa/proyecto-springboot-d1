package com.explicacionD1.projectD1Campuslands.service;

import com.explicacionD1.projectD1Campuslands.model.Bodega;
import com.explicacionD1.projectD1Campuslands.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Le dice a Spring que esta clase es un servicio
public class BodegaService {

    @Autowired // Conecta automáticamente el repositorio que creamos antes
    private BodegaRepository bodegaRepository;

    public List<Bodega> obtenerTodas() {
        return bodegaRepository.findAll();
    }

    public Bodega guardar(Bodega bodega) {
        return bodegaRepository.save(bodega);
    }

    public Bodega buscarPorId(Long id) {
        // Si no lo encuentra, devuelve un "null" (vacío)
        return bodegaRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        bodegaRepository.deleteById(id);
    }
}