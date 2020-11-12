/**
 * 
 */
package br.com.distribuidora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.distribuidora.model.Endereco;

/**
 * @author marcio
 *
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
