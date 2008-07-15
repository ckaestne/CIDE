package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PropertyName3 extends PropertyName {
  public PropertyName3(ASTStringNode decimal_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("decimal_literal", decimal_literal)
    }, firstToken, lastToken);
  }
  public PropertyName3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PropertyName3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDecimal_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("decimal_literal")).getValue();
  }
}
