package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Modifier12 extends Modifier {
  public Modifier12(Annotation annotation, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Annotation>("annotation", annotation)
    }, firstToken, lastToken);
  }
  public Modifier12(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Modifier12(cloneProperties(),firstToken,lastToken);
  }
  public Annotation getAnnotation() {
    return ((PropertyOne<Annotation>)getProperty("annotation")).getValue();
  }
}
