package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class production1 extends production {
  public production1(javacode_production javacode_production, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<javacode_production>("javacode_production", javacode_production)
    }, firstToken, lastToken);
  }
  public production1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new production1(cloneProperties(),firstToken,lastToken);
  }
  public javacode_production getJavacode_production() {
    return ((PropertyOne<javacode_production>)getProperty("javacode_production")).getValue();
  }
}
