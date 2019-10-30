/*
 * ------------------------------------------------------------------------------------------------
 * Copyright 2014 by Swiss Post, Information Technology Services
 * ------------------------------------------------------------------------------------------------
 * $Id$
 * ------------------------------------------------------------------------------------------------
 */

package com.ecommerce.microcommerce.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by michel on 01.05.2018.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProduitIntrouvableException extends RuntimeException {
    public ProduitIntrouvableException(String s) {
        super(s);
    }
}
