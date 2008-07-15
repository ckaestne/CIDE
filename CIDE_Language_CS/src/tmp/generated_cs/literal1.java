package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class literal1 extends literal {
  public literal1(boolean_literal boolean_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<boolean_literal>("boolean_literal", boolean_literal)
    }, firstToken, lastToken);
  }
  public literal1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new literal1(cloneProperties(),firstToken,lastToken);
  }
  public boolean_literal getBoolean_literal() {
    return ((PropertyOne<boolean_literal>)getProperty("boolean_literal")).getValue();
  }
}
