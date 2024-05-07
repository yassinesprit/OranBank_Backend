package com.bfi.orabank.Repositories;

import com.bfi.orabank.Entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {
    @Query( "select t from Token t inner join Utilisateur u on t.user.id = u.id where u.id = :id  and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);
}
