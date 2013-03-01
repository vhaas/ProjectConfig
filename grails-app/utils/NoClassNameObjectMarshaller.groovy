import grails.converters.JSON;
import groovy.lang.GroovyObject;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.codehaus.groovy.grails.web.converters.Converter;
import org.codehaus.groovy.grails.web.converters.exceptions.ConverterException;
import org.codehaus.groovy.grails.web.converters.marshaller.ObjectMarshaller;
import org.codehaus.groovy.grails.web.json.JSONWriter;
import org.springframework.beans.BeanUtils;

public class NoClassNameObjectMarshaller  implements ObjectMarshaller {

	public boolean supports(Object object) {
		return object instanceof GroovyObject;
	}

	@Override
	public void marshalObject(Object o, JSON json)
		throws ConverterException {
		JSONWriter writer = json.getWriter();
		try {
			writer.object();
			PropertyDescriptor[] properties =
				BeanUtils.getPropertyDescriptors(o.getClass());
			for (PropertyDescriptor property : properties) {
				String name = property.getName();
				Method readMethod = property.getReadMethod();
				if (readMethod != null
				    && !name.equals("metaClass")
				    && !name.equals("class")) {
					Object value =
						readMethod.invoke(o, (Object[]) null);
					writer.key(name);
					json.convertAnother(value);
				}
			}
			Field[] fields = o.getClass().getDeclaredFields();
			for (Field field : fields) {
				int modifiers = field.getModifiers();
				if (Modifier.isPublic(modifiers)
					&& !(Modifier.isStatic(modifiers)
						|| Modifier.isTransient(modifiers))) {
					writer.key(field.getName());
					json.convertAnother(field.get(o));
				}
			}
			writer.endObject();
		} catch (ConverterException ce) {
			throw ce;
		} catch (Exception e) {
			throw new ConverterException(
				"Error converting Bean with class "
				+ o.getClass().getName(), e);
		}
	}

	@Override
	public void marshalObject(Object object, Converter converter)
			throws ConverterException {
		// TODO Auto-generated method stub
		
	}
}
