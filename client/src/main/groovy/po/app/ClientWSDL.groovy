// from -> wsimport -d . -keep -verbose http://localhost:8080/galaxy/RecordProcessingWebService?wsdl

package po.app

import po.po.dao.record.webservices.RecordProcessingWebService
import po.po.dao.record.webservices.RecordProcessingWebServiceImplService

/**
 * MAIN CLASS SECTION
 */
RecordProcessingWebService service = new RecordProcessingWebServiceImplService().getRecordProcessingWebServicePort()

service.getRecords().each { println it.depart + " : " + it.descrition }