package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class literal5 extends literal {
  public literal5(ASTStringNode regular_string_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("regular_string_literal", regular_string_literal)
    }, firstToken, lastToken);
  }
  public literal5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new literal5(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getRegular_string_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("regular_string_literal")).getValue();
  }
}
