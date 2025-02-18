package Repository;

import java.util.List;
import Model.HasID;

/**
 * An interface that defines the basic CRUD operations for a repository.
 *
 * @param <T> The type of objects stored in the repository, which must implement HasId.
 */
public interface RepositoryProduct<T> {
    /**
     * Creates a new object in the repository.
     *
     * @param obj The object to create.
     */
    void create(T obj);

    /**
     * Retrieves an object from the repository by its ID.
     *
     * @paramn name The unique identifier of the object to retrieve.
     * @return The object with the specified ID, or null if not found.
     */
    T get(String name);

    /**
     * Updates an existing object in the repository.
     *
     * @param obj The object to update.
     */
    void update(T obj);

    /**
     * Deletes an object from the repository by its ID.
     *
     * @param name The unique identifier of the object to delete.
     */
    void delete(String name);

    /**
     * Retrieves all objects from the repository.
     *
     * @return A list of all objects in the repository.
     */
    List<T> getAll();

    Integer getMaxID();

}


