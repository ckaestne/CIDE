package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class literal6 extends literal {
  public literal6(ASTStringNode verbatim_string_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("verbatim_string_literal", verbatim_string_literal)
    }, firstToken, lastToken);
  }
  public literal6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new literal6(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getVerbatim_string_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("verbatim_string_literal")).getValue();
  }
}
