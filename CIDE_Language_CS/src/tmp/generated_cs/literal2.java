package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class literal2 extends literal {
  public literal2(ASTStringNode numeric_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("numeric_literal", numeric_literal)
    }, firstToken, lastToken);
  }
  public literal2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new literal2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getNumeric_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("numeric_literal")).getValue();
  }
}
