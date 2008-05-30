package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class naamMain2 extends naamMain {
  public naamMain2(constructorOperator constructorOperator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<constructorOperator>("constructorOperator", constructorOperator)
    }, firstToken, lastToken);
  }
  public naamMain2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new naamMain2(cloneProperties(),firstToken,lastToken);
  }
  public constructorOperator getConstructorOperator() {
    return ((PropertyOne<constructorOperator>)getProperty("constructorOperator")).getValue();
  }
}
