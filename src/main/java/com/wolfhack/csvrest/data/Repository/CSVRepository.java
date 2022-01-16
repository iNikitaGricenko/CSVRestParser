package com.wolfhack.csvrest.data.Repository;

import com.wolfhack.csvrest.data.Model.CSVModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CSVRepository extends PagingAndSortingRepository<CSVModel, Long> {
    public boolean existsTestModelsByNameAndInstitutionType(String name, String type);
}
