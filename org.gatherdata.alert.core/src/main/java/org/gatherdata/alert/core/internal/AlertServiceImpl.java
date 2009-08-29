package org.gatherdata.alert.core.internal;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.activation.MimeType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gatherdata.alert.core.model.Rule;
import org.gatherdata.alert.core.spi.AlertService;
import org.gatherdata.alert.core.spi.RuleDao;

/**
 * Concrete implementation of the AlertService.
 * 
 */
public class AlertServiceImpl implements AlertService {
	Log log = LogFactory.getLog(AlertService.class);
	
	private List<RuleDao> ruleDaos;
	
	public void setRuleDaos(List<RuleDao> ruleDaos) {
		this.ruleDaos = ruleDaos;
	}

	public Collection<Rule> getRulesFor(MimeType type, String qualifier) {
		Collection<Rule> applicableRules = new HashSet<Rule>();
		
		if (ruleDaos != null) {
			for (RuleDao ruleDao : ruleDaos) {
				applicableRules.addAll(ruleDao.getRulesFor(type, qualifier));
			}
		} else {
			log.warn("AlertService has no RuleDao implementations to use. Please check the system configuration.");
		}
	    return null;
    }


}
