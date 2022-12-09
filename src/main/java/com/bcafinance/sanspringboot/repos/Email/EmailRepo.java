package com.bcafinance.sanspringboot.repos.Email;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/4/2022 5:01 PM
Last Modified on 12/4/2022 5:01 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.models.Emails.Emails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepo extends JpaRepository<Emails, Long> {

    Optional<Emails> findByEmails(String emails);

    Optional<Emails> findByTokens(String tokens);
}
