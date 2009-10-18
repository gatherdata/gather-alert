package org.gatherdata.alert.core.model.impl;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.gatherdata.alert.core.model.LanguageScript;
import org.gatherdata.alert.core.model.mock.MockRuleSetFactory;
import org.junit.Test;

public class MutableRuleSetTest {

    @Test
    public void shouldBeEqualToCopy() {
        MutableRuleSet originalRuleSet = MockRuleSetFactory.createHeaderFilter("mock/text");
        MutableRuleSet copiedRuleSet = new MutableRuleSet();
        copiedRuleSet.copy(originalRuleSet);
        assertTrue(copiedRuleSet.equals(originalRuleSet));
        assertTrue(copiedRuleSet.hashCode() == originalRuleSet.hashCode());
        assertTrue(RuleSetSupport.deepEquals(originalRuleSet, copiedRuleSet));
    }
    
    @Test
    public void shouldUpdateContextFromPartial() {
        final String UPDATED_CONTEXT = "updated context";
        
        MutableRuleSet originalRuleSet = MockRuleSetFactory.createHeaderFilter("mock/text");
        MutableRuleSet partialRuleSet = new MutableRuleSet();
        partialRuleSet.setContext(UPDATED_CONTEXT);
        
        originalRuleSet.update(partialRuleSet);
        
        assertThat(originalRuleSet.getContext(), is(UPDATED_CONTEXT));
    }

    @Test
    public void shouldUpdateToIncludePredicateFromPartial() {
        final MutableLanguageScript EXPECTED_PREDICATE = new MutableLanguageScript("mock", "update to include me");
        EXPECTED_PREDICATE.selfIdentify();
        MutableRuleSet originalRuleSet = MockRuleSetFactory.createHeaderFilter("mock/text");
        MutableRuleSet partialRuleSet = new MutableRuleSet();
        partialRuleSet.add(EXPECTED_PREDICATE);
        
        originalRuleSet.update(partialRuleSet);
        
        boolean foundExpectedPredicate = false;
        for (LanguageScript predicate : originalRuleSet.getPredicates()) {
            if (predicate.equals(EXPECTED_PREDICATE)) {
                foundExpectedPredicate = true;
                break;
            }
        }
        assertTrue (foundExpectedPredicate);
    }
    

    @Test
    public void shouldUpdateToModifiedPredicateFromPartial() {
        final String EXPECTED_PREDICATE_LANGUAGE = "has been updated";
        
        MutableRuleSet originalRuleSet = MockRuleSetFactory.createHeaderFilter("mock/text");
        LanguageScript originalPredicate = originalRuleSet.getPredicates().iterator().next();
        MutableRuleSet partialRuleSet = new MutableRuleSet();
        MutableLanguageScript partialPredicate = new MutableLanguageScript();
        partialPredicate.setUid(originalPredicate.getUid());
        partialPredicate.setLanguage(EXPECTED_PREDICATE_LANGUAGE);
        partialRuleSet.add(partialPredicate);
        
        originalRuleSet.update(partialRuleSet);

        LanguageScript updatedPredicate = originalRuleSet.getPredicates().iterator().next();
        assertThat(updatedPredicate.getLanguage(), is (EXPECTED_PREDICATE_LANGUAGE));
    }
}
