package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class operator1 extends operator {
  public operator1(varOperator varOperator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<varOperator>("varOperator", varOperator)
    }, firstToken, lastToken);
  }
  public operator1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new operator1(cloneProperties(),firstToken,lastToken);
  }
  public varOperator getVarOperator() {
    return ((PropertyOne<varOperator>)getProperty("varOperator")).getValue();
  }
}
