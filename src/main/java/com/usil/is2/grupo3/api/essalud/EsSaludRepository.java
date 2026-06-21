package com.usil.is2.grupo3.api.essalud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsSaludRepository extends JpaRepository<SeguroEsSalud, String> {
}