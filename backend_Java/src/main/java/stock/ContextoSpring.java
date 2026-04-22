package stock;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ContextoSpring {

    private static ApplicationContext contexto;

    public ContextoSpring(ApplicationContext contexto) {
        ContextoSpring.contexto = contexto;
    }

    public static ApplicationContext getContexto() {
        return contexto;
    }
}