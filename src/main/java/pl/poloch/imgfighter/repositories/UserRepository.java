package pl.poloch.imgfighter.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.poloch.imgfighter.database_models.UserModel;

import java.util.List;

public interface UserRepository extends CrudRepository<UserModel, Long> {
    List<UserModel> findByNickname(String nickname);

    List<UserModel> findByEmail(String email);
}
