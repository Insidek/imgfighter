package pl.poloch.imgfighter.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.poloch.imgfighter.database_models.UserModel;

public interface UserRepository extends CrudRepository<UserModel, Long> {

}
