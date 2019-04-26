package com.appNgeek.blog.service;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appNgeek.blog.exception.EntityNotFoundException;

public abstract class AbstractService<T, ID extends Serializable> {

	protected JpaRepository<T, ID> jpaRepository;

	public AbstractService(JpaRepository<T, ID> jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	/**
	 * Saves a given entity. Use the returned instance for further operations as the save operation might have changed
	 * the entity instance completely.
	 *
	 * @param entity
	 *            must not be {@literal null}.
	 * @return the saved entity will never be {@literal null}.
	 */
	@Transactional
	public <S extends T> S save(S entity) {
		return jpaRepository.save(entity);
	}

	/**
	 * Saves all given entities.
	 *
	 * @param entities
	 *            must not be {@literal null}.
	 * @return the saved entities will never be {@literal null}.
	 * @throws IllegalArgumentException
	 *             in case the given entity is {@literal null}.
	 */
	@Transactional
	public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
		return jpaRepository.saveAll(entities);
	}

	/**
	 * Retrieves an entity by its id.
	 *
	 * @param id
	 *            must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none found
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}.
	 */
	public Optional<T> findById(ID id) {
		return jpaRepository.findById(id);
	}
	
	/**
	 * Retrieves an entity by its id.
	 *
	 * @param id
	 *            must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none found
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}.
	 */
	public T findOne(ID id) {
		Optional<T> result = jpaRepository.findById(id);
		if (result.isPresent())
			return result.get();
		else
			throw new EntityNotFoundException(getEntityType(), (Long) id);
	}
	
	/**
	 * Retrieves an entity by its id if not exist doent throw exception but returns null.
	 *
	 * @param id
	 *            must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none found
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}.
	 */
	public T findOneSafe(ID id) {
		Optional<T> result = jpaRepository.findById(id);
		if (result.isPresent())
			return result.get();
		else
			return null;
	}
	
	

	/**
	 * Returns whether an entity with the given id exists.
	 *
	 * @param id
	 *            must not be {@literal null}.
	 * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}.
	 */
	public boolean existsById(ID id) {
		return jpaRepository.existsById(id);
	}

	/**
	 * Returns all instances of the type.
	 *
	 * @return all entities
	 */
	public Iterable<T> findAll() {
		return jpaRepository.findAll();
	}

	/**
	 * Returns all instances of the type with the given IDs.
	 *
	 * @param ids
	 * @return
	 */
	public Iterable<T> findAllById(Iterable<ID> ids) {
		return jpaRepository.findAllById(ids);
	}

	/**
	 * Returns the number of entities available.
	 *
	 * @return the number of entities
	 */
	public long count() {
		return jpaRepository.count();
	}

	/**
	 * Deletes the entity with the given id.
	 *
	 * @param id
	 *            must not be {@literal null}.
	 * @throws IllegalArgumentException
	 *             in case the given {@code id} is {@literal null}
	 */
	@Transactional
	public void deleteById(ID id) {
		jpaRepository.deleteById(id);
	}

	/**
	 * Deletes a given entity.
	 *
	 * @param entity
	 * @throws IllegalArgumentException
	 *             in case the given entity is {@literal null}.
	 */
	@Transactional
	public void delete(T entity) {
		jpaRepository.delete(entity);
	}

	/**
	 * Deletes the given entities.
	 *
	 * @param entities
	 * @throws IllegalArgumentException
	 *             in case the given {@link Iterable} is {@literal null}.
	 */
	@Transactional
	public void deleteAll(Iterable<? extends T> entities) {
		jpaRepository.deleteAll(entities);
	}

	/**
	 * Deletes all entities managed by the repository.
	 */
	@Transactional
	public void deleteAll() {
		jpaRepository.deleteAll();
	}

	/**
	 * Returns all entities sorted by the given options.
	 *
	 * @param sort
	 * @return all entities sorted by the given options
	 */
	public Iterable<T> findAll(Sort sort) {
		return jpaRepository.findAll(sort);
	}

	/**
	 * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
	 *
	 * @param pageable
	 * @return a page of entities
	 */
	public Page<T> findAll(Pageable pageable) {
		return jpaRepository.findAll(pageable);
	}

	/**
	 * Flushes all pending changes to the database.
	 */
	@Transactional
	public void flush() {
		jpaRepository.flush();
	}

	/**
	 * Saves an entity and flushes changes instantly.
	 *
	 * @param entity
	 * @return the saved entity
	 */
	@Transactional
	public <S extends T> S saveAndFlush(S entity) {
		return jpaRepository.saveAndFlush(entity);
	}

	/**
	 * Deletes the given entities in a batch which means it will create a single {@link Query}. Assume that we will
	 * clear the {@link javax.persistence.EntityManager} after the call.
	 *
	 * @param entities
	 */
	@Transactional
	public void deleteInBatch(Iterable<T> entities) {
		jpaRepository.deleteInBatch(entities);
	}

	/**
	 * Deletes all entities in a batch call.
	 */
	@Transactional
	public void deleteAllInBatch() {
		jpaRepository.deleteAllInBatch();
	}

	/**
	 * Returns a reference to the entity with the given identifier. Depending on how the JPA persistence provider is
	 * implemented this is very likely to always return an instance and throw an
	 * {@link javax.persistence.EntityNotFoundException} on first access. Some of them will reject invalid identifiers
	 * immediately.
	 *
	 * @param id
	 *            must not be {@literal null}.
	 * @return a reference to the entity with the given identifier.
	 * @see EntityManager#getReference(Class, Object) for details on when an exception is thrown.
	 */
	public T getOne(ID id) {
		return findOne(id);
	}
	
	public abstract String getEntityType();

}
