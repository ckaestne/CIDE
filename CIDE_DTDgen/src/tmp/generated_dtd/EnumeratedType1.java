package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EnumeratedType1 extends EnumeratedType {
  public EnumeratedType1(NotationType notationType, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<NotationType>("notationType", notationType)
    }, firstToken, lastToken);
  }
  public EnumeratedType1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new EnumeratedType1(cloneProperties(),firstToken,lastToken);
  }
  public NotationType getNotationType() {
    return ((PropertyOne<NotationType>)getProperty("notationType")).getValue();
  }
}
