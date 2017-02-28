package ee.adit.dvk.api.container.v2_1;

import java.util.Date;

import org.exolab.castor.mapping.FieldHandler;
import org.exolab.castor.mapping.ValidityException;

/**
 * @author Hendrik Pärna
 * @since 29.01.14
 */
public class AccessRestrictionBeginDateHandler implements FieldHandler {

    private DvkDateHandler dateHandler = new DvkDateHandler("yyyy-MM-dd");

    public AccessRestrictionBeginDateHandler() {
        super();
    }

    @Override
    public Object getValue(Object parent) throws IllegalStateException {
        AccessRestriction accessRestriction = (AccessRestriction) parent;
        Date date = accessRestriction.getRestrictionBeginDate();
        if (date != null) {
           return dateHandler.formatDateTime(date);
        }
        return null;
    }

    @Override
    public void setValue(Object parent, Object value) throws IllegalStateException, IllegalArgumentException {
        AccessRestriction accessRestriction = (AccessRestriction) parent;
        String date = (String) value;
        if (date != null) {
            accessRestriction.setRestrictionBeginDate(dateHandler.parseDateTime(date));
        }
    }

    @Override
    public void resetValue(Object parent) throws IllegalStateException, IllegalArgumentException {
        AccessRestriction accessRestriction = (AccessRestriction) parent;
        accessRestriction.setRestrictionEndDate(null);
    }

    @Override
    public void checkValidity(Object o) throws ValidityException, IllegalStateException {
    }

    @Override
    public Object newInstance(Object o) throws IllegalStateException {
        return null;
    }
}
