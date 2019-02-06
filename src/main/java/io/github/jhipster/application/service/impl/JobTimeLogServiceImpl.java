package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.JobTimeLogService;
import io.github.jhipster.application.domain.JobTimeLog;
import io.github.jhipster.application.repository.JobTimeLogRepository;
import io.github.jhipster.application.service.dto.JobTimeLogDTO;
import io.github.jhipster.application.service.mapper.JobTimeLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing JobTimeLog.
 */
@Service
@Transactional
public class JobTimeLogServiceImpl implements JobTimeLogService {

    private final Logger log = LoggerFactory.getLogger(JobTimeLogServiceImpl.class);

    private final JobTimeLogRepository jobTimeLogRepository;

    private final JobTimeLogMapper jobTimeLogMapper;

    public JobTimeLogServiceImpl(JobTimeLogRepository jobTimeLogRepository, JobTimeLogMapper jobTimeLogMapper) {
        this.jobTimeLogRepository = jobTimeLogRepository;
        this.jobTimeLogMapper = jobTimeLogMapper;
    }

    /**
     * Save a jobTimeLog.
     *
     * @param jobTimeLogDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public JobTimeLogDTO save(JobTimeLogDTO jobTimeLogDTO) {
        log.debug("Request to save JobTimeLog : {}", jobTimeLogDTO);
        JobTimeLog jobTimeLog = jobTimeLogMapper.toEntity(jobTimeLogDTO);
        jobTimeLog = jobTimeLogRepository.save(jobTimeLog);
        return jobTimeLogMapper.toDto(jobTimeLog);
    }

    /**
     * Get all the jobTimeLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JobTimeLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JobTimeLogs");
        return jobTimeLogRepository.findAll(pageable)
            .map(jobTimeLogMapper::toDto);
    }


    /**
     * Get one jobTimeLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JobTimeLogDTO> findOne(Long id) {
        log.debug("Request to get JobTimeLog : {}", id);
        return jobTimeLogRepository.findById(id)
            .map(jobTimeLogMapper::toDto);
    }

    /**
     * Delete the jobTimeLog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JobTimeLog : {}", id);        jobTimeLogRepository.deleteById(id);
    }
}
