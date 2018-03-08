package po.po.config;

import po.po.dao.record.restservices.RecordProcessingRestServiceImpl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("appwebservice")
public class AppWebService extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        addRecordRecourceRestClasses(resources);
        return resources;
    }

    private boolean addRecordRecourceRestClasses(Set<Class<?>> resources) {
        return resources.add(RecordProcessingRestServiceImpl.class);
    }

}
