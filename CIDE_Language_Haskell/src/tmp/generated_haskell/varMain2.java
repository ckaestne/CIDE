package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class varMain2 extends varMain {
  public varMain2(varOperator varOperator, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<varOperator>("varOperator", varOperator)
    }, firstToken, lastToken);
  }
  public varMain2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new varMain2(cloneProperties(),firstToken,lastToken);
  }
  public varOperator getVarOperator() {
    return ((PropertyOne<varOperator>)getProperty("varOperator")).getValue();
  }
}
