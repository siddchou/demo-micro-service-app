package io.github.jhipster.application.web.rest;
import io.github.jhipster.application.service.AppUserService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.AppUserDTO;
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
 * REST controller for managing AppUser.
 */
@RestController
@RequestMapping("/api")
public class AppUserResource {

    private final Logger log = LoggerFactory.getLogger(AppUserResource.class);

    private static final String ENTITY_NAME = "demoMicroserviceAppAppUser";

    private final AppUserService appUserService;

    public AppUserResource(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * POST  /app-users : Create a new appUser.
     *
     * @param appUserDTO the appUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new appUserDTO, or with status 400 (Bad Request) if the appUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/app-users")
    public ResponseEntity<AppUserDTO> createAppUser(@RequestBody AppUserDTO appUserDTO) throws URISyntaxException {
        log.debug("REST request to save AppUser : {}", appUserDTO);
        if (appUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new appUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppUserDTO result = appUserService.save(appUserDTO);
        return ResponseEntity.created(new URI("/api/app-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /app-users : Updates an existing appUser.
     *
     * @param appUserDTO the appUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated appUserDTO,
     * or with status 400 (Bad Request) if the appUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the appUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/app-users")
    public ResponseEntity<AppUserDTO> updateAppUser(@RequestBody AppUserDTO appUserDTO) throws URISyntaxException {
        log.debug("REST request to update AppUser : {}", appUserDTO);
        if (appUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AppUserDTO result = appUserService.save(appUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, appUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /app-users : get all the appUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of appUsers in body
     */
    @GetMapping("/app-users")
    public ResponseEntity<List<AppUserDTO>> getAllAppUsers(Pageable pageable) {
        log.debug("REST request to get a page of AppUsers");
        Page<AppUserDTO> page = appUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/app-users");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /app-users/:id : get the "id" appUser.
     *
     * @param id the id of the appUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the appUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/app-users/{id}")
    public ResponseEntity<AppUserDTO> getAppUser(@PathVariable Long id) {
        log.debug("REST request to get AppUser : {}", id);
        Optional<AppUserDTO> appUserDTO = appUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(appUserDTO);
    }

    /**
     * DELETE  /app-users/:id : delete the "id" appUser.
     *
     * @param id the id of the appUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/app-users/{id}")
    public ResponseEntity<Void> deleteAppUser(@PathVariable Long id) {
        log.debug("REST request to delete AppUser : {}", id);
        appUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
