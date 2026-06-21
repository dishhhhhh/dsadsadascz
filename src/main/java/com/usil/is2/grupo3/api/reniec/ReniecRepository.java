package com.usil.is2.grupo3.api.reniec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReniecRepository extends JpaRepository<PadronReniec, String> {
}