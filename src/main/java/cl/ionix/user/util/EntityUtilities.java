package cl.ionix.user.util;

import cl.ionix.user.util.exception.CopyObjectRuntimeException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityUtilities {
	private static ObjectMapper objectMapper;

	private static final Logger log = LoggerFactory.getLogger(EntityUtilities.class);

	private EntityUtilities() {
	}

	static {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static <T> T copyObjectFrom(Object source, Class<T> tarjetClass) {
		log.trace("parsing [{}] to [{}]", source.getClass(), tarjetClass);
		try {
			return objectMapper.convertValue(source, tarjetClass);
		} catch (Exception e) {
			throw new CopyObjectRuntimeException("No fue posible convertir el objeto a la clase [" + tarjetClass + "]", e);
		}
	}
}
