package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.request.InternalRefDataRequestList;
import com.brihaspathee.zeus.web.response.InternalRefDataResponseList;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, February 2022
 * Time: 10:05 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface ReferenceDataService {
    InternalRefDataResponseList lookupReferenceData(InternalRefDataRequestList internalRefDataRequestList);
}
