package store;

import models.Advertisement;
import models.Body;
import models.Brand;
import models.User;

import java.util.List;

/**
 * @author ArvikV
 * @version 1.0
 * @since 22.01.2022
 */
public interface Store {
    List<Advertisement> advertisementListForLastDay();

    List<Advertisement> advertisementListWithPhotos();

    List<Advertisement> advertisementByBrand(int brand);

}
