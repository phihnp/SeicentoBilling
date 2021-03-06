
package ch.xwr.seicentobilling.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.Conversion;

import com.xdev.dal.JPADAO;

import ch.xwr.seicentobilling.entities.Bank;
import ch.xwr.seicentobilling.entities.Conversion_;

/**
 * Home object for domain model class Bank.
 *
 * @see Bank
 */
public class ConversionDAO extends JPADAO<Conversion, Long> {
	public ConversionDAO() {
		super(Conversion.class);
	}

	/**
	 * @queryMethod Do not edit, method is generated by editor!
	 */
	public List<ch.xwr.seicentobilling.entities.Conversion> findByValue(final String groups, final String subgroup,
			final String key) {
		final EntityManager entityManager = em();

		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		final ParameterExpression<String> groupsParameter = criteriaBuilder.parameter(String.class, "groups");
		final ParameterExpression<String> subgroupParameter = criteriaBuilder.parameter(String.class, "subgroup");
		final ParameterExpression<String> keyParameter = criteriaBuilder.parameter(String.class, "key");

		final CriteriaQuery<ch.xwr.seicentobilling.entities.Conversion> criteriaQuery = criteriaBuilder
				.createQuery(ch.xwr.seicentobilling.entities.Conversion.class);

		final Root<ch.xwr.seicentobilling.entities.Conversion> root = criteriaQuery
				.from(ch.xwr.seicentobilling.entities.Conversion.class);

		criteriaQuery.where(criteriaBuilder.and(
				criteriaBuilder.and(criteriaBuilder.equal(root.get(Conversion_.cnvGroup), groupsParameter),
						criteriaBuilder.equal(root.get(Conversion_.cnvSubGroup), subgroupParameter)),
				criteriaBuilder.equal(root.get(Conversion_.cnvValueIn), keyParameter)));

		final TypedQuery<ch.xwr.seicentobilling.entities.Conversion> query = entityManager.createQuery(criteriaQuery);
		query.setParameter(groupsParameter, groups);
		query.setParameter(subgroupParameter, subgroup);
		query.setParameter(keyParameter, key);
		return query.getResultList();
	}
}