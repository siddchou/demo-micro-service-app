package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.service.JobTimeLogService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.JobTimeLogDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing JobTimeLog.
 */
@RestController
@RequestMapping("/api")
public class JobTimeLogResource {

    private final Logger log = LoggerFactory.getLogger(JobTimeLogResource.class);

    private static final String ENTITY_NAME = "demoMicroserviceAppJobTimeLog";

    private final JobTimeLogService jobTimeLogService;

    public JobTimeLogResource(JobTimeLogService jobTimeLogService) {
        this.jobTimeLogService = jobTimeLogService;
    }

    /**
     * POST  /job-time-logs : Create a new jobTimeLog.
     *
     * @param jobTimeLogDTO the jobTimeLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jobTimeLogDTO, or with status 400 (Bad Request) if the jobTimeLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-time-logs")
    public ResponseEntity<JobTimeLogDTO> createJobTimeLog(@RequestBody JobTimeLogDTO jobTimeLogDTO) throws URISyntaxException {
        log.debug("REST request to save JobTimeLog : {}", jobTimeLogDTO);
        if (jobTimeLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new jobTimeLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobTimeLogDTO result = jobTimeLogService.save(jobTimeLogDTO);
        return ResponseEntity.created(new URI("/api/job-time-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-time-logs : Updates an existing jobTimeLog.
     *
     * @param jobTimeLogDTO the jobTimeLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jobTimeLogDTO,
     * or with status 400 (Bad Request) if the jobTimeLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the jobTimeLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-time-logs")
    public ResponseEntity<JobTimeLogDTO> updateJobTimeLog(@RequestBody JobTimeLogDTO jobTimeLogDTO) throws URISyntaxException {
        log.debug("REST request to update JobTimeLog : {}", jobTimeLogDTO);
        if (jobTimeLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JobTimeLogDTO result = jobTimeLogService.save(jobTimeLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobTimeLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /job-time-logs : get all the jobTimeLogs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of jobTimeLogs in body
     */
    @GetMapping("/job-time-logs")
    public ResponseEntity<List<JobTimeLogDTO>> getAllJobTimeLogs(Pageable pageable) {
        log.debug("REST request to get a page of JobTimeLogs");
        Page<JobTimeLogDTO> page = jobTimeLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/job-time-logs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /job-time-logs/:id : get the "id" jobTimeLog.
     *
     * @param id the id of the jobTimeLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobTimeLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/job-time-logs/{id}")
    public ResponseEntity<JobTimeLogDTO> getJobTimeLog(@PathVariable Long id) {
        log.debug("REST request to get JobTimeLog : {}", id);
        Optional<JobTimeLogDTO> jobTimeLogDTO = jobTimeLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobTimeLogDTO);
    }

    /**
     * DELETE  /job-time-logs/:id : delete the "id" jobTimeLog.
     *
     * @param id the id of the jobTimeLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-time-logs/{id}")
    public ResponseEntity<Void> deleteJobTimeLog(@PathVariable Long id) {
        log.debug("REST request to delete JobTimeLog : {}", id);
        jobTimeLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
