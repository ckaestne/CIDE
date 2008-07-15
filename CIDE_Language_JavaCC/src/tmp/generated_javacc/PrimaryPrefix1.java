package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryPrefix1 extends PrimaryPrefix {
  public PrimaryPrefix1(Literal literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Literal>("literal", literal)
    }, firstToken, lastToken);
  }
  public PrimaryPrefix1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryPrefix1(cloneProperties(),firstToken,lastToken);
  }
  public Literal getLiteral() {
    return ((PropertyOne<Literal>)getProperty("literal")).getValue();
  }
}
