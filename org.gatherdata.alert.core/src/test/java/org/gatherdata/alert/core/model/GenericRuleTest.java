package org.gatherdata.alert.core.model;

import static org.junit.Assert.*;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.verify;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.FalsePredicate;
import org.apache.commons.collections.functors.TruePredicate;
import org.junit.Test;

public class GenericRuleTest {

	@Test
	public void shouldRunActionWhenPredicateIsTrue()
	{
		AlertPredicate mockPredicate = createMock(AlertPredicate.class);
		Reaction mockAction = createMock(Reaction.class);
		String mockSubject = "subject does not matter for this test";
		
		expect(mockPredicate.evaluate(mockSubject)).andReturn(true);
		
		mockAction.reactTo(mockSubject, mockPredicate);
		
		replay(mockAction);
		replay(mockPredicate);
		
		GenericRule testRule = new GenericRule(mockPredicate, mockAction);
		testRule.applyTo(mockSubject);
		
		verify(mockAction);
	}
	
	@Test
	public void shouldNotRunActionWhenPredicateIsFalse()
	{
		AlertPredicate mockPredicate = createMock(AlertPredicate.class);
		Reaction mockAction = createMock(Reaction.class);
		String mockSubject = "subject does not matter for this test";
		
		expect(mockPredicate.evaluate(mockSubject)).andReturn(false);
		
		replay(mockAction);
		
		GenericRule testRule = new GenericRule(mockPredicate, mockAction);
		testRule.applyTo(mockSubject);
		
		verify(mockAction);
	}
}
