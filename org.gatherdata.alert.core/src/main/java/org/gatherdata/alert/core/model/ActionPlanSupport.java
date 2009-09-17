package org.gatherdata.alert.core.model;

import java.net.URI;

import org.gatherdata.commons.model.UniqueEntitySupport;

public class ActionPlanSupport {

    public int hashCode(ActionPlan entity) {
        final URI uid = entity.getUid();
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        return result;
    }

    public boolean equals(ActionPlan lhs, ActionPlan rhs) {
        if (lhs == rhs)
            return true;
        if (rhs == null)
            return false;
        if (lhs.getUid() == null) {
            if (rhs.getUid() != null)
                return false;
        } else if (!lhs.getUid().equals(rhs.getUid()))
            return false;
        
        if (lhs.getEventType() == null) {
            if (rhs.getEventType() != null)
                return false;
        } else if (!lhs.getEventType().equals(rhs.getEventType()))
            return false;
        
        if (lhs.getName() == null) {
            if (rhs.getName() != null)
                return false;
        } else if (!lhs.getName().equals(rhs.getName()))
            return false;
        
        return true;
    }
}
