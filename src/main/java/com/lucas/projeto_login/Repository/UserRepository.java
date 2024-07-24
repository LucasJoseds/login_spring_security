package com.lucas.projeto_login.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucas.projeto_login.Model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Page<User> findAll(Pageable pageable);


   //@Query(nativeQuery=true,value="select * from tbl_users where idade >=18")
  // List<User> findByMaiorIdade();
 
}
