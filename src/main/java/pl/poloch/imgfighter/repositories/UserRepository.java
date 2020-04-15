package pl.poloch.imgfighter.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.poloch.imgfighter.database_models.UserModel;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
    List<UserModel> findByNickname(String nickname);

    List<UserModel> findByEmail(String email);
}
