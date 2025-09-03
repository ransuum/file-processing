package org.acme.repository;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import org.acme.entity.File;

public interface FileRepository extends PanacheEntityResource<File, String> { }
