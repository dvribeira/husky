/*
 * This code is made available under the terms of the Eclipse Public License v1.0 
 * in the github project https://github.com/project-husky/husky there you also 
 * find a list of the contributors and the license information.
 * 
 * This project has been developed further and modified by the joined working group Husky 
 * on the basis of the eHealth Connector opensource project from June 28, 2021, 
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 *
 */
package org.husky.xua.saml2.impl;

import org.herasaf.xacml.core.policy.impl.ActionType;
import org.herasaf.xacml.core.policy.impl.ActionsType;
import org.herasaf.xacml.core.policy.impl.ResourceType;
import org.herasaf.xacml.core.policy.impl.ResourcesType;
import org.herasaf.xacml.core.policy.impl.SubjectsType;
import org.herasaf.xacml.core.policy.impl.TargetType;
import org.husky.xua.core.SecurityObjectBuilder;
import org.husky.xua.saml2.SimpleBuilder;
import org.opensaml.xacml.policy.impl.ActionTypeImplBuilder;
import org.opensaml.xacml.policy.impl.ActionsTypeImplBuilder;
import org.opensaml.xacml.policy.impl.ResourceTypeImplBuilder;
import org.opensaml.xacml.policy.impl.ResourcesTypeImplBuilder;
import org.opensaml.xacml.policy.impl.SubjectTypeImplBuilder;
import org.opensaml.xacml.policy.impl.SubjectsTypeImplBuilder;
import org.opensaml.xacml.policy.impl.TargetTypeImplBuilder;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class implementing the corresponding interface for PolicyType building.</div>
 * <div class="de">Die Klasse implementiert das entsprechende Interface um PolicyType bilden zu können.</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public class TargetBuilderImpl
		implements SimpleBuilder<TargetType>, SecurityObjectBuilder<org.opensaml.xacml.policy.TargetType, TargetType> {

	@Override
	public TargetType create(org.opensaml.xacml.policy.TargetType aInternalObject) {
			var targetType = new TargetType();

			if (aInternalObject.getActions() != null && aInternalObject.getActions().getActions() != null) {
				var actionsType = new ActionsType();
				for (var type : aInternalObject.getActions().getActions()) {
					var actionType = new ActionType();

					for (var typeMatch : type.getActionMatches()) {
						actionType.getActionMatches().add(new ActionMatchBuilderImpl().create(typeMatch));
					}

					actionsType.getActions().add(actionType);
				}

				targetType.setActions(actionsType);
			}

			if (aInternalObject.getSubjects() != null && aInternalObject.getSubjects().getSubjects() != null) {
				var subjectsType = new SubjectsType();

				for (var type : aInternalObject.getSubjects().getSubjects()) {
					var subjectType = new org.herasaf.xacml.core.policy.impl.SubjectType();

					for (var typeMatch : type.getSubjectMatches()) {
						subjectType.getSubjectMatches().add(new SubjectMatchBuilderImpl().create(typeMatch));
					}

					subjectsType.getSubjects().add(subjectType);
				}
				targetType.setSubjects(subjectsType);
			}

			if (aInternalObject.getResources() != null && aInternalObject.getResources().getResources() != null) {
				var resourcesType = new ResourcesType();

				for (var type : aInternalObject.getResources().getResources()) {
					var resourceType = new ResourceType();

					for (var typeMatch : type.getResourceMatches()) {
						resourceType.getResourceMatches().add(new ResourceMatchBuilderImpl().create(typeMatch));
					}

					resourcesType.getResources().add(resourceType);
				}
				targetType.setResources(resourcesType);
			}

			return targetType;
	}

	@Override
	public TargetType create() {
		return new TargetType();
	}

	public org.opensaml.xacml.policy.TargetType create(TargetType aInternalObject) {
		var targetType = new TargetTypeImplBuilder().buildObject();

		if (aInternalObject.getActions() != null && aInternalObject.getActions().getActions() != null) {
			var actionsType = new ActionsTypeImplBuilder().buildObject();
			for (var type : aInternalObject.getActions().getActions()) {
				var actionType = new ActionTypeImplBuilder().buildObject();

				for (var typeMatch : type.getActionMatches()) {
					actionType.getActionMatches().add(new ActionMatchBuilderImpl().create(typeMatch));
				}

				actionsType.getActions().add(actionType);
			}

			targetType.setActions(actionsType);
		}

		if (aInternalObject.getSubjects() != null && aInternalObject.getSubjects().getSubjects() != null) {
			var subjectsType = new SubjectsTypeImplBuilder().buildObject();

			for (var type : aInternalObject.getSubjects().getSubjects()) {
				var subjectType = new SubjectTypeImplBuilder().buildObject();

				for (var typeMatch : type.getSubjectMatches()) {
					subjectType.getSubjectMatches().add(new SubjectMatchBuilderImpl().create(typeMatch));
				}

				subjectsType.getSubjects().add(subjectType);
			}
			targetType.setSubjects(subjectsType);
		}

		if (aInternalObject.getResources() != null && aInternalObject.getResources().getResources() != null) {
			var resourcesType = new ResourcesTypeImplBuilder().buildObject();

			for (var type : aInternalObject.getResources().getResources()) {
				var resourceType = new ResourceTypeImplBuilder().buildObject();

				for (var typeMatch : type.getResourceMatches()) {
					resourceType.getResourceMatches().add(new ResourceMatchBuilderImpl().create(typeMatch));
				}

				resourcesType.getResources().add(resourceType);
			}
			targetType.setResources(resourcesType);
		}

		return targetType;
	}

}