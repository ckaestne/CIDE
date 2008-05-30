package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class operator2 extends operator {
  public operator2(constructorOperator constructorOperator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<constructorOperator>("constructorOperator", constructorOperator)
    }, firstToken, lastToken);
  }
  public operator2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new operator2(cloneProperties(),firstToken,lastToken);
  }
  public constructorOperator getConstructorOperator() {
    return ((PropertyOne<constructorOperator>)getProperty("constructorOperator")).getValue();
  }
}
