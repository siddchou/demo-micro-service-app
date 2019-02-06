package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ContractorServiceService;
import io.github.jhipster.application.domain.ContractorService;
import io.github.jhipster.application.repository.ContractorServiceRepository;
import io.github.jhipster.application.service.dto.ContractorServiceDTO;
import io.github.jhipster.application.service.mapper.ContractorServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ContractorService.
 */
@Service
@Transactional
public class ContractorServiceServiceImpl implements ContractorServiceService {

    private final Logger log = LoggerFactory.getLogger(ContractorServiceServiceImpl.class);

    private final ContractorServiceRepository contractorServiceRepository;

    private final ContractorServiceMapper contractorServiceMapper;

    public ContractorServiceServiceImpl(ContractorServiceRepository contractorServiceRepository, ContractorServiceMapper contractorServiceMapper) {
        this.contractorServiceRepository = contractorServiceRepository;
        this.contractorServiceMapper = contractorServiceMapper;
    }

    /**
     * Save a contractorService.
     *
     * @param contractorServiceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ContractorServiceDTO save(ContractorServiceDTO contractorServiceDTO) {
        log.debug("Request to save ContractorService : {}", contractorServiceDTO);
        ContractorService contractorService = contractorServiceMapper.toEntity(contractorServiceDTO);
        contractorService = contractorServiceRepository.save(contractorService);
        return contractorServiceMapper.toDto(contractorService);
    }

    /**
     * Get all the contractorServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ContractorServiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ContractorServices");
        return contractorServiceRepository.findAll(pageable)
            .map(contractorServiceMapper::toDto);
    }


    /**
     * Get one contractorService by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContractorServiceDTO> findOne(Long id) {
        log.debug("Request to get ContractorService : {}", id);
        return contractorServiceRepository.findById(id)
            .map(contractorServiceMapper::toDto);
    }

    /**
     * Delete the contractorService by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContractorService : {}", id);        contractorServiceRepository.deleteById(id);
    }
}
