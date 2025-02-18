package Repository;

import Model.HasName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A repository implementation that stores data in memory.
 *
 * @param <T> The type of objects stored in the repository, which must implement HasId.
 */
public class InMemoryRepositoryProduct<T extends HasName> implements RepositoryProduct<T > {
    private final Map<Integer, T> data = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(T obj) {
        data.putIfAbsent(obj.getName(), obj);
    }

    @Override
    public T get(String name) {
        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(T obj) {
        data.replace(obj.getName(), obj);
    }

    @Override
    public void delete(String name) {
        data.remove(name);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() {
        return data.values().stream().toList();
    }

    @Override
    public Integer getMaxID() {
        return data.keySet().stream().max(Integer::compareTo).orElse(0);
    }


}
